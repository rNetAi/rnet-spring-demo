package io.github.rnetai.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ErrorDetail error;
    private String timestamp;

    private ApiResponse(boolean success, T data, ErrorDetail error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.timestamp = Instant.now().toString();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return new ApiResponse<>(false, null, new ErrorDetail(message, code));
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public ErrorDetail getError() { return error; }
    public void setError(ErrorDetail error) { this.error = error; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public static class ErrorDetail {
        private String message;
        private int code;

        public ErrorDetail() {}
        public ErrorDetail(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
    }
}
