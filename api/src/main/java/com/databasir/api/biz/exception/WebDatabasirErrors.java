package com.databasir.api.biz.exception;

import com.databasir.common.DatabasirErrors;
import com.databasir.common.DatabasirException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WebDatabasirErrors implements DatabasirErrors {

    NOT_SUPPORT_DATABASE_TYPE("A_10000", "不支持的数据库类型, 请检查项目配置"),
    SCHEMA_SOURCE_NOT_FOUND("A_10001", "项目不存在"),
    DATABASE_META_NOT_FOUND("A_10002", "获取数据库信息失败"),
    CONNECT_DATABASE_FAILED("A_10003", "连接数据库失败，请检查连接配置"),
    ;

    private final String errCode;

    private final String errMessage;

    public DatabasirException exception() {
        return new DatabasirException(this);
    }

    public DatabasirException exception(Throwable origin) {
        return new DatabasirException(this, origin);
    }
}
