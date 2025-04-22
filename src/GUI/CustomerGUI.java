package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DTO.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CustomerGUI {
    DefaultTableModel model;
    JTable table;
    JTextField T_name, T_phone, searchField;
    JFrame f;
    private String oldPhone = null;

    private void showMessage(String message, int type) {
        JOptionPane.showMessageDialog(f, message, "Thông báo", type);
    }

    private void resetForm() {
        T_name.setText("");
        T_phone.setText("");
    }

    // private void validateCustomerInput(String ten, String sdt) throws
    // ValidationException {
    // if (ten == null || ten.trim().isEmpty()) {
    // throw new ValidationException("Tên khách hàng không được để trống!");
    // }
    // if (ten.length() > 150) {
    // throw new ValidationException("Tên khách hàng quá dài (tối đa 150 ký tự).");
    // }

    // if (sdt == null || sdt.trim().isEmpty()) {
    // throw new ValidationException("Số điện thoại không được để trống!");
    // }
    // if (!sdt.matches("\\d{10}")) {
    // throw new ValidationException("Số điện thoại không hợp lệ (phải đủ 10 chữ số
    // và chỉ chứa số).");
    // }
    // }

    private void loadCustomersToTable() {
        model.setRowCount(0);
        CustomerBLL customerBLL = new CustomerBLL();
        for (customerDTO customer : customerBLL.getAllCustomers()) {
            model.addRow(new Object[] {
                    customer.getmaKH(),
                    customer.gettenKH(),
                    customer.getSDT()
            });
        }
    }

    public CustomerGUI() {
        f = new JFrame("Quản lí khách hàng");
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(800, 600);

        JLabel name = new JLabel("Tên khách hàng: ");
        JLabel phone = new JLabel("Số điện thoại: ");
        T_name = new JTextField(50);
        T_phone = new JTextField(50);
        name.setBounds(20, 20, 150, 30);
        T_name.setBounds(170, 20, 300, 30);
        phone.setBounds(20, 60, 150, 30);
        T_phone.setBounds(170, 60, 300, 30);
        f.add(name);
        f.add(T_name);
        f.add(phone);
        f.add(T_phone);

        JButton addButton = new JButton("Thêm khách hàng");
        JButton updateButton = new JButton("Cập nhật thông tin");
        JButton deleteButton = new JButton("Xóa khách hàng");
        JButton search = new JButton("Tìm kiếm");
        JButton clearInput = new JButton("Xóa nội dung");
        JButton clearSearch = new JButton("Hủy bỏ");
        searchField = new JTextField(20);

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.add(clearInput);
        bar.add(addButton);
        bar.add(updateButton);
        bar.add(deleteButton);
        bar.add(search);
        bar.add(searchField);
        bar.add(clearSearch);
        searchField.setPreferredSize(new Dimension(200, 27));
        bar.setBounds(20, 150, 1200, 40);
        bar.setBorder(BorderFactory.createEmptyBorder(0, -4, 0, 0));
        f.add(bar);

        String[] columnNames = { "ID", "Tên khách hàng", "Số điện thoại" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        loadCustomersToTable();

        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setRowHeight(30);
        tableScrollPane.setBounds(20, 200, f.getWidth() - 50, f.getHeight() - 250);
        f.add(tableScrollPane);

        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                tableScrollPane.setBounds(20, 200, f.getWidth() - 50, f.getHeight() - 250);
                bar.setBounds(20, 150, f.getWidth() - 50, 40);
            }
        });

        // đỡ được 1 chút :v nếu thao tác lỗi thì vẫn hiện chữ đen bên dướidưới
        SwingUtilities.invokeLater(() -> {
            addButton.repaint();
            updateButton.repaint();
            deleteButton.repaint();
            search.repaint();
        });

        // tắt cái mặc định của nónó
        table.setDefaultEditor(Object.class, null);

        // tự vẽ nên cái bản theo ý muốn
        table.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value != null ? value.toString() : "");
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setBorder(new EmptyBorder(0, 5, 0, 0));
            label.setOpaque(true);
            label.setBackground(isSelected ? Color.RED : table.getBackground());
            label.setForeground(isSelected ? Color.WHITE : table.getForeground());
            return label;
        });

        // click vào 1 dòng sẽ hiện lên trên khung chat
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                T_name.setText(model.getValueAt(row, 1).toString());
                T_phone.setText(model.getValueAt(row, 2).toString());
                oldPhone = model.getValueAt(row, 2).toString();
            }
        });

        addButton.addActionListener(e -> {
            String ten = T_name.getText().trim();
            String sdt = T_phone.getText().trim();
            CustomerBLL bll = new CustomerBLL();
            try {
                bll.validateCustomerInput(ten, sdt);
                customerDTO customer = new customerDTO();
                customer.settenKH(ten);
                customer.setSDT(sdt);
                Result result = bll.addCustomer(customer);
                showMessage(result.getMessage(), JOptionPane.INFORMATION_MESSAGE);
                if (result.isSuccess()) {
                    loadCustomersToTable();
                    resetForm();
                }
            } catch (ValidationException ex) {
                showMessage(ex.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Vui lòng chọn một khách hàng để cập nhật.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String ten = T_name.getText().trim();
            String sdt = T_phone.getText().trim();
            int id = (int) model.getValueAt(selectedRow, 0);

            customerDTO customer = new customerDTO();
            customer.setmaKH(id);
            customer.settenKH(ten);
            customer.setSDT(sdt);

            CustomerBLL bll = new CustomerBLL();
            if (!sdt.equals(oldPhone)) {
                if (bll.isPhoneExist(sdt)) {
                    showMessage("Số điện thoại đã tồn tại ở khách hàng khác.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Result result = bll.updateCustomer(customer);
            showMessage(result.getMessage(), JOptionPane.INFORMATION_MESSAGE);
            if (result.isSuccess()) {
                loadCustomersToTable();
                resetForm();
                oldPhone = null;
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Vui lòng chọn khách hàng để xóa.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(f, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION)
                return;
            int id = (int) model.getValueAt(selectedRow, 0);
            boolean success = new CustomerBLL().deleteCustomer(id);
            showMessage(success ? "Xóa thành công!" : "Xóa thất bại!", JOptionPane.INFORMATION_MESSAGE);
            if (success) {
                loadCustomersToTable();
                resetForm();
            }
        });

        search.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (keyword.isEmpty()) {
                loadCustomersToTable();
                return;
            }
            List<customerDTO> results = new CustomerBLL().searchCustomersByName(keyword);
            model.setRowCount(0);
            for (customerDTO customer : results) {
                model.addRow(new Object[] {
                        customer.getmaKH(),
                        customer.gettenKH(),
                        customer.getSDT()
                });
            }
            if (model.getRowCount() == 0) {
                showMessage("Không tìm thấy khách hàng nào với tên: " + keyword, JOptionPane.INFORMATION_MESSAGE);
                searchField.setText("");
                loadCustomersToTable();
            } else {
                showMessage("Tìm thấy " + model.getRowCount() + " khách hàng.", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearInput.addActionListener(e -> resetForm());
        clearSearch.addActionListener(e -> {
            searchField.setText("");
            loadCustomersToTable();
        });

        // click ngoài table thì sẽ bỏ chọn dòng đang chọn
        f.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (table.getSelectedRow() != -1 &&
                        !table.getBounds().contains(SwingUtilities.convertPoint(f, e.getPoint(), table))) {
                    table.clearSelection();
                    resetForm();
                }
            }
        });

        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerGUI());
    }
}
/*
 * 1. pick cus có sẵn xong cập nhật là nó không check điều kiện nữa. => check
 * điều kiện cho update
 * 2. xóa cus nhưng nó để lại viền :v (done)
 * 3. Vẫn còn chữ đen bên dưới các nút :v
 * 4. Cố dùng Layout
 * 5. ơ tự nhiên xóa cus ko được nữa. check lại xem. (done)
 */