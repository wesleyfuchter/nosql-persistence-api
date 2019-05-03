/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Writes on file a properties map using .properties text pattern
 *
 * @author wesley
 */
public class PropertiesWriter implements IWriter<Map<String, Object>> {

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
    protected PropertiesWriter() {
    }

    @Override
    public void write(final String filePath, final Map<String, Object> value) throws IOException {
        write(new File(filePath), value);
    }

    @Override
    public void write(final File file, final Map<String, Object> value) throws IOException {
        getLog().info("Writing in the properties file.");
        if (!file.exists()) {
            file.createNewFile();
        }
        final PrintWriter printWriter = new PrintWriter(file);
        value.keySet().forEach((row) -> {
            printWriter.println(row.concat(" = ").concat(value.get(row).toString()));
        });
        printWriter.close();
        getLog().info("Successfully written properties.");
    }

}
