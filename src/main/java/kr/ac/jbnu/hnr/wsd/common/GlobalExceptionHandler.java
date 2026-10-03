package kr.ac.jbnu.hnr.wsd.common;

import kr.ac.jbnu.hnr.wsd.post.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Map<String, String>> handlePostNotFound(
            PostNotFoundException e
    ) {
        return ApiResponse.error(
                Map.of("message", e.getMessage())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Map<String, String>> handleBadRequest(
            IllegalArgumentException e
    ) {
        return ApiResponse.error(
                Map.of("message", e.getMessage())
        );
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiResponse<Map<String, String>> handleServiceUnavailable(
            ServiceUnavailableException e
    ) {
        return ApiResponse.error(
                Map.of("message", e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Map<String, String>> handleServerError(
            Exception e
    ) {
        return ApiResponse.error(
                Map.of("message", "서버 처리 중 오류가 발생했습니다.")
        );
    }
}