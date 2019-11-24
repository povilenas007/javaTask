package pk.com.javaTask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {

	public List<String> getWords(String text) {
		List<String> allMatches = new ArrayList<String>();

		String wordPattern = "\\w+";
		Pattern r = Pattern.compile(wordPattern);
		Matcher m = r.matcher(text);

		while (m.find()) {
			allMatches.add(m.group());
		}

		return allMatches;
	}

}
