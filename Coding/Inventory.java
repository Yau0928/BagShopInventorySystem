package project;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

public class Inventory{
		
		public JFrame f; 
		public JLabel item, lT, lS, lD, lQ, lR, lP, lStock, inf;
		public JTextField stockNum;
		public JComboBox<String> bag, rea;
		public JSpinner q;
		public JCheckBox con;
		public JTextArea dis;
		public JButton yes, done, h;
		public HashMap<String, Integer> stockCounts;
		public String stockFile = "inventory.txt";

		//call initialize method and load the stock number
		public Inventory(){
			//initializes a instance of the HashMap class 
			stockCounts = new HashMap<>();
			initialize();
			loadStockCounts();
		}
		
		public void initialize() {
			f = new JFrame();
	        f.setTitle("Edit the Inventory");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setBounds(100, 100, 800, 500);
	        f.setResizable(false);
	        f.getContentPane().setLayout(null);
	        
	        item = new JLabel("Item");
	        item.setFont(new Font("Times New Roman", Font.BOLD, 45));
		    item.setSize(100, 30);
		    item.setLocation(350, 20);
	        f.getContentPane().add(item);
	        
	        lT = new JLabel("Type: ");
	        lT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		    lT.setSize(300, 30);
		    lT.setLocation(50, 70);
	        f.getContentPane().add(lT);
	        
	        String[] listBag = {"Laptop Bag", "Hand Bag", "School Bag", "Sling Bag", "Tote Bag"};
	        bag = new JComboBox<>(listBag);
	        bag.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        bag.setSize(120, 30);
	        bag.setLocation(100, 70);
	        f.getContentPane().add(bag);
	        
	        yes = new JButton("Yes");
	        yes.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	        yes.setSize(60, 25);
	        yes.setLocation(280, 75);
	        f.getContentPane().add(yes);
	        
	        //show supplier and price for different bag when selected
	        yes.addActionListener(e -> {
	            String selected = (String) bag.getSelectedItem();
	            display(selected);
	            updateStock(selected);
	        });
	        
	        lS = new JLabel("Supplier:");
	        lS.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		    lS.setSize(500, 30);
		    lS.setLocation(50, 120);
	        f.getContentPane().add(lS);
	        
	        lP = new JLabel("Price:");
	        lP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        lP.setSize(150, 30);
	        lP.setLocation(50, 170);
	        f.getContentPane().add(lP);
	        
	        lStock = new JLabel("Stock: ");
	        lStock.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        lStock.setSize(150, 30);
	        lStock.setLocation(50, 220);
	        f.getContentPane().add(lStock);

	        lQ = new JLabel("Quantity: ");
	        lQ.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        lQ.setSize(150, 30);
	        lQ.setLocation(50, 270);
	        f.getContentPane().add(lQ); 
	        
	        int min=-5, max=20, step=1, i=1;
	        SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
	        q = new JSpinner(value);
	        q.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        q.setSize(50, 30);
	        q.setLocation(140, 270);
	        f.getContentPane().add(q);
	        
	        //show reason when quantity below than 0
	        //hide the reason when quantity more than 30
	        //show message when quantity is 0
	        q.addChangeListener(new ChangeListener() {
	            
	            public void stateChanged(ChangeEvent e) {
	                int sV = (int) q.getValue();
	                if (sV < 0) {
	                	lR.setVisible(true);
	                    rea.setVisible(true);
	                }               	
	                else {
	                	lR.setVisible(false);
	                    rea.setVisible(false);
	                }
	                
	                if(sV == 0) {
	                	int option = JOptionPane.showConfirmDialog(f, 
	                			"Quantity cannot be 0. Please reset the quantity!",
	                            "Reset Quantity",JOptionPane.YES_NO_OPTION);
	                    if (option == JOptionPane.YES_OPTION) {
	                        q.setValue(0);
	                    }
	                }
	            }

	        });
	        
	        lR = new JLabel("Reason: ");
	        lR.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        lR.setSize(150, 30);
	        lR.setLocation(50, 320);
	        lR.setVisible(false);
	        f.getContentPane().add(lR);
	        
	        String[] reasons = {"Customer Order", "Free Gift", "Broken"};
	        rea = new JComboBox<>(reasons);
	        rea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        rea.setSize(150, 30);
	        rea.setLocation(140, 320);
	        rea.setVisible(false);
	        f.getContentPane().add(rea);
	        
	        con = new JCheckBox("I have verify the quantity.");
	        con.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	        con.setSize(300, 30);
	        con.setLocation(50, 360);
	        f.getContentPane().add(con);
	        
	        inf = new JLabel("");
	        inf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	        inf.setSize(300, 30);
	        inf.setLocation(50, 390);
	        inf.setForeground(Color.red);
	        f.getContentPane().add(inf);
	        
	        lD = new JLabel();
	        lD.setFont(new Font("Times New Roman", Font.PLAIN, 20));      
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(new Date());
	        lD.setText("Date: " + formattedDate);
	        
	        dis = new JTextArea("");
	        dis.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	        dis.setSize(320, 250);
	        dis.setLocation(400, 80);
	        dis.setLineWrap(true);
	        f.getContentPane().add(dis);
	        
	        done = new JButton("Done");
		    done.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		    done.setSize(100, 20);
		    done.setLocation(250, 400);
		    f.getContentPane().add(done);
		    
		    done.addActionListener(this::doneActionPerformed);
		    
		    h = new JButton("H");
		    h.setFont(new Font("Times New Roman", Font.BOLD, 15));
		    h.setSize(45, 30);
		    h.setLocation(20, 20);
		    f.getContentPane().add(h);

		    h.addActionListener(new ActionListener() {
		    	
				public void actionPerformed(ActionEvent e) {
					Menu menu = new Menu();
					menu.frame.setVisible(true);
					f.dispose();
				}
			});

		}
		
		//show supplier and price of different bag
		private void display(String selected) {
			
			if(selected.equals("Laptop Bag")) {
				
				lS.setText("Supplier: All the One Supply Warehouse ");
				lP.setText("Price: RM 30.00");
			}
			else if(selected.equals("Hand Bag")) {
				lS.setText("Supplier: Bag Best Supply");
				lP.setText("Price: RM 27.00");
			}
			else if(selected.equals("School Bag")) {
				lS.setText("Supplier: All the One Supply Warehouse ");
				lP.setText("Price: RM 35.00");
			}
			else if(selected.equals("Sling Bag")) {
				lS.setText("Supplier: Bag Best Supply");
				lP.setText("Price: RM 22.00");
			}
			else{
				lS.setText("Supplier: My Online Bag Supply");
				lP.setText("Price: RM 17.00");
			}
		}
	    
		//retrieves the stock number with different bag
		private Integer getStockCount(String bagType) {
	        Integer stockCount = stockCounts.get(bagType);
	        return stockCount != null ? stockCount : 0;
	    }
		
		//update stock number in the map with quantity
		private void updateStock(String bagType, int quantity) {
	        int stockCount = getStockCount(bagType);
	        stockCounts.put(bagType, stockCount + quantity);
	    }
		
		//update the GUI with current stock number for selected bag 
	    private void updateStock(String selectedBag) {
	        int stockCount = getStockCount(selectedBag);
	        lStock.setText("Stock: " + stockCount);
	    }
	    
	    //action for done button
	    private void doneActionPerformed(ActionEvent e) {
	        if (con.isSelected()) {
	            int num = (int) q.getValue();
	            String selectedBag = (String) bag.getSelectedItem();
	            int updatedStockCount = getStockCount(selectedBag);

	            updateStock(selectedBag, num);
	            saveStockCounts();
	            lStock.setText("Stock: " + updatedStockCount);
	            

	            String data = "Type: " + bag.getSelectedItem() + "\n"
	                    + lS.getText() + "\n"
	                    + lP.getText() + "\n"
	                    + "Stock: " + (updatedStockCount+ num) + "\n"
	                    + "Quantity: " + num + "\n";
	            
	            
	            if (num < 0) {
	                String data1 = "Reason: " + rea.getSelectedItem() + "\n";
	                data += data1;
	            }

	            String data2 = lD.getText();

	            dis.setText(data + data2);
	            dis.setEditable(false);
	            inf.setText("Stock has been updated!");
	            
	        } else {
	            dis.setText("");
	            inf.setText("Please accept the verification!");
	        }
	    }
		
	    //load stock number and type from file
		private void loadStockCounts() {
		    try (BufferedReader reader = new BufferedReader(new FileReader(stockFile))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            String[] parts = line.split(":");
		            if (parts.length == 2) {
		                String bagType = parts[0].trim();
		                String stockCountStr = parts[1].trim();
		                // Skip lines with invalid bag types
		                if (bagType.equals("Type") || bagType.equals("Supplier") || bagType.equals("Price") || bagType.equals("Date")) {
		                    continue;
		                }
		                try {
		                    int stockCount = Integer.parseInt(stockCountStr);
		                    stockCounts.put(bagType, stockCount);
		                } catch (NumberFormatException e) {
		                    System.err.println("Invalid stock count for bag type: " + bagType);
		                }
		            }
		        }
		    } catch (IOException e) {
		        // Handle file reading error
		        e.printStackTrace();
		    }
		}
		
		//save the stock number form map into file
		private void saveStockCounts() {
		    try (BufferedWriter writer = new BufferedWriter(new FileWriter(stockFile))) {
		        writer.write("Type: StockCount");

		        for (String bagType : stockCounts.keySet()) {
		            int stockCount = stockCounts.get(bagType);
		            writer.newLine();
		            writer.write(bagType + ": " + stockCount);
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
}
