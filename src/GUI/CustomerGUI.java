package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomerGUI {
	public CustomerGUI() {
		JFrame f = new JFrame("Quản lí khách hàng");
		f.setLayout(null); 

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel id = new JLabel("Mã khách hàng: ");
		JLabel name = new JLabel("Tên khách hàng: ");
		JLabel phone = new JLabel("Số điện thoại: ");
		JTextField T_id = new JTextField(50);
		JTextField T_name = new JTextField(50);
		JTextField T_phone = new JTextField(50);


		id.setBounds(10, 10, 150, 30); 
		T_id.setBounds(170, 10, 200, 30);

		name.setBounds(10, 50, 150, 30);
		T_name.setBounds(170, 50, 200, 30);

		phone.setBounds(10, 90, 150, 30);
		T_phone.setBounds(170, 90, 200, 30);

		f.add(id);
		f.add(T_id);
		f.add(name);
		f.add(T_name);
		f.add(phone);
		f.add(T_phone);
		String[] columnNames = { "ID", "Tên khách hàng", "Số điện thoại", "Email" };
		Object[][] data = {
				{ "1", "Nguyễn Văn A", "0123456789", "a@gmail.com" },
				{ "2", "Trần Thị B", "0987654321", "b@gmail.com" }
		};
		JTable table = new JTable(data, columnNames);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(10, 130, 560, 200);
		table.setRowHeight(40);

		f.add(tableScrollPane);

		f.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {tableScrollPane.setBounds(20, 130, f.getWidth() - 50, 200); }
		});
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new CustomerGUI();
	}
}
