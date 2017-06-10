/**
 * 
 */

/**
 * A controller interface for working with the TUI class CEDictUI.
 * 
 * @author S H S Wong
 * @version 05/05/2017
 */
public interface CEDictController {
	
	/**
	 * Returns a String which contains summary statistics about this CC-CEDICT application.
	 * The data includes:
	 * - number of unique traditional Chinese words
	 * - number of unique simplified Chinese words
	 * - number of unique pinyin entries
	 * - number of unique English meanings
	 * - number of potential Chinese prefixes (in traditional characters) recorded in the lookup table
	 * - number of words associated to prefixes
	 * - number of actual prefixes (ie prefixes with at least one associated word)
	 * 
	 * @return a String representation of the summary statistics
	 */
	String summaryStatistics();

	/**
	 * Lookup a specified Chinese word.
	 * 
	 * @param word	the specified Chinese word
	 * @return a String representation of the results
	 */
	String lookupChinese(String chineseWord);
	
	/**
	 * Lookup a specified English word or phrase.
	 * @param meaning	the specified English word or phrase
	 * @return	a String representation of the results
	 */
	String lookupEnglish(String English);
	
	/**
	 * Lookup a specified PinYin transliteration.
	 * @param pinyin	the specified PinYin
	 * @return	a String representation of the results
	 */
	String lookupPinyin(String pinyin);
	
	/**
	 * Lookup a specified Chinese prefix (represented in traditional characters).
	 * @param prefix	a Chinese prefix represented in traditional characters
	 * @return	a String representation of results
	 */
	String lookupChinesePrefix(String chinesePrefix);

	
}
