package com.niuniu.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你要找的问题不在啦！要不要自己提问一下？"),
    TARGET_PARAM_NOT_FOUND(2002 ,"未选中任何问题进行回复"),
    NO_LOGIN(2003 ,"当前用户未登录，请登录后重试"),
    SYS_ERROR(2004,"服务冒烟啦，稍后再试试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复评论不存在啦！"),
    CONTENT_IS_EMPTY(2007,"评论不可以为空哦！");


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
