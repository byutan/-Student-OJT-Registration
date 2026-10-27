
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Xử lý tập trung các ngoại lệ phát sinh trong REST API.
 *
 * Đảm bảo response có cấu trúc JSON thống nhất
 * và không để lộ thông tin nội bộ của hệ thống.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Xử lý lỗi validation từ @Valid.
     *
     * Ví dụ: fullName bị bỏ trống hoặc email sai định dạng.
     *
     * Response:
     * {
     *   "status": 400,
     *   "message": "Validation failed",
     *   "errors": [...]
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(
            MethodArgumentNotValidException exception) {

        // Thu thập danh sách lỗi từ các trường dữ liệu.
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Dữ liệu đầu vào không hợp lệ");
        response.put("errors", errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Xử lý lỗi dữ liệu bị trùng lặp.
     *
     * Response:
     * {
     *   "timestamp": "...",
     *   "status": 409,
     *   "message": "..."
     * }
     */
    @ExceptionHandler(DuplicateCheckError.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateError(
            DuplicateCheckError exception) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    /**
     * Xử lý lỗi không tìm thấy dữ liệu.
     *
     * Response:
     * {
     *   "timestamp": "...",
     *   "status": 404,
     *   "message": "..."
     * }
     */
    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundError(
            NotFoundError exception) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex
    ) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Không tìm thấy hồ sơ sinh viên với ID cung cấp");

        return ResponseEntity.badRequest().body(body);
    }

}
