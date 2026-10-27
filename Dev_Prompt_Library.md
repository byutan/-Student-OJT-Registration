
## 1. CẤU HÌNH LÁ CHẮN VÙNG ĐỎ (.gitignore)
```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation, không sử dụng lombok. Package chính là com.example.demo.dto
[TASK] Viết tiếp file gitignore có nội dung như sau:
# Default ignored files
/shelf/
/workspace.xml
# Editor-based HTTP Client requests
/httpRequests/
# Ignored default folder with query files
/queries/
# Datasource local storage ignored files
/dataSources/
/dataSources.local.xml

[CONSTRAINT] thêm src/main/resources/application*.yml
src/main/resources/application*.properties
.env
*.pem
*.key
secrets vào trong file .gitignore
[OUTPUT] .gitignore đạt điều kiện
```


## 2. PROMPT 5 THÀNH TỐ (R-C-T-C-O) TẠO REST API & PII MASKING
```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation. Package chính là com.example.demo
[TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho file: StudentRepository  
[CONSTRAINT]Spring Data JPA với phương thức existsByEmail và existsByNationalId
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo 
```

```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation. Package chính là com.example.demo
[TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho file: StudentEntity (JPA Entity với ID UUID, status mặc định PENDING_APPROVAL) 
[CONSTRAINT]Các trường dữ liệu sau: id uuid, fullName, email, phone, nationalId, university, major, status có giá trị mặc định "PENDING_APPROVAL". Các trường id, email unique, nationalId unique, tất cả not null;
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo 
```

```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation. Package chính là com.example.demo.exception
TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho 3 file: NotFoundError, DuplicateCheckError, GlobalExceptionHandler
[CONSTRAINT] Cấu hình @RestControllerAdvice cho GlobalExceptionHandler xử lý 
lỗi validation (MethodArgumentNotValidException) và trả về JSON chuẩn dạng { "status": 400, "message": "...", "errors": [...] },
lỗi Duplicate (DuplicateCheckError) và trả về JSON chuẩn dạng {"timestamp": "...", "status": 409, "message": "..."},
lỗi NotFound (NotFoundError) và trả về JSON chuẩn dạng {"timestamp": "...", "status": 404, "message": "..."}, không để lộ SQL Stacktrace.
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo.exception
```

```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation, không sử dụng lombok. Package chính là com.example.demo.dto
[TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho 2 file: StudentResponseDTO và StudentRequestDTO
[CONSTRAINT]
Với StudentRequestDTO, ta có các trường dữ liệu sau: fullName, email, phone, nationalId, university, major. Validate dữ liệu đầu vào bắt buộc: fullName: Không được để trống (@NotBlank). email: Đúng định dạng Email (@Email). phone: Đúng định dạng SĐT Việt Nam 10 số (@Pattern(regexp ="^(0[3|5|7|8|9])+\\d{8}$")).nationalId: Số CCCD đúng 12 chữ số (@Pattern(regexp = "^\\d{12}$")).
Với StudentResponseDTO, ta có "id", "fullName","email",
"maskedPhone": "090*****67",
"maskedNationalId": "079******789",
"university",
"major",
"status": "PENDING_APPROVAL"
dữ liệu nhạy cảm phải được mã hóa tự động trước khi trả về Frontend: Sử dụng annotation builder.
"phone": "Số điện thoại phải đủ 10 chữ số hợp lệ tại Việt Nam",
"nationalId": "Số CCCD phải đúng 12 chữ số", các message error còn lại sử dụng tiếng Việt
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo.dto
```

```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API POST /api/v1/students/register: Tiếp nhận thông tin đăng ký OJT của sinh viên và GET /api/v1/students/{id}: Truy vấn thông tin chi tiết sinh viên theo ID của phân hệ Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation. Package chính là com.example.demo
TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho 2 file:StudentService và StudentServiceImpl 
[CONSTRAINT] StudentService là interface và StudentServiceImpl implementsStudentService. nhận DTO StudentRequestDTO và trả StudentResponseDTO, kèm PII Masking, CẤM lộ Entity JPA, BẮT BUỘC dùng Record DTO, Masking CCCD (079******789) và SĐT
(090*****67), Validate Jakarta, Duplicate Check 409 Conflict, Có logic kiểm tra trùng Email/CCCD, biết rằng DTO request sử dụng getter/setter và DTO response sử dụng pattern builder (không xài lombok). Trong DTO response trả về cho register sẽ không có status và major, chỉ riêng DTO response cho getStudentById là đầy đủ
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo.serivce/service.impl 
```


```
[ROLE] Bạn là Senior Java Architect với 10+ năm kinh nghiệm thiết kế REST API Spring Boot Enterprise và tuân thủ nghiêm ngặt Clean Architecture.
[CONTEXT] Tôi đang viết API POST /api/v1/students/register: Tiếp nhận thông tin đăng ký OJT của sinh viên và GET /api/v1/students/{id}: Truy vấn thông tin chi tiết sinh viên theo ID của phân hệ Đăng ký Sinh viên OJT trên Spring Boot 3.2, Java 21, Spring Data JPA, H2 Database và Jakarta Validation. Package chính là com.example.demo
[TASK] Khởi tạo toàn bộ bộ mã nguồn Java cho 1 file: StudentController.java
[CONSTRAINT] StudentController.java là 1 RestController gồm 2 hàm nhận request DTO và trả DTO dưới dạng ResponseEntity<...>, gồm API POST /api/v1/students/register và GET /api/v1/students/{id}. 
[OUTPUT] Mã nguồn Java sạch sẽ, đầy đủ annotation, có comment giải thích ngắn gọn bằng tiếng Việt và đường dẫn package  com.example.demo.controller
```

## 3. NHẬN XÉT AUDIT CỦA LẬP TRÌNH VIÊN (HUMAN-IN-THE-LOOP)
- **Message error sai so với đặc tả:** AI Agent có tự tạo mới message error để trả về.
- **Thao tác Refactor của tôi:** Tự sửa lại message error cho đúng với đặc tả
- **Kết quả Audit:** Kết quả đúng đặc tả.

- **Tự ý thêm xử lý lỗi 500** AI Agent có tự tạo thêm xử lý lỗi 500 trong GlobalExceptionHandler.
- **Thao tác Refactor của tôi:** Xóa đi phần xử lý lỗi 500
- **Kết quả Audit:** Bớt đi chỗ dư thừa.

- **** AI Agent có tự tạo thêm xử lý lỗi 500 trong GlobalExceptionHandler.
- **Thao tác Refactor của tôi:** Xóa đi phần xử lý lỗi 500
- **Kết quả Audit:** Bớt đi chỗ dư thừa.
