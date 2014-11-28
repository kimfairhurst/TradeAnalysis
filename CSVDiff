import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CVSDiff {

	public static void main(String args[]) throws IOException{

		double timeStamp1; 
		long timeStamp2;
		String symbol1, symbol2;
		int quantity1, quantity2;
		int price1, price2;
		
		BufferedReader firstFile = new BufferedReader(new FileReader("src/output.csv"));
		String firstInput = firstFile.readLine();
		
		BufferedReader secondFile = new BufferedReader(new FileReader("src/output2.csv"));
		String secondInput = secondFile.readLine();
		
		boolean truthValue = true;
		
		
		while (firstInput != null){
			if (secondInput == null){
				System.out.println("Unequal lengths");
				break;
			} else {

				Scanner scanner1 = new Scanner(firstInput);
				scanner1.useDelimiter(",");
			
				// Read data from first file
				symbol1 = scanner1.next();
				String temp1 = scanner1.next();
				timeStamp1 = Double.parseDouble(temp1);
				timeStamp1 = (int) timeStamp1;
				quantity1 = scanner1.nextInt();
				price1 = scanner1.nextInt();
				
				Scanner scanner2 = new Scanner(secondInput);
				scanner2.useDelimiter(",");
				
				// Read data from second file
				symbol2 = scanner2.next();
				String temp2 = scanner2.next();
				timeStamp2 = Long.parseLong(temp2);
				quantity2 = scanner2.nextInt();
				price2 = scanner2.nextInt();
				
				if (!symbol1.equals(symbol2)){
					System.out.println("Smybols unequal.");
					truthValue = false;
					System.out.println(timeStamp1);
					System.out.println(timeStamp2);
					break;
				}
				if (timeStamp1 != timeStamp2){
					System.out.println("time stamps unequal.");
					truthValue = false;
					break;
				}
				if (quantity1 != quantity2){
					System.out.println("Quantity unequal.");
					truthValue = false;
					break;
				}
				if (price1 != price2){
					System.out.println("Prices unequal.");
					truthValue = false;
					break;
				}
				
				scanner1.close();
				scanner2.close();
				
			}
			if (truthValue){
				secondInput = secondFile.readLine();
				firstInput = firstFile.readLine();
			} else {
				break;
			}

		}
		if (truthValue){
			System.out.println("All values equal!");
		}

		firstFile.close();
		secondFile.close();

	}

}
