package GUI;
import DTO.staffDTO;
import BLL.staffBLL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class staffGUI {
    JFrame frame = new JFrame("Quản lý nhân viên");
    JButton exit = new JButton("Thoát");
    JLabel maNVlb = new JLabel("Mã nhân viên: ");
    JLabel tenNVlb = new JLabel("Tên nhân viên: ");
    JLabel SDTlb = new JLabel("Số điện thoại: ");
    JTextField maNVtf = new JTextField();
    JTextField tenNVtf = new JTextField();
    JTextField SDTtf = new JTextField();
    JButton addBtn = new JButton("Thêm nhân viên");
    JButton updateBtn = new JButton("Cập nhật nhân viên");
    JButton deleteBtn = new JButton("Xóa nhân viên");
    JButton searchBtn = new JButton("Tìm kiếm nhân viên");
    JButton refreshBtn = new JButton("Tải lại");
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane();
    
    staffBLL staffhandle = new staffBLL();

    public void setStaffInterface(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(maNVlb); frame.add(maNVtf); 
        frame.add(tenNVlb); frame.add(tenNVtf);
        frame.add(SDTlb); frame.add(SDTtf);
        frame.add(addBtn); frame.add(updateBtn); frame.add(deleteBtn); frame.add(searchBtn);
        frame.add(exit); frame.add(refreshBtn);

        exit.setBounds(10, 10, 75, 30);
        maNVlb.setBounds(30, 50, 150, 30); maNVtf.setBounds(180, 50, 250, 30);
        tenNVlb.setBounds(500, 50, 150, 30); tenNVtf.setBounds(630, 50, 250, 30);
        SDTlb.setBounds(30, 100, 150, 30); SDTtf.setBounds(180, 100, 250, 30);
        addBtn.setBounds(1000, 50, 150, 30); updateBtn.setBounds(1200, 50, 150, 30);
        deleteBtn.setBounds(1000, 100, 150, 30); searchBtn.setBounds(1200, 100, 150, 30);
        refreshBtn.setBounds(1450, 150, 70, 30); 

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, frame.getWidth() - 70, frame.getHeight() - 300);
        frame.add(scrollPane); 
    }

    public void loadStaffTable(Vector<staffDTO> arr){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã nhân viên"); model.addColumn("Tên nhân viên"); 
        model.addColumn("Số điện thoại");
        table.setModel(model);
        for(int i=0; i<arr.size(); i++){
            staffDTO temp = arr.get(i);
            model.addRow(new Object[]{temp.getmaNV(), temp.gettenNV(), temp.getSDT()});
        }

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                maNVtf.setText(table.getValueAt(row, 0).toString());
                tenNVtf.setText(table.getValueAt(row, 1).toString());
                SDTtf.setText(table.getValueAt(row, 2).toString());
            }
        });
    }

    public Boolean checktf(){
        boolean check = true;
        if(tenNVtf.getText().equals("") || SDTtf.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            return check = false;
        }
        if(!SDTtf.getText().matches("[0-9]+")){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
            return check = false;
        }
        if(SDTtf.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 chữ số!");
            return check = false;
        }  
        return check;
    }

    public void addStaff(){
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(checktf()){
                    staffDTO newstaff = new staffDTO();
                    newstaff.settenNV(tenNVtf.getText());
                    newstaff.setSDT(SDTtf.getText());
                    JOptionPane.showMessageDialog(null, "Mã nhân viên sẽ được tự động cập nhật!");
                    JOptionPane.showMessageDialog(null, staffhandle.addStaff(newstaff));
                    loadStaffTable(staffhandle.getAllStaff());
                }
            }
        });
    }
    
    public void refreshTable(){
        refreshBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadStaffTable(staffhandle.getAllStaff());
                maNVtf.setText(""); tenNVtf.setText(""); SDTtf.setText("");
            }
        });
    }

    public void exit(){
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    frame.dispose();
                }
            }
        });
    }

    public staffGUI(){
        setStaffInterface();
        loadStaffTable(staffhandle.getAllStaff());
        addStaff();
        //updateStaff();
        //deleteStaff();
        //searchStaff();
        refreshTable();
        exit();
    }

    public static void main(String[] args){
        staffGUI staff = new staffGUI();
    }
}
