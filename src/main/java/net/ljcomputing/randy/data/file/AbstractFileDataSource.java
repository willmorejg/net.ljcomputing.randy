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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * Abstract implementation of a file data source.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractFileDataSource implements DataSource {

  /** The data source. */
  private final transient URI dataSource;

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
   * @see net.ljcomputing.randy.data.DataSource#getMaxSize()
   */
  @Override
  public abstract long getMaxSize();
}
