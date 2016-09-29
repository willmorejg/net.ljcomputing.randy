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
import java.net.URL;

import net.ljcomputing.randy.exception.DataSourceException;

/**
 * Interface shared by all data sources.
 * 
 * @author James G. Willmore
 *
 */
public interface DataSource {

  /**
   * <p>Read the specified record from the data source.</p>
   * <p>For example: read he given line number from a text file</p>
   *
   * @param record the record
   * @return the string
   * @throws DataSourceException the data source exception
   */
  String read(int record) throws DataSourceException;

  /**
   * Gets the data source.
   *
   * @return the data source
   */
  URI getDataSource();
  
  /**
   * Gets the data source URL.
   *
   * @return the uri
   * @throws DataSourceException the data source exception
   */
  URL toUrl() throws DataSourceException;
  
  /**
   * Gets the maximum size of the data source (maximum number of records available).
   *
   * @return the max size
   */
  long getMaxSize();
}
