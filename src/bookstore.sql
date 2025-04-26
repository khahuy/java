CREATE DATABASE javaDB;
GO
USE javaDB;
GO

CREATE TABLE products (
    maSP INT IDENTITY(1,1) PRIMARY KEY,
    tenSP NVARCHAR(150),
    loaiSP NVARCHAR(150),
    theloai NVARCHAR(150),
    giaca INT,
    soluong INT,
    ngayxuatban DATE,
    ngaynhapkho DATE DEFAULT GETDATE(),
);
CREATE TABLE customers (
    maKH INT IDENTITY(1,1) PRIMARY KEY,
    tenKH NVARCHAR(50),
    SDT NVARCHAR(10) UNIQUE,  
    ngayDangKy DATETIME DEFAULT GETDATE()
);
select * from customers where 1 = 1
CREATE TABLE staffs (
    maNV INT IDENTITY(1,1) PRIMARY KEY,
    tenNV NVARCHAR(150),
    SDT NVARCHAR(150)
);
CREATE TABLE bills (
    maHD INT IDENTITY(1,1) PRIMARY KEY,
    maKH INT,
    tongtien INT,
	ngaymua DATE DEFAULT GETDATE(),
	FOREIGN KEY (maKH) REFERENCES customers(maKH)
);
CREATE TABLE bill_details (
    maHD INT,
    maSP INT,
    soluong INT,
    giaca INT,
    FOREIGN KEY (maHD) REFERENCES bills(maHD),
    FOREIGN KEY (maSP) REFERENCES products(maSP)
);
SET IDENTITY_INSERT products ON;
INSERT INTO products (maSP, tenSP, loaiSP, theloai, giaca, soluong, ngayxuatban, ngaynhapkho) VALUES
(1, N'Sách 1', N'Kỹ năng', N'Văn', 173310, 20, '2022-10-13', '2022-12-26'),
(2, N'Sách 2', N'Tiểu thuyết', N'Lịch sử', 239825, 30, '2022-04-04', '2024-04-10'),
(3, N'Sách 3', N'Kỹ năng', N'Kinh doanh', 264237, 11, '2023-05-02', '2024-06-13'),
(4, N'Sách 4', N'Tham khảo', N'Văn', 156479, 24, '2022-07-15', '2022-12-29'),
(5, N'Sách 5', N'Kỹ năng', N'Văn', 162304, 50,'2023-08-01', '2024-05-30'),
(6, N'Sách 6', N'Kỹ năng', N'Văn', 233170, 43,'2023-09-13', '2024-05-28'),
(7, N'Sách 7', N'Giáo khoa', N'Lý', 269958, 31, '2023-03-21', '2023-10-13'),
(8, N'Sách 8', N'Tham khảo', N'Văn', 203557, 34,'2023-04-11', '2024-07-15'),
(9, N'Sách 9', N'Kỹ năng', N'Lịch sử', 170400, 6, '2022-04-09', '2022-05-21'),
(10, N'Sách 10', N'Kỹ năng', N'Kinh doanh', 130984, 23,'2022-07-06', '2023-04-10'),
(11, N'Sách 11', N'Tiểu thuyết', N'Toán', 276300, 42, '2023-03-20', '2023-04-08'),
(12, N'Sách 12', N'Tham khảo', N'Văn', 166093, 35, '2022-08-06', '2022-10-22'),
(13, N'Sách 13', N'Giáo khoa', N'Lý', 248037, 27, '2023-04-14', '2023-09-13'),
(14, N'Sách 14', N'Giáo khoa', N'Toán', 78135, 19, '2023-04-08', '2023-10-27'),
(15, N'Sách 15', N'Tiểu thuyết', N'Lịch sử', 253702, 28, '2023-09-21', '2023-12-16'),
(16, N'Sách 16', N'Tham khảo', N'Văn', 227631, 56, '2023-11-03', '2024-08-23'),
(17, N'Sách 17', N'Kỹ năng', N'Kinh doanh', 201674, 46, '2024-01-17', '2024-11-25'),
(18, N'Sách 18', N'Tiểu thuyết', N'Văn', 265300, 44, '2022-04-04', '2023-03-08'),
(19, N'Sách 19', N'Kỹ năng', N'Kinh doanh', 245361, 24, '2022-05-21', '2023-11-24'),
(20, N'Sách 20', N'Giáo khoa', N'Kinh doanh', 207060, 11, '2023-12-15', '2023-12-31');


INSERT INTO staffs (tenNV, SDT) VALUES
(N'Nhân viên 1', '0943642625'),
(N'Nhân viên 2', '0927853882'),
(N'Nhân viên 3', '0961408792'),
(N'Nhân viên 4', '0916857983'),
(N'Nhân viên 5', '0916984349');

INSERT INTO customers (tenKH, SDT, ngayDangKy) VALUES
(N'Khách hàng 1', '0916808250', '2021-01-01'),
(N'Khách hàng 2', '0981441179', '2021-01-01'),
(N'Khách hàng 3', '0957472281', '2021-01-01'),
(N'Khách hàng 4', '0935051479', '2021-01-01'),
(N'Khách hàng 5', '0989014867', '2021-01-01'),
(N'Khách hàng 6', '0912116844', '2021-01-01'),
(N'Khách hàng 7', '0915869570', '2021-01-01'),
(N'Khách hàng 8', '0915531883', '2021-01-01'),
(N'Khách hàng 9', '0947692586', '2021-01-01'),
(N'Khách hàng 10', '0981517485', '2021-01-01'),
(N'Khách hàng 11', '0987872539', '2021-01-01'),
(N'Khách hàng 12', '0922709184', '2021-01-01'),
(N'Khách hàng 13', '0916788806', '2021-01-01'),
(N'Khách hàng 14', '0998419066', '2021-01-01'),
(N'Khách hàng 15', '0913311073', '2021-01-01');
-- UPDATE customers
-- SET ngayDangKy = '2021-01-01 00:00:00'
-- WHERE ngayDangKy != '2021-01-01 00:00:00';

-- Chèn hóa đơn
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (9, 203557*5, '2024-02-10');    
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (14, 201674*5 + 239825*4, '2024-01-10');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (12, 201674*1, '2024-07-09');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (10, 130984*3 + 173310*1, '2022-10-12');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (3, 265300*3, '2023-11-15');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (15, 78135*2 + 162304*2, '2023-05-06');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (13, 170400*5, '2023-07-17');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (8, 239825*1 + 264237*2, '2024-02-28');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (2, 269958*2 + 156479*1, '2024-07-08');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (10, 248037*2, '2023-09-08');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (4, 162304*1, '2023-10-01');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (3, 166093*3, '2022-03-08');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (7, 233170*4, '2023-01-18');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (6, 253702*1 + 203557*2, '2024-01-10');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (12, 245361*3, '2024-01-14');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (13, 207060*1, '2022-06-16');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (1, 276300*2, '2024-04-03');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (9, 227631*2, '2023-08-30');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (10, 173310*3, '2022-09-28');
INSERT INTO bills (maKH, tongtien, ngaymua) VALUES (4, 264237*1, '2024-01-23');



-- Chi tiết hóa đơn 1
INSERT INTO bill_details VALUES (1, 8, 2, 203557);
INSERT INTO bill_details VALUES (1, 8, 3, 203557);

-- Hóa đơn 2
INSERT INTO bill_details VALUES (2, 17, 5, 201674);
INSERT INTO bill_details VALUES (2, 2, 4, 239825);

-- Hóa đơn 3
INSERT INTO bill_details VALUES (3, 17, 1, 201674);

-- Hóa đơn 4
INSERT INTO bill_details VALUES (4, 10, 3, 130984);
INSERT INTO bill_details VALUES (4, 1, 1, 173310);

-- Hóa đơn 5
INSERT INTO bill_details VALUES (5, 18, 3, 265300);

-- Hóa đơn 6
INSERT INTO bill_details VALUES (6, 14, 2, 78135);
INSERT INTO bill_details VALUES (6, 5, 2, 162304);

-- Hóa đơn 7
INSERT INTO bill_details VALUES (7, 9, 5, 170400);

-- Hóa đơn 8
INSERT INTO bill_details VALUES (8, 2, 1, 239825);
INSERT INTO bill_details VALUES (8, 3, 2, 264237);

-- Hóa đơn 9
INSERT INTO bill_details VALUES (9, 7, 2, 269958);
INSERT INTO bill_details VALUES (9, 4, 1, 156479);

-- Hóa đơn 10
INSERT INTO bill_details VALUES (10, 13, 2, 248037);

-- Hóa đơn 11
INSERT INTO bill_details VALUES (11, 5, 1, 162304);

-- Hóa đơn 12
INSERT INTO bill_details VALUES (12, 12, 3, 166093);

-- Hóa đơn 13
INSERT INTO bill_details VALUES (13, 6, 4, 233170);

-- Hóa đơn 14
INSERT INTO bill_details VALUES (14, 15, 1, 253702);
INSERT INTO bill_details VALUES (14, 8, 2, 203557);

-- Hóa đơn 15
INSERT INTO bill_details VALUES (15, 19, 3, 245361);

-- Hóa đơn 16
INSERT INTO bill_details VALUES (16, 20, 1, 207060);

-- Hóa đơn 17
INSERT INTO bill_details VALUES (17, 11, 2, 276300);

-- Hóa đơn 18
INSERT INTO bill_details VALUES (18, 16, 2, 227631);

-- Hóa đơn 19
INSERT INTO bill_details VALUES (19, 1, 3, 173310);

-- Hóa đơn 20
INSERT INTO bill_details VALUES (20, 3, 1, 264237);
