/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author wesley
 */
public class PropertiesReader implements IReader<Map<String, Object>> {

    private Logger log;

    /**
     * @return the log
     */
    public Logger getLog() {
        if (Objects.isNull(log)) {
            log = Logger.getLogger(this.getClass().getName());
        }
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(Logger log) {
        this.log = log;
    }

    /**
     * Constructor protected by facade pattern
     * <p>
     * for instance, use {@link IOLookupFacade}
     */
    protected PropertiesReader() {
    }

    @Override
    public Map<String, Object> read(String filePath) throws IOException {
        return read(new File(filePath));
    }

    @Override
    public Map<String, Object> read(File file) throws IOException {
        getLog().info("Reading the property file.");
        if (!file.getAbsolutePath().endsWith(".properties")) {
            throw new IllegalArgumentException("File must be property file!");
        }
        Map<String, Object> readValue = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String key = null;
        Object value = null;
        String line;
        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();
            if (line.contains("=")) {
                key = line.substring(0, line.indexOf("=")).trim();
                value = line.substring(line.indexOf("=") + 1).trim();
                readValue.put(key, value);
            }
        }
        bufferedReader.close();
        getLog().info("Successfully read text.");
        return readValue;
    }

}
