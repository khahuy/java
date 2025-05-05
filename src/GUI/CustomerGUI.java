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
    private JFrame f;
    private JTable table;
    private DefaultTableModel model;
    private JTextField T_name, T_phone, searchField;
    private JButton addButton, updateButton, deleteButton, searchButton, clearInputButton, clearSearchButton, lovisong;
    private String oldPhone = null;
    private int soluong = 0;
    private JLabel soluonglabel;
    JComboBox<String> month, year;

    public CustomerGUI() {
        initUI();
        initListeners();
        initData();
    }

    /* ---------- 1. Giao diện ---------- */
    private void initUI() {
        f = new JFrame("Quản lí khách hàng");
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setResizable(false);

        // padding left - right cho thằng frame :V
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Panel nhập liệu
        JPanel TopPanel = new JPanel(new GridLayout(1, 2));
        TopPanel.setPreferredSize(new Dimension(0, 120));

        Font fontI = new Font("Arial", Font.ITALIC, 24);
        Font fontP = new Font("Arial", Font.PLAIN, 24);

        JPanel leftFormPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Tên
        JLabel nameLabel = new JLabel("Tên khách hàng:");
        nameLabel.setFont(fontI);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        leftFormPanel.add(nameLabel, gbc);
        // TextField Tên
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        T_name = new JTextField(20);
        T_name.setFont(fontP);
        leftFormPanel.add(T_name, gbc);
        // số điện thoại
        JLabel phoneLabel = new JLabel("Số điện thoại:");
        phoneLabel.setFont(fontI);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.4;
        leftFormPanel.add(phoneLabel, gbc);
        // TextField Số điện thoại
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.6;
        T_phone = new JTextField(20);
        T_phone.setFont(fontP);
        leftFormPanel.add(T_phone, gbc);

        // đặt size cho textfield
        T_name.setPreferredSize(new Dimension(300, 30));
        T_phone.setPreferredSize(new Dimension(300, 30));

        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        // nút làm sạch ô inputinput
        clearInputButton = new JButton("Tải lại dữ liệu <3");
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(clearInputButton, g);
        // JCombobox
        month = new JComboBox<>(new String[] {
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        });
        year = new JComboBox<>(new String[] { "2025", "2024", "2023", "2022", "2021" });
        soluonglabel = new JLabel("Số lượng: ");
        lovisong = new JButton("Thoát");

        g.gridx = 2;
        g.gridy = 0;
        g.gridwidth = 1;
        rightPanel.add(lovisong, g);

        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        rightPanel.add(month, g);

        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 1;
        rightPanel.add(year, g);

        g.gridx = 2;
        g.gridy = 1;
        g.gridwidth = 1;
        rightPanel.add(soluonglabel, g);

        month.setPreferredSize(new Dimension(200, 30));
        year.setPreferredSize(new Dimension(300, 30));

        TopPanel.add(leftFormPanel);
        TopPanel.add(rightPanel);
        mainPanel.add(TopPanel, BorderLayout.NORTH);

        // Bảng hiển thị danh sách khách hàng
        String[] columnNames = { "ID", "Tên khách hàng", "Số điện thoại" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setDefaultEditor(Object.class, null); // Không user chỉnh sửa trực tiếp trên bảng
        table.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value != null ? value.toString() : "");
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setBorder(new EmptyBorder(0, 5, 0, 0));
            label.setOpaque(true);
            label.setBackground(isSelected ? Color.RED : table.getBackground());
            label.setForeground(isSelected ? Color.WHITE : table.getForeground());
            return label;
        });
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel chứa các nút và trường tìm kiếm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        addButton = new JButton("Thêm khách hàng");
        updateButton = new JButton("Cập nhật thông tin");
        deleteButton = new JButton("Xóa khách hàng");
        searchButton = new JButton("Tìm kiếm");
        clearSearchButton = new JButton("Hủy bỏ");
        searchField = new JTextField(30);
        searchField.setPreferredSize(new Dimension(300, 25));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(searchField);
        buttonPanel.add(clearSearchButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        f.add(mainPanel, BorderLayout.CENTER);
        f.setVisible(true);
    }

    /* ---------- 2. Xử Lý Sự Kiện <3 ---------- */
    private void initListeners() {
        addButton.addActionListener(e -> addCustomer());
        updateButton.addActionListener(e -> updateCustomer());
        deleteButton.addActionListener(e -> deleteCustomer());
        searchButton.addActionListener(e -> searchCustomer());
        clearInputButton.addActionListener(e -> {
            resetForm();
            loadCustomersToTable();
        });
        clearSearchButton.addActionListener(e -> {
            searchField.setText("");
            loadCustomersToTable();
        });
        lovisong.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(f, "Bạn có chắc chắn muốn thoát không?", "Question", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                f.dispose();
                new BookStore(); 
            }
        });

        month.addItemListener(e -> updateCustomerTable());
        year.addItemListener(e -> updateCustomerTable());

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                T_name.setText(model.getValueAt(row, 1).toString());
                T_phone.setText(model.getValueAt(row, 2).toString());
                oldPhone = model.getValueAt(row, 2).toString();
            }
        });

        f.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (table.getSelectedRow() != -1 &&
                        !table.getBounds().contains(SwingUtilities.convertPoint(f, e.getPoint(), table))) {
                    table.clearSelection();
                    resetForm();
                }
            }
        });
    }

    /* ---------- 3. Dữ liệu và chức năng ---------- */
    private void initData() {
        loadCustomersToTable();
    }

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

    private void resetForm() {
        T_name.setText("");
        T_phone.setText("");
    }

    private void addCustomer() {
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
    }

    private void updateCustomer() {
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
    }

    private void deleteCustomer() {
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
    }

    private void searchCustomer() {
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
            showMessage("Không tìm thấy khách hàng nào với " + keyword, JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            loadCustomersToTable();
        } else {
            showMessage("Tìm thấy " + model.getRowCount() + " khách hàng.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateCustomerTable() {
        int selectedMonth = month.getSelectedIndex() + 1;
        int selectedYear = Integer.parseInt((String) year.getSelectedItem());

        CustomerBLL customerBLL = new CustomerBLL();
        List<customerDTO> customers = customerBLL.getCustomersByMonthAndYear(selectedMonth, selectedYear);

        model.setRowCount(0);

        for (customerDTO customer : customers) {
            model.addRow(new Object[] {
                    customer.getmaKH(),
                    customer.gettenKH(),
                    customer.getSDT()
            });
        }
        soluong = customers.size();
        soluonglabel.setText("Số lượng: "+ soluong);
    }

    private void showMessage(String message, int type) {
        JOptionPane.showMessageDialog(f, message, "Thông báo", type);
    }

    /* ---------- 4. Main ---------- */
    public static void main(String[] args) {
        new CustomerGUI();
    }
}
// cài đặt chiều rộng chiều caocao
// tableScrollPane.setPreferredSize(new Dimension(500, 300));
// đặt giới hạnhạn
// tableScrollPane.setMinimumSize(new Dimension(0, 300));
