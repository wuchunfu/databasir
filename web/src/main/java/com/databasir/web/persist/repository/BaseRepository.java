package com.databasir.web.persist.repository;


import com.databasir.web.persist.exception.DataNotExistsException;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class BaseRepository<T extends Record, R> {

    private final Table<T> table;

    private final Class<R> pojoType;

    public abstract DSLContext getDslContext();

    public boolean existsById(Integer id) {
        return getDslContext().fetchExists(table, identity().eq(id));
    }

    public Integer insertAndReturnId(R pojo) {
        T record = getDslContext().newRecord(table, pojo);
        UpdatableRecord<?> updatableRecord = (UpdatableRecord<?>) record;
        updatableRecord.store();
        Object value = updatableRecord.getValue(table.getIdentity().getField());
        return (Integer) value;
    }

    public int batchInsert(Collection<R> pojoList) {
        List<TableRecord<?>> records = pojoList.stream()
                .map(pojo -> {
                    T record = getDslContext().newRecord(table, pojo);
                    return (TableRecord<?>) record;
                })
                .collect(Collectors.toList());
        return Arrays.stream(getDslContext().batchInsert(records).execute()).sum();
    }

    public int deleteById(Integer id) {
        return getDslContext()
                .deleteFrom(table).where(identity().eq(id))
                .execute();
    }

    public int updateById(R pojo) {
        T record = getDslContext().newRecord(table, pojo);
        record.changed(table.getIdentity().getField(), false);
        return getDslContext().executeUpdate((UpdatableRecord<?>) record);
    }

    public Optional<R> selectOptionalById(Integer id) {
        return getDslContext()
                .select(table.fields()).from(table).where(identity().eq(id))
                .fetchOptionalInto(pojoType);
    }

    public R selectById(Integer id) {
        return getDslContext()
                .select(table.fields()).from(table).where(identity().eq(id))
                .fetchOptionalInto(pojoType)
                .orElseThrow(() -> new DataNotExistsException("data not exists in " + table.getName() + " with id = " + id));
    }

    public Page<R> selectByPage(Pageable request, Condition condition) {
        int total = getDslContext()
                .selectCount().from(table).where(condition)
                .fetchOne(0, int.class);
        List<R> data = getDslContext()
                .selectFrom(table).where(condition)
                .offset(request.getOffset()).limit(request.getPageSize())
                .fetchInto(pojoType);
        return new PageImpl<>(data, request, total);
    }

    protected Field<Integer> identity() {
        Identity<T, ?> identity = table.getIdentity();
        if (identity == null) {
            throw new IllegalStateException("can not find identity column in " + table.getName());
        }
        return identity.getField().cast(Integer.class);
    }

    protected Table<T> table() {
        return this.table;
    }
}
