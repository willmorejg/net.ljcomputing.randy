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
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * The CSV data source.
 * 
 * @author James G. Willmore
 *
 */
public class CsvDataSource extends AbstractFileDataSource implements DataSource {

  /** The max file size. */
  private transient long maxFileSize;

  /**
   * Instantiates a new file data source.
   *
   * @param uri the uri
   * @throws DataSourceException the data source exception
   */
  public CsvDataSource(final String uri) throws DataSourceException {
    super(uri);
    convertUri();
    countLines();
  }
  
  /**
   * Convert the URI to determine the underlying data source 
   * (the file to use as the CSV data source).
   *
   * @return the string
   */
  private String convertUri() {
    final StringBuilder result = new StringBuilder();
    
    if (getDataSource() != null) {
      final URI theUri = getDataSource();
      final String rawUri = theUri.toString(); //NOPMD
      final int colonIdx = rawUri.indexOf(':'); //NOPMD
      result.append(rawUri.substring(colonIdx + 1)); //NOPMD
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
  private Stream<String> getStream() throws IOException, DataSourceException {
    final URI newUri = URI.create(convertUri());
    final Path path = Paths.get(newUri);
    return Files.lines(path);
  }

  /**
   * Count lines.
   *
   * @throws DataSourceException the data source exception
   */
  private void countLines() throws DataSourceException {
    try {
      final Stream<String> lines = getStream();
      maxFileSize = (int) lines.count(); //NOPMD 
      lines.close(); //NOPMD
    } catch (IOException exception) {
      throw new DataSourceException("IO Exception", exception);
    } catch (NoSuchElementException exception) {
      throw new DataSourceException("Given record does not exist.", exception);
    }
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#read(int)
   */
  @Override
  public String read(final int record) throws DataSourceException {
    String result;
    
    try {
      final Stream<String> lines = getStream();
      result = lines.skip(record).findFirst().get(); //NOPMD
      lines.close(); //NOPMD
    } catch (IOException exception) {
      throw new DataSourceException("IO Exception", exception);
    } catch (NoSuchElementException exception) {
      throw new DataSourceException("Record " + record + " does not exist.", exception);
    }

    return result;
  }

  /**
   * @see net.ljcomputing.randy.data.file.AbstractFileDataSource#getMaxSize()
   */
  @Override
  public long getMaxSize() {
    return maxFileSize;
  }
}
