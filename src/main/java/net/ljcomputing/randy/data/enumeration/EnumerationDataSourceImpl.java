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

package net.ljcomputing.randy.data.enumeration;

import java.net.URL;

import net.ljcomputing.randy.data.AbstractDataSource;
import net.ljcomputing.randy.data.EnumerationBasedDataSource;
import net.ljcomputing.randy.exception.DataSourceException;
import net.ljcomputing.randy.util.Utilities;

/**
 * Abstract implementation of an enumeration-based record source.
 * 
 * @author James G. Willmore
 *
 */
public class EnumerationDataSourceImpl extends AbstractDataSource
    implements EnumerationBasedDataSource {

  /** The enumeration. */
  private Class<?> enumeration;

  /**
   * Instantiates a new base enumeration data source.
   *
   * @param uri the uri
   * @throws DataSourceException the data source exception
   */
  public EnumerationDataSourceImpl(final String uri)
      throws DataSourceException {
    super(uri);
    final String classname = Utilities.convertUri(uri);
    
    try {
      this.enumeration = Class.forName(classname);
    } catch (ClassNotFoundException exception) {
      throw new DataSourceException("Exception encountered instaniating data source", exception);
    }
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#read(int)
   */
  @Override
  public String read(final int record) throws DataSourceException {
    return enumeration.getEnumConstants()[record].toString();
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#toUrl()
   */
  @Override
  public URL toUrl() throws DataSourceException {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#getMaxSize()
   */
  @Override
  public long getMaxSize() throws DataSourceException {
    // need to add 1 for random generation
    return enumeration.getEnumConstants().length + 1;
  }
}
