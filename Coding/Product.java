package project;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Product{
    
    public JFrame frame;
    public JPanel panel;
    public JLabel type, imageLabel;
    public JList<String> bagList;
    public JScrollPane scrollPane;
    public ImageIcon[] imagesBag;
    public JButton home;
    
    //call initialize method
  	public Product() {
  		initialize();
  	}
  	
  	public void initialize() {
 
        panel = new JPanel();
        panel.setLayout(null);
        
  		frame = new JFrame();
		frame.setTitle("Product");
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);

        type = new JLabel("Type of Bag");
        type.setFont(new Font("Times New Roman", Font.BOLD, 35));
        type.setSize(200, 50);
        type.setLocation(120, 10);
        panel.add(type);

        String[] bagTypes = {"Hand Bag", "Tote Bag", "School Bag", "Laptop Bag", "Sling Bag"};
        bagList = new JList<>(bagTypes);
        bagList.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        bagList.setSize(100, 130);
        bagList.setLocation(20, 115);
        panel.add(bagList);
        
        imagesBag = new ImageIcon[5]; 
        imagesBag[0]= resizedImage("Images/handbag.jpg", 250, 250);
        imagesBag[1]= resizedImage("Images/totebag.jpg", 250, 250);
        imagesBag[2]= resizedImage("Images/schoolbag.jpg", 250, 250);
        imagesBag[3]= resizedImage("Images/laptopbag.jpg", 250, 250);
        imagesBag[4]= resizedImage("Images/slingbag.jpg", 250, 250);
        
        // Replace with the actual path to your image file
        // Create a JLabel and set the ImageIcon as its icon
        imageLabel = new JLabel();
        imageLabel.setSize(250, 250);
        imageLabel.setLocation(150, 60);
        frame.getContentPane().add(imageLabel);
        
        ListSelectionModel selectionModel = bagList.getSelectionModel();
        //get the selected from list
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = bagList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        imageLabel.setIcon(imagesBag[selectedIndex]);
                    }
                }
            }
        });

        frame.getContentPane().add(panel);
  
        // Create a button
        home = new JButton("H");
        home.setFont(new Font("Times New Roman", Font.BOLD, 15));
        home.setSize(45, 30);
	    home.setLocation(20, 20);
        panel.add(home);
	    
        //action for home button
        home.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.frame.setVisible(true);
				frame.dispose();
			} 
        });
  	
  	}
  	
  	//resized the image
  	private ImageIcon resizedImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
  	
}
