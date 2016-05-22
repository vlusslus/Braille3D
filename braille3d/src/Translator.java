import java.io.*;

/**
 * Created by vlus on 22.05.2016.
 */
public class Translator {

    private String inputText;

    public Translator(File inputFile) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
        String text = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            text += line;
        }
        bufferedReader.close();
        this.inputText = text;
    }

    public void convertTextToSVG() {
        for (int i=0; i<this.inputText.length(); i++) {

        }
    }

}
