package com.techelevator.inventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;

public class FileLogWriter implements LogWriter {

	@Override
	public void write(String lines) throws Exception {

		File outputFile = new File("Log.txt");

		try (FileWriter writer = new FileWriter(outputFile, true);
				BufferedWriter buffered = new BufferedWriter(writer)) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String audit = timestamp + lines;
			buffered.write(audit);
			buffered.newLine();
		} catch (Exception e) {
			System.out.println("Unknown error has occured.");
		}
	}

}
