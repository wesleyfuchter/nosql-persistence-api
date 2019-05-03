package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.IOException;

/**
 * Agreement to standardize file read objects.
 * <p>
 * Generic type represents object type to read.
 *
 * @author wesley
 */
public interface IReader<E> {

    /**
     * Read {@link File} values
     *
     * @param filePath path of file to read
     * @return the read value
     * @throws {@link IOException} io operations exceptions
     * @author wesley
     */
    E read(String filePath) throws IOException;

    /**
     * Read {@link File} values
     *
     * @param file to read
     * @return the read value
     * @throws {@link IOException} io operations exceptions
     * @author wesley
     */
    E read(File file) throws IOException;

}
