import java.io.IOException;

/**
 * Created by HWILKHU on 25/06/2017.
 */
public class Application {

    /**
     * Main method
     * creates the user interface
     * @param args
     */
    public static void main(String[] args){
        try {
            LoadCEDictData loadCEDictData = new LoadCEDictData("cedict_ts.u8");
            new CEDictUI(
                    new Controller(loadCEDictData)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
