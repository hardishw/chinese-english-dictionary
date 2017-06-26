import java.util.*;
import java.util.function.Consumer;

/**
 * Created by HWILKHU on 25/06/2017.
 */
public class Controller implements CEDictController{

    LoadCEDictData ceDictData;


    public Controller(LoadCEDictData ceDictData) {
        this.ceDictData = ceDictData;
    }

    @Override
    public String summaryStatistics() {
        StringBuilder statistics = new StringBuilder();

        statistics.append("CC-CEDICT Data Statistics: \n");
        statistics.append("Unique Traditional Chinese words: " + ceDictData.getTraditionalChinese().size() + "\n");
        statistics.append("Unique Simplified Chinese words: " + ceDictData.getSimplifiedChinese().size() + "\n");
        statistics.append("Unique PinYin transliterations: " + ceDictData.getPinYin().size() + "\n");
        statistics.append("Unique English meanings: " + ceDictData.getEnglish().size() + "\n");

        TreeSet<String> traditionalChineseWordsTree = new TreeSet<String>(ceDictData.getTraditionalChinese());
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

        statistics.append("Number of Traditional Chinese prefixes : " + prefixes + "\n");

        return statistics.toString();
    }

    @Override
    public String lookupChinese(String chineseWord) {
        return ceDictData.getChineseToEnglish().get(chineseWord);
    }

    @Override
    public String lookupEnglish(String English) {
        return (ceDictData.getEnglishToChinese().get(English.toLowerCase())).toString();
    }

    @Override
    public String lookupPinyin(String pinyin) {
        return ceDictData.getPinYinToChinese().get(pinyin.toLowerCase());
    }

    @Override
    public String lookupChinesePrefix(String chinesePrefix) {
        StringBuilder wordsWithPrefix = new StringBuilder();
        Iterator<String> traditionalChineseWords = ceDictData.getTraditionalChinese().iterator();

        if(ceDictData.getTraditionalChinese().contains(chinesePrefix)){
            wordsWithPrefix.append(chinesePrefix + ": ");
        }

        while (traditionalChineseWords.hasNext()){
            String chineseWord = traditionalChineseWords.next();
            if(chineseWord.startsWith(chinesePrefix)){
                wordsWithPrefix.append(chineseWord + ", ");
            }
        }

        return wordsWithPrefix.substring(0,(wordsWithPrefix.length() - 2)).toString();
    }
}