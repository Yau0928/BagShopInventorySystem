package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	
	public JPanel panel;
	public JFrame frame;
	public JLabel userLabel, passwordLabel, success;
	public JTextField userText;
	public JPasswordField passwordText;
	public JButton button;

	//call initialize method
	public Login() {
		initialize();
	}
	
	public void initialize() {
		
		panel = new JPanel();
		panel.setLayout(null);
		
		frame = new JFrame();
		frame.setTitle("Bag Inventory System");
		frame.setBounds(100, 100, 300, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);

		userLabel = new JLabel("User");
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		userLabel.setBounds(20, 30, 150, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		userText.setBounds(100, 30, 150, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordLabel.setBounds(20, 80, 150, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 80, 150, 25);
		panel.add(passwordText);
			
		button = new JButton("Login");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		button.setBounds(100, 130, 80, 25);
		button.addActionListener(this::buttonActionPerformed);
		panel.add(button);
		
		success = new JLabel("");
		success.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		success.setForeground(Color.RED);
		success.setBounds(10, 110, 300,25);
		panel.add(success);
		
	}
	
	//action for logout button
	private void buttonActionPerformed(ActionEvent e){
			String user = userText.getText();
			String password = passwordText.getText();
			
			if(user.equals("Manager") && password.equals("1234"))
			{
				success.setText("Login successful!");
				mainF();		
			}
			else
			{
				success.setText("Wrong password or username!");
			}
	}
	
	//to display menu window and dispose current login window
	public void mainF() {
		Menu menu = new Menu();
		menu.frame.setVisible(true);
		frame.dispose();
	}
	
	public static void main(String[] args) {
		//create and display and login window
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					Login l = new Login();
					l.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}