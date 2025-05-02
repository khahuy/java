package DAL;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.sql.*;

import DTO.billDTO;
import DTO.customerDTO;
import DTO.productDTO;

public class BillDAL {
    private Connection conn;

    public boolean openConnection() {
        try {
            String dbUrl="jdbc:sqlserver://localhost:1433;DatabaseName=javaDB;trustServerCertificate=true;";
            String username = "huynh";
            String password = "huynh";
            conn = DriverManager.getConnection(dbUrl, username, password);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<billDTO> getAllBills() {
        ArrayList<billDTO> billList = new ArrayList<>();
        String sql = "SELECT * FROM bills";

        if (openConnection()) {
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int maHD = rs.getInt(1);
                    int maKH = rs.getInt(2);
                    int tongtien = rs.getInt(3);
                    LocalDate ngaymua = rs.getDate(4).toLocalDate();

                    billDTO bill = new billDTO();
                    bill.setmaHD(maHD);
                    bill.setmaKH(maKH);
                    bill.settongtien(tongtien);
                    bill.setngaymua(ngaymua);


                    billList.add(bill);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }

        }
        return billList;
    }

    public boolean deleteBill(int maHD) {
        boolean result = false;
        String sql_billDetail = "DELETE FROM bill_details WHERE maHD = ?";
        String sql_bill = "DELETE FROM bills WHERE maHD = ?";

        if (openConnection()) {
            try (
                    PreparedStatement stmtDetail = conn.prepareStatement(sql_billDetail);
                    PreparedStatement stmtBill = conn.prepareStatement(sql_bill)) {

                stmtDetail.setInt(1, maHD);
                stmtDetail.executeUpdate();

                stmtBill.setInt(1, maHD);
                int rowsAffected = stmtBill.executeUpdate();

                if (rowsAffected > 0) {
                    result = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        return result;
    }

    public ArrayList<customerDTO> getAllCustomers() {
        ArrayList<customerDTO> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        if (openConnection()) {
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int maKH = rs.getInt(1);
                    String tenKH = rs.getString(2);
                    String SDT = rs.getString(3);

                    customerDTO customer = new customerDTO();
                    customer.setmaKH(maKH);
                    customer.settenKH(tenKH);
                    customer.setSDT(SDT);

                    customerList.add(customer);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }

        }
        return customerList;
    }

    public ArrayList<productDTO> getAllProducts() {
        ArrayList<productDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        if (openConnection()) {
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int maSP = rs.getInt(1);
                    String tenSP = rs.getString(2);
                    String loaiSP = rs.getString(3);
                    String theLoai = rs.getString(4);
                    int giaCa = rs.getInt(5);
                    int soLuong = rs.getInt(6);
                    LocalDate ngayXuatBan = rs.getDate(7).toLocalDate();
                    LocalDate ngayNhapKho = rs.getDate(8).toLocalDate();

                    productDTO product = new productDTO();
                    product.setmaSP(maSP);
                    product.settenSP(tenSP);
                    product.setloaiSP(loaiSP);
                    product.settheloai(theLoai);
                    product.setgiaca(giaCa);
                    product.setsoluong(soLuong);
                    product.setngayxuatban(ngayXuatBan);
                    product.setngaynhapkho(ngayNhapKho);

                    productList.add(product);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }

        }
        return productList;
    }

    public boolean createBill(int maKH) {
        String sql = "INSERT INTO bills (maKH, tongtien) VALUES (?, 0)";

        if (openConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, maKH);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false;
    }

    public void updateTongTien(int maHD) {
        String sql = "UPDATE bills " +
                "SET tongtien = ( " +
                "    SELECT SUM(soluong * giaca) " +
                "    FROM bill_details " +
                "    WHERE bill_details.maHD = bills.maHD " +
                "    GROUP BY bill_details.maHD " +
                ") " +
                "WHERE maHD = ?";

        if (openConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, maHD);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }

    public void updateSoLuongKho(int maSP, int soLuong, int soLuongTrongKho) {
        String sql = "UPDATE products SET soluong = ? WHERE maSP = ?"; 
        int temp = soLuongTrongKho - soLuong; 

        if (openConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, temp);
                pstmt.setInt(2, maSP);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

    }

    public boolean createBillDetail(int maHD, int maSP, int soLuong, int giaCa) {
        String sql = "INSERT INTO bill_details (maHD, maSP, soluong, giaca) VALUES (?, ?, ?, ?)";

        if (openConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, maHD);
                pstmt.setInt(2, maSP);
                pstmt.setInt(3, soLuong);
                pstmt.setInt(4, giaCa);
                pstmt.executeUpdate();

                updateTongTien(maHD);
                ArrayList<productDTO> products = getAllProducts();
                int soLuongTrongKho = 0;
                for (productDTO product : products) {
                    if (product.getmaSP() == maSP) {
                        soLuongTrongKho = product.getsoluong();
                        break;
                    }
                }
                updateSoLuongKho(maSP, soLuong, soLuongTrongKho);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false;
    }

    public boolean updateBillCustomer(int maHD, int maKH) {
        String sql = "UPDATE bills SET maKH = ? WHERE maHD = ?"; 

        if (openConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, maKH);
                pstmt.setInt(2, maHD);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false;
    }

    public ArrayList<billDTO> getBillsByMonthRange(int tuThang, int tuNam, int denThang, int denNam) {
    ArrayList<billDTO> list = new ArrayList<>();
    

    LocalDate startDate = LocalDate.of(tuNam, tuThang, 1);
    LocalDate endDate = LocalDate.of(denNam, denThang, YearMonth.of(denNam, denThang).lengthOfMonth());
    
    String sql = "SELECT * FROM bills WHERE ngaymua BETWEEN ? AND ?";
    
    if (openConnection()) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(startDate));
            pstmt.setDate(2, java.sql.Date.valueOf(endDate));
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                billDTO bill = new billDTO();
                bill.setmaHD(rs.getInt(1));
                bill.setmaKH(rs.getInt(2));
                bill.setngaymua(rs.getDate(4).toLocalDate());
                bill.settongtien(rs.getInt(3));

                list.add(bill);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    return list;
}


}
