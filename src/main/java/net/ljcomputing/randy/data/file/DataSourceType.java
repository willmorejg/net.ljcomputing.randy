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

import net.ljcomputing.randy.data.DataSource;

/**
 * Data source type.
 * 
 * @author James G. Willmore
 *
 */
public enum DataSourceType {
  FILE("file", FileDataSource.class), 
  CSV("csv", CsvDataSource.class)
  ;

  /** The scheme. */
  private String scheme;

  /** The data source impl. */
  private Class<? extends DataSource> dataSourceImpl;

  /**
   * Instantiates a new data source type.
   *
   * @param scheme the scheme
   * @param dataSourceImpl the data source impl
   */
  private DataSourceType(final String scheme, final Class<? extends DataSource> dataSourceImpl) {
    this.scheme = scheme;
    this.dataSourceImpl = dataSourceImpl;
  }

  /**
   * Gets the scheme.
   *
   * @return the scheme
   */
  public String getScheme() {
    return scheme;
  }

  /**
   * Gets the data source impl.
   *
   * @return the data source impl
   */
  public Class<? extends DataSource> getDataSourceImpl() {
    return dataSourceImpl;
  }

  /**
   * Gets the type.
   *
   * @param uri the URI of the data source
   * @return the type
   */
  public static DataSourceType getType(final String uri) { //NOPMD
    final String theScheme = uri.substring(0, uri.indexOf(':')); //NOPMD

    for (final DataSourceType type : values()) {
      if (type.getScheme().equals(theScheme)) { //NOPMD
        return type; //NOPMD
      }
    }

    return null;
  }
}
