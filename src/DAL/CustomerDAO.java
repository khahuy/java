package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DTO.customerDTO;

public class CustomerDAO {
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String dbUrl="jdbc:sqlserver://localhost:1433;DatabaseName=javaDB;trustServerCertificate=true;";
        String username = "huynh";
        String password = "huynh";
				conn = DriverManager.getConnection(dbUrl, username, password);
			}
		} catch (Exception e) {
			System.out.println("Kết nối thất bại: " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<customerDTO> getAllCustomers() {
		List<customerDTO> list = new ArrayList<>();
		String sql = "SELECT * FROM customers";

		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

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

	public static boolean isPhoneExist(String sdt) {
		String sql = "SELECT 1 FROM customers WHERE SDT = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, sdt);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean insertCustomer(customerDTO customer) {
		String sql = "INSERT INTO customers (tenKH, SDT) VALUES (?, ?)";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, customer.gettenKH());
			ps.setString(2, customer.getSDT());
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateCustomer(customerDTO customer) {
		String sql = "UPDATE customers SET tenKH = ?, SDT = ? WHERE maKH = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, customer.gettenKH());
			ps.setString(2, customer.getSDT());
			ps.setInt(3, customer.getmaKH());
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteCustomer(int maKH) {
		String sql = "DELETE FROM customers WHERE maKH = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maKH);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<customerDTO> searchCustomersByName(String keyword) {
		List<customerDTO> list = new ArrayList<>();
		String sql = "SELECT * FROM customers WHERE tenKH LIKE ? OR SDT LIKE ?";

		try (Connection conn = getConnection();
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

	public static List<customerDTO> getCustomersByMonthAndYear(int month, int year) {
		List<customerDTO> customers = new ArrayList<>();
		String sql = "SELECT * FROM customers WHERE MONTH(ngayDangKy) = ? AND YEAR(ngayDangKy) = ?";

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, month);
			stmt.setInt(2, year);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maKH = rs.getInt("maKH");
				String tenKH = rs.getString("tenKH");
				String sdt = rs.getString("SDT");
				Date ngayDangKy = rs.getDate("ngayDangKy");

				// Tập hợp các khách hàng trong thời gian đó
				customerDTO customer = new customerDTO();
				customer.setmaKH(maKH);
				customer.settenKH(tenKH);
				customer.setSDT(sdt);
				customer.setSignIn(ngayDangKy);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

}
