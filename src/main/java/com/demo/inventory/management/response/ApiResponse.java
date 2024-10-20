package com.demo.inventory.management.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiResponse<T> {
    private boolean success;
    private String message;

    @JsonIgnoreProperties
    private HttpStatus status;

    private T data;

    public static <T> ApiResponse<T> empty() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> success(HttpStatus status, String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .status(status)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(false)
                .build();
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .status(status)
                .success(false)
                .build();
    }
}
