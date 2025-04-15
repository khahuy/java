package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    JTable table;
    JScrollPane scrollPane;
    JButton addBtn = new JButton("Thêm sản phẩm");
    JButton updateBtn = new JButton("Cập nhật sản phẩm");
    JButton deleteBtn = new JButton("Xóa sản phẩm");
    JButton searchBtn = new JButton("Tìm kiếm sản phẩm");

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

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Thể loại", "Giá", "Ngày xuất bản"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, frame.getWidth() - 70, frame.getHeight() - 300);
        frame.add(scrollPane);
    }

    public productGUI(){
        interface_setting();
    }

    public static void main(String[] args) {
        new productGUI();
    }
}
