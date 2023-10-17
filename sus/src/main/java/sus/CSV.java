package sus;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;


import com.opencsv.*;

public class CSV {
	public static void main(String[] args) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter("outpt.csv"));
			String[] record = "John,Doe,30".split(",");
			writer.writeNext(record);
			writer.close();
			System.out.println("Data has been writen to CSV file.");
		} catch (IOException e) {
			e.printStackTrace();
				
		}
	}

}
