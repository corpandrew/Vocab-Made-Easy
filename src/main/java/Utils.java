import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Utils {

	static int i = 1;
	String customFormatText = "";

	/**
	 * @param address
	 *            of link
	 * @return definition of word
	 * @throws IOException
	 */
	public String getDefintion(String address, String word,
			boolean partOfSpeechSelected, boolean multipleDefinitionSelected,
			boolean customFormatSelected) throws IOException {
		Document doc = null;
		try {
			doc = Jsoup.connect(address).get();
		} catch (HttpStatusException e1) {

		}
		Element definitionContent = null;
		Element partOfSpeechContent = null;

		String partOfSpeech = "";
		String definition = "";
		try {
			definitionContent = doc.getElementsByClass("def-content").get(0);
			definition = definitionContent.text();

			partOfSpeechContent = doc.getElementsByClass("dbox-pg").get(0);
			partOfSpeech = partOfSpeechContent.text();

		} catch (IndexOutOfBoundsException e) {
			definition = "No Definition Found.";
			partOfSpeech = "NoneFound";
		} catch (NullPointerException e1) {
			definition = "No Definition Found.";
			partOfSpeech = "NoneFound";
		}

		if (partOfSpeech.equals("interjection"))
			partOfSpeech = "interj";

		word = word.substring(0, 1).toUpperCase() + word.substring(1);

		String defWithSpeech = word + " -" + " (" + partOfSpeech + ")" + "\t"
				+ format(definition);
		String defWithoutSpeech = word + " -" + "\t" + format(definition);

		if (customFormatSelected) {
			if (multipleDefinitionSelected && partOfSpeechSelected) {
				String def2 = doc.getElementsByClass("def-content").get(1)
						.text();
				String def3 = doc.getElementsByClass("def-content").get(2)
						.text();
				return (customFormat(customFormatText, format(word),
						definition, partOfSpeech)
						+ "\n\t"
						+ format(def2)
						+ "\n\t" + format(def3));
			} else if (multipleDefinitionSelected) {
				String def2 = doc.getElementsByClass("def-content").get(1)
						.text();
				String def3 = doc.getElementsByClass("def-content").get(2)
						.text();
				return (customFormat(customFormatText, word, definition,
						partOfSpeech) + "\n\t" + format(def2) + "\n\t" + format(def3));
			} else if (partOfSpeechSelected) {
				return customFormat(customFormatText, word, definition,
						partOfSpeech);
			} else {
				return customFormat(customFormatText, word, definition,
						partOfSpeech);
			}
		} else {

			if (multipleDefinitionSelected && partOfSpeechSelected) {
				String def2 = doc.getElementsByClass("def-content").get(1)
						.text();
				String def3 = doc.getElementsByClass("def-content").get(2)
						.text();
				return (defWithSpeech + "\n\t" + format(def2) + "\n\t" + format(def3));
			} else if (multipleDefinitionSelected) {
				String def2 = doc.getElementsByClass("def-content").get(1)
						.text();
				String def3 = doc.getElementsByClass("def-content").get(2)
						.text();
				return (defWithoutSpeech + "\n\t" + format(def2) + "\n\t" + format(def3));
			} else {
				return (partOfSpeechSelected ? defWithSpeech : defWithoutSpeech);
			}
		}
	}

	/**
	 * 
	 * @param words
	 *            in an ArrayList of String type
	 * @return definition to the current word its iterating through in ArrayList
	 * @throws IOException
	 */
	public String getDefinitions(ArrayList<String> words,
			boolean partOfSpeechSelected, boolean multipleDefinitionSelected,
			boolean customFormatSelected) throws IOException {
		String definitionText = "";
		for (String word : words) {
			try {
				definitionText += (getDefintion(
						"http://dictionary.reference.com/browse/" + word
								+ "?s=t", word, partOfSpeechSelected,
						multipleDefinitionSelected, customFormatSelected) + "\n");
				i++;
			} catch (HttpStatusException e) {
				definitionText += "No Definition Found.\n";
				e.printStackTrace();
			}
		}
		return definitionText;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public String format(String str) {
		if (str.contains(";"))
			str = str.substring(0, str.indexOf(';')) + '.';
		if (str.startsWith("("))
			str = str.substring(1, str.indexOf(')'));
		if (str.contains(":"))
			str = str.substring(0, str.indexOf(':')) + '.';

		str = str.substring(0, 1).toUpperCase() + str.substring(1);
		return str;
	}

	/**
	 * @param formatIn
	 * @param word
	 * @param def
	 * @param partOfSpeech
	 * @return
	 */
	public String customFormat(String formatIn, String word, String def,
			String partOfSpeech) {
		if (formatIn.contains("@tab"))
			formatIn = formatIn.replace("@tab", "\t");
		if (formatIn.contains("@def"))
			formatIn = formatIn.replace("@def", def);
		if (formatIn.contains("@word"))
			formatIn = formatIn.replace("@word", word);
		if (formatIn.contains("@speech"))
			formatIn = formatIn.replace("@speech", partOfSpeech);
		if (formatIn.contains("@num"))
			formatIn = formatIn.replace("@num", "" + i);
		return formatIn;
	}
}
