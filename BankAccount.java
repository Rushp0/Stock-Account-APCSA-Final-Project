package accounts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//for getting current date/time
import java.time.LocalDateTime;   
import java.time.format.DateTimeFormatter;


public class BankAccount extends Account{
	
	PrintWriter history;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	LocalDateTime now = LocalDateTime.now();
	public BankAccount(){
		super();
		String fileName = "bank_transaction_history.txt";
		
		PrintWriter ops;
			try {
				ops = new PrintWriter(new FileWriter(fileName,true));
				history = ops;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public void deposit(double amt){
		super.deposit(amt);
		history.println("Deposit\t"+amt+"\t"+dtf.format(now)+"\t$"+super.returnBalance());
		history.flush();
		
	}
	public void withdraw(double amt) {
		if(amt<super.returnBalance()){
			super.withdraw(amt);
			history.println("Withdraw\t"+amt+"\t\t"+dtf.format(now)+"\t$"+super.returnBalance());
		}
		else {
			System.out.println("Failure to withdraw $"+amt);
			history.println("Withdraw\t"+"I.F."+"\t\t"+dtf.format(now)+"\t$"+super.returnBalance());
		}
		history.flush();
	}
	public void printHistory(){
		String line = "";
		
		JFrame tFrame = new JFrame("Bank Account Transactions");
		tFrame.setVisible(true);
		tFrame.setSize(new Dimension(400,250));
		tFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel,BoxLayout.LINE_AXIS));
		tPanel.setBackground(Color.lightGray);
		tFrame.add(tPanel);
		tPanel.setPreferredSize(new Dimension(400,250));
		
		JTextArea textArea = new JTextArea(400,250);
		textArea.setBackground(Color.lightGray);
		textArea.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		tPanel.add(scroll);
		tFrame.setVisible(true);
		try{
			textArea.append("   Event\tAmount\tDate\tBalance\n\n");
		FileReader fr = new FileReader("bank_transaction_history.txt");
		BufferedReader br = new BufferedReader(fr);
		while((line = br.readLine())!=null)
			if(line.contains("D")||line.contains("W"))
			textArea.append("   "+line+"\n");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Failure to print history in BankAccount Class");
		}
	}
	protected void setBalance(double d){
	
		super.setBalance(d);
		history.println("$"+super.returnBalance());
	System.out.println("calls update balance from bank account class");
		
	}
}