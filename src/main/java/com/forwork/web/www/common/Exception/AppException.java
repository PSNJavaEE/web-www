package com.forwork.web.www.common.Exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class AppException extends Exception{
    private static final long serialVersionUID = 1L;
    private Exception exception;
    private Object msgObject;

    public AppException() {
    }

    public AppException(String msgText) {
        super(msgText);
        this.msgObject = msgText;
    }

    public AppException(Exception e) {
        this.exception = e;
    }

    public AppException(Exception e, Object msgObject) {
        this.exception = e;
        this.msgObject = msgObject;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public String getMsgText() {
        return this.msgObject != null ? this.msgObject.toString() : "";
    }

    protected String getAdditionalMsgText() {
        return "[" + this.getName() + "]" + this.getMsgText();
    }

    public Exception getException() {
        return this.exception;
    }

    public Object getMsgObject() {
        return this.msgObject;
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (this.exception != null) {
            this.exception.printStackTrace();
        }

    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        if (this.exception != null) {
            this.exception.printStackTrace(s);
        }

    }

    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        if (this.exception != null) {
            this.exception.printStackTrace(s);
        }

    }

    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] s1 = super.getStackTrace();
        StackTraceElement[] s2 = (StackTraceElement[])null;
        if (this.exception != null) {
            s2 = this.exception.getStackTrace();
        }

        int size = s1.length + (s2 != null ? s2.length : 0);
        StackTraceElement[] result = new StackTraceElement[size];

        int i;
        for(i = 0; i < s1.length; ++i) {
            result[i] = s1[i];
        }

        if (s2 != null) {
            for(i = 0; i < s2.length; ++i) {
                result[s1.length + i] = s2[i];
            }
        }

        return result;
    }
}
