package DAL;
import DTO.staffDTO;
import java.util.*;
import java.sql.*;

public class staffDAL {
    private Connection con;    

    public boolean openConnection(){
        try{
         String dbUrl="jdbc:sqlserver://localhost:1433;DatabaseName=javaDB;trustServerCertificate=true;";
         String username = "huynh";
         String password = "huynh";
         con = DriverManager.getConnection(dbUrl, username, password);
         return true;
         } catch(Exception e) {
             System.out.println(e);
             return false;
         }
     }

    public void closeConnection(){
    try{
        if(con!=null)
            con.close();
    } catch (SQLException e){
        System.out.println(e);
    }
    }

    public Vector<staffDTO> getAllStaff(){
        Vector<staffDTO> arr = new Vector<staffDTO>(); 
        if(openConnection()){
            try{
                String sql = "select * from staffs";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    staffDTO temp = new staffDTO();
                    temp.setmaNV(rs.getInt(1));
                    temp.settenNV(rs.getString(2));
                    temp.setSDT(rs.getString(3));
                    arr.add(temp);
                }
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return arr;
    }

    public boolean addStaff(staffDTO staff){
        Boolean result = false;
        if(openConnection()){
            try{
                String sql = "insert into staffs values(?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(2, staff.gettenNV());
                pstmt.setString(4, staff.getSDT());
                if(pstmt.executeUpdate() > 0){
                    result = true;
                }
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasStaff(String SDT){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "select * from staffs where SDT =" + SDT;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                    result = true;
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean updateStaff(staffDTO staff){
        Boolean result = false;
        if(openConnection()){
            try{
                String sql = "update staffs set tenNV = ?, SDT = ? where maNV = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, staff.gettenNV());
                pstmt.setString(2, staff.getSDT());
                pstmt.setInt(3, staff.getmaNV());
                if(pstmt.executeUpdate() > 0){
                    result = true;
                }
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean deleteStaff(int maNV){
        Boolean result = false;
        if(openConnection()){
            try{
                String sql = "delete from staffs where maNV = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, maNV);
                if(pstmt.executeUpdate() > 0){
                    result = true;
                }
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public Vector<staffDTO> searchStaff(String tenNV){
        if(openConnection()){
            try{
                Vector<staffDTO> arr = new Vector<staffDTO>();
                String sql = "select * from staffs where tenNV like N'%"+tenNV+"%'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                staffDTO staff = new staffDTO();
                while(rs.next()){
                    staff.setmaNV(rs.getInt(1));
                    staff.settenNV(rs.getString(2));
                    staff.setSDT(rs.getString(3));
                    arr.add(staff);
                }
                return arr;
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return null;
    }
}
