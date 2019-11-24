package pk.com.javaTask;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pk.com.javaTask.util;
import pk.com.javaTask.Words;

public class App {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		String firstArg = "";
		if (args.length > 0) {
			try {
				firstArg = args[0];
			} catch (NumberFormatException e) {
				System.err.println("Directory to parse .txt files from:" + args[0]);
			}
		}

		if (firstArg.equals(null) || firstArg.isEmpty()) {
			firstArg = "";
			
			//worker("C:\\Users\\Povilas\\Desktop\\Lorem Ipsum");
			
			System.exit(0);
		} else {
			worker(firstArg);
		}

		long stopTime = System.currentTimeMillis();
		System.out.println("Total runtime: " + (startTime - stopTime)*-1+" millis");
	}

	public static void worker(String inputDir) {

		String inputDirs = inputDir;
		String resultDirs = "\\results";
		int totalWords = 0;
		int notExcludedWords = 0;
		int excludedWords = 0;

		List<String> filesList = new LinkedList<String>();
		List<String> allWords = new ArrayList<String>();
		List<String> excludeList = new ArrayList<String>();
		ArrayList<String> countedWords = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();

		util util = new util();
		Words words = new Words();

		util.createDir(inputDirs, resultDirs);

		excludeList = words.getWords(util.readFile(inputDirs + "\\exclude.txt"));

		filesList = util.listFiles(inputDirs);

		for (String fileList : filesList) {
			allWords.addAll(words.getWords(util.readFile(fileList)));
		}

		Collections.sort(allWords, Collator.getInstance());

		totalWords = allWords.size();

		for (String word : allWords) {
			String nextWord = word.toLowerCase();
			if (countedWords.contains(nextWord)) {
				int index = countedWords.indexOf(nextWord);
				count.set(index, count.get(index) + 1);
			} else {
				countedWords.add(nextWord);
				count.add(1);
			}
		}

		for (int i = 0; i < countedWords.size(); i++) {

			int check = 0;
			if (excludeList.contains(countedWords.get(i))) {
				check = 1;
			}

			if (check == 0) {
				util.writeFile(inputDirs + resultDirs + "\\",
						"FILE_" + countedWords.get(i).substring(0, 1).toUpperCase(),
						countedWords.get(i) + " " + count.get(i));
				notExcludedWords = notExcludedWords+count.get(i);
			} else {
				util.writeFile(inputDirs + resultDirs + "\\",
						"exclude_FILE" + countedWords.get(i).substring(0, 1).toUpperCase(),
						countedWords.get(i) + " " + count.get(i));
				excludedWords = excludedWords + count.get(i);
			}
		}
		
		System.out.println("Total words processed: " + totalWords);
		System.out.println("Total good words:      " + notExcludedWords);
		System.out.println("Total words excluded:  " + excludedWords);
		

	}
}
