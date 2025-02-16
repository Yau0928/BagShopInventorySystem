package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu{
	
	public JFrame frame;
	public JPanel panel;
	public JButton bP, bE, bL;

	//call initialize method
	public Menu() {
		initialize();
	}
	
	public void initialize() {
		panel = new JPanel();
		panel.setLayout(null);
		
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setBounds(100, 100, 350, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		
		bP= new JButton("Product");
		bP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bP.setBounds(90, 50, 150, 25);
		bP.addActionListener(this::bPActionPerformed);
		panel.add(bP);
		
		bE= new JButton("Edit Inventory");
		bE.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bE.setBounds(90, 100, 150, 25);
		bE.addActionListener(this::bEActionPerformed);
		panel.add(bE);
		
		bL= new JButton("Logout");
		bL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bL.setBounds(90, 150, 150, 25);
		bL.addActionListener(this::bLActionPerformed);
		panel.add(bL);
		
		frame.setVisible(true);
	}
	
	//action for product button
	private void bPActionPerformed(ActionEvent e){
		Product p = new Product();
		p.frame.setVisible(true);
		frame.dispose();
	}
	
	//action for edit inventory button
	private void bEActionPerformed(ActionEvent e){
		Inventory i = new Inventory();
		i.f.setVisible(true);
		frame.dispose();
	}
	
	//action for logout button
	private void bLActionPerformed(ActionEvent e){
		frame.dispose();
	}

}
