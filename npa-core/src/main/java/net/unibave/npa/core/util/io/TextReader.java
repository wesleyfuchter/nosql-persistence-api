/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Reads simple text to String
 *
 * @author wesley
 */
public class TextReader implements IReader<String> {

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
    protected TextReader() {
    }

    @Override
    public String read(String filePath) throws IOException {
        return read(new File(filePath));
    }

    @Override
    public String read(File file) throws IOException {
        getLog().info("Reading the text file.");
        String readValue = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            readValue = readValue.concat(bufferedReader.readLine()).concat("\n");
        }
        bufferedReader.close();
        getLog().info("Successfully read text.");
        return readValue;
    }

}
