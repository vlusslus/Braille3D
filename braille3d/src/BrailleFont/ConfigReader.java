package BrailleFont;

import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlusslus on 31.05.2016.
 */
public class ConfigReader {

    private class ConfigParam {

        private String paramName;
        private float paramValue;

        public ConfigParam(String paramName, String paramValue) {
            this.paramName = paramName;
            this.paramValue = Float.parseFloat(paramValue);
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }

    public List<ConfigParam> getConfig(String configFile) throws IOException{

        List<ConfigParam> configParams = new ArrayList<ConfigParam>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), "UTF-8"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {

            int indexOfName = line.lastIndexOf(":");
            int indexOfValue = line.lastIndexOf(";");

            configParams.add(new ConfigParam(line.substring(0,indexOfName-1),line.substring(indexOfName,indexOfValue-1)));
        }
        bufferedReader.close();
        return  configParams;
    }

}
