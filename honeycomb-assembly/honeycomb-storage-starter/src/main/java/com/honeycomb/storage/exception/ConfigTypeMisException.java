package com.honeycomb.storage.exception;


public class ConfigTypeMisException extends BaseException {

    public ConfigTypeMisException() {
    }

    public ConfigTypeMisException(int errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public ConfigTypeMisException(String message, int errCode, String errMsg) {
        super(message, errCode, errMsg);
    }

    public ConfigTypeMisException(String message, Throwable cause, int errCode, String errMsg) {
        super(message, cause, errCode, errMsg);
    }

    public ConfigTypeMisException(Throwable cause, int errCode, String errMsg) {
        super(cause, errCode, errMsg);
    }

    public ConfigTypeMisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg) {
        super(message, cause, enableSuppression, writableStackTrace, errCode, errMsg);
    }

    public static class Code {
        public static final int OK = 0;

        public static final int ConfigTypeMismatchException = 70001;
    }
}
