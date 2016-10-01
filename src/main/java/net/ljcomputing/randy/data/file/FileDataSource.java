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
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;

/**
 * The text file data source.
 * 
 * @author James G. Willmore
 *
 */
public class FileDataSource extends AbstractFileDataSource implements DataSource {

  /**
   * Instantiates a new file data source.
   *
   * @param uri the uri
   * @throws DataSourceException the data source exception
   */
  public FileDataSource(final String uri) throws DataSourceException {
    super(uri);
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
}
