package com.zhanglubin.shoppingmallbackend.domain;

/**
 * @author zhanglubin
 * @date 2021/2/23
 */
public class AxiosResult {
    private String isError;
    private String resultMsg;
    private String errorMsg;

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public AxiosResult(String isError, String resultMsg, String errorMsg) {
        this.isError = isError;
        this.resultMsg = resultMsg;
        this.errorMsg = errorMsg;
    }

    public AxiosResult() {
    }
}
