package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
public class BookStore {
    JFrame frame = new JFrame("Quản lý cửa hàng sách");
    JButton exit = new JButton("Thoát");
    JLabel selection = new JLabel("Các chức năng quản lý:");
    JButton customerBtn = new JButton();
    JButton productBtn = new JButton();
    JButton staffBtn = new JButton();
    JButton billBtn = new JButton();

    public void setInterface(){
        frame.setBounds(250, 0, 900, 750);
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(exit); frame.add(selection);
        frame.add(productBtn); frame.add(customerBtn);
        frame.add(staffBtn); frame.add(billBtn);

        exit.setBounds(0, 0, 70, 30);
        selection.setBounds(80, 40, 300, 30);
        selection.setFont(new Font("SansSerif", Font.BOLD, 25));
        customerBtn.setBounds(100, 90, 250, 250);
        productBtn.setBounds(560, 90, 250, 250);
        staffBtn.setBounds(100, 430, 250, 250);
        billBtn.setBounds(560, 430, 250, 250);
        ImageIcon cusImageIcon = new ImageIcon("C:\\Users\\PC\\OneDrive\\Documents\\GitHub\\java\\src\\img\\customer_icon.jpg");
        ImageIcon proImageIcon = new ImageIcon("C:\\Users\\PC\\OneDrive\\Documents\\GitHub\\java\\src\\img\\book_icon.jpg");
        ImageIcon staffImageIcon = new ImageIcon("C:\\Users\\PC\\OneDrive\\Documents\\GitHub\\java\\src\\img\\staff_icon.jpg");
        ImageIcon billImageIcon = new ImageIcon("C:\\Users\\PC\\OneDrive\\Documents\\GitHub\\java\\src\\img\\bill_icon.jpg");
        Image cusImage = cusImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image proImage = proImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image staffImage = staffImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Image billImage = billImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        customerBtn.setIcon(new ImageIcon(cusImage)); customerBtn.setToolTipText("Quản lý khách hàng");
        productBtn.setIcon(new ImageIcon(proImage)); productBtn.setToolTipText("Quản lý sản phẩm");
        staffBtn.setIcon(new ImageIcon(staffImage)); staffBtn.setToolTipText("Quản lý nhân viên");
        billBtn.setIcon(new ImageIcon(billImage)); billBtn.setToolTipText("Quản lý hóa đơn");
    }

    public void manageCustomer(){
        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerGUI();
            }
        });
    }

    public void manageProduct(){
        productBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new productGUI();
            }
        });
    }

    public void manageStaff(){
        staffBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new staffGUI();
            }
        });
    }

    public void manageBill(){
        billBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BillGUI();
            }
        });
    }

    public void exit(){
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public BookStore(){
        setInterface();
        JOptionPane.showMessageDialog(frame, "Chào mừng bạn đến với cửa hàng sách!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        manageCustomer(); manageProduct();
        manageStaff(); manageBill();
        exit();
    }

    public static void main(String[] args){
        BookStore bookStore = new BookStore();
    }
}
