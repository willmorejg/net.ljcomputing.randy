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

import java.net.URI;
import java.net.URL;

import net.ljcomputing.randy.data.EnumerationBasedDataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * An enumeration of genders.
 * 
 * @author James G. Willmore
 *
 */
public enum Gender implements EnumerationBasedDataSource {
  MALE, FEMALE;

  /**
   * @see net.ljcomputing.randy.data.DataSource#read(int)
   */
  @Override
  public String read(final int record) throws DataSourceException {
    final Gender result = values()[record]; //NOPMD
    return result.toString(); //NOPMD
  }

  /**
   * @see net.ljcomputing.randy.data.DataSource#toUri()
   */
  @Override
  public URI toUri() {
    throw new UnsupportedOperationException("Not implemented");
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
    return values().length; //NOPMD
  }
}
