package BLL;
import DAL.productDAL;
import DTO.productDTO;
import java.util.*;
public class productBLL {
    productDAL productdata = new productDAL();

    public Vector<productDTO> getAllProduct(){
        return productdata.getAllProduct();
    }    

    public String addProduct(productDTO newproduct){
        if(productdata.hasProduct(newproduct.getmaSP()))
            return "Mã sản phẩm đã tồn tại!";
        if(productdata.addProduct(newproduct))
            return "Sản phẩm đã được thêm thành công!";
        return "Thêm thất bại!";
    }

    public String delProduct(int maSP){
        if(productdata.delProduct(maSP))
            return "Đã xóa sản phẩm thành công!";
        return "Xóa sản phẩm thất bại!";
    }

    public String updateProduct(productDTO productUp){
        if(productdata.updateProduct(productUp))
            return "Đã cập nhật thành công sản phẩm!";
        return "Cập nhật sản phẩm thất bại!";
    }

    public Vector<productDTO> searchProduct(String tenSP){
        return productdata.searchProduct(tenSP);
    }
}
