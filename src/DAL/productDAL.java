package DAL;
import java.util.Vector;
import java.sql.*;
import java.time.format.DateTimeFormatter;

import DTO.productDTO;

public class productDAL {
    private Connection con;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    public Vector<productDTO> getAllProduct(){
        Vector<productDTO> arr = new Vector<productDTO>(); 
        if(openConnection()){
            try{
                String sql = "select * from products";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    productDTO temp = new productDTO();
                    temp.setmaSP(rs.getInt(1));
                    temp.settenSP(rs.getString(2));
                    temp.setloaiSP(rs.getString(3));
                    temp.settheloai(rs.getString(4));
                    temp.setgiaca(rs.getInt(5));
                    temp.setsoluong(rs.getInt(6));
                    temp.setngayxuatban(rs.getDate(7).toLocalDate());
                    temp.setngaynhapkho(rs.getDate(8).toLocalDate());
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

    public boolean hasProduct(String tenSP){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "select * from products where tenSP COLLATE Latin1_General_CI_AI like N'%" + tenSP + "%'";
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

    public boolean addProduct(productDTO product){
        boolean result = false;
        if(openConnection()){
            try{
                String sql= "insert into products values(?,?,?,?,?,?,?)";
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setString(1, product.gettenSP());
                prst.setString(2, product.getloaiSP());
                prst.setString(3, product.gettheloai());
                prst.setInt(4, product.getgiaca());
                prst.setInt(5, product.getsoluong());
                prst.setString(6, product.getngayxuatban().format(formatter));
                prst.setString(7, product.getngaynhapkho().format(formatter));
                if(prst.executeUpdate() >= 1)
                    result = true;
            } catch(SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean delProduct(int id){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "delete from products where maSP = "+id;
                PreparedStatement prst = con.prepareStatement(sql);
                if(prst.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return result;
    }

    public boolean updateProduct(productDTO product){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "update products set tenSP=?, loaiSP=?, theloai=?, giaca=?, soluong=?, ngayxuatban=?, ngaynhapkho=?  where maSP="+product.getmaSP();
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setString(1, product.gettenSP());
                prst.setString(2, product.getloaiSP());
                prst.setString(3, product.gettheloai());
                prst.setInt(4, product.getgiaca());
                prst.setInt(5, product.getsoluong());
                prst.setString(6, product.getngayxuatban().format(formatter));
                prst.setString(7, product.getngaynhapkho().format(formatter));
                if(prst.executeUpdate() >= 1)
                    result = true;
            } catch (SQLException e){
                System.out.println(e);
            } finally{
                closeConnection();
            }
        }
        return result;
    }

    public Vector<productDTO> searchProduct(String tenSP){
        if(openConnection()){
            try{
                Vector<productDTO> arr = new Vector<productDTO>();
                String sql = "select * from products where tenSP like N'%"+tenSP+"%'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    productDTO resproduct = new productDTO();
                    resproduct.setmaSP(rs.getInt(1));
                    resproduct.settenSP(rs.getString(2));
                    resproduct.setloaiSP(rs.getString(3));
                    resproduct.settheloai(rs.getString(4));
                    resproduct.setgiaca(rs.getInt(5));
                    resproduct.setsoluong(rs.getInt(6));
                    resproduct.setngayxuatban(rs.getDate(7).toLocalDate());
                    resproduct.setngaynhapkho(rs.getDate(8).toLocalDate());
                    arr.add(resproduct);
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

    public Vector<productDTO> statiticProduct(String month, String year){
        if(openConnection()){
            try{
                Vector<productDTO> result = new Vector<productDTO>();
                String sql = "select * from products where month(ngaynhapkho) = "+month+ " and year(ngaynhapkho) = "+year;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    productDTO temp = new productDTO();
                    temp.setmaSP(rs.getInt(1));
                    temp.settenSP(rs.getString(2));
                    temp.setloaiSP(rs.getString(3));
                    temp.settheloai(rs.getString(4));
                    temp.setgiaca(rs.getInt(5));
                    temp.setsoluong(rs.getInt(6));
                    temp.setngayxuatban(rs.getDate(7).toLocalDate());
                    temp.setngaynhapkho(rs.getDate(8).toLocalDate());
                    result.add(temp);
                }
                return result;
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return null;
    }
}
