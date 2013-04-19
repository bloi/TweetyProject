package classify;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TweetyAttributes {
	private HashMap<String, Integer> positiveKeywordsMap;
	private HashMap<String, Integer> negativeKeywordsMap;
	private ArrayList<ArrayList<String>> tweetySubsets;
	private ArrayList<String> posArray;
	private ArrayList<String> negArray;
	
	public TweetyAttributes() {
		positiveKeywordsMap = new HashMap<String, Integer>();
		negativeKeywordsMap = new HashMap<String, Integer>();
		tweetySubsets = new ArrayList<ArrayList<String>>();
		posArray = new ArrayList<String>();
		negArray = new ArrayList<String>();
	}
	
	public void createSubsets(FileReader positiveFile, FileReader negativeFile) {
		
		Scanner posScanner = new Scanner(positiveFile);
		Scanner negScanner = new Scanner(negativeFile);
		BufferedWriter subsetsWriter = null;
		ArrayList<String> tweetSubset;
		int i, j, subsetSize;
		String line = "";
		
		try {
			subsetsWriter = new BufferedWriter(new FileWriter("subsets.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (posScanner.hasNextLine()) {
			line = posScanner.nextLine();
			posArray.add(line);
		}

		while (negScanner.hasNextLine()) {
			line = negScanner.nextLine();
			negArray.add(line);
		}

		Collections.shuffle(posArray);
		Collections.shuffle(negArray);
		
		try {
			subsetsWriter.write("----Tweets Subsets----\n");
			subsetsWriter.write(posArray.size() + "   " + negArray.size()
					+ "\n");

			for (i = 0; i < 10; i++) {

				tweetSubset = new ArrayList<String>();
				subsetSize = i * 50 + 50;

				subsetsWriter.write("    Subset " + (i + 1) + ":\n");

				subsetsWriter.write("      Pos:\n");
				for (j = i * 50; j < subsetSize; j++) {
					tweetSubset.add(posArray.get(j));
					subsetsWriter.write("        "
							+ tweetSubset.get(j - 50 * i) + "\n");
				}
				subsetsWriter.write("      Neg:\n");
				for (j = i * 50; j < subsetSize; j++) {
					tweetSubset.add(negArray.get(j));
					subsetsWriter.write("        "
							+ tweetSubset.get((j + 50) - 50 * i) + "\n");
				}

				tweetySubsets.add(tweetSubset);
			}
			positiveFile.close();
			negativeFile.close();
			subsetsWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable(Integer currentTest) {
		
		String keyWord = "", checkGood = "";
		Scanner tweetScanner;
		boolean isGood = true;
		
		for(ArrayList<String> subset : tweetySubsets){
			
			if(subset == tweetySubsets.get(currentTest)){
				continue;
			}
			
			for(String tweet : subset){
				
				tweetScanner = new Scanner(tweet);
				
				checkGood = tweetScanner.next();				
				if(checkGood.equals("1")){
					isGood = true;
				}
				else{
					isGood = false;
				}
				
				while(tweetScanner.hasNext()){
				
					keyWord = tweetScanner.next();
					
					if(isGood){
						if(!positiveKeywordsMap.containsKey(keyWord)){
							positiveKeywordsMap.put(keyWord, 1);
						}
						else{
							positiveKeywordsMap.put(keyWord, (positiveKeywordsMap.get(keyWord) + 1));
						}
					}
					else{
						if(!negativeKeywordsMap.containsKey(keyWord)){
							negativeKeywordsMap.put(keyWord, 1);
						}
						else{
							negativeKeywordsMap.put(keyWord, (negativeKeywordsMap.get(keyWord) + 1));
						}
					}
				}	
			}
		}
		
		Integer print = 0;
		
		System.out.println("Positive Map:");
		for(String key : positiveKeywordsMap.keySet()){
			print = positiveKeywordsMap.get(key);
			System.out.println(key + " " + print);
		}
		
		System.out.println("Negative Map:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		for(String key : negativeKeywordsMap.keySet()){
			print = negativeKeywordsMap.get(key);
			System.out.println(key + " " + print);
		}
	}
}
