import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by HWILKHU on 25/06/2017.
 *
 */
public class Controller implements CEDictController{

    private LoadCEDictData ceDictData;

    /**
     * constructor
     * @param ceDictData - LoadCEDictData class which contains the data in necessary structures
     */
    public Controller(LoadCEDictData ceDictData) {
        this.ceDictData = ceDictData;
    }

    @Override
    public String summaryStatistics() {
        StringBuilder statistics = new StringBuilder();

        statistics.append("CC-CEDICT Data Statistics: \n")
                .append("Unique Traditional Chinese words: ").append(ceDictData.getTraditionalChinese().size()).append("\n")
                .append("Unique Simplified Chinese words: ").append(ceDictData.getSimplifiedChinese().size()).append("\n")
                .append("Unique PinYin transliterations: ").append(ceDictData.getPinYin().size()).append("\n")
                .append("Unique English meanings: ").append(ceDictData.getEnglish().size()).append("\n");

        TreeSet<String> traditionalChineseWordsTree = new TreeSet<>(ceDictData.getTraditionalChinese());
        Iterator<String> traditionalChineseWords = ceDictData.getTraditionalChinese().iterator();

        int prefixes = 0;

        while (traditionalChineseWords.hasNext()){

            String prefix = traditionalChineseWords.next();
            traditionalChineseWordsTree.remove(prefix);
            String chineseWord = traditionalChineseWordsTree.ceiling(prefix);
            if(chineseWord != null) {
                if (chineseWord.startsWith(prefix) && !chineseWord.equals(prefix)) {
                    prefixes++;
                }
            }
        }

        statistics.append("Number of Traditional Chinese prefixes : ").append(prefixes).append("\n");

        return statistics.toString();
    }

    @Override
    public String lookupChinese(String chineseWord) {
        if (ceDictData.getChineseToEnglish().get(chineseWord) == null){
            return "No translation found for " + chineseWord;
        }
        return ceDictData.getChineseToEnglish().get(chineseWord);
    }

    @Override
    public String lookupEnglish(String English) {
        HashMap<String,HashSet<String>> englishToChinese = ceDictData.getEnglishToChinese();
        StringBuilder translations = new StringBuilder();

        //ignores brackets and returns all translations matching
        for(String key : englishToChinese.keySet()){
            String cleanKey = key.replaceAll("\\(.*?\\)","").trim();
            if (English.equalsIgnoreCase(cleanKey)){
                translations.append(key).append(": ").append(englishToChinese.get(key)).append("\n");
            }
        }

        if (translations.length() == 0){
            return "No translation found for " + English;
        }
        return translations.toString();
    }

    @Override
    public String lookupPinyin(String pinyin) {
        if (ceDictData.getPinYinToChinese().get(pinyin.toLowerCase()) == null){
            return "No transliteration found for " + pinyin;
        }
        return ceDictData.getPinYinToChinese().get(pinyin.toLowerCase());
    }

    @Override
    public String lookupChinesePrefix(String chinesePrefix) {
        StringBuilder wordsWithPrefix = new StringBuilder();
        Iterator<String> traditionalChineseWords = ceDictData.getTraditionalChinese().iterator();

        wordsWithPrefix.append(chinesePrefix).append(": ");

        while (traditionalChineseWords.hasNext()){
            String chineseWord = traditionalChineseWords.next();
            if(chineseWord.startsWith(chinesePrefix)){
                wordsWithPrefix.append(chineseWord).append(", ");
            }
        }

        if (wordsWithPrefix.toString().equals(chinesePrefix + ": ")){
            return "Found no words with prefix " + chinesePrefix;
        }

        return wordsWithPrefix.substring(0,(wordsWithPrefix.length() - 2));
    }
}
