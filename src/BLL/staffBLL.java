package BLL;
import DAL.staffDAL;
import DTO.staffDTO;
import java.util.Vector;

public class staffBLL {
    private staffDAL staffdal = new staffDAL();
    
    public Vector<staffDTO> getAllStaff(){
        return staffdal.getAllStaff();
    }
    
    public String addStaff(staffDTO staff){
        if(staffdal.hasStaff(staff.getSDT())){
            return "Nhân viên với số máy: "+staff.getSDT()+ " đã tồn tại!";
        } else {
            if(staffdal.addStaff(staff)){
                return "Thêm nhân viên thành công!";
            } else {
                return "Thêm nhân viên thất bại!";
            }
        }
    }

    public String updateStaff(staffDTO staff){
        if(staffdal.updateStaff(staff)){
            return "Cập nhật nhân viên thành công!";
        } else {
            return "Cập nhật nhân viên thất bại!";
        }
    }

    public String deleteStaff(int maNV){
        if(staffdal.deleteStaff(maNV)){
            return "Xóa nhân viên thành công!";
        } else {
            return "Xóa nhân viên thất bại!";
        }
    }
    
    public Vector<staffDTO> searchStaffs(String tenNV){
        return staffdal.searchStaff(tenNV);
    }
    
}
