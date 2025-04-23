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
        if(productdata.hasProduct(productUp.getmaSP()))
            return "Mã sản phẩm đã tồn tại!";
        if(productdata.updateProduct(productUp))
            return "Đã cập nhật thành công sản phẩm!";
        return "Cập nhật sanr phẩm thất bại!";
    }

    public String searchProduct(String tenSP){
        productDTO result = productdata.searchProduct(tenSP); 
        if(result != null){
            return "Mã SP: "+ result.getmaSP() + "\t Tên SP: "+result.gettenSP()+ "\nLoại SP: "+result.getloaiSP() + "\t Thể loại: "+result.gettheloai()+"\nGiá cả: "+result.getgiaca()+ "\t Số lượng: "+result.getsoluong() +"\n Ngày xuất bản: "+result.getngayxuatban()+ "\t Ngày nhập kho: "+result.getngaynhapkho();
        }
        return "Không tìm thấy sản phẩm nào!";
    }
}
