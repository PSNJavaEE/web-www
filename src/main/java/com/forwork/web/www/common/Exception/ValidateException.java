package com.forwork.web.www.common.Exception;

import java.util.HashMap;
import java.util.Map;

public class ValidateException extends ControllerException{
    private Map<String, String> fieldMsgs = new HashMap();

    public ValidateException(String msgText) {
        super(msgText);
    }

    public ValidateException(String field, String msgText) {
        super(msgText);
        this.fieldMsgs.put(field, msgText);
    }

    public ValidateException putMsg(String field, String msgText) {
        this.fieldMsgs.put(field, msgText);
        return this;
    }

    @Override
    public String getMsgText() {
        return super.getMsgText();
    }

    public Map<String, String> getAllMsgText() {
        return this.fieldMsgs;
    }
}
