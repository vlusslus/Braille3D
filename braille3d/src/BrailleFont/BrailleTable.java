package BrailleFont;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vlusslus on 25.05.2016.
 */
public class BrailleTable {

    private final int lines = 10;
    private List<BrailleLine> brailleLines;

    public BrailleTable() {
        this.brailleLines = new ArrayList<>();
    }

    public void add(BrailleLine brailleLine) {
        this.brailleLines.add(brailleLine);
    }

    public int getLinesCount() {
        return lines;
    }

    public void print() {
        for(int i=0; i<this.brailleLines.size(); i++) {
            System.out.print("Line #" + i + ": ");
                for(int k=0; k<this.brailleLines.get(i).getBrailleTokens().size(); k++) {
                    System.out.print(" " + this.brailleLines.get(i).getBrailleTokens().get(k).getSymbol());
                }
            System.out.println();
        }
    }
}
