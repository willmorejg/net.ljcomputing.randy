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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.ljcomputing.randy.data.enumeration.EnumerationDataSourceImpl;
import net.ljcomputing.randy.data.file.CsvDataSource;
import net.ljcomputing.randy.data.file.FileDataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * Data source factory - determines the appropriate data source 
 * implementation based upon the URI of the data source.
 * 
 * @author James G. Willmore
 *
 */
public final class DataSourceFactory {

  /** The instance. */
  private static DataSourceFactory instance = new DataSourceFactory();

  /** The Constant MAP. */
  private static final Map<String, Class<? extends DataSource>> MAPPINGS = 
      new ConcurrentHashMap<String, Class<? extends DataSource>>();

  /**
   * Instantiates a new data source factory.
   */
  private DataSourceFactory() {
  }

  /**
   * Gets the single instance of DataSourceFactory.
   *
   * @return single instance of DataSourceFactory
   */
  public static DataSourceFactory getInstance() {
    initMappings();
    return instance;
  }

  /**
   * Instantiates a new data source factory.
   */
  private static void initMappings() {
    if (MAPPINGS.isEmpty()) {
      MAPPINGS.put("file", FileDataSource.class);
      MAPPINGS.put("csv", CsvDataSource.class);
      MAPPINGS.put("enum", EnumerationDataSourceImpl.class);
    }
  }

  /**
   * Find mapping.
   *
   * @param uri the uri
   * @return the data source mapping
   */
  private static Class<? extends DataSource> findMapping(final String uri) { //NOPMD
    Class<? extends DataSource> dataSource = null; //NOPMD

    if (uri != null) {
      final int index = uri.indexOf(':');
      final String theScheme = uri.substring(0, index); //NOPMD
      for (final String scheme : MAPPINGS.keySet()) {
        if (scheme.equals(theScheme)) { //NOPMD
          dataSource = MAPPINGS.get(scheme);
          break;
        }
      }
    }

    return dataSource;
  }
  
  /**
   * Inits the data source.
   *
   * @param dataSource the data source
   */
  private void initDataSource(final DataSource dataSource) {
    if (dataSource instanceof RecordBasedDataSource) {
      ((RecordBasedDataSource) dataSource).setRecordPosition(1);
    }
  }

  /**
   * Gets the data source.
   *
   * @param uri the uri
   * @return the data source
   * @throws DataSourceException the data source exception
   */
  public DataSource getDataSource(final String uri) throws DataSourceException {
    final Class<? extends DataSource> dsImpl = findMapping(uri);
    
    if (dsImpl == null) {
      throw new DataSourceException("Data source implementation [uri = " + uri + "] not found");
    }

    try {
      Constructor<? extends DataSource> constructor = dsImpl.getConstructor(String.class); //NOPMD
      final DataSource dataSource = constructor.newInstance(uri); //NOPMD
      initDataSource(dataSource);
      return dataSource;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException exception) {
      throw new DataSourceException(
          "Exception encountered instaniating data source implementation " + "[uri = " + uri + "]",
          exception);
    }
  }
}
