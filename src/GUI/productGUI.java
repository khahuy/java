package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Vector;
import java.util.zip.DataFormatException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BLL.productBLL; 
import DTO.productDTO;
public class productGUI {
    JFrame frame = new JFrame("Quản lý sản phẩm");
    JLabel maSPlb = new JLabel("Mã sản phẩm: ");
    JTextField maSPtf = new JTextField();
    JLabel tenSPlb = new JLabel("Tên sản phẩm: ");
    JTextField tenSPtf = new JTextField();
    JLabel loaiSPlb = new JLabel("Loại sản phẩm: ");
    JTextField loaiSPtf = new JTextField();
    JLabel theloailb = new JLabel("Thể loại: ");
    JTextField theloaitf = new JTextField();
    JLabel giacalb = new JLabel("Giá: ");
    JTextField giacatf = new JTextField();
    JLabel ngayxuatbanlb = new JLabel("Ngày xuất bản: ");
    JTextField ngayxuatbantf = new JTextField();
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    JButton addBtn = new JButton("Thêm sản phẩm");
    JButton updateBtn = new JButton("Cập nhật sản phẩm");
    JButton deleteBtn = new JButton("Xóa sản phẩm");
    JButton searchBtn = new JButton("Tìm kiếm sản phẩm");

    productBLL productHanle = new productBLL();

    public void interface_setting(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(maSPlb); frame.add(maSPtf);
        frame.add(tenSPlb); frame.add(tenSPtf);
        frame.add(loaiSPlb); frame.add(loaiSPtf);
        frame.add(theloailb); frame.add(theloaitf);
        frame.add(giacalb); frame.add(giacatf);
        frame.add(ngayxuatbanlb); frame.add(ngayxuatbantf);
        maSPlb.setBounds(30, 50, 150, 30); maSPtf.setBounds(180, 50, 250, 30);
        tenSPlb.setBounds(500, 50, 150, 30); tenSPtf.setBounds(630, 50, 250, 30);
        loaiSPlb.setBounds(30, 100, 150, 30); loaiSPtf.setBounds(180, 100, 250, 30);
        theloailb.setBounds(500, 100, 150, 30); theloaitf.setBounds(630, 100, 250, 30);
        giacalb.setBounds(30, 150, 150, 30); giacatf.setBounds(180, 150, 250, 30);
        ngayxuatbanlb.setBounds(500, 150, 150, 30); ngayxuatbantf.setBounds(630, 150, 250, 30);
        frame.add(addBtn); frame.add(updateBtn); frame.add(deleteBtn); frame.add(searchBtn);
        addBtn.setBounds(1000, 50, 150, 30); updateBtn.setBounds(1200, 50, 150, 30);
        deleteBtn.setBounds(1000, 100, 150, 30); searchBtn.setBounds(1200, 100, 150, 30);

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, frame.getWidth() - 70, frame.getHeight() - 300);
        frame.add(scrollPane);
    }

    public void loadProductList(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã sản phẩm"); dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Loại sản phẩm"); dtm.addColumn("Thể loại");
        dtm.addColumn("Giá cả"); dtm.addColumn("Ngày xuất bản");
        table.setModel(dtm);
        Vector<productDTO> arr = new Vector<productDTO>();
        arr = productHanle.getAllProduct();
        for(int i=0; i<arr.size(); i++){
            productDTO temp = arr.get(i);
            int maSP = temp.getmaSP(); String tenSP = temp.gettenSP();
            String loaiSP = temp.getloaiSP(); String theloai = temp.gettheloai();
            int giaca = temp.getgiaca(); LocalDate ngayxuatban = temp.getngayxuatban();
            Object[] row = {maSP, tenSP, loaiSP, theloai, giaca + " VND", ngayxuatban};
            dtm.addRow(row);
        }
    }

    public boolean checktf(){
        boolean check = true;
        String tenSP = tenSPtf.getText().trim();
        String loaiSP = loaiSPtf.getText().trim();
        String theloai = theloaitf.getText().trim();
        try{
            int maSP = Integer.parseInt(maSPtf.getText().trim());
            if(maSP <= 1){
                JOptionPane.showMessageDialog(null, "Mã sản phẩm phải lớn hơn 1!");
                return check = false;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Mã sản phẩm phải là số!");
            return check = false;
        }
        if(tenSP == ""){
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống!");
            return check = false;
        }
        if(loaiSP == ""){
            JOptionPane.showMessageDialog(null, "Loại sản phẩm không được để trống!");
            return check = false;
        }
        if(theloai == ""){
            JOptionPane.showMessageDialog(null, "Thể loại không được để trống");
            return check = false;
        }
        try{
            int giaca = Integer.parseInt(giacatf.getText().trim());
            if(giaca <= 0){
                JOptionPane.showMessageDialog(null, "Giá cả phải lớn hơn 0!");
                return check = false;
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Giá cả phải là số!");
            return check = false;
        }
        try{
            LocalDate ngayxuatban = LocalDate.parse(ngayxuatbantf.getText().trim());
            if(ngayxuatban.isAfter(LocalDate.now())){
                JOptionPane.showMessageDialog(null, "Ngày xuất bản không được vượt qua ngày hiện tại!");
                return check = false;
            }

        } catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Ngày xuất bản không đúng định dạng yyyy-MM-dd!");
            return check = false;
        }
        return check;
    }
    
    public void addProductFunction(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               if(checktf()){
                productDTO newprod = new productDTO();
                newprod.setmaSP(Integer.parseInt(maSPtf.getText().trim())); 
                newprod.settenSP(tenSPtf.getText().trim());
                newprod.setloaiSP(loaiSPtf.getText().trim());
                newprod.settheloai(theloaitf.getText().trim());
                newprod.setgiaca(Integer.parseInt(giacatf.getText().trim()));
                newprod.setngayxuatban(LocalDate.parse(ngayxuatbantf.getText().trim()));
                JOptionPane.showMessageDialog(null, productHanle.addProduct(newprod));
                loadProductList();
               }
            }
        });
    }

    public productGUI(){
        interface_setting();
        loadProductList();
        addProductFunction();
    }

    public static void main(String[] args) {
        new productGUI();
    }
}
