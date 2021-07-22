package com.honeycomb.storage.exception;

public class BaseException extends Exception {
  private int errCode;

  private String errMsg;

  public BaseException() {
  }

  public BaseException(int errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public BaseException(String message, int errCode, String errMsg) {
    super(message);
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public BaseException(String message, Throwable cause, int errCode, String errMsg) {
    super(message, cause);
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public BaseException(Throwable cause, int errCode, String errMsg) {
    super(cause);
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public int getErrCode() {
    return this.errCode;
  }

  public void setErrCode(int errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }
}
