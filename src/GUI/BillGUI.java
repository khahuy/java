package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.awt.event.*;

import javax.swing.table.*;
import javax.swing.border.EmptyBorder;

import BLL.BillBLL;
import DTO.billDTO;
import DTO.customerDTO;
import DTO.productDTO;

import java.time.format.DateTimeFormatter;

public class BillGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_newMaHD;
	private JTextField textField_soLuong;
	private JTextField textField_search;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JLabel lbThongKe;

	private BillBLL billBLL = new BillBLL();

	DecimalFormat df = new DecimalFormat("#,###");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public BillGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbMaHoaDon = new JLabel("Mã Hóa Đơn");
		lbMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lbMaHoaDon.setBounds(62, 26, 164, 50);
		contentPane.add(lbMaHoaDon);

		textField_newMaHD = new JTextField();
		textField_newMaHD.setBounds(176, 37, 265, 37);
		contentPane.add(textField_newMaHD);
		textField_newMaHD.setColumns(10);
		textField_newMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblTnSnPhm = new JLabel("Tên Sản Phẩm");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 19));

		lblTnSnPhm.setBounds(483, 26, 164, 50);
		contentPane.add(lblTnSnPhm);

		comboBox = new JComboBox();
		comboBox.setBounds(621, 37, 298, 37);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(comboBox);

		ArrayList<productDTO> productList = billBLL.getAllProducts();
		for (productDTO product : productList) {
			comboBox.addItem(product.gettenSP());
		}

		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(1056, 37, 241, 37);
		contentPane.add(textField_soLuong);
		textField_soLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 19));

		lblSLng.setBounds(968, 26, 164, 50);
		contentPane.add(lblSLng);

		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(1356, 30, 134, 50);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (textField_newMaHD.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn!");
					return;
				}
				if ((String) comboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
					return;
				}
				if (textField_soLuong.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng!");
					return;
				}

				try {
					int maHD = Integer.parseInt(textField_newMaHD.getText().trim());
					int soLuong = Integer.parseInt(textField_soLuong.getText().trim());
					String tenSP = (String) comboBox.getSelectedItem();

					String message = billBLL.createBillDetail(maHD, tenSP, soLuong);

					JOptionPane.showMessageDialog(null, message);
					refresh();

				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Mã hóa đơn và Số Lượng phải là số!");
					refresh();
					return;
				}
			}
		});

		textField_search = new JTextField();
		textField_search.setColumns(10);
		textField_search.setBounds(62, 131, 265, 37);
		contentPane.add(textField_search);
		textField_search.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnSearch = new JButton("Tìm Kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(337, 131, 134, 39);
		contentPane.add(btnSearch);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textField_search.getText().trim();
				if (str.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn!");
				} else {
					try {
						int maHD = Integer.parseInt(str);
						ArrayList<billDTO> billList = billBLL.searchBill(maHD);
						updateTable(billList);

						if (billList.isEmpty()) {

							JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn");
							refresh();
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Mã hóa đơn phải là số!");
					}
				}
			}
		});

		JButton btnThongKe = new JButton("Thống Kê\r\n");
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThongKe.setBounds(1333, 125, 157, 50);
		contentPane.add(btnThongKe);

		btnThongKe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTextField textFieldFromMonth = new JTextField();
				JTextField textFieldFromYear = new JTextField();

				JTextField textFieldToMonth = new JTextField();
				JTextField textFieldToYear = new JTextField();

				textFieldFromMonth.setPreferredSize(new Dimension(100, 20));
				textFieldFromYear.setPreferredSize(new Dimension(100, 20));
				textFieldToMonth.setPreferredSize(new Dimension(100, 20));
				textFieldToYear.setPreferredSize(new Dimension(100, 20));

				JPanel panel = new JPanel(new GridLayout(2, 6, 10, 20));
				panel.add(new JLabel("Từ"));
				panel.add(new JLabel(""));

				panel.add(new JLabel("Tháng:"));
				panel.add(textFieldFromMonth);
				panel.add(new JLabel("Năm:"));
				panel.add(textFieldFromYear);

				panel.add(new JLabel("Đến"));
				panel.add(new JLabel(""));

				panel.add(new JLabel("Tháng:"));
				panel.add(textFieldToMonth);
				panel.add(new JLabel("Năm:"));
				panel.add(textFieldToYear);

				int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thời điểm",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					String fromMonth = textFieldFromMonth.getText().trim();
					String fromYear = textFieldFromYear.getText().trim();
					String toMonth = textFieldToMonth.getText().trim();
					String toYear = textFieldToYear.getText().trim();

					if (fromMonth.isEmpty() || fromYear.isEmpty() || toMonth.isEmpty() || toYear.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Cần nhập đầy đủ thông tin!");
						return;
					}

					try {
						int tuThang = Integer.parseInt(fromMonth);
						int tuNam = Integer.parseInt(fromYear);
						int denThang = Integer.parseInt(toMonth);
						int denNam = Integer.parseInt(toYear);
						String mes = billBLL.checkThangNam(tuThang, tuNam, denThang, denNam);
						if (!mes.isEmpty()) {
							JOptionPane.showMessageDialog(null, mes);
							return;
						}
						ArrayList<billDTO> billList = billBLL.getBillsByMonthRange(tuThang, tuNam, denThang, denNam);
						updateTable(billList);
						lbThongKe.setText("Tổng Tiền: " + df.format(billBLL.sumTotalBillAmount(billList)) + "");

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Tháng và năm phải là số!");
					}
				}
			}
		});

		JButton btnDeleteBill = new JButton("Xóa Hóa Đơn");
		btnDeleteBill.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteBill.setBounds(1119, 125, 206, 50);
		contentPane.add(btnDeleteBill);

		btnDeleteBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Nhập mã hóa đơn cần xóa:",
						"Xóa Hóa Đơn",
						JOptionPane.INFORMATION_MESSAGE);

				if (input != null) {
					input = input.trim();

					if (input.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã hóa đơn!");
					} else {
						try {
							int maHD = Integer.parseInt(input);
							boolean success = billBLL.deleteBill(maHD);
							if(success) {
								JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!");
							} else {
								JOptionPane.showMessageDialog(null, "Mã hóa đơn không tồn tại!");
							}
							refresh();
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Mã hóa đơn phải là số!");

						}
					}
				}
			}
		});

		JButton btnUpdateBill = new JButton("Cập Nhật Hóa Đơn");
		btnUpdateBill.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdateBill.setBounds(890, 125, 220, 50);
		contentPane.add(btnUpdateBill);

		btnUpdateBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String input = JOptionPane.showInputDialog(null,
						"Nhập mã hóa đơn cần cập nhật",
						"Cập Nhật Hóa Đơn",
						JOptionPane.INFORMATION_MESSAGE);

				if (input != null) {
					input = input.trim();
					if (input.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã hóa đơn!");
						return;
					}

					int maHD;
					try {
						maHD = Integer.parseInt(input);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Mã hóa đơn phải là số!");
						return;
					}

					String input2 = JOptionPane.showInputDialog(null,
							"Nhập mã khách hàng mới",
							"Cập Nhật Hóa Đơn",
							JOptionPane.INFORMATION_MESSAGE);

					if (input2 != null) {
						input2 = input2.trim();
						if (input2.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khách hàng!");
							return;
						}

						int maKH;
						try {
							maKH = Integer.parseInt(input2);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Mã khách hàng phải là số!");
							return;
						}

						String message = billBLL.updateBillCustomer(maHD, maKH);
						JOptionPane.showMessageDialog(null, message);
						refresh();

					}
				}
			}
		});

		JButton btnNewHD = new JButton("Tạo Hóa Đơn");
		btnNewHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewHD.setBounds(690, 125, 190, 50);
		contentPane.add(btnNewHD);

		btnNewHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null,
						"Nhập Mã Khách Hàng:",
						"Tạo Hóa Đơn",
						JOptionPane.INFORMATION_MESSAGE);

				if (input != null) {
					input = input.trim();

					if (input.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khách hàng!");
					} else {

						try {
							int maKH = Integer.parseInt(input);
							if (billBLL.isCustomerExist(maKH)) {
								boolean success = billBLL.createBill(maKH);
								if (success) {
									JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!");
									updateTable(billBLL.getAllBills());
								} else {
									JOptionPane.showMessageDialog(null, "Tạo hóa đơn thất bại!");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại!");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Mã khách hàng phải là số!");
							return;
						}

					}
				}
			}
		});

		JButton btnRefresh = new JButton("Tải Lại");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefresh.setBounds(1350, 200, 140, 30);
		contentPane.add(btnRefresh);

		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		});

		model = new DefaultTableModel();
		model.addColumn("Mã Hóa Đơn");
		model.addColumn("Mã Khách Hàng");
		model.addColumn("Tên Khách Hàng");
		model.addColumn("Số Điện Thoại");
		model.addColumn("Ngày Mua");
		model.addColumn("Tổng Tiền");

		table = new JTable(model);

		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font("Arial", Font.PLAIN, 15));
				return c;
			}
		});
		ArrayList<billDTO> billList = billBLL.getAllBills();
		updateTable(billList);

		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(62, 234, 1428, 500);
		contentPane.add(scrollPane);

		lbThongKe = new JLabel("");
		contentPane.add(lbThongKe);

		lbThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbThongKe.setBounds(1240, 750, 270, 30);

	}

	public void updateTable(ArrayList<billDTO> billList) {
		ArrayList<customerDTO> customerList = billBLL.getAllCustomers();

		// Định dạng tiền với dấu phẩy và 2 chữ số thập phân
		model.setRowCount(0);

		for (billDTO bill : billList) {
			for (customerDTO customer : customerList) {
				if (bill.getmaKH() == customer.getmaKH()) {
					Object[] row = new Object[6];
					row[0] = bill.getmaHD();
					row[1] = bill.getmaKH();
					row[2] = customer.gettenKH();
					row[3] = customer.getSDT();
					row[4] = bill.getngaymua().format(formatter);
					row[5] = df.format(bill.gettongtien());
					model.addRow(row); // Thêm dòng vào bảng
				}
			}
		}
	}

	public void refresh() {
		ArrayList<billDTO> billList = billBLL.getAllBills();
		updateTable(billList);
		textField_search.setText("");
		textField_newMaHD.setText("");
		textField_soLuong.setText("");
		lbThongKe.setText("");
		comboBox.setSelectedIndex(-1);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					BillGUI frame = new BillGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
