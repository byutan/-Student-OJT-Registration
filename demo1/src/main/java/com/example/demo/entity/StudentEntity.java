
package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

/**
 * Entity đại diện cho thông tin đăng ký sinh viên OJT.
 *
 * Ánh xạ đối tượng StudentEntity với bảng students trong database.
 */
@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_students_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_students_national_id",
                        columnNames = "national_id"
                )
        }
)
public class StudentEntity {

    /**
     * Khóa chính của sinh viên.
     * UUID được Hibernate tự động sinh khi persist entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private UUID id;

    /**
     * Họ và tên sinh viên.
     */
    @NotBlank(message = "Full name must not be blank")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * Email sinh viên.
     * Email phải đúng định dạng và không được trùng lặp.
     */
    @Email(message = "Email must be valid")
    @Column(name = "email")
    private String email;

    /**
     * Số điện thoại sinh viên.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Số định danh cá nhân.
     * Không được trùng lặp giữa các sinh viên.
     */
    @Column(name = "national_id")
    private String nationalId;

    /**
     * Trường đại học sinh viên đang theo học.
     */
    @Column(name = "university")
    private String university;

    /**
     * Chuyên ngành đào tạo của sinh viên.
     */
    @Column(name = "major")
    private String major;

    /**
     * Trạng thái đăng ký mặc định.
     */
    @Column(name = "status", nullable = false)
    private String status = "PENDING_APPROVAL";

    /**
     * Constructor mặc định dành cho JPA.
     */
    public StudentEntity() {
    }

    // Getters và Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
