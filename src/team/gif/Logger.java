package team.gif;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	
	File log;
	FileWriter writer;
	
	public Logger() throws IOException {
		log = new File("/Patrick.txt");
		writer = new FileWriter(log, true);
	}
	
	public void write(String str) {
		try {
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String[] str) {
		for (String s : str) {
			write(s);
		}
	}
	
	public void write(double d) {
		write(Double.toString(d));
	}
	
	public void write(double[] d) {
		for (double d1 : d) {
			write(Double.toString(d1));
		}
	}
	
}
