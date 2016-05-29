import BrailleFont.BrailleLine;
import BrailleFont.BrailleTable;
import BrailleFont.BrailleToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void convertToSVG() {

        BrailleTable brailleTable = new BrailleTable();
        int rest = brailleTable.getLinesCount() - this.inputText.length()%brailleTable.getLinesCount();
        for(int restIndex=0; restIndex<rest; restIndex++) {
            this.inputText += "~";
        }

        for (int tableIndex=0; tableIndex<this.inputText.length(); tableIndex+=brailleTable.getLinesCount()) {
            BrailleLine brailleLine = new BrailleLine();
            for(int lineIndex=0; lineIndex<brailleLine.getSymbolsCount(); lineIndex++) {
                int position = tableIndex + lineIndex;
                brailleLine.add(new BrailleToken(this.inputText.substring(position, position + 1)));
            }
            brailleTable.add(brailleLine);
        }
        brailleTable.print();
    }



}
