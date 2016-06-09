import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by vlusslus on 08.06.2016.
 */
public class SvgBuilder {

    private BufferedWriter svgWriter;

    public SvgBuilder(int tableIndex) {
        File svgFile = new File(System.getProperty("user.dir") + "\\output\\" + "table" + tableIndex + ".svg");
        try {
            if(svgFile.createNewFile()) {
                FileWriter fw = new FileWriter(svgFile);
                this.svgWriter = new BufferedWriter(fw);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void write(String entity) {
        try {
            this.svgWriter.write(entity);
            this.svgWriter.write("\r\n");
            this.svgWriter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        try {
            this.svgWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
