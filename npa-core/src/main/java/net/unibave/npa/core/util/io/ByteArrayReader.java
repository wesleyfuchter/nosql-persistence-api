/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Reads a file into byte array
 *
 * @author wesley
 */
public class ByteArrayReader implements IReader<byte[]> {

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
    protected ByteArrayReader() {
    }

    @Override
    public byte[] read(String filePath) throws IOException {
        return read(new File(filePath));
    }

    @Override
    public byte[] read(File file) throws IOException {
        getLog().info("Reading the property file.");
        FileInputStream fileInputStream = null;
        byte[] returnByteArray = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(returnByteArray);
        fileInputStream.close();
        getLog().info("Successfully read text.");
        return returnByteArray;
    }

}
