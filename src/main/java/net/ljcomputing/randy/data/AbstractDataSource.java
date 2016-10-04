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

package net.ljcomputing.randy.data;

import java.net.URI;
import java.net.URISyntaxException;

import net.ljcomputing.randy.exception.DataSourceException;

/**
 * Abstract implementation of  a data source.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractDataSource implements DataSource {

  /** The constant indicating the max file size was not initialized. */
  public static final long EMPTY_SIZE = 0L;

  /** The data source. */
  protected final transient URI uri;

  /** The max size. */
  protected transient long maxSize;

  /**
   * Instantiates a new abstract data source.
   *
   * @param uri the uri
   * @throws DataSourceException the data source exception
   */
  public AbstractDataSource(final String uri) throws DataSourceException {
    try {
      this.uri = new URI(uri);
    } catch (URISyntaxException exception) {
      throw new DataSourceException("Invalid URL.", exception);
    }
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#toUri()
   */
  @Override
  public URI toUri() {
    return uri;
  }
}
