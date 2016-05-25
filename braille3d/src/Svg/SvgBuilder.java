package Svg;

/**
 * Created by vlusslus on 23.05.2016.
 */
public class SvgBuilder {

    public static String buildSvg(String symbol) {
        return symbol;
    }

    public String getSvgRectangle(int x, int y) {
        return new String(
                "<rect x=\"" + x + "\" " +
                        "y=\"" + y + "\" " +
                        "width=\"30\" " +
                        "height=\"40\"\n" +
                        "stroke=\"black\" " +
                        "stroke-width=\"1\"  />"
        );
    }

    public String getSvgCircle(int cx, int cy) {
        return new String(
                "<circle cx=\"" + cx + "\" " +
                        "cy=\"" + cy + "\" " +
                        "r=\"3\"\n" +
                        "fill=\"black\" " +
                        "stroke=\"black\" " +
                        "stroke-width=\"1\"  />"
        );
    }

}
