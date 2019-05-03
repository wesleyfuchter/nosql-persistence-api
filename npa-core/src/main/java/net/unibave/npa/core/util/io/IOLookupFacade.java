/**
 *
 */
package net.unibave.npa.core.util.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Facade pattern object to IO module
 * <p>
 * Have access to the classes of IO operations.
 * <p>
 * <b>Writers</b>
 *
 * @author wesley
 * @see PropertiesWriter
 * @see ByteArrayWriter
 * @see OutputStreamWriter
 * @see TextWriter
 * <p>
 * <b>Readers</b>
 * @see PropertiesReader
 * @see ByteArrayReader
 * @see InputStreamReader
 * @see TextReader
 */
public final class IOLookupFacade {

    /**
     * Singleton Pattern
     * for instance use getInstance static method
     */
    private IOLookupFacade() {
    }

    /**
     * Singleton Pattern
     * contains unique-instance of this object
     */
    private static IOLookupFacade instance;

    /**
     * Object to write propeties
     *
     * @see PropertiesWriter
     */
    private IWriter<Map<String, Object>> propertiesWriter;

    /**
     * Object to write text
     *
     * @see TextWriter
     */
    private IWriter<String> textWriter;

    /**
     * Object to write byte value
     *
     * @see ByteArrayWriter
     */
    private IWriter<byte[]> byteWriter;

    /**
     * Object to write byte value
     *
     * @see OutputStreamWriter
     */
    private IWriter<OutputStream> outputStreamWriter;

    /**
     * Object to read byte value
     *
     * @see ByteArrayReader
     */
    private IReader<byte[]> byteArrayReader;

    /**
     * Object to read text file
     *
     * @see TextReader
     */
    private IReader<String> textReader;

    /**
     * Object to read property file
     *
     * @see PropertiesReader
     */
    private IReader<Map<String, Object>> propertiesReader;

    /**
     * Object to read byte file into inputStream
     *
     * @see InputStreamReader
     */
    private IReader<InputStream> inputStreamReader;

    private final FilesUtil filesUtil = new FilesUtil();

    /**
     * @return the instance
     */
    public static IOLookupFacade getInstance() {
        if (Objects.isNull(instance)) {
            instance = new IOLookupFacade();
        }
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public static void setInstance(IOLookupFacade instance) {
        IOLookupFacade.instance = instance;
    }

    /**
     * @return the propertiesWriter
     */
    public IWriter<Map<String, Object>> getPropertiesWriter() {
        if (Objects.isNull(propertiesWriter)) {
            propertiesWriter = new PropertiesWriter();
        }
        return propertiesWriter;
    }

    /**
     * @param propertiesWriter the propertiesWriter to set
     */
    public void setPropertiesWriter(IWriter<Map<String, Object>> propertiesWriter) {
        this.propertiesWriter = propertiesWriter;
    }

    /**
     * @return the textWriter
     */
    public IWriter<String> getTextWriter() {
        if (Objects.isNull(textWriter)) {
            textWriter = new TextWriter();
        }
        return textWriter;
    }

    /**
     * @param textWriter the textWriter to set
     */
    public void setTextWriter(IWriter<String> textWriter) {
        this.textWriter = textWriter;
    }

    /**
     * @return the byteWriter
     */
    public IWriter<byte[]> getByteWriter() {
        if (Objects.isNull(byteWriter)) {
            byteWriter = new ByteArrayWriter();
        }
        return byteWriter;
    }

    /**
     * @param byteWriter the byteWriter to set
     */
    public void setByteWriter(IWriter<byte[]> byteWriter) {
        this.byteWriter = byteWriter;
    }

    /**
     * @return the outputStreamWriter
     */
    public IWriter<OutputStream> getOutputStreamWriter() {
        if (Objects.isNull(outputStreamWriter)) {
            outputStreamWriter = new OutputStreamWriter();
        }
        return outputStreamWriter;
    }

    /**
     * @param outputStreamWriter the outputStreamWriter to set
     */
    public void setOutputStreamWriter(IWriter<OutputStream> outputStreamWriter) {
        this.outputStreamWriter = outputStreamWriter;
    }

    /**
     * @return the byteArrayReader
     */
    public IReader<byte[]> getByteArrayReader() {
        if (Objects.isNull(byteArrayReader)) {
            byteArrayReader = new ByteArrayReader();
        }
        return byteArrayReader;
    }

    /**
     * @param byteArrayReader the byteArrayReader to set
     */
    public void setByteArrayReader(IReader<byte[]> byteArrayReader) {
        this.byteArrayReader = byteArrayReader;
    }

    /**
     * @return the textReader
     */
    public IReader<String> getTextReader() {
        if (Objects.isNull(textReader)) {
            textReader = new TextReader();
        }
        return textReader;
    }

    /**
     * @param textReader the textReader to set
     */
    public void setTextReader(IReader<String> textReader) {
        this.textReader = textReader;
    }

    /**
     * @return the propertiesReader
     */
    public IReader<Map<String, Object>> getPropertiesReader() {
        if (Objects.isNull(propertiesReader)) {
            propertiesReader = new PropertiesReader();
        }
        return propertiesReader;
    }

    /**
     * @param propertiesReader the propertiesReader to set
     */
    public void setPropertiesReader(IReader<Map<String, Object>> propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    /**
     * @return the inputStreamReader
     */
    public IReader<InputStream> getInputStreamReader() {
        if (Objects.isNull(inputStreamReader)) {
            inputStreamReader = new InputStreamReader();
        }
        return inputStreamReader;
    }

    /**
     * @param inputStreamReader the inputStreamReader to set
     */
    public void setInputStreamReader(IReader<InputStream> inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public String getDeploymentDirectory() {
        return ""; //TODO must return the meta-inf directory on SRC
    }

    public List<File> getFilesByExtension(String directory, String extension) {
        return filesUtil.getFilesByExtension(directory, extension);
    }

    public List<File> getPropertiesFiles(String directory) {
        return filesUtil.getPropertiesFiles(directory);
    }


}
