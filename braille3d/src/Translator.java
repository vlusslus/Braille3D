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

    public void convertToSVG() throws IOException{

        String svg = new String();

        String brailleTableParams = new String();
        brailleTableParams += "400 150 10 10 5" + System.getProperty("line.separator");

        File paramsFile = new File("C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\outparams.txt");
        FileWriter fw = new FileWriter(paramsFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(brailleTableParams);
        brailleTableParams = null;

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
            tokenIndex = 0;
            for(BrailleToken token:line.getBrailleTokens()) {
                this.svg += "<rect " +
                        "x=\"" + (int)getRX(tokenIndex) + "\" " +
                        "y=\"" + (int)getRY(lineIndex) + "\" " +
                        "width=\"" + (int)(this.widthRatio + 1 * this.circleRadio + this.circlesRatio) + "\" " +
                        "height=\"" + (int)(this.heigthRatio + 2 * this.circleRadio + 2 * this.circlesRatio) + "\" " +
                        "style=\"fill:none;stroke:black;stroke-width:5\" />";
                for(int circleIndex=0; circleIndex < token.getCirclesPositions().size(); circleIndex++) {
                    int cx = (int)getCX(tokenIndex, token.getCirclesPositions().get(circleIndex).getXPosition());
                    int cy = (int)getCY(lineIndex, token.getCirclesPositions().get(circleIndex).getYPosition());
                    brailleTableParams = (cx + " " + cy + System.getProperty("line.separator"));
                    bw.write(brailleTableParams);
                    this.svg += "<circle " +
                            "cx=\"" + cx + "\" " +
                            "cy=\"" + cy + "\" " +
                            "r=\"" +  (int)circleRadio + "\" " +
                            "stroke=\"black\" " +
                            "stroke-width=\"1\" " +
                            "fill=\"black\" /> ";
                    /*this.svg += "<rect " +
                            "x=\"" + (int)getRX(tokenIndex) + "\" " +
                            "y=\"" + (int)getRY(lineIndex) + "\" " +
                            "width=\"" + (int)(this.widthRatio + 1 * this.circleRadio + this.circlesRatio) + "\" " +
                            "height=\"" + (int)(this.heigthRatio + 2 * this.circleRadio + 2 * this.circlesRatio) + "\" " +
                            "style=\"fill:none;stroke:black;stroke-width:5\" />";*/
                }
                currentHorizontalPos += widthRatio;
                tokenIndex++;
            }
            lineIndex++;
            currentVerticalPos += heigthRatio;
        }

        bw.close();
        /*File paramsFile = new File("C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\outparams.txt");
        if(paramsFile.exists()) {
            try{
                FileWriter fw = new FileWriter(paramsFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(brailleTableParams);
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }*/

        File svgFile = new File("C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\out.txt");
        if(svgFile.exists()) {
            try{
                FileWriter fw2 = new FileWriter(svgFile);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                bw2.write(this.svg);
                bw2.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        /*try{
            Runtime.getRuntime("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe C:\\Users\\vlusslus\\IdeaProjects\\Braille3D\\out.svg");
        }*/


    }

    private double getCX(int tokenIndex, int circleX) {

        return this.widthRatio/2 + tokenIndex * (this.widthRatio + 2 * this.circleRadio + this.circlesRatio) + (circleX - 1) * (this.circlesRatio + this.circleRadio);
    }

    private double getCY(int lineIndex, int circleY) {

        return this.heigthRatio/2 + lineIndex * (this.heigthRatio + 3 * this.circleRadio + 2 * this.circlesRatio) + (circleY - 1) * (this.circlesRatio + this.circleRadio);
    }

    private double getRX(int tokenIndex) {
        return tokenIndex * (this.widthRatio + 2 * this.circleRadio + this.circlesRatio);
    }

    private double getRY(int lineIndex) {
        return lineIndex * (this.heigthRatio + 3 * this.circleRadio + 2 * this.circlesRatio);
    }




}
