
package com.example.demo.exception;

/**
 * Ngoại lệ được sử dụng khi không tìm thấy dữ liệu
 * theo ID hoặc điều kiện tìm kiếm.
 */
public class NotFoundError extends RuntimeException {

    /**
     * Khởi tạo ngoại lệ với thông báo lỗi.
     *
     * @param message Nội dung thông báo lỗi.
     */
    public NotFoundError(String message) {
        super(message);
    }
}
