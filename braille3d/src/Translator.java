import java.io.*;

/**
 * Created by vlus on 22.05.2016.
 */
public class Translator {

    private ConfigReader config;
    private String inputText;
    private String svg;

    public Translator(File inputFile) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
        String text = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            text += line;
        }
        bufferedReader.close();
        this.inputText = text;
        this.config = new ConfigReader(System.getProperty("user.dir") + "//braille.params");
    }

    public void convertToSVG() {

        String svg = new String();

        int linesCount = (int)config.getParamByName("lines_count");
        int symbolsCount = (int)config.getParamByName("tokens_count");
        double circleRadio = config.getParamByName("circle_radio");
        double circlesRatio = config.getParamByName("circles_ratio");
        double widthRatio = config.getParamByName("width_ratio");
        double heigthRatio = config.getParamByName("heigth_ratio");



        BrailleTable brailleTable = new BrailleTable();
        int rest = linesCount - this.inputText.length()%linesCount;
        for(int restIndex=0; restIndex<rest; restIndex++) {
            this.inputText += "~";
        }

        for (int tableIndex=0; tableIndex<this.inputText.length(); tableIndex+=symbolsCount) {
            BrailleLine brailleLine = new BrailleLine();
            for(int lineIndex=0; lineIndex<symbolsCount; lineIndex++) {
                int position = tableIndex + lineIndex;
                brailleLine.add(new BrailleToken(this.inputText.substring(position, position + 1)));
            }
            brailleTable.add(brailleLine);
        }
        brailleTable.print();

        /*for(int index=0; index<this.inputText.length(); index+=linesCount*symbolsCount) {

        }*/

        double currentVerticalPos = 0;
        double currentHorizontalPos = 0;

        for(BrailleLine line: brailleTable.getBrailleLines()) {
            for(BrailleToken token:line.getBrailleTokens()) {
                for(int circle=0; circle < token.getCirclesPositions().size(); circle++) {
                    this.svg = "<circle cx=\"50\" cy=\"50\" r=\"40\" stroke=\"black\" stroke-width=\"3\" fill=\"red\" />"
                }

            }
        }

    }




}
