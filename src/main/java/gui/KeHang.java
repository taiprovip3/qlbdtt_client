package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.SanPham;
import util.GetLocalTime;
import util.HibernateUtil;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class KeHang extends JFrame {

	private JPanel contentPane;
	private JTable tblSanPham;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeHang frame = new KeHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KeHang() {
		setTitle("VNSPORT > Client > mua s\u1EAFm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDatHang = new JButton("\u0110\u1EB7t h\u00E0ng");
		btnDatHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = tblSanPham.getSelectedRow();
				if(a != -1)
				{
					String maSP = tblSanPham.getValueAt(a,0).toString();
					String tenSP = tblSanPham.getValueAt(a,1).toString();
					int tonKho = Integer.parseInt(tblSanPham.getValueAt(a,2).toString());
					float dg = Float.parseFloat(tblSanPham.getValueAt(a,3).toString());
					String loai = tblSanPham.getValueAt(a,4).toString();
					String mota = tblSanPham.getValueAt(a,5).toString();
					DatHang dh = new DatHang();
					dh.main(maSP,dg);
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn dòng SP muốn mua!");
				}
			}
		});
		btnDatHang.setBackground(new Color(0, 128, 128));
		btnDatHang.setForeground(Color.GREEN);
		btnDatHang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDatHang.setBounds(10, 335, 122, 37);
		contentPane.add(btnDatHang);
		
		JButton btnBack = new JButton("Quay lại");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu mn = new Menu();
				mn.main(null);
			}
		});
		btnBack.setForeground(new Color(220, 20, 60));
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBackground(new Color(139, 0, 0));
		btnBack.setBounds(697, 335, 122, 37);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 809, 313);
		contentPane.add(scrollPane);
		
		tblSanPham = new JTable();
		tblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblSanPham.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 SP", "T\u00EAn SP", "Kho c\u00F2n", "\u0110\u01A1n gi\u00E1", "Lo\u1EA1i h\u00E0ng", "M\u00F4 t\u1EA3"
			}
		));
		scrollPane.setViewportView(tblSanPham);
		
		JButton btnXemChiTit = new JButton("Xem chi ti\u1EBFt");
		btnXemChiTit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = tblSanPham.getSelectedRow();
				if(a != -1)
				{
					JOptionPane.showMessageDialog(contentPane, "!!!!!");
				}else
					JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn dòng SP cần xem");
			}
		});
		btnXemChiTit.setForeground(Color.GREEN);
		btnXemChiTit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXemChiTit.setBackground(new Color(0, 128, 128));
		btnXemChiTit.setBounds(152, 335, 122, 37);
		contentPane.add(btnXemChiTit);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(565, 335, 90, 14);
		contentPane.add(lblTime);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(565, 358, 90, 14);
		contentPane.add(lblDate);
		//Codey tay
		GetLocalTime getLocalTime = new GetLocalTime(lblDate, lblTime);
		getLocalTime.showTime();
		getLocalTime.showDate();
		loadDataToTable();
	}
	private void loadDataToTable() {
		SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
				List<SanPham> lsSanPhams = new ArrayList<SanPham>();
				tr.begin();
				lsSanPhams = session.createNativeQuery("select * from SanPham", SanPham.class).list();
				tr.commit();
				for (Iterator iterator = lsSanPhams.iterator(); iterator.hasNext();) {
					SanPham sanPham = (SanPham) iterator.next();
					DefaultTableModel tblModelSanPham = (DefaultTableModel) tblSanPham.getModel();
					tblModelSanPham.addRow(new Object[] {
						sanPham.getMaSanPham(),
						sanPham.getTenSanPham(),
						sanPham.getSoLuongTon(),
						sanPham.getDonGia(),
						sanPham.getPhanLoai().getTenPhanLoai(),
						sanPham.getMoTa()
					});
				}
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}
}
