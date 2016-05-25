package BrailleFont;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlusslus on 25.05.2016.
 */
public class BrailleTable {

    private List<BrailleLine> brailleLines;

    public BrailleTable() {
        this.brailleLines = new ArrayList<>();
    }

    public void add(BrailleLine brailleLine) {
        this.brailleLines.add(brailleLine);
    }
}
