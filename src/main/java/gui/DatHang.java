package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.DonMuaDao;
import entity.SanPham;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class DatHang extends JFrame {

	private JPanel contentPane;
	private JTextField txtSoLuong, txtMaSanPham;
	private JComboBox cbThanhToan;
	private JTextField txtMaSanPham_2;
	private static String maSPField;
	private static float dgField;
	private JTextField txtDonGia;

	/**
	 * Launch the application.
	 */
	public static void main(final String maSP, final float dg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maSPField = maSP;
					dgField = dg;
					DatHang frame = new DatHang();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatHang() {
		setTitle("VNSPORT > k\u1EC7 h\u00E0ng > \u0111\u1EB7t h\u00E0ng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuong.setBounds(10, 95, 255, 30);
		contentPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng mu\u1ED1n mua:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 66, 255, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnThanhToan = new JButton("\u0110\u1EB7t h\u00E0ng");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSoLuong.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập số lượng");
				else {
					String maSanPham = txtMaSanPham_2.getText();
					int sl = Integer.parseInt(txtSoLuong.getText());
					String tt = cbThanhToan.getSelectedItem().toString();
					Float dg = Float.parseFloat(txtDonGia.getText());
					float thanhTien = dg*sl;
					int cf = JOptionPane.showConfirmDialog(contentPane, "Sẽ tốn "+thanhTien+", có muốn tiếp tục?");
					if(cf == JOptionPane.YES_OPTION)
					{
						try {
							SecurityManager securityManager = System.getSecurityManager();
							if(securityManager == null) {
								System.setProperty("java.security.policy", "rmi/policy.policy");
								System.setSecurityManager(new SecurityManager());
							}
							DonMuaDao donMuaDao = (DonMuaDao) Naming.lookup("rmi://192.168.1.8:1090/donMuaDao");
							boolean a = donMuaDao.themDonMua(maSanPham, sl, dg);
							if(a)
								JOptionPane.showMessageDialog(contentPane, "Đặt đơn thành công,");
							else
								JOptionPane.showMessageDialog(contentPane, "Đặt hàng thất bại, lỗi kxd!");
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(contentPane, "Lỗi kxd lv.2");
						}
					}//end Nếu bấm OK joption
				}//end Else check rỗng
			}
		});
		btnThanhToan.setForeground(new Color(0, 255, 0));
		btnThanhToan.setBackground(new Color(0, 128, 0));
		btnThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThanhToan.setBounds(10, 202, 109, 36);
		contentPane.add(btnThanhToan);
		
		cbThanhToan = new JComboBox();
		cbThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cbThanhToan.setModel(new DefaultComboBoxModel(new String[] {"Thanh to\u00E1n khi nh\u1EADn h\u00E0ng", "Thanh to\u00E1n online (b\u1EA3o tr\u00EC)"}));
		cbThanhToan.setBounds(10, 158, 255, 33);
		contentPane.add(cbThanhToan);
		
		JLabel lblChnHnhThc = new JLabel("Ch\u1ECDn h\u00ECnh th\u1EE9c thanh to\u00E1n:");
		lblChnHnhThc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblChnHnhThc.setBounds(10, 131, 255, 23);
		contentPane.add(lblChnHnhThc);
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeHang kh = new KeHang();
				kh.main(null);
				dispose();
			}
		});
		btnBack.setForeground(new Color(255, 0, 0));
		btnBack.setBackground(new Color(165, 42, 42));
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBounds(262, 202, 109, 36);
		contentPane.add(btnBack);
		
		JLabel lblMSp = new JLabel("Mã SP:");
		lblMSp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMSp.setBounds(10, 0, 54, 23);
		contentPane.add(lblMSp);
		
		txtMaSanPham_2 = new JTextField();
		txtMaSanPham_2.setForeground(Color.RED);
		txtMaSanPham_2.setEditable(false);
		txtMaSanPham_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaSanPham_2.setColumns(10);
		txtMaSanPham_2.setBounds(10, 29, 255, 30);
		contentPane.add(txtMaSanPham_2);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDonGia.setForeground(new Color(255, 0, 0));
		txtDonGia.setEditable(false);
		txtDonGia.setBounds(275, 27, 96, 30);
		contentPane.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblnGi = new JLabel("Đơn Giá:");
		lblnGi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblnGi.setBounds(275, 0, 96, 23);
		contentPane.add(lblnGi);
		setSanPham();
	}

	private void setSanPham() {
		txtMaSanPham_2.setText(maSPField);
		txtDonGia.setText(String.valueOf(dgField));
	}
}
