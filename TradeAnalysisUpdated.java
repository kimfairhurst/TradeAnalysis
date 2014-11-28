import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/* Uses an input.csv, a list of trades with the format: <TimeStamp>,<Symbol>,<Quantity>,<Price>
 * Analyzes trades by calculating AverageWeightedPrice, MaxTimeGap, Volume, MaxPrice based on 3-character unique identified.
 * Stores data within a TreeMap for easy access, automatic sorting, and assurance of uniqueness across entries.
 * Outputs to a "output.csv" with the format: <symbol>,<MaxTimeGap>,<Volume>,<WeightedAveragePrice>,<MaxPrice>
 */
public class TradeAnalysisUpdated {

	@SuppressWarnings("resource")
	public static void main(String args[]){

		// Variable declarations
		TradeAnalysisUpdated myAnalysis = new TradeAnalysisUpdated();
		TreeMap<String, Entry> finalMap = new TreeMap<>();
		long timeStamp;
		String symbol;
		int quantity;
		int price;

		try {
			/* ALTERNATE INPUTS:
			 * 
			 * To change the input file, adjust "src/input.csv" to desired file location.
			 * 
			 */
			BufferedReader br = new BufferedReader(new FileReader("src/input.csv"));
			String inputLine = br.readLine();

			// Begin iterating through the input File (row by row)
			while((inputLine != null)){
				Scanner scanner = new Scanner(inputLine);
				scanner.useDelimiter(",");
			
				// Read data from file
				String temp = scanner.next();
				timeStamp = Long.parseLong(temp);
				symbol = scanner.next();
				quantity = scanner.nextInt();
				price = scanner.nextInt();

				// If symbol not in finalMap yet, create new entry.
				if (!finalMap.containsKey(symbol)){
					Entry newEntry = myAnalysis.new Entry(symbol, 0, quantity, calculateAWP(price, quantity, 0, 0), price, timeStamp);
					finalMap.put(symbol, newEntry);
				} 
				// Otherwise, symbol's Entry to reflect new data.
				else {
					Entry curr = finalMap.get(symbol);
					curr.updateWith(timeStamp, quantity, price);
				}
				inputLine = br.readLine();
			}			
			
			/* OUTPUT TO FILE:
			 * Writes to destination "src/output.csv"
			 * To change output, adjust "src/ouput.csv" to desired file destination.
			 * Writes all finalMap entries with correct formatting
			 * Converts time doubles to int (format specified in specs)
			 */
			try {
				FileWriter fw = new FileWriter("src/output2.csv");
				for (String a: finalMap.keySet()){
					fw.append(finalMap.get(a).toString());
					fw.append("\n");
				}
				fw.close();
			} catch(IOException e){
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			System.out.println("input.csv not found.");
		} catch (IOException e){
			System.out.println("threw an IO Exception.");
		}
	}
	
	// Calculates the Average Weighted Price given price1, vol1 and a price2, vol2
	public static double calculateAWP(double price1, int vol1, int price2, int vol2){
		return ((price1 * vol1)+(price2 * vol2))/(vol1 + vol2);
	}
	
	/* ENTRY class for finalMap
	 * Essentially a "holder" for the variables used by each finalMap entry
	 * In addition to output items, stores "recentTime", the most recent timeStamp for the particular entry
	 */
	public class Entry {
		private String symbol;
		private long MaxTimeGap;
		private int Volume;
		private double AverageWeightedPrice;
		private int MaxPrice;
		private long recentTime;
		
		// Constructor: <MaxTimeGap>,<Volume>,<AWP>,<MaxPrice>,<recentTime>
		public Entry(String symbol, long MaxTimeGap, int Volume, double AverageWeightedPrice, int MaxPrice, long recentTime){
			this.symbol = symbol;
			this.MaxTimeGap = MaxTimeGap;
			this.Volume = Volume;
			this.AverageWeightedPrice = AverageWeightedPrice;
			this.MaxPrice = MaxPrice;
			this.recentTime = recentTime;
		}
		
		public String toString(){
			String output = this.symbol + "," + this.MaxTimeGap + "," + this.Volume + "," + (int) this.AverageWeightedPrice + "," + (int) this.MaxPrice;
			return output;
		}
		
		public void updateWith(long receieveTime, int volume, int price){
			if (this.getMaxTimeGap() < receieveTime - this.getRecentTime()){
				this.setMaxTimeGap(receieveTime-this.getRecentTime());
			}
			this.setRecentTime(receieveTime);
			this.setAWP(TradeAnalysis.calculateAWP(this.AverageWeightedPrice, this.Volume, price, volume));
			this.setVolume(this.getVolume() + volume);
			if (price > this.getMaxPrice()){
				this.setMaxPrice(price);
			}			
		}
	
		// Getters and Setters for each data item within Entry
		public int getVolume() {
		    return this.Volume;
		}
		public void setVolume(int volume) {
		    this.Volume = volume;
		}
		
		public double getAWP() {
		    return this.AverageWeightedPrice;
		}
		public void setAWP(double newPrice) {
		    this.AverageWeightedPrice = newPrice;
		}
		
		public long getMaxTimeGap() {
		    return this.MaxTimeGap;
		}
		public void setMaxTimeGap(long timeGap) {
		    this.MaxTimeGap = timeGap;
		}
		
		public int getMaxPrice() {
		    return this.MaxPrice;
		}
		public void setMaxPrice(int maxPrice) {
		    this.MaxPrice = maxPrice;
		}
		
		public long getRecentTime() {
		    return this.recentTime;
		}
		public void setRecentTime(long timeStamp) {
		    this.recentTime = timeStamp;
		}
	}
}
