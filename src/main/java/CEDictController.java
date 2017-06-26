/**
 * A controller interface for working with the TUI class CEDictUI.
 * 
 * @author S H S Wong
 * @version 11/06/2017
 */
public interface CEDictController {
	
	/**
	 * Returns a String which contains summary statistics about this CC-CEDICT application.
	 * The data may include:
	 * - number of unique traditional Chinese words
	 * - number of unique simplified Chinese words
	 * - number of unique pinyin entries
	 * - number of unique English meanings
	 * - number of potential Chinese prefixes (in traditional characters) recorded in the lookup table
	 * 
	 * @return a String representation of the summary statistics
	 */
	String summaryStatistics();

	/**
	 * Looks up a specified Chinese word.
	 * 
	 * @param chineseWord	the specified Chinese word
	 * @return a String representation of the lookup results
	 */
	String lookupChinese(String chineseWord);
	
	/**
	 * Looks up a specified English word or phrase.
	 * 
	 * @param english	the specified English word or phrase
	 * @return	a String representation of the lookup results
	 */
	String lookupEnglish(String english);
	
	/**
	 * Looks up a specified PinYin transliteration.
	 * 
	 * @param pinyin	the specified PinYin transliteration
	 * @return	a String representation of the lookup results
	 */
	String lookupPinyin(String pinyin);
	
	/**
	 * Looks up a specified Chinese prefix (represented in traditional Chiense characters).
	 * 
	 * @param chinesePrefix	a Chinese prefix represented in traditional characters
	 * @return	a String representation of lookup results
	 */
	String lookupChinesePrefix(String chinesePrefix);

	
}
