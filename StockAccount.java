package accounts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import stocks.Stock;
//for getting current date/time

public class StockAccount extends Account {

	FileWriter history;
	ArrayList<Stock> stocks = new ArrayList<Stock>();
	
	public StockAccount() {
		super();
		String fileName = "stock_transaction_history.txt";
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String line = "";
				while((line = br.readLine())!=null){
					String s = line.substring(0,line.indexOf("#")-1);
					int amount = Integer.parseInt(line.substring(line.indexOf("#")+1,line.indexOf("$")-1));
					double price = Double.parseDouble(line.substring(line.indexOf("$")+1));
					stocks.add(new Stock(s,amount,price));
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public double getCashBalance(){return super.returnBalance();}
	protected void setCashBalance(double amt){super.setBalance(amt);}
	
	public String displayStockPrice(String symbol) {
	//	i
//		symbol = symbol.toUpperCase();
		String text = "";
		if(symbol==null||symbol.equals("")||symbol.equals(" ")) 
			return "No Symbol Entered";
		
		try {
			Document dc1 = Jsoup.connect("https://www.tradingview.com/markets/stocks-usa/market-movers-active/").get();
			Elements body = dc1.select("div.tv-screener__content-pane");
			
			for(Element e:body){
				String method = e.text();
				if(method.contains(symbol))
					if(symbol.length()<=2){
						String temp = " "+symbol+" ";
						int index = method.indexOf(temp);
						text = method.substring(index, method.indexOf("%",index));
					}
					else{
						int index = method.indexOf(symbol);
						text = method.substring(index,method.indexOf("%",index));
					}
			}
			
			if(text.equals(""))
				return "Symbol not Found";
			
			int index = text.indexOf(".");
			
			while(text.substring(index+1,index+2).contains("1234567890"))
				index = text.indexOf(".",index++);
			
			return "Price per share for "+symbol+": "+text.substring(index-3,index+3);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "Company not Found";
	}
	public void displayPortfolio(){
	
		JFrame tFrame = new JFrame("Bank Account Transactions");
		tFrame.setVisible(true);
		tFrame.setSize(new Dimension(500,250));
		tFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new BoxLayout(tPanel,BoxLayout.LINE_AXIS));
		tPanel.setBackground(Color.lightGray);
		tFrame.add(tPanel);
		
		JTextArea textArea = new JTextArea(500,250);
		textArea.setBackground(Color.lightGray);
		textArea.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tPanel.add(scroll);
		textArea.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		textArea.append("   Symbol\tAmount\tPrice per Share\tTotal Value\n");
			for(int i = 0;i<stocks.size();i++)
				textArea.append("   "+stocks.get(i).getSymbol()+"\t#"+stocks.get(i).getAmount()+"\t$"+stocks.get(i).getPrice()+
						"\t\t"+stocks.get(i).getAmount()*stocks.get(i).getPrice()+"\n");

			tFrame.setVisible(true);
	}

	public void buyShares(String symbol,int amount, double limit){
		symbol=symbol.toUpperCase();
		String r = displayStockPrice(symbol);
		if(r.equals("Company not Found")){
			JOptionPane.showMessageDialog(null, "Company not Found");
			return;
			}
		double per = Double.parseDouble(r.substring(r.indexOf(":")+1));
		
		if(per>limit){
			JOptionPane.showMessageDialog(null, "Price is higher than Limit");
			return;
		}
		if(per*amount>super.returnBalance()){
			JOptionPane.showMessageDialog(null, "Not enough Funds");
			return;
	
		}
		if(per*amount>super.returnBalance()){
			JOptionPane.showMessageDialog(null, "Insufficient Funds");
		}
		
		//check is stock is in portfolio
			super.withdraw(amount*per);
			boolean isInPortfolio = false;
			for(int i=0;i<stocks.size();i++)
				if(stocks.get(i).getSymbol().equals(symbol)) 
					isInPortfolio = true;
			
		if(isInPortfolio){
			for(int i = 0; i<stocks.size();i++)
				if(stocks.get(i).getSymbol().equals(symbol))
					stocks.get(i).IncreaseAmount(amount);
			System.out.println("done");
		}
		else{
			stocks.add(new Stock(symbol,amount,per));
			System.out.println("DONE");
		}
		updatePortfolio();
		JOptionPane.showMessageDialog(null, "You have bought "+amount+" shares of "+symbol);
	}
	public void sellShares(String symbol,int amt,double min){
		symbol=symbol.toUpperCase();
		String temp = displayStockPrice(symbol);
		double price = Double.parseDouble(temp.substring(temp.indexOf(":")+3));
	
		if(price>min){
			JOptionPane.showMessageDialog(null, "Price is too low");
			return;
		}
			
		for(int i = 0;i<stocks.size();i++){
			if(stocks.get(i).getSymbol().equals(symbol)){
				if(stocks.get(i).getAmount()<amt){
					JOptionPane.showMessageDialog(null, "Not enough Stocks to Sell");
					return;
				}
				stocks.get(i).DecreaseAmount(amt);
				
				if(stocks.get(i).getAmount()==0)
					stocks.remove(i);
			}
		}
		updatePortfolio();
		JOptionPane.showMessageDialog(null, "You have sold "+amt+" shares of "+symbol);
	}
	protected void updatePortfolio(){
		
		try {
			history = new FileWriter("stock_transaction_history.txt");
			for(int i = 0;i<stocks.size();i++){
				history.write(stocks.get(i).getSymbol()+"\t#"+stocks.get(i).getAmount()+
						"\t$"+stocks.get(i).getPrice()+"\n");
				history.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("updated");

	}
}