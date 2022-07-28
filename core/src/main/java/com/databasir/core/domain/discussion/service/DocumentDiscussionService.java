package com.databasir.core.domain.discussion.service;

import com.databasir.common.exception.Forbidden;
import com.databasir.core.domain.discussion.converter.DiscussionResponseConverter;
import com.databasir.core.domain.discussion.data.DiscussionCreateRequest;
import com.databasir.core.domain.discussion.data.DiscussionListCondition;
import com.databasir.core.domain.discussion.data.DiscussionResponse;
import com.databasir.core.domain.discussion.event.DiscussionCreated;
import com.databasir.core.domain.discussion.event.converter.DiscussionEventConverter;
import com.databasir.core.infrastructure.event.EventPublisher;
import com.databasir.dao.impl.DocumentDiscussionDao;
import com.databasir.dao.impl.ProjectDao;
import com.databasir.dao.impl.UserDao;
import com.databasir.dao.tables.pojos.DocumentDiscussion;
import com.databasir.dao.tables.pojos.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentDiscussionService {

    private final DocumentDiscussionDao documentDiscussionDao;

    private final ProjectDao projectDao;

    private final UserDao userDao;

    private final DiscussionResponseConverter discussionResponseConverter;

    private final DiscussionEventConverter discussionEventConverter;

    private final EventPublisher eventPublisher;

    @Transactional
    public void deleteById(Integer groupId,
                           Integer projectId,
                           Integer discussionId) {
        if (projectDao.exists(groupId, projectId)) {
            documentDiscussionDao.deleteById(discussionId);
        } else {
            throw new Forbidden();
        }
    }

    public Page<DiscussionResponse> list(Integer groupId,
                                         Integer projectId,
                                         Pageable pageable,
                                         DiscussionListCondition condition) {
        if (projectDao.exists(groupId, projectId)) {
            Page<DocumentDiscussion> data =
                    documentDiscussionDao.selectByPage(pageable, condition.toCondition(projectId));
            Set<Integer> userIdList = data.getContent()
                    .stream()
                    .map(DocumentDiscussion::getUserId)
                    .collect(Collectors.toSet());
            Map<Integer, User> userMapById = userDao.selectUserIdIn(userIdList)
                    .stream()
                    .collect(Collectors.toMap(User::getId, Function.identity()));
            return data
                    .map(discussionPojo -> {
                        User user = userMapById.get(discussionPojo.getUserId());
                        return discussionResponseConverter.of(discussionPojo, user);
                    });
        } else {
            throw new Forbidden();
        }
    }

    @Transactional
    public void create(Integer groupId, Integer projectId, Integer userId, DiscussionCreateRequest request) {
        if (projectDao.exists(groupId, projectId)) {
            DocumentDiscussion pojo = new DocumentDiscussion();
            pojo.setUserId(userId);
            pojo.setProjectId(projectId);
            pojo.setContent(request.getContent());
            pojo.setTableName(request.getTableName());
            pojo.setColumnName(request.getColumnName());
            Integer discussionId = documentDiscussionDao.insertAndReturnId(pojo);
            DiscussionCreated event = discussionEventConverter.of(pojo, discussionId);
            eventPublisher.publish(event);
        } else {
            throw new Forbidden();
        }
    }
}
