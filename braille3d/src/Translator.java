import java.io.*;

/**
 * Created by vlus on 22.05.2016.
 */
public class Translator {

    private ConfigReader config;
    private String inputText;
    private String svg;

    private int linesCount;
    private int symbolsCount;
    private double circleRadio;
    private double circlesRatio;
    private double widthRatio;
    private double heigthRatio;




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

        this.linesCount = (int)config.getParamByName("lines_count");
        this.symbolsCount = (int)config.getParamByName("tokens_count");
        this.circleRadio = config.getParamByName("circle_radio");
        this.circlesRatio = config.getParamByName("circles_ratio");
        this.widthRatio = config.getParamByName("width_ratio");
        this.heigthRatio = config.getParamByName("heigth_ratio");
    }

    public void convertToSVG() {

        String svg = new String();




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

        double currentVerticalPos = 100;
        double currentHorizontalPos = 100;

        int tokenIndex = 0;
        int lineIndex = 0;

        for(BrailleLine line: brailleTable.getBrailleLines()) {
            for(BrailleToken token:line.getBrailleTokens()) {
                for(int circleIndex=0; circleIndex < token.getCirclesPositions().size(); circleIndex++) {
                    this.svg += "<circle " +
                            "cx=\"" + (int)getCX(tokenIndex, token.getCirclesPositions().get(circleIndex).getXPosition()) + "\"" +
                            "cy=\"" + (int)getCY(lineIndex, token.getCirclesPositions().get(circleIndex).getYPosition()) + "\"" +
                            "r=\"" +  (int)circleRadio + "\" " +
                            "stroke=\"black\" " +
                            "stroke-width=\"1\" " +
                            "fill=\"black\" />";
                }
                currentHorizontalPos += widthRatio;
                tokenIndex++;
            }
            lineIndex++;
            currentVerticalPos += heigthRatio;
        }


        File svgFile = new File("C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\out.txt");
        if(svgFile.exists()) {
            try{
                FileWriter fw = new FileWriter(svgFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(this.svg);
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        /*try{
            Runtime.getRuntime("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\out.svg");
        }*/


    }

    private double getCX(int tokenIndex, int circleX) {

        return tokenIndex * (this.widthRatio + 2 * this.circleRadio + this.circlesRatio) + (circleX - 1) * (this.circlesRatio + this.circleRadio);

    }

    private double getCY(int lineIndex, int circleY) {

        return lineIndex * (this.heigthRatio + 3 * this.circleRadio + 2 * this.circleRadio) + (circleY - 1) * (this.circlesRatio + this.circleRadio);
    }




}
