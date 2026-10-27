
package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * DTO tiếp nhận thông tin đăng ký sinh viên OJT từ Frontend.
 * Sử dụng Jakarta Validation để kiểm tra dữ liệu đầu vào.
 */
public class StudentRequestDTO {

    // Họ và tên sinh viên, không được để trống.
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    // Email phải đúng định dạng.
    @Email(message = "Email không đúng định dạng")
    private String email;

    // Số điện thoại Việt Nam gồm 10 chữ số hợp lệ.
    @Pattern(
            regexp = "^0[35789]\\d{8}$",
            message = "Số điện thoại phải đủ 10 chữ số hợp lệ tại Việt Nam"
    )
    private String phone;

    // Số CCCD gồm chính xác 12 chữ số.
    @Pattern(
            regexp = "^\\d{12}$",
            message = "Số CCCD phải đúng 12 chữ số"
    )
    private String nationalId;

    // Trường đại học sinh viên đang theo học.
    private String university;

    // Chuyên ngành đào tạo.
    private String major;

    /**
     * Constructor không tham số dành cho quá trình
     * deserialize JSON của Spring/Jackson.
     */
    public StudentRequestDTO() {
    }

    /**
     * Constructor đầy đủ tham số.
     */
    public StudentRequestDTO(
            String fullName,
            String email,
            String phone,
            String nationalId,
            String university,
            String major) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.nationalId = nationalId;
        this.university = university;
        this.major = major;
    }

    // Getters và Setters.

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
