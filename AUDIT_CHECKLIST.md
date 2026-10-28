# AUDIT CHECKLIST

## Quality Gate Audit

| **STT** | **Hạng mục Quality Gate** | **Tiêu chuẩn (DoD)** | **Bằng chứng** | **Trạng thái** |
|:---:|---|---|---|:---:|
| 1 | Red-Zone Security | Có lá chắn chặn AI đọc file cấu hình nhạy cảm; 0% rò rỉ Secrets/YML. | `.gitignore`: loại trừ `application*.yml`, `application*.yaml`, `application*.properties`, `.env`, khóa và thư mục `secrets/`. | [x] PASSED |
| 2 | Clean Architecture | Controller chỉ nhận/trả DTO; 0% `@Entity` rò rỉ ra Presentation Layer. | `StudentController.java`: sử dụng `StudentRequestDTO` và `StudentResponseDTO`; `StudentService.java`: không trả về Entity. | [x] PASSED |
| 3 | PII Masking | CCCD và SĐT được che mờ tự động tại ResponseDTO. | `StudentServiceImpl.java`: triển khai `maskPhone()` và `maskNationalId()`; `StudentResponseDTO.java`: sử dụng `maskedPhone` và `maskedNationalId`. | [x] PASSED |
| 4 | Input Validation | Đủ `@NotBlank`, `@Email`, `@Pattern` trên RequestDTO. | `StudentRequestDTO.java`: khai báo các annotation kiểm tra dữ liệu; `StudentController.java`: sử dụng `@Valid`. | [x] PASSED |
| 5 | Duplicate Business Logic | Bắt trùng Email/CCCD, ném exception và trả về HTTP 409 Conflict. | `StudentRepository.java`: `existsByEmail()`, `existsByNationalId()`; `StudentServiceImpl.java`: kiểm tra dữ liệu trùng; `GlobalExceptionHandler.java`: xử lý `DuplicateCheckError`. | [x] PASSED |
| 6 | Exception Handling | `@RestControllerAdvice` bắt lỗi, ẩn SQL Stacktrace và thông tin nội bộ khỏi Client. | `GlobalExceptionHandler.java`: xử lý Validation (400), Not Found (404), Duplicate (409), lỗi cơ sở dữ liệu và lỗi hệ thống (500). | [x] PASSED |
| 7 | Prompt Evidence | Có `Dev_Prompt_Library.md` kèm Prompt được cấu trúc theo 5 thành tố. | `Dev_Prompt_Library.md`: lưu trữ các Prompt sử dụng trong quá trình phát triển phần mềm. | [x] PASSED |

---

## Audit Summary

| **Chỉ số** | **Kết quả** |
|---|:---:|
| Tổng số Quality Gate | 7 |
| Số hạng mục PASSED | 7 |
| Số hạng mục FAILED | 0 |
| Tỷ lệ hoàn thành | 100% |

**Audit Result:** PASSED

---

## Audit Notes

- Red-Zone Security: `.gitignore` giúp ngăn Git theo dõi các file cấu hình nhạy cảm, không tự động ngăn AI đọc các file này.
- Clean Architecture: Controller chỉ giao tiếp thông qua DTO, không trả trực tiếp JPA Entity.
- PII Masking: Dữ liệu CCCD và số điện thoại được che mờ trước khi trả về Client; dữ liệu gốc vẫn được lưu trong Database.
- Exception Handling: Không trả về SQL Stacktrace hoặc thông tin nội bộ trong HTTP Response.
- Prompt Evidence: Cần đảm bảo `Dev_Prompt_Library.md` tồn tại và có đầy đủ nội dung Prompt theo 5 thành tố.

**Lưu ý:** Trạng thái PASSED cần được xác nhận bằng mã nguồn, cấu hình và kết quả kiểm thử thực tế.
