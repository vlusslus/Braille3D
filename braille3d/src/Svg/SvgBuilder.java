package Svg;

import jdk.internal.util.xml.impl.Pair;

import java.util.Objects;

/**
 * Created by vlusslus on 23.05.2016.
 */
public class SvgBuilder {

    private int currentLinePosition;
    private int currentTokenPosition;
    private StringBuilder svgBuilder;

    private final double circleRadio = 30;
    private final double rectangleWidth = 300;
    private final double rectangleHeight = 400;

    public SvgBuilder() {
        this.currentLinePosition = 0;
        this.currentTokenPosition = 0;
        this.svgBuilder = new StringBuilder();
    }

    public String buildSvg(String symbol) {
        //Пока непонятно как внедрить символ "Следуюший заглавный". Нужно пересматривать архитектуры таблицы.
        switch (new String(symbol.toLowerCase())) {
            case "а":
        }
        return symbol;
    }

    public void buildRectangle() {

        this.svgBuilder.append(
                "<rect x=\"" + this.currentTokenPosition*this.rectangleWidth + "\" " +
                        "y=\"" + this.currentLinePosition*this.rectangleHeight + "\" " +
                        "width=\"" + this.rectangleWidth + "\" " +
                        "height=\"" + this.rectangleHeight + "\"\n" +
                        "stroke=\"black\" " +
                        "stroke-width=\"5\"  />"
        );

        /*return new String(
                "<rect x=\"" + x + "\" " +
                        "y=\"" + y + "\" " +
                        "width=\"30\" " +
                        "height=\"40\"\n" +
                        "stroke=\"black\" " +
                        "stroke-width=\"1\"  />"
        );*/
    }

    public void buildCircle() {

        this.svgBuilder.append(
                "<circle cx=\"" + (this.currentTokenPosition*this.rectangleWidth*this.currentLinePosition*this.rectangleHeight + 100) + "\" " +
                        "cy=\"" + (this.currentLinePosition*this.rectangleHeight + 100) + "\" " +
                        "r=\"3\"\n" +
                        "fill=\"black\" " +
                        "stroke=\"black\" " +
                        "stroke-width=\"1\"  />"
        );

        /*return new String(
                "<circle cx=\"" + cx + "\" " +
                        "cy=\"" + cy + "\" " +
                        "r=\"3\"\n" +
                        "fill=\"black\" " +
                        "stroke=\"black\" " +
                        "stroke-width=\"1\"  />"
        );*/
    }

}
