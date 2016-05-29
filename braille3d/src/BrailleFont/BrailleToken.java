package BrailleFont;

import Svg.SvgBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlusslus on 23.05.2016.
 */
public class BrailleToken {

    private String symbol;
    private List<CirclesPosition> circlesPositions;

    public BrailleToken(String symbol) {
        this.symbol = symbol.toLowerCase();
        this.circlesPositions = new ArrayList<CirclesPosition>();
        try{
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File(System.getProperty("user.dir") + "\\symbols\\" + this.symbol + ".txt")
                            ), "UTF-8"
                    )
            );
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                int separator = line.indexOf(";");
                System.out.print(line.substring(separator-1, separator));
                System.out.print(line.substring(separator, separator+1));

                this.circlesPositions.add(new CirclesPosition(
                                line.substring(separator - 1, separator), line.substring(separator+1, separator+2))
                );
            }
            bufferedReader.close();
        } catch (IOException e) {
               System.err.println(e.getMessage());
        }
    }

    public String getSymbol() {
        return this.symbol;
    }

    private class CirclesPosition {

        private int xPosition;
        private int yPosition;

        public CirclesPosition(String  xPosition, String yPosition) {
            try {
                this.xPosition = Integer.parseInt(xPosition);
                this.yPosition = Integer.parseInt(yPosition);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        public int getxPosition() {
            return xPosition;
        }

        public void setxPosition(int xPosition) {
            this.xPosition = xPosition;
        }

    }

}



