package com.example.demo.service.impl;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.exception.DuplicateCheckError;
import com.example.demo.exception.NotFoundError;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Triển khai nghiệp vụ đăng ký và truy vấn sinh viên OJT.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    /**
     * Khởi tạo Repository bằng Constructor Injection.
     */
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Đăng ký sinh viên OJT mới.
     *
     * @param request thông tin đăng ký từ Frontend
     * @return thông tin sinh viên sau khi đăng ký
     */
    @Override
    @Transactional
    public StudentResponseDTO registerStudent(StudentRequestDTO request) {

        // Kiểm tra Email đã tồn tại.
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateCheckError("Số CCCD hoặc Email đã tồn tại trên hệ thống OJT");
        }

        // Kiểm tra CCCD đã tồn tại.
        if (studentRepository.existsByNationalId(request.getNationalId())) {
            throw new DuplicateCheckError("Số CCCD hoặc Email đã tồn tại trên hệ thống OJT");
        }

        // Chuyển Request DTO thành Entity để lưu Database.
        StudentEntity student = new StudentEntity();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setNationalId(request.getNationalId());
        student.setUniversity(request.getUniversity());
        student.setMajor(request.getMajor());

        // Lưu sinh viên vào Database.
        StudentEntity savedStudent = studentRepository.save(student);

        // POST không trả về major và status.
        return mapToResponseDTO(savedStudent, false);
    }

    /**
     * Truy vấn thông tin sinh viên theo UUID.
     *
     * @param id UUID của sinh viên
     * @return thông tin chi tiết sinh viên
     */
    @Override
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(UUID id) {

        // Tìm sinh viên, trả lỗi 404 nếu không tồn tại.
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundError(
                                "Không tìm thấy hồ sơ sinh viên với ID cung cấp"
                        )
                );

        // GET trả về đầy đủ thông tin.
        return mapToResponseDTO(student, true);
    }

    /**
     * Chuyển Entity thành Response DTO và che dữ liệu nhạy cảm.
     *
     * @param student Entity sinh viên
     * @param includeDetails có trả về major và status hay không
     */
    private StudentResponseDTO mapToResponseDTO(
            StudentEntity student,
            boolean includeDetails
    ) {

        StudentResponseDTO.Builder builder = StudentResponseDTO.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .email(student.getEmail())
                .maskedPhone(maskPhone(student.getPhone()))
                .maskedNationalId(maskNationalId(student.getNationalId()))
                .university(student.getUniversity());

        // Chỉ API GET mới trả về major và status.
        if (includeDetails) {
            builder.major(student.getMajor())
                    .status(student.getStatus());
        }

        return builder.build();
    }

    /**
     * Che số điện thoại, giữ lại 3 số đầu và 2 số cuối.
     * Ví dụ: 0901234567 -> 090*****67
     */
    private String maskPhone(String phone) {

        if (phone == null || !phone.matches("^0[35789]\\d{8}$")) {
            return null;
        }

        return phone.substring(0, 3)
                + "*****"
                + phone.substring(8);
    }

    /**
     * Che CCCD, giữ lại 3 số đầu và 3 số cuối.
     * Ví dụ: 079123456789 -> 079******789
     */
    private String maskNationalId(String nationalId) {

        if (nationalId == null || !nationalId.matches("^\\d{12}$")) {
            return null;
        }

        return nationalId.substring(0, 3)
                + "******"
                + nationalId.substring(9);
    }
}