/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.randy.data.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * Abstract implementation of a file data source.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractFileDataSource implements DataSource {

  /** The constant indicating the max file size was not initialized. */
  private static final long EMPTY_SIZE = 0L;

  /** The data source. */
  private final transient URI dataSource;

  /** The max file size. */
  private transient long maxFileSize;

  /**
   * Instantiates a new abstract file data source.
   *
   * @param uri the uri
   * @throws DataSourceException the data source exception
   */
  public AbstractFileDataSource(final String uri) throws DataSourceException {
    try {
      this.dataSource = new URI(uri);
    } catch (URISyntaxException exception) {
      throw new DataSourceException("Invalid URL.", exception);
    }
  }

  /**
   * Convert the URI to determine the underlying data source 
   * (the file to use as the CSV data source).
   *
   * @param theUri the data source URI
   * @return the string
   */
  protected static String convertUri(final String theUri) {
    final StringBuilder result = new StringBuilder();

    if (theUri != null) {
      final String rawUri = theUri;
      final int colonIdx = rawUri.indexOf(':'); 
      final String newUri = rawUri.substring(colonIdx + 1); 
      result.append(newUri);
    }

    return result.toString();
  }

  /**
   * Gets the stream.
   *
   * @return the stream
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws DataSourceException the data source exception
   */
  protected Stream<String> getStream() throws IOException, DataSourceException {
    final URI uri = getDataSource();
    final Path path = Paths.get(uri);
    return Files.lines(path);
  }

  /**
   * Sets the max file size.
   *
   * @throws DataSourceException the data source exception
   */
  private void setMaxFileSize() throws DataSourceException {
    try {
      final Stream<String> lines = getStream();
      maxFileSize = (int) lines.count(); 
      lines.close(); 
    } catch (IOException exception) {
      throw new DataSourceException("IO Exception: " + exception.toString(), exception); 
    } catch (NoSuchElementException exception) {
      throw new DataSourceException("Given record does not exist.", exception);
    }
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#read(int)
   */
  @Override
  public abstract String read(int record) throws DataSourceException;

  /**
   * @see net.ljcomputing.randy.data.DataSource#getDataSource()
   */
  @Override
  public URI getDataSource() {
    return dataSource;
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#toUrl()
   */
  @Override
  public URL toUrl() throws DataSourceException {
    try {
      return dataSource.toURL();
    } catch (MalformedURLException exception) {
      throw new DataSourceException("URL of data source is invalid.", exception);
    }
  }

  /**
   * @see net.ljcomputing.randy.data.file.AbstractFileDataSource#getMaxSize()
   */
  @Override
  public long getMaxSize() throws DataSourceException {
    if (maxFileSize == EMPTY_SIZE) {
      setMaxFileSize();
    }

    return maxFileSize;
  }
}
