package accounts;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinalRunner {

	// creates objects as data members to be accessed over 2 classes
	static BankAccount b = new BankAccount();
	static StockAccount s = new StockAccount();

//	public static void main(String[] args) {
//		returnMenu();
//	}

	public static void main(java.lang.String[] args) {
		returnMenu();
	}
	
	public static class Action extends FinalRunner implements ActionListener {

		public void actionPerformed(ActionEvent e) {

/* Bank Account */if (e.toString().contains("text=Bank Account")) {
/* GUI */
/* Adjust JFrame */
				JFrame bankAccount = new JFrame("Bank Account");
				bankAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				bankAccount.setVisible(true);
				bankAccount.setSize(250, 225);
				
/* Adjust JPanel */
				JPanel bankAccountPanel = new JPanel();
				bankAccountPanel.setLayout(new BoxLayout(bankAccountPanel,BoxLayout.Y_AXIS));
				bankAccount.add(bankAccountPanel);
				bankAccountPanel.setBackground(Color.LIGHT_GRAY);
				
/* Create JButtons */
				JButton deposit = new JButton("Deposit");
				JButton withdraw = new JButton("Withdraw");
				JButton printHistory = new JButton("Print History");
				JButton showBalance = new JButton("Display Balance");
				JButton menu = new JButton("Return to Menu");
				
/* Set Fonts for Buttons */
				// sets fonts for text in each button (for aesthetics)
				deposit.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				withdraw.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				printHistory.setFont(new Font("Times New Roman", Font.ITALIC,12));
				showBalance.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				menu.setFont(new Font("Times New Roman", Font.ITALIC, 12));

/* Add Buttons and Spacing */				
				bankAccountPanel.add(Box.createRigidArea(new Dimension(75, 10))); // Creates spacing
				bankAccountPanel.add(deposit); // adds each button to JPanel
				bankAccountPanel.add(Box.createRigidArea(new Dimension(75, 10)));
				bankAccountPanel.add(withdraw);
				bankAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				bankAccountPanel.add(printHistory);
				bankAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				bankAccountPanel.add(showBalance);
				bankAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				bankAccountPanel.add(menu);

/* Action Listeners */
				deposit.addActionListener(new Action());
				withdraw.addActionListener(new Action());
				printHistory.addActionListener(new Action());
				showBalance.addActionListener(new Action());
				menu.addActionListener(new Action());
/* Bank Account */}
/* GUI End */

/* (BA) Print History Button Action*/	
				if (e.toString().contains("text=Print History")) { // Action when print history is clicked
				System.out.println("Event\t\tAmount\t\tDate\t\tCurrent Balance");
				b.printHistory();
				System.out.println("WORKS from print history - bank account");
			}
/* (BA) Deposit Button Action*/ 
				if (e.toString().contains("text=Deposit")) { // Action when Deposit is clicked
					String temp = JOptionPane.showInputDialog("Enter Amount to Deposit");
				if (temp == null || temp.equals("") || temp.contains("abcdefghijklmnopqrstuvwxyz")||temp.isEmpty())
					JOptionPane.showMessageDialog(null, "Illegal Argument.");
				else {
					b.deposit(Integer.parseInt(temp));
					System.out.println("Works from deposit");
				}
			}
/* (BA) Withdraw Button Action*/
			if (e.toString().contains("text=Withdraw")) { // Action when withdraw is clicked
				String temp = JOptionPane.showInputDialog("Enter Amount to withdraw");
				if (temp == null || temp.equals("") || temp.contains("abcdefghijklmnopqrstuvwxyz")||temp.isEmpty())
					JOptionPane.showMessageDialog(null, "Illegal Argument.");
				else {
					b.withdraw(Integer.parseInt(temp));
					System.out.println("Works from Withdraw");
				}
			}
			
/* Return to Menu Button Action */			
			if (e.toString().contains("text=Return to Menu"))
				returnMenu();			
			
/* End Program Button Action */						
			if (e.toString().contains("text=End Program")) {
				storeBalance();
				s.updatePortfolio();
				System.exit(0);
			}
			
/* Stock Account GUI */		
			if (e.toString().contains("Stock Account")) {
				
/* JFrame Adjustments */							
				JFrame StockAccount = new JFrame("Stock Account");
				StockAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				StockAccount.setVisible(true);
				StockAccount.setSize(250, 285);

/* JPanel Adjustments */							
				JPanel StockAccountPanel = new JPanel();
				StockAccountPanel.setLayout(new BoxLayout(StockAccountPanel,BoxLayout.Y_AXIS));
				StockAccountPanel.setBackground(Color.LIGHT_GRAY);
				StockAccount.add(StockAccountPanel);

/* JButton Creation */							
				JButton displayPrice = new JButton("Display Stock Price");
				JButton displayPort = new JButton("Display Portfolio");
				JButton buyShare = new JButton("Buy Shares");
				JButton sellShare = new JButton("Sell Shares");
				JButton displayCash = new JButton("Display Cash Balance");
				JButton menu = new JButton("Return to Menu");

/* Set Button Font */							
				displayPrice.setFont(new Font("Times New Roman", Font.ITALIC,12));
				displayPort.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				buyShare.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				sellShare.setFont(new Font("Times New Roman", Font.ITALIC, 12));
				displayCash.setFont(new Font("Times New Roman",Font.ITALIC,12));
				menu.setFont(new Font("Times New Roman", Font.ITALIC, 12));

/* Add JButtons and Spacings */							
				StockAccountPanel.add(Box.createRigidArea(new Dimension(75, 10))); // Create spacing (for aesthetics)
				StockAccountPanel.add(displayPrice); // adds each button to JPanel
				StockAccountPanel.add(Box.createRigidArea(new Dimension(75, 10)));
				StockAccountPanel.add(displayPort);
				StockAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				StockAccountPanel.add(buyShare);
				StockAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				StockAccountPanel.add(sellShare);
				StockAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				StockAccountPanel.add(displayCash);
				StockAccountPanel.add(Box.createRigidArea(new Dimension(10, 10)));
				StockAccountPanel.add(menu);

/* Button Action Listeners */							
				displayPrice.addActionListener(new Action());
				displayPort.addActionListener(new Action());
				buyShare.addActionListener(new Action());
				sellShare.addActionListener(new Action());
				displayCash.addActionListener(new Action());
				menu.addActionListener(new Action());
			
/* Stock Account GUI End */			}
			
/* (SA) Display Balance Button Action */						
			if(e.toString().contains("Display Balance")){
				JOptionPane.showMessageDialog(null, "$"+b.returnBalance());
			}
			
/* (SA) Display Stock Price */			
			if(e.toString().contains("Display Stock Price")){
				String p = JOptionPane.showInputDialog("Enter the Symbol of the Company");
				if(p==null || p.isEmpty() || p.contains("1234567890") || p.equals("")||p.equals(" "))
					JOptionPane.showMessageDialog(null,"Invalid Symbol");
				else{
				String temp = s.displayStockPrice(p);
				JOptionPane.showMessageDialog(null, temp);
				}
			}
			
/* (SA) Display Current Portfolio */
			if(e.toString().contains("Display Portfolio"))
				s.displayPortfolio();
			
			if(e.toString().contains("Display Cash Balance"))
				JOptionPane.showMessageDialog(null,s.getCashBalance());
				
			
			
/* (SA) Buy Shares */
			if(e.toString().contains("Buy Shares")){
				String symbol;
				String amt;
				String limit;
				symbol = JOptionPane.showInputDialog("Enter company symbol");
				if(symbol==null||symbol.contains("1234567890")||symbol.isEmpty()||symbol.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Symbol");
					return;
				}
				amt = JOptionPane.showInputDialog("Enter amount of stocks to buy");
				if(amt==null||amt.contains("abcdefghijklmnopqrstuvwxyz")||amt.isEmpty()||amt.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Amount");
					return;
				}
				limit = JOptionPane.showInputDialog("Enter max payment");
				if(limit==null||limit.contains("abcdefghijklmnopqrstuvwxyz")||limit.isEmpty()||limit.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Limit");
					return;
				}
				s.buyShares(symbol, Integer.parseInt(amt), Double.parseDouble(limit));
				
			}
			
/* (SA) Sell Shares */			
			if(e.toString().contains("Sell Shares")){
				String symbol;
				String amt;
				String min;
				symbol = JOptionPane.showInputDialog("Enter company symbol");
				if(symbol==null||symbol.contains("1234567890")||symbol.isEmpty()||symbol.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Symbol");
					return;
				}
				amt = JOptionPane.showInputDialog("Enter amount of stocks to buy");
				if(amt==null||amt.contains("abcdefghijklmnopqrstuvwxyz")||amt.isEmpty()||amt.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Amount");
					return;
				}
				min = JOptionPane.showInputDialog("Enter min amount per stock");
				if(min==null||min.contains("abcdefghijklmnopqrstuvwxyz")||min.isEmpty()||min.equals(" ")){
					JOptionPane.showMessageDialog(null, "Invalid Limit");
					return;
				}
				s.sellShares(symbol, Integer.parseInt(amt), Double.parseDouble(min));
			}
			
		}
	}

	public static void returnMenu(){
		storeBalance();
		
		JFrame Account = new JFrame("Account");
	
		Account.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Account.setVisible(true);
		Account.setSize(525, 200);
			 
		// Creates JPanel to hold JButtons
		JPanel AccountPanel = new JPanel();
		AccountPanel.setLayout(new BoxLayout(AccountPanel, BoxLayout.LINE_AXIS));
		AccountPanel.setBackground(Color.lightGray);
		Account.add(AccountPanel);

		// Creates directions for user
		JLabel instructions = new JLabel(" ~ Pick and Account type:     ");
		instructions.setFont(new Font("Monospaced Bold", Font.BOLD, 15));
		AccountPanel.add(instructions);

		// Creates JButtons for each Choice, and edits font, then adds to JPanel
		JButton BankAccount = new JButton("Bank Account");
		JButton StockAccount = new JButton("Stock Account");
		JButton EndProgram = new JButton("End Program");
		
		BankAccount.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		StockAccount.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		EndProgram.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		AccountPanel.add(BankAccount);
		AccountPanel.add(StockAccount);
		AccountPanel.add(EndProgram);
		// calls Action class to determine what to do for said button being clicked
		BankAccount.addActionListener(new Action());
		StockAccount.addActionListener(new Action());
		EndProgram.addActionListener(new Action());

	}

	public static void storeBalance() {
	double amount = 10000;
		try {
			BufferedReader br = new BufferedReader(new FileReader("bank_transaction_history.txt"));
			String line = "";
			while((line = br.readLine())!=null)
				 amount = Double.parseDouble(line.substring(line.indexOf("$")+1));
			b.setBalance(amount);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
}