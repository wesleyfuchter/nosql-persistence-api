/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Writes on file a byte array object
 *
 * @author wesley
 */
public class OutputStreamWriter implements IWriter<OutputStream> {

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
    protected OutputStreamWriter() {
    }

    @Override
    public void write(String filePath, OutputStream value) throws IOException {
        write(new File(filePath), value);
    }

    @Override
    public void write(File file, OutputStream value) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.writeTo(value);
        IOLookupFacade.getInstance().getByteWriter().write(file, byteArrayOutputStream.toByteArray());
    }

}
