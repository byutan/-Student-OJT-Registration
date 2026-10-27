
package com.example.demo.exception;

/**
 * Ngoại lệ được sử dụng khi dữ liệu đăng ký
 * bị trùng lặp, chẳng hạn email hoặc national ID.
 */
public class DuplicateCheckError extends RuntimeException {

    /**
     * Khởi tạo ngoại lệ với thông báo lỗi.
     *
     * @param message Nội dung thông báo lỗi.
     */
    public DuplicateCheckError(String message) {
        super(message);
    }
}
