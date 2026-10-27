package com.example.demo.controller;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST Controller quản lý API đăng ký và tra cứu sinh viên OJT.
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructor Injection để khởi tạo StudentService.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * API đăng ký sinh viên OJT.
     *
     * POST /api/v1/students/register
     *
     * @param request thông tin đăng ký sinh viên
     * @return thông tin sinh viên sau khi đăng ký
     */
    @PostMapping("/register")
    public ResponseEntity<StudentResponseDTO> registerStudent(
            @Valid @RequestBody StudentRequestDTO request
    ) {
        // Gọi Service xử lý nghiệp vụ đăng ký sinh viên.
        StudentResponseDTO response = studentService.registerStudent(request);

        // Trả HTTP 201 Created khi đăng ký thành công.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * API truy vấn thông tin sinh viên theo ID.
     *
     * GET /api/v1/students/{id}
     *
     * @param id UUID của sinh viên
     * @return thông tin sinh viên
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable UUID id
    ) {
        // Gọi Service truy vấn sinh viên theo ID.
        StudentResponseDTO response = studentService.getStudentById(id);

        // Trả HTTP 200 OK khi tìm thấy sinh viên.
        return ResponseEntity.ok(response);
    }
}