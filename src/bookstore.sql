USE javaDB;
GO

CREATE TABLE warehouse (
    maSP INT IDENTITY(1,1) PRIMARY KEY,
    tenSP NVARCHAR(150),
    ngaynhap DATE DEFAULT GETDATE(),
    soluong INT
);
CREATE TABLE products (
    maSP INT PRIMARY KEY,
    tenSP NVARCHAR(150),
    loaiSP NVARCHAR(150),
    theloai NVARCHAR(150),
    giaca INT,
    ngayxuatban DATE,
    FOREIGN KEY (maSP) REFERENCES warehouse(maSP)
);
CREATE TABLE customers (
    maKH INT IDENTITY(1,1) PRIMARY KEY,
    tenKH NVARCHAR(150),
    SDT NVARCHAR(150)
);
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

INSERT INTO warehouse (tenSP, ngaynhap, soluong) VALUES
(N'Sách 1', '2022-12-26', 72),
(N'Sách 2', '2024-04-10', 77),
(N'Sách 3', '2024-06-13', 19),
(N'Sách 4', '2022-12-29', 95),
(N'Sách 5', '2023-05-30', 44),
(N'Sách 6', '2023-05-28', 99),
(N'Sách 7', '2023-10-13', 66),
(N'Sách 8', '2022-07-15', 89),
(N'Sách 9', '2022-05-21', 23),
(N'Sách 10', '2022-04-10', 23),
(N'Sách 11', '2023-03-08', 27),
(N'Sách 12', '2022-10-22', 76),
(N'Sách 13', '2023-02-13', 76),
(N'Sách 14', '2023-01-27', 95),
(N'Sách 15', '2022-12-16', 48),
(N'Sách 16', '2022-08-23', 44),
(N'Sách 17', '2023-11-25', 37),
(N'Sách 18', '2023-03-08', 31),
(N'Sách 19', '2023-11-24', 37),
(N'Sách 20', '2022-12-02', 59);

INSERT INTO products (maSP, tenSP, loaiSP, theloai, giaca, ngayxuatban) VALUES
(1, N'Sách 1', N'Kỹ năng', N'Văn', 173310, '2022-10-13'),
(2, N'Sách 2', N'Tiểu thuyết', N'Lịch sử', 239825, '2022-04-04'),
(3, N'Sách 3', N'Kỹ năng', N'Kinh doanh', 264237, '2023-05-02'),
(4, N'Sách 4', N'Tham khảo', N'Văn', 156479, '2022-07-15'),
(5, N'Sách 5', N'Kỹ năng', N'Văn', 162304, '2023-08-01'),
(6, N'Sách 6', N'Kỹ năng', N'Văn', 233170, '2023-09-13'),
(7, N'Sách 7', N'Giáo khoa', N'Lý', 269958, '2023-03-21'),
(8, N'Sách 8', N'Tham khảo', N'Văn', 203557, '2023-04-11'),
(9, N'Sách 9', N'Kỹ năng', N'Lịch sử', 170400, '2022-04-09'),
(10, N'Sách 10', N'Kỹ năng', N'Kinh doanh', 130984, '2022-07-06'),
(11, N'Sách 11', N'Tiểu thuyết', N'Toán', 276300, '2023-03-20'),
(12, N'Sách 12', N'Tham khảo', N'Văn', 166093, '2022-08-06'),
(13, N'Sách 13', N'Giáo khoa', N'Lý', 248037, '2023-04-14'),
(14, N'Sách 14', N'Giáo khoa', N'Toán', 78135, '2023-04-08'),
(15, N'Sách 15', N'Tiểu thuyết', N'Lịch sử', 253702, '2023-09-21'),
(16, N'Sách 16', N'Tham khảo', N'Văn', 227631, '2023-11-03'),
(17, N'Sách 17', N'Kỹ năng', N'Kinh doanh', 201674, '2024-01-17'),
(18, N'Sách 18', N'Tiểu thuyết', N'Văn', 265300, '2022-04-04'),
(19, N'Sách 19', N'Kỹ năng', N'Kinh doanh', 245361, '2022-05-21'),
(20, N'Sách 20', N'Giáo khoa', N'Kinh doanh', 207060, '2023-12-15');


INSERT INTO staffs (tenNV, SDT) VALUES
(N'Nhân viên 1', '0943642625'),
(N'Nhân viên 2', '0927853882'),
(N'Nhân viên 3', '0961408792'),
(N'Nhân viên 4', '0916857983'),
(N'Nhân viên 5', '0916984349');

INSERT INTO customers (tenKH, SDT) VALUES
(N'Khách hàng 1', '0916808250'),
(N'Khách hàng 2', '0981441179'),
(N'Khách hàng 3', '0957472281'),
(N'Khách hàng 4', '0935051479'),
(N'Khách hàng 5', '0989014867'),
(N'Khách hàng 6', '0912116844'),
(N'Khách hàng 7', '0915869570'),
(N'Khách hàng 8', '0915531883'),
(N'Khách hàng 9', '0947692586'),
(N'Khách hàng 10', '0981517485'),
(N'Khách hàng 11', '0987872539'),
(N'Khách hàng 12', '0922709184'),
(N'Khách hàng 13', '0916788806'),
(N'Khách hàng 14', '0998419066'),
(N'Khách hàng 15', '0913311073');


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
