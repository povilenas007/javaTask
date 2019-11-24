package pk.com.javaTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class util {

	public boolean createDir(String directory, String resultDir) {
		return new File(directory + resultDir).mkdirs();
	}

	public List<String> listFiles(String directory) {
		List<String> inputFilePaths = new LinkedList<String>();
		String inputDir = directory;

		File directoryPath = new File(inputDir);

		File[] files = directoryPath.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return !name.contains("exclude") && name.endsWith(".txt");
			}
		});

		for (File file : files) {
			inputFilePaths.add(file.toString());
		}

		return inputFilePaths;
	}

	public String readFile(String filePath) {

		StringBuffer content = new StringBuffer();
		try {
			String text;
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((text = reader.readLine()) != null) {
				content.append(text);
				content.append("\n");

			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();

	}

	public void writeFile(String directory, String fileName, String content) {

		Charset utf8 = StandardCharsets.UTF_8;
		List<String> lines = Arrays.asList(content);

		try {
			Files.write(Paths.get(directory + fileName + ".txt"), lines, utf8, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
