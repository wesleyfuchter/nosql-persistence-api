/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.IOException;

/**
 * Agreement to standardize file write objects.
 * <p>
 * Generic type represents object type to write.
 *
 * @author wesley
 */
public interface IWriter<E> {

    /**
     * Write on {@link File} the value
     *
     * @param filePath path of file to write
     * @param value    value to write on file
     * @throws {@link IOException} io operations exceptions
     * @author wesley
     */
    void write(final String filePath, final E value) throws IOException;

    /**
     * Write on {@link File} the value
     *
     * @param file  file to write
     * @param value value to write on file
     * @throws {@link IOException} io operations exceptions
     * @author wesley
     */
    void write(final File file, final E value) throws IOException;

}
