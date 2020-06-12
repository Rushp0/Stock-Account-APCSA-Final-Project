package accounts;

public class StockAccountRunner {

	public static void main(String [] args){
		
		StockAccount s = new StockAccount();
		
		/* Testing displayStockPrice(String symbol) */
		
		s.displayStockPrice("T");
		
		//Testing numbered stock
		s.displayStockPrice("1234");
		
		//Testing empty and only containing space 
		s.displayStockPrice("");
		s.displayStockPrice(" ");
		
		//Testing not found stock
		s.displayStockPrice("dfg");
		
		/* Testing displayPortfolio */
		s.displayPortfolio();
		
		/* Buying shares */
		s.buyShares("T", 1, 50);
		//buying shares that are out of limit
		s.buyShares("T", 1, 1);
		//buying shares that cost too much
		s.buyShares("C", 1000, 50);
		
		s.displayPortfolio();
		
		/* Selling Shares */
		s.sellShares("T", 1, 50);
		
		//Shares I dont have
		s.sellShares("T", 5000000, 50);
		
		
	}

}