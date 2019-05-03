/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Writes on file a byte array object
 *
 * @author wesley
 */
public class ByteArrayWriter implements IWriter<byte[]> {

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
    protected ByteArrayWriter() {
    }

    @Override
    public void write(String filePath, byte[] value) throws IOException {
        write(new File(filePath), value);
    }

    @Override
    public void write(File file, byte[] value) throws IOException {
        getLog().info("Writing in the byte file.");
        if (!file.exists()) {
            file.createNewFile();
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(value);
        fileOutputStream.close();
        getLog().info("Successfully written bytes.");
    }

}
