package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;
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
    JLabel soluonglb = new JLabel("Số lượng: ");
    JTextField soluongtf = new JTextField();
    JLabel ngayxuatbanlb = new JLabel("Ngày xuất bản: ");
    JTextField ngayxuatbantf = new JTextField();
    JLabel ngaynhapkholb = new JLabel("Ngày nhập kho: ");
    JTextField ngaynhapkhotf = new JTextField();
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    JButton addBtn = new JButton("Thêm sản phẩm");
    JButton updateBtn = new JButton("Cập nhật sản phẩm");
    JButton deleteBtn = new JButton("Xóa sản phẩm");
    JButton searchBtn = new JButton("Tìm kiếm sản phẩm");
    JButton refreshBtn = new JButton("Tải lại");
    String filterstr[] = {"Thể loại", "Loại sản phẩm"};
    JLabel filterlb =  new JLabel("Sắp xếp:");
    JComboBox filtercb = new JComboBox(filterstr);
    JLabel numstatiticlb = new JLabel("Thống kê hàng nhập trong tháng: ");
    String months[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String years[] = {"2022", "2023", "2024", "2025"};
    JComboBox mstatiticcb = new JComboBox(months);
    JComboBox ystatiticcb = new JComboBox(years); 
    JButton ok = new JButton("OK");
    JButton exit = new JButton("Thoát");

    productBLL productHandle = new productBLL();

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
        frame.add(soluonglb); frame.add(soluongtf);
        frame.add(ngayxuatbanlb); frame.add(ngayxuatbantf);
        frame.add(ngaynhapkholb); frame.add(ngaynhapkhotf);
        frame.add(filterlb); frame.add(filtercb);
        frame.add(numstatiticlb); frame.add(mstatiticcb); frame.add(ystatiticcb); frame.add(ok); frame.add(exit);
        exit.setBounds(10, 10, 75, 30);
        maSPlb.setBounds(30, 50, 150, 30); maSPtf.setBounds(180, 50, 250, 30);
        tenSPlb.setBounds(500, 50, 150, 30); tenSPtf.setBounds(630, 50, 250, 30);
        loaiSPlb.setBounds(30, 100, 150, 30); loaiSPtf.setBounds(180, 100, 250, 30);
        theloailb.setBounds(500, 100, 150, 30); theloaitf.setBounds(630, 100, 250, 30);
        giacalb.setBounds(30, 150, 150, 30); giacatf.setBounds(180, 150, 250, 30);
        soluonglb.setBounds(500, 150, 150, 30); soluongtf.setBounds(630, 150, 250, 30);
        ngayxuatbanlb.setBounds(30, 200, 150, 30); ngayxuatbantf.setBounds(180, 200, 250, 30);
        ngaynhapkholb.setBounds(500, 200, 150, 30); ngaynhapkhotf.setBounds(630, 200, 250, 30);
        frame.add(addBtn); frame.add(updateBtn); frame.add(deleteBtn); frame.add(searchBtn); frame.add(refreshBtn); 
        addBtn.setBounds(1000, 50, 150, 30); updateBtn.setBounds(1200, 50, 150, 30);
        deleteBtn.setBounds(1000, 100, 150, 30); searchBtn.setBounds(1200, 100, 150, 30);
        refreshBtn.setBounds(1450, 200, 70, 30); 
        filterlb.setBounds(1000, 150, 70, 30); filtercb.setBounds(1080, 150, 100, 30);
        numstatiticlb.setBounds(1000, 200, 200, 30); 
        mstatiticcb.setBounds(1210, 200, 40, 30); ystatiticcb.setBounds(1260, 200, 65, 30);
        ok.setBounds(1340, 200, 55, 30);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, frame.getWidth() - 70, frame.getHeight() - 300);
        frame.add(scrollPane);
    }

    public void loadProductList(Vector<productDTO> arr){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã sản phẩm"); dtm.addColumn("Tên sản phẩm");
        dtm.addColumn("Loại sản phẩm"); dtm.addColumn("Thể loại");
        dtm.addColumn("Giá cả"); dtm.addColumn("Số lượng");
        dtm.addColumn("Ngày xuất bản"); dtm.addColumn("Ngày nhập kho");
        table.setModel(dtm);
        for(int i=0; i<arr.size(); i++){
            productDTO temp = arr.get(i);
            int maSP = temp.getmaSP(); String tenSP = temp.gettenSP();
            String loaiSP = temp.getloaiSP(); String theloai = temp.gettheloai();
            int giaca = temp.getgiaca(); int soluong = temp.getsoluong();
            LocalDate ngayxuatban = temp.getngayxuatban(); LocalDate ngaynhapkho = temp.getngaynhapkho();
            Object[] row = {maSP, tenSP, loaiSP, theloai, giaca + " VND", soluong, ngayxuatban, ngaynhapkho};
            dtm.addRow(row);
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int i =table.getSelectedRow();
                if(i >= 0){
                    maSPtf.setText(dtm.getValueAt(i, 0).toString());
                    tenSPtf.setText(dtm.getValueAt(i, 1).toString());
                    loaiSPtf.setText(dtm.getValueAt(i, 2).toString());
                    theloaitf.setText(dtm.getValueAt(i, 3).toString());
                    giacatf.setText(dtm.getValueAt(i, 4).toString().replace(" VND", ""));
                    soluongtf.setText(dtm.getValueAt(i,5).toString());
                    ngayxuatbantf.setText(dtm.getValueAt(i, 6).toString());
                    ngaynhapkhotf.setText(dtm.getValueAt(i, 7).toString());
                }
            }
        });
    }

    public boolean checkAddtf(){
        boolean check = true;
        String tenSP = tenSPtf.getText().trim();
        String loaiSP = loaiSPtf.getText().trim(); 
        String theloai = theloaitf.getText().trim();
        int giaca, soluong;
        LocalDate ngayxuatban;
        LocalDate ngaynhapkho;
        
        if(tenSP.isEmpty()){
            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống!");
            return check = false;
        }
        if(loaiSP.isEmpty()){
            JOptionPane.showMessageDialog(null, "Loại sản phẩm không được để trống!");
            return check = false;
        }
        if(theloai.isEmpty()){
            JOptionPane.showMessageDialog(null, "Thể loại không được để trống");
            return check = false;
        }
        try{
            giaca = Integer.parseInt(giacatf.getText().trim());
            if(giaca <= 0){
                JOptionPane.showMessageDialog(null, "Giá cả phải lớn hơn 0!");
                return check = false;
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Giá cả phải là số! Không được để trống!");
            return check = false;
        }
        try{
            soluong = Integer.parseInt(soluongtf.getText().trim());
            if(soluong <= 0){
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
                return check = false;
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Số lượng phải là số! Không được để trống!");
            return check = false;
        }
        try{
            ngayxuatban = LocalDate.parse(ngayxuatbantf.getText().trim());
            if(ngayxuatban.isAfter(LocalDate.now())){
                JOptionPane.showMessageDialog(null, "Ngày xuất bản không được vượt qua ngày hiện tại!");
                return check = false;
            }

        } catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Ngày xuất bản không đúng định dạng yyyy-MM-dd! Không để trống!");
            return check = false;
        }
        try{
            ngaynhapkho = LocalDate.parse(ngaynhapkhotf.getText().trim());
            if(ngaynhapkho.isAfter(LocalDate.now()) || ngaynhapkho.isBefore(ngayxuatban)){
                JOptionPane.showMessageDialog(null, "Ngày nhập kho không được vượt qua ngày hiện tại và ngày xuất bản!");
                return check = false;
            }

        } catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Ngày nhập kho không đúng định dạng yyyy-MM-dd! Không để trống!");
            return check = false;
        }
        return check;
    }

    public void addProductFunction(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               if(checkAddtf()){
                productDTO newprod = new productDTO();
                newprod.settenSP(tenSPtf.getText().trim());
                newprod.setloaiSP(loaiSPtf.getText().trim());
                newprod.settheloai(theloaitf.getText().trim());
                newprod.setgiaca(Integer.parseInt(giacatf.getText().trim()));
                newprod.setsoluong(Integer.parseInt(soluongtf.getText().trim()));
                newprod.setngayxuatban(LocalDate.parse(ngayxuatbantf.getText().trim()));
                newprod.setngaynhapkho(LocalDate.parse(ngaynhapkhotf.getText().trim()));
                JOptionPane.showMessageDialog(null, "Mã sản phẩm sẽ được tự động thêm vào!");
                JOptionPane.showMessageDialog(null, productHandle.addProduct(newprod));
                loadProductList(productHandle.getAllProduct());
               }
            }
        });
    }

    public void delProductFunction(){
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, productHandle.delProduct(Integer.parseInt(maSPtf.getText().trim())));
                loadProductList(productHandle.getAllProduct());
            }
        });
    }

    public void updateProductFunction(){
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                productDTO newprod = new productDTO();
                newprod.setmaSP(Integer.parseInt(maSPtf.getText().trim()));
                newprod.settenSP(tenSPtf.getText().trim());
                newprod.setloaiSP(loaiSPtf.getText().trim());
                newprod.settheloai(theloaitf.getText().trim());
                newprod.setgiaca(Integer.parseInt(giacatf.getText().trim()));
                newprod.setsoluong(Integer.parseInt(soluongtf.getText().trim()));
                newprod.setngayxuatban(LocalDate.parse(ngayxuatbantf.getText().trim()));
                newprod.setngaynhapkho(LocalDate.parse(ngaynhapkhotf.getText().trim()));
                JOptionPane.showMessageDialog(null, productHandle.updateProduct(newprod));
                loadProductList(productHandle.getAllProduct());
            }
        });
    }

    public void searchProductFunction(){
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String prodname = tenSPtf.getText().trim();
                if(!prodname.equals("")){
                    Vector<productDTO> products = productHandle.searchProduct(prodname);
                    if(!products.isEmpty()){
                        loadProductList(products);
                    } else{
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào phù hợp!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy nhập tên sản phẩm để tìm kiếm!");
                }
            }
        });
    }

    public void filterProductFunction(){
        filtercb.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Vector<productDTO> result = new Vector<productDTO>(); 
                if(filtercb.getSelectedItem().equals("Thể loại")){
                    String tempstr = theloaitf.getText();
                    if(tempstr.equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập thể loại để sắp xếp");
                    } else {
                        Vector<productDTO> temparr = productHandle.getAllProduct();
                        for(int i=0; i<temparr.size(); i++){
                            if(temparr.get(i).gettheloai().equals(tempstr))
                                result.add(temparr.get(i));
                        }
                        loadProductList(result);
                    }
                }
                else if(filtercb.getSelectedItem().equals("Loại sản phẩm")){
                    String tempstr = loaiSPtf.getText();
                    if(tempstr.equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập loại sản phẩm để sắp xếp");
                    } else{
                        Vector<productDTO> temparr = productHandle.getAllProduct();
                        for(int i=0; i<temparr.size(); i++){
                            if(temparr.get(i).getloaiSP().equals(tempstr))
                                result.add(temparr.get(i));
                        }
                        loadProductList(result);
                    }
                }
            }
        });
    }

    public void refreshTable(){
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductList(productHandle.getAllProduct());
            }
        });;
    }

    public void statiticFunction(){
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String month = mstatiticcb.getSelectedItem().toString();
                String year = ystatiticcb.getSelectedItem().toString();
                Vector<productDTO> arr = productHandle.statiticProduct(month, year);
                if(!arr.isEmpty()){
                    loadProductList(arr);
                } else {
                    JOptionPane.showMessageDialog(null, "Không có sản phẩm nào được nhập kho vào tháng " +month+" năm "+year);
                }
            }
        });
    }

    public void exit(){
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int ques = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát chức năng quản lý sản phẩm?",  "Question", JOptionPane.YES_NO_OPTION);
                if(ques == JOptionPane.YES_OPTION){
                    frame.dispose();
                }
            }
        });
    }

    public productGUI(){
        interface_setting();
        loadProductList(productHandle.getAllProduct());
        addProductFunction();
        delProductFunction();
        updateProductFunction();
        searchProductFunction();
        filterProductFunction();
        statiticFunction();
        refreshTable();
        exit();
    }

    public static void main(String[] args) {
        new productGUI();
    }
}
