package BLL;

import GUI.ValidationException;
import DAL.CustomerDAO;
import DTO.customerDTO;
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

            if (CustomerDAO.isPhoneExist(customer.getSDT())) {
                return new Result(false, "Số điện thoại đã được đăng ký.");
            }

            boolean success = CustomerDAO.insertCustomer(customer);
            if (success) {
                return new Result(true, "Thêm khách hàng thành công!");
            } else {
                return new Result(false, "Không thể thêm khách hàng.");
            }

        } catch (ValidationException e) {
            return new Result(false, e.getMessage());
        }
    }

    // update
    public Result updateCustomer(customerDTO customer) {
        try {
            validateCustomerInput(customer.gettenKH(), customer.getSDT());
            boolean success = CustomerDAO.updateCustomer(customer);

            if (success) {
                return new Result(true, "Cập nhật thành công!");
            } else {
                return new Result(false, "Không thể cập nhật khách hàng.");
            }
        } catch (ValidationException e) {
            return new Result(false, e.getMessage());
        }
    }

    // Xóa
    public boolean deleteCustomer(int maKH) {
        return CustomerDAO.deleteCustomer(maKH);
    }

    // Tìm kiếm
    public List<customerDTO> searchCustomersByName(String keyword) {
        return CustomerDAO.searchCustomersByName(keyword);
    }

    public boolean isPhoneExist(String sdt) {
        return CustomerDAO.isPhoneExist(sdt);
    }

    public List<customerDTO> getCustomersByMonthAndYear(int month, int year) {
        return CustomerDAO.getCustomersByMonthAndYear(month, year);
    }
}
