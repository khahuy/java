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
                    temp.setngayxuatban(rs.getDate(6).toLocalDate());
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

    public boolean hasProduct(int id){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "select * from products where maSP ="+id;
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
                String sql= "insert into products values(?,?,?,?,?,?)";
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setInt(1, product.getmaSP());
                prst.setString(2, product.gettenSP());
                prst.setString(3, product.getloaiSP());
                prst.setString(4, product.gettheloai());
                prst.setInt(5, product.getgiaca());
                prst.setString(6, product.getngayxuatban().format(formatter));
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
                String sql = "update products set maSP=?, tenSP=?, loaiSP=?, theloai=?, giaca=?, ngayxuatban=?  where maSP="+product.getmaSP();
                PreparedStatement prst = con.prepareStatement(sql);
                prst.setInt(1, product.getmaSP());
                prst.setString(2, product.gettenSP());
                prst.setString(3, product.getloaiSP());
                prst.setString(4, product.gettheloai());
                prst.setInt(5, product.getgiaca());
                prst.setString(6, product.getngayxuatban().format(formatter));
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

    public productDTO searchProduct(String tenSP){
        productDTO resproduct = new productDTO();
        if(openConnection()){
            try{
                String sql = "select * from products where tenSP = "+tenSP;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){
                    resproduct.setmaSP(rs.getInt(1));
                    resproduct.settenSP(rs.getString(2));
                    resproduct.setloaiSP(rs.getString(3));
                    resproduct.settheloai(rs.getString(4));
                    resproduct.setgiaca(rs.getInt(5));
                    resproduct.setngayxuatban(rs.getDate(6).toLocalDate());
                    return resproduct;
                }
            } catch (SQLException e){
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return null;
    }
}
