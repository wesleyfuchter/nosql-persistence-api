/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Reads simple text to String
 *
 * @author wesley
 */
public class InputStreamReader implements IReader<InputStream> {

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
    protected InputStreamReader() {
    }

    @Override
    public InputStream read(String filePath) throws IOException {
        return read(new File(filePath));
    }

    @Override
    public InputStream read(File file) throws IOException {
        return new ByteArrayInputStream(IOLookupFacade.getInstance().getByteArrayReader().read(file));
    }

}
