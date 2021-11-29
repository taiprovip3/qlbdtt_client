package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.GetLocalTime;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Mua s\u1EAFm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeHang kh = new KeHang();
				kh.main(null);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.setForeground(new Color(124, 252, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 11, 119, 42);
		contentPane.add(btnNewButton);
		
		JButton btnChatViServer = new JButton("Chat v\u1EDBi Server");
		btnChatViServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoxChatClient bcc;
				try {
					bcc = new BoxChatClient();
					bcc.main(null);
					dispose();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnChatViServer.setForeground(new Color(124, 252, 0));
		btnChatViServer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnChatViServer.setBackground(new Color(60, 179, 113));
		btnChatViServer.setBounds(139, 11, 160, 42);
		contentPane.add(btnChatViServer);
		
		JButton btnDong = new JButton("\u0110\u00F3ng");
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnDong.setForeground(new Color(255, 0, 0));
		btnDong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDong.setBackground(new Color(139, 0, 0));
		btnDong.setBounds(321, 132, 103, 36);
		contentPane.add(btnDong);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTime.setBounds(10, 145, 103, 14);
		contentPane.add(lblTime);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDate.setBounds(10, 165, 103, 14);
		contentPane.add(lblDate);
		
		//Code tay
		GetLocalTime getLocalTime = new GetLocalTime(lblDate, lblTime);
		getLocalTime.showTime();
		getLocalTime.showDate();
	}

}
