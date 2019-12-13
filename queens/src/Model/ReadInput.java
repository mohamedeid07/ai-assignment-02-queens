package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {
	
	public char[][] read(){
		char[][] res = new char[8][8];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Sample_Input.txt"));
			String line = reader.readLine();
			int j = 0;
			while (line != null) {
				System.out.println(line);
		        char[] ch = new char[line.length()]; 
		        for (int i = 0; i < line.length(); i++) { 
		            ch[i] = line.charAt(i);
		        }
		        res[j++] = ch;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
