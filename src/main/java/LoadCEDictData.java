import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by HWILKHU on 25/06/2017.
 *
 */
public class LoadCEDictData {

    private HashSet<String> traditionalChinese = new HashSet<>(200000);
    private HashSet<String> simplifiedChinese = new HashSet<>();
    private HashSet<String> pinYin = new HashSet<>();
    private HashSet<String> english = new HashSet<>();

    private HashMap<String,String> pinYinToChinese = new HashMap<>();
    private HashMap<String,String> chineseToEnglish = new HashMap<>();
    private HashMap<String,HashSet<String>> englishToChinese = new HashMap<>();

    /**
     * reads dictionary file and breaks it apart into necessary data structures for translations
     * @param fileLocation - where the dictionary data file is located
     * @throws IOException - gets file which may throw file not find and also reads file
     */
    LoadCEDictData(String fileLocation) throws IOException {
        File cedictFile = new File(this.getClass().getClassLoader().getResource(fileLocation).getFile());
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(cedictFile));
        while ((line = reader.readLine()) != null){
            //ignores commented lines
            if(line.startsWith("#")){
                continue;
            }

            //splits the line by spaces to extract chinese characters
            String[] chinese = line.split("\\s");

            traditionalChinese.add(chinese[0]);
            simplifiedChinese.add(chinese[1]);

            String chineseTranslations = chinese[0] + ", " + chinese[1];

            //splits by [] to extract pinyin transliteration
            String[] pinYin = line.split("\\[");
            pinYin = pinYin[1].split("]");
            String pinYinTransliteration = ("["+pinYin[0]+"]").toLowerCase();

            this.pinYin.add(pinYinTransliteration);

            //counts the number of english delimiters and splits by / to extract english translations
            int numEnglishMeanings = (line.length() - line.replace("/","").length());
            String[] english = line.split("/");
            StringBuilder englishTranslations = new StringBuilder();

            for (int i=1;i < numEnglishMeanings;i++){
                String englishValue = english[i];
                String englishValueLower = englishValue.toLowerCase();
                this.english.add(englishValue);
                englishTranslations.append(englishValue).append(", ");

                //checks if value has already been added and amends it
                if(englishToChinese.containsKey(englishValueLower)){
                    addEnglishToChineseTranslation(englishValueLower,chinese,englishToChinese.get(englishValueLower));

                }else {
                    addEnglishToChineseTranslation(englishValueLower,chinese,new HashSet<>());
                }
            }
            String chineseEnglishTranslations = englishTranslations.substring(0,(englishTranslations.length() - 2));

            pinYinToChinese.put(pinYinTransliteration,chineseTranslations);
            chineseToEnglish.put(chinese[0],chineseEnglishTranslations);
            chineseToEnglish.put(chinese[1],chineseEnglishTranslations);



        }
    }


    /**
     * appends a current translation with more values
     * @param english - the english meanings
     * @param translations - chinese translations
     * @param currentValue - current value for key
     */
    private void addEnglishToChineseTranslation(String english,String[] translations, HashSet<String> currentValue){
        currentValue.add(translations[0]);
        currentValue.add(translations[1]);
        englishToChinese.put(english,currentValue);
    }

    /**
     * returns all traditional chinese charaters in CEDict file
     * @return - HashSet of all Chinese traditional words in file
     */
    public HashSet<String> getTraditionalChinese() {
        return traditionalChinese;
    }

    /**
     * returns all simplified chinese charaters in CEDict file
     * @return - HashSet of all Chinese simplified words in file
     */
    public HashSet<String> getSimplifiedChinese() {
        return simplifiedChinese;
    }

    /**
     *returns all pinyin transliterations in CEDict file
     * @return - HashSet of all pinyin transliterations in file
     */
    public HashSet<String> getPinYin() {
        return pinYin;
    }

    /**
     *returns all english meanings in CEDict file
     * @return - HashSet of all english translations in file
     */
    public HashSet<String> getEnglish() {
        return english;
    }

    /**
     *
     * @return - HashMap Key pinyin Value both traditional and simplified transliterations
     */
    public HashMap<String, String> getPinYinToChinese() {
        return pinYinToChinese;
    }

    /**
     *
     * @return - HashMap Key traditional or simplified meanings Value english meanings
     */
    public HashMap<String, String> getChineseToEnglish() {
        return chineseToEnglish;
    }

    /**
     *
     * @return - HashMap Key english meanings Value both traditional and simplified meanings
     */
    public HashMap<String, HashSet<String>> getEnglishToChinese() {
        return englishToChinese;
    }
}
