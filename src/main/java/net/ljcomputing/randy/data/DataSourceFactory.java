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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.ljcomputing.randy.data.file.CsvDataSource;
import net.ljcomputing.randy.data.file.FileDataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * @author James G. Willmore
 *
 */
public final class DataSourceFactory {

  /** The Constant MAP. */
  private static final Map<String, Class<? extends DataSource>> MAP = 
      new ConcurrentHashMap<String, Class<? extends DataSource>>();

  /**
   * Instantiates a new data source factory.
   */
  private DataSourceFactory() {
  }
  
  /**
   * Instantiates a new data source factory.
   */
  private static void init() {
    MAP.put("file", FileDataSource.class);
    MAP.put("csv", CsvDataSource.class);
  }

  /**
   * Find mapping.
   *
   * @param uri the uri
   * @return the data source mapping
   */
  private static Class<? extends DataSource> findMapping(final String uri) {
    if (uri != null) {
      final int index = uri.indexOf(':');
      final String theScheme = uri.substring(0, index);
      for (final String scheme : MAP.keySet()) {
        if (scheme.equals(theScheme)) {
          return MAP.get(scheme);
        }
      }
    }

    return null;
  }

  /**
   * Gets the data source.
   *
   * @param uri the uri
   * @return the data source
   * @throws DataSourceException the data source exception
   */
  public static DataSource getDataSource(final String uri) throws DataSourceException {
    if (MAP.isEmpty()) {
      init();
    }
    
    final Class<? extends DataSource> dsImpl = findMapping(uri);

    try {
      if (dsImpl != null) {
        return dsImpl.getConstructor(String.class).newInstance(uri);
      } else {
        throw new DataSourceException("Data source implementation [uri = " + uri + "] not found");
      }
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException exception) {
      throw new DataSourceException("Exception encountered instaniating data source implementation",
          exception);
    }
  }
}
