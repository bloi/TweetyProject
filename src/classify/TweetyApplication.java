package classify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TweetyApplication {

	public static String positiveFileName = "";
	public static String negativeFileName = "";
	public static Scanner inLine = null;
	public static BufferedReader inReader = new BufferedReader(new InputStreamReader(
			System.in));

	public static void main(String[] args) {

		FileReader positiveFile = null;
		FileReader negativeFile = null;
		TweetyAttributes tweetyAttributes = new TweetyAttributes();
		TweetyExperiment tweetyExperiment = new TweetyExperiment();
		
		System.out.println("--Tweety File Parser--");
		
		System.out.println("Please specify the name of a .txt file containing POSITIVE line-separated tweets:");		
		positiveFile = getFile();
		
		System.out.println("Please specify the name of a .txt file containing NEGATIVE line-separated tweets:");
		negativeFile = getFile();

		tweetyAttributes.createSubsets(positiveFile, negativeFile);
		tweetyAttributes.createTable(1);

		try {
			positiveFile.close();
			negativeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static FileReader getFile() {
		
		FileReader file = null;
		String fileName = "";

		while (file == null) {
			fileName = getFileName();
			try {
				file = new FileReader(fileName);
			} catch (FileNotFoundException e) {
				System.err.println("File not found, try again...\n");
			}
		}
		
		return file;
	}

	public static String getFileName() {
		String fName = "";

		try {
			fName = inReader.readLine();
		} catch (IOException e) {
			System.err.println("Error reading input. Exitting.");
			System.exit(1);
		}
		
		return fName;
	}

}