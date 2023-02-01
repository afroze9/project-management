package com.afroze.projectmanagement.core.ui.model;

public class HttpResponseModel<T> {
    private T data;
    private boolean isError;
    private String errorMessage;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    protected HttpResponseModel(T data, boolean isError, String errorMessage) {
        this.data = data;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public static <T> HttpResponseModel<T> Success(T data) {
        return new HttpResponseModel<>(data, false, "");
    }

    public static <T> HttpResponseModel<T> Failure(T data, String errorMessage) {
        return new HttpResponseModel<>(data, true, errorMessage);
    }
}
