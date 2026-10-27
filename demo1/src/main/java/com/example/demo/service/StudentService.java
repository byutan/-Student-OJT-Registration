package com.example.demo.service;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;

import java.util.UUID;

/**
 * Khai báo các nghiệp vụ liên quan đến đăng ký sinh viên OJT.
 */
public interface StudentService {

    /**
     * Tiếp nhận thông tin và đăng ký sinh viên OJT.
     */
    StudentResponseDTO registerStudent(StudentRequestDTO request);

    /**
     * Truy vấn thông tin sinh viên theo ID.
     */
    StudentResponseDTO getStudentById(UUID id);
}