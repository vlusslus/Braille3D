import java.io.File;
import java.io.IOException;

/**
 * Created by vlus on 22.05.2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Convert text to printable format!");
        try {
            Translator translator = new Translator(new File(System.getProperty("user.dir") + "//input.txt"));
            translator.convertTextToSVG();
        } catch (IOException e) {
            System.out.println("Loading file exception: " + e.getMessage());
        }
    }

}
