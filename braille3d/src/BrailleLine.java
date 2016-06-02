import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlusslus on 25.05.2016.
 */
public class BrailleLine {

    private List<BrailleToken> brailleTokens;

    public BrailleLine() {
        this.brailleTokens = new ArrayList<BrailleToken>();
    }

    public void add(BrailleToken brailleToken) {
        this.brailleTokens.add(brailleToken);
    }

    public List<BrailleToken> getBrailleTokens() {
        return this.brailleTokens;
    }
}


