package BLL;

import GUI.ValidationException;
import DAO.CustomerDAO;
import DTO.customerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerBLL {
    public List<customerDTO> getAllCustomers() {
        return CustomerDAO.getAllCustomers();
    }

    public void validateCustomerInput(String ten, String sdt) throws ValidationException {
        if (ten == null || ten.trim().isEmpty()) {
            throw new ValidationException("Tên khách hàng không được để trống!");
        }
        if (ten.length() > 150) {
            throw new ValidationException("Tên khách hàng quá dài (tối đa 150 ký tự).");
        }

        if (sdt == null || sdt.trim().isEmpty()) {
            throw new ValidationException("Số điện thoại không được để trống!");
        }
        if (!sdt.matches("\\d{10}")) {
            throw new ValidationException("Số điện thoại không hợp lệ (phải đủ 10 chữ số và chỉ chứa số).");
        }
    }

    // add
    public Result addCustomer(customerDTO customer) {
        try {
            validateCustomerInput(customer.gettenKH(), customer.getSDT());

            String checkSql = "SELECT * FROM customers WHERE SDT = ?";
            try (Connection conn = CustomerDAO.getConnection();
                    PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

                checkStmt.setString(1, customer.getSDT());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    return new Result(false, "Số điện thoại đã được đăng ký trong cơ sở dữ liệu.");
                }
            }

            String insertSql = "INSERT INTO customers (tenKH, SDT) VALUES (?, ?)";
            try (Connection conn = CustomerDAO.getConnection();
                    PreparedStatement ps = conn.prepareStatement(insertSql)) {

                ps.setString(1, customer.gettenKH());
                ps.setString(2, customer.getSDT());

                if (ps.executeUpdate() > 0) {
                    return new Result(true, "Thêm khách hàng thành công!");
                } else {
                    return new Result(false, "Không thể thêm khách hàng.");
                }
            }
        } catch (ValidationException e) {
            return new Result(false, e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false, "Lỗi khi thêm khách hàng.");
        }
    }

    // update
    public Result updateCustomer(customerDTO customer) {
        try {
            validateCustomerInput(customer.gettenKH(), customer.getSDT());
            String sql = "UPDATE customers SET tenKH = ?, SDT = ? WHERE maKH = ?";
            try (Connection conn = CustomerDAO.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, customer.gettenKH());
                ps.setString(2, customer.getSDT());
                ps.setInt(3, customer.getmaKH());

                if (ps.executeUpdate() > 0) {
                    return new Result(true, "Cập nhật thành công!");
                } else {
                    return new Result(false, "Không thể cập nhật khách hàng.");
                }
            }
        } catch (ValidationException e) {
            return new Result(false, e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result(false, "Lỗi khi cập nhật khách hàng.");
        }
    }

    // Xóa
    public boolean deleteCustomer(int maKH) {
        String sql = "DELETE FROM customers WHERE maKH = ?";

        try (Connection conn = CustomerDAO.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKH);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm
    public List<customerDTO> searchCustomersByName(String keyword) {
        List<customerDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE tenKH LIKE ? OR SDT LIKE ?";

        try (Connection conn = CustomerDAO.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customerDTO customer = new customerDTO();
                customer.setmaKH(rs.getInt("maKH"));
                customer.settenKH(rs.getString("tenKH"));
                customer.setSDT(rs.getString("SDT"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isPhoneExist(String sdt) {
        String sql = "SELECT 1 FROM customers WHERE SDT = ?";
        try (Connection conn = CustomerDAO.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
