package com.forwork.web.www.common.Exception;

public class ControllerException extends AppException{
    public ControllerException(String msgText) {
        super(msgText);
    }

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(Exception e, Object msgObject) {
        super(e, msgObject);
    }
}
