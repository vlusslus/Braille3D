package BrailleFont;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlusslus on 25.05.2016.
 */
public class BrailleLine {

    private int lineNumber;
    private List<BrailleToken> brailleTokens;

    public BrailleLine(int lineNumber) {
        this.brailleTokens = new ArrayList<>();
        this.lineNumber = lineNumber;
    }

    public void add(BrailleToken brailleToken) {
        this.brailleTokens.add(brailleToken);
    }
}


