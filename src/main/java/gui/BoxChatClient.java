package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import chatServer.ChatInterface;
import util.GetLocalTime;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class BoxChatClient extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoxChatClient frame = new BoxChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public BoxChatClient() throws MalformedURLException, RemoteException, NotBoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "N\u1ED9i d\u1EE5ng chat:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(4, 0, 799, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JTextArea taContent = new JTextArea();
		taContent.setEditable(false);
		taContent.setBounds(6, 16, 787, 178);
		panel.add(taContent);
		
		final JTextArea taMessage = new JTextArea();
		taMessage.setBounds(10, 216, 605, 110);
		contentPane.add(taMessage);
		
		JButton btnSend = new JButton("G\u1EEDi");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(taMessage.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Nhập nội dung chat!");
				else {
					try {
						InetAddress IP=InetAddress.getLocalHost();
						taContent.setText(IP.getHostName()+":  "+taMessage.getText()+"\n");
						taMessage.setText("");
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSend.setBackground(new Color(60, 179, 113));
		btnSend.setForeground(new Color(0, 255, 0));
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSend.setBounds(626, 288, 97, 38);
		contentPane.add(btnSend);
		
		JButton btnBack = new JButton("Quay l\u1EA1i");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.main(null);
				dispose();
			}
		});
		btnBack.setBackground(new Color(139, 0, 0));
		btnBack.setForeground(new Color(220, 20, 60));
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnBack.setBounds(716, 256, 91, 29);
		contentPane.add(btnBack);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(726, 300, 81, 14);
		contentPane.add(lblTime);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(722, 318, 81, 17);
		contentPane.add(lblDate);
		
		//Code tay
		GetLocalTime getLocalTime = new GetLocalTime(lblDate, lblTime);
		getLocalTime.showTime();
		getLocalTime.showDate();
		thamGiaVaoBoxChat();
	}
	private void thamGiaVaoBoxChat() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
//		DonMuaDao donMuaDao = (DonMuaDao) Naming.lookup("rmi://192.168.1.8:1090/donMuaDao");
		ChatInterface chatInterface = (ChatInterface) Naming.lookup("rmi://192.168.1.8:1090/chatInterface");
		boolean a = chatInterface.login(getName());
	}
}
