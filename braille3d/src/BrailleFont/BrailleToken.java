package BrailleFont;

import Svg.SvgBuilder;

/**
 * Created by vlusslus on 23.05.2016.
 */
public class BrailleToken {
    
    private String symbol;
    private String svg;

    public BrailleToken(String symbol) {
        this.symbol = symbol;
        this.svg = SvgBuilder.buildSvg(symbol);
    }

}
