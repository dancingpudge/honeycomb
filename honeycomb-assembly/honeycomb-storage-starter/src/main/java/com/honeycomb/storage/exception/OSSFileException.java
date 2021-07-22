package com.honeycomb.storage.exception;

public class OSSFileException extends BaseException {

    public OSSFileException() {
    }

    public OSSFileException(int errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public OSSFileException(String message, int errCode, String errMsg) {
        super(message, errCode, errMsg);
    }

    public OSSFileException(String message, Throwable cause, int errCode, String errMsg) {
        super(message, cause, errCode, errMsg);
    }

    public OSSFileException(Throwable cause, int errCode, String errMsg) {
        super(cause, errCode, errMsg);
    }

    public OSSFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg) {
        super(message, cause, enableSuppression, writableStackTrace, errCode, errMsg);
    }

    public static class Code {
        public static final int OK = 0;

        public static final int OSSSaveErrException = 60001;
    }
}