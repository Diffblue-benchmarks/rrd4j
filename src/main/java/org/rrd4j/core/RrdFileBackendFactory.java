package org.rrd4j.core;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

/**
 * An abstract backend factory which is used to store RRD data to ordinary files on the disk.
 * <p>
 * Every backend factory storing RRD data as ordinary files should inherit from it, some check are done
 * in the code for instanceof.
 *
 */
public abstract class RrdFileBackendFactory extends RrdBackendFactory {

    /**
     * {@inheritDoc}
     *
     * Method to determine if a file with the given path already exists.
     */
    protected boolean exists(String path) {
        return Util.fileExists(path);
    }

    /** {@inheritDoc} */
    protected boolean shouldValidateHeader(String path) throws IOException {
        return true;
    }

    @Override
    public boolean canStore(URI uri) {
        return "file".equals(uri.getScheme());
    }

    @Override
    public URI getUri(String path) {
        return Paths.get(path).normalize().toAbsolutePath().toUri();
    }

}
