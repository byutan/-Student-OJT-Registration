
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

/**
 * DTO trả về thông tin sinh viên cho Frontend.
 *
 * Các thông tin nhạy cảm như số điện thoại và CCCD
 * chỉ được trả về dưới dạng đã che một phần (masking).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponseDTO {

    // UUID định danh sinh viên.
    private UUID id;

    // Họ và tên sinh viên.
    private String fullName;

    // Email sinh viên.
    private String email;

    // Số điện thoại đã che một phần.
    // Ví dụ: 0901234567 -> 090*****67.
    private String maskedPhone;

    // Số CCCD đã che một phần.
    // Ví dụ: 079123456789 -> 079******789.
    private String maskedNationalId;

    // Trường đại học.
    private String university;

    // Chuyên ngành.
    private String major;

    // Trạng thái đăng ký OJT.
    private String status;

    /**
     * Constructor private, chỉ Builder được phép khởi tạo.
     */
    private StudentResponseDTO(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.maskedPhone = builder.maskedPhone;
        this.maskedNationalId = builder.maskedNationalId;
        this.university = builder.university;
        this.major = builder.major;
        this.status = builder.status;
    }

    /**
     * Tạo đối tượng Builder.
     *
     * @return Builder để khởi tạo StudentResponseDTO.
     */
    public static Builder builder() {
        return new Builder();
    }

    // Getters.

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMaskedPhone() {
        return maskedPhone;
    }

    public String getMaskedNationalId() {
        return maskedNationalId;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Builder Pattern giúp khởi tạo DTO linh hoạt
     * mà không cần sử dụng constructor nhiều tham số.
     */
    public static class Builder {

        private UUID id;
        private String fullName;
        private String email;
        private String maskedPhone;
        private String maskedNationalId;
        private String university;
        private String major;
        private String status = "PENDING_APPROVAL";

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder maskedPhone(String maskedPhone) {
            this.maskedPhone = maskedPhone;
            return this;
        }

        public Builder maskedNationalId(String maskedNationalId) {
            this.maskedNationalId = maskedNationalId;
            return this;
        }

        public Builder university(String university) {
            this.university = university;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public StudentResponseDTO build() {
            return new StudentResponseDTO(this);
        }
    }
}
