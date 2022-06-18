package Student;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;

public class Student {

	private JFrame frmStudentDetails;
	private JTextField txtname;
	private JTextField txtcourse;
	private JTextField txtphone;
	private JTextField txtlocation;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student();
					window.frmStudentDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Student() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mini","root","Loga@2000");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("select * from details");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentDetails = new JFrame();
		frmStudentDetails.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frmStudentDetails.setTitle("STUDENT DETAILS");
		frmStudentDetails.setBounds(100, 100, 813, 452);
		frmStudentDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentDetails.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 54, 364, 196);
		frmStudentDetails.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 23, 74, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 68, 74, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone No");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 114, 74, 13);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Location");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(10, 162, 74, 13);
		panel.add(lblNewLabel_1_3);
		
		txtname = new JTextField();
		txtname.setBounds(112, 23, 167, 19);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtcourse = new JTextField();
		txtcourse.setColumns(10);
		txtcourse.setBounds(112, 68, 167, 19);
		panel.add(txtcourse);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(112, 114, 167, 19);
		panel.add(txtphone);
		
		txtlocation = new JTextField();
		txtlocation.setColumns(10);
		txtlocation.setBounds(112, 162, 167, 19);
		panel.add(txtlocation);
		
		JLabel lblNewLabel = new JLabel("STUDENT DETAILS");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(321, 10, 186, 34);
		frmStudentDetails.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,course,phone,location;
				name = txtname.getText();
				course = txtcourse.getText();
				phone = txtphone.getText();
				location = txtlocation.getText();
				
				try {
					pst = con.prepareStatement("insert into details(name,course,phone,location)values(?,?,?,?)");
				    pst.setString(1,name);
				    pst.setString(2,course);
				    pst.setString(3,phone);
				    pst.setString(4,location);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null,"SAVED");
				    
				    table_load();
				    
				    txtname.setText("");
				    txtcourse.setText("");
				    txtphone.setText("");
				    txtlocation.setText("");
				    
				    txtname.requestFocus();
				}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(56, 270, 85, 35);
		frmStudentDetails.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CLEAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtname.setText("");
			    txtcourse.setText("");
			    txtphone.setText("");
			    txtlocation.setText("");
				
			}
		});
		btnNewButton_1.setBounds(242, 270, 85, 35);
		frmStudentDetails.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EDIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id,name,course,phone,location;
				
				name = txtname.getText();
				course = txtcourse.getText();
				phone = txtphone.getText();
				location = txtlocation.getText();
				id = txtid.getText();
				
				try {
					pst = con.prepareStatement("update details set name=?,course=?,phone=?,location=? where id=?");
				    pst.setString(1,name);
				    pst.setString(2,course);
				    pst.setString(3,phone);
				    pst.setString(4,location);
				    pst.setString(5,id);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null,"UPDATED");
				    
				    table_load();
				    
				    txtname.setText("");
				    txtcourse.setText("");
				    txtphone.setText("");
				    txtlocation.setText("");
				    
				    txtname.requestFocus();
				}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(439, 351, 85, 35);
		frmStudentDetails.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 54, 360, 251);
		frmStudentDetails.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 326, 364, 84);
		frmStudentDetails.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Student ID");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(23, 31, 74, 13);
		panel_1.add(lblNewLabel_1_4);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String id = txtid.getText();
					
					pst = con.prepareStatement("select name,course,phone,location from details where id = ?");
					pst.setString(1, id);
					rs = pst.executeQuery();
					
					if(rs.next() == true) {
						
						String name = rs.getString(1);
						String course = rs.getString(2);
						String phone = rs.getString(3);
						String location = rs.getString(4);
						
						txtname.setText(name);
						txtcourse.setText(course);
						txtphone.setText(phone);
						txtlocation.setText(location);
						
					}
					
					else {
						txtname.setText("");
					    txtcourse.setText("");
					    txtphone.setText("");
					    txtlocation.setText("");
						
					}
				}
				
				catch (SQLException ex) {
					
				}
				
			}
		});
		txtid.setColumns(10);
		txtid.setBounds(112, 29, 167, 19);
		panel_1.add(txtid);
		
		JButton btnNewButton_2_1 = new JButton("DELETE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id;
				
				id = txtid.getText();
				
				try {
					pst = con.prepareStatement("delete from details where id=?");
				    pst.setString(1,id);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null,"DELETED");
				    
				    table_load();
				    
				    txtname.setText("");
				    txtcourse.setText("");
				    txtphone.setText("");
				    txtlocation.setText("");
				    
				    txtname.requestFocus();
				}
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2_1.setBounds(574, 351, 85, 35);
		frmStudentDetails.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("EXIT");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnNewButton_2_2.setBounds(707, 351, 85, 35);
		frmStudentDetails.getContentPane().add(btnNewButton_2_2);
	}
}
