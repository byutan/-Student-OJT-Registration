
package com.example.demo.repository;

import com.example.demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository quản lý thao tác truy xuất dữ liệu sinh viên OJT.
 *
 * Kế thừa JpaRepository để sử dụng các thao tác CRUD
 * và truy vấn dữ liệu với Spring Data JPA.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    /**
     * Kiểm tra email đã tồn tại trong database hay chưa.
     *
     * @param email Email cần kiểm tra.
     * @return true nếu email đã tồn tại, ngược lại false.
     */
    boolean existsByEmail(String email);

    /**
     * Kiểm tra số định danh cá nhân đã tồn tại hay chưa.
     *
     * @param nationalId Số định danh cá nhân cần kiểm tra.
     * @return true nếu số định danh đã tồn tại, ngược lại false.
     */
    boolean existsByNationalId(String nationalId);

}
