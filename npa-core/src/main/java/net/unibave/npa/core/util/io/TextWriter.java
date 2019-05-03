/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Writes on file a String text
 *
 * @author wesley
 */
public class TextWriter implements IWriter<String> {

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
    protected TextWriter() {
    }

    @Override
    public void write(String filePath, String value) throws IOException {
        write(new File(filePath), value);
    }

    @Override
    public void write(File file, String value) throws IOException {
        getLog().info("Writing in the text file.");
        if (!file.exists()) {
            file.createNewFile();
        }
        final PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(value);
        printWriter.close();
        getLog().info("Successfully written text.");
    }

}
