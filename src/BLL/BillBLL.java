package BLL;

import java.util.ArrayList;

import DAL.BillDAL;
import DTO.billDTO;
import DTO.customerDTO;
import DTO.productDTO;



public class BillBLL {
    private BillDAL billDAL = new BillDAL();


    public ArrayList<billDTO> getAllBills() {
        
        return billDAL.getAllBills();
    }

    public ArrayList<customerDTO> getAllCustomers() {
        
        return billDAL.getAllCustomers();
    }

    public ArrayList<productDTO> getAllProducts() {
        
        return billDAL.getAllProducts();
    }

    public boolean deleteBill(int maHD) {
        if(!isBillExist(maHD)) {
            return false; 
        }
        return billDAL.deleteBill(maHD);
    }

    public boolean isCustomerExist (int maKH) {
        ArrayList<customerDTO> customers = billDAL.getAllCustomers();
        for (customerDTO customer : customers) {
            if (customer.getmaKH() == maKH) {
                return true;
            }
        }
        return false;
    }

    public boolean isBillExist (int maHD) {
        ArrayList<billDTO> bills = billDAL.getAllBills();
        for (billDTO bill : bills) {
            if (bill.getmaHD() == maHD) {
                return true;
            }
        }
        return false;
    }

    public boolean createBill(int maKH) {
        
        return billDAL.createBill(maKH);
    }

    public ArrayList<billDTO> searchBill(int maHD) {
        ArrayList<billDTO> bills = billDAL.getAllBills();
        ArrayList<billDTO> result = new ArrayList<>();
        for (billDTO bill : bills) {
            if (bill.getmaHD() == maHD) {
                result.add(bill);
                break;
            }
        }
        return result;
    }

    public String createBillDetail(int maHD, String tenSP, int soLuong) {

        // billDAL.createBillDetail(maHD, maSP, quantity);
        ArrayList<productDTO> products = billDAL.getAllProducts();
        ArrayList<billDTO> bills = billDAL.getAllBills();
       
        for (billDTO bill : bills) {
            if (bill.getmaHD() == maHD) {
                for (productDTO product : products) {
                    if (product.gettenSP().equals(tenSP)) {
                        if(soLuong > product.getsoluong()) {
                            return "Số lượng sản phẩm trong kho không đủ";
                        }
                        boolean success = billDAL.createBillDetail(maHD, product.getmaSP(), soLuong, product.getgiaca());
                        if(success) {
                            return "Thêm chi tiết hóa đơn thành công";
                        } else {
                            return "Thêm chi tiết hóa đơn thất bại";
                        }
                    }
                }
                return "Sản phẩm không tồn tại";
            }
            
        }
        return "Mã hóa đơn không tồn tại";
    }

    public String updateBillCustomer(int maHD, int maKH) {
        ArrayList<customerDTO> customers = billDAL.getAllCustomers();
        ArrayList<billDTO> bills = billDAL.getAllBills();
        for(billDTO bill : bills) {
            if(bill.getmaHD() == maHD) {
                for(customerDTO customer : customers) {
                    if(customer.getmaKH() == maKH) {
                        boolean success = billDAL.updateBillCustomer(maHD, maKH);
                        if(success) {
                            return "Cập nhật khách hàng thành công";
                        } else {
                            return "Cập nhật khách hàng thất bại";
                        }
                    }
                }
                return "Mã khách hàng không tồn tại";
            }
        }
        return "Mã hóa đơn không tồn tại";

    }

    public String checkThangNam(int tuThang, int tuNam, int denThang, int denNam) {
        if(tuThang > 12 || tuThang < 1 || denThang > 12 || denThang < 1){
            return "Tháng không hợp lệ";
        }
        if(tuNam > 2025 || denNam > 2025){
            return "Năm phải nhỏ hơn 2025";
        }
        return "";

    }

    public ArrayList<billDTO> getBillsByMonthRange(int tuThang, int tuNam, int denThang, int denNam) {
        return billDAL.getBillsByMonthRange(tuThang, tuNam, denThang, denNam);
    }

    public int sumTotalBillAmount(ArrayList<billDTO> bills) {
        int sum = 0;
        for (billDTO bill : bills) {
            sum += bill.gettongtien();
        }
        return sum;
    }

}
