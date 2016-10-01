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

package net.ljcomputing.randy.generator;

import java.util.Random;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;
import net.ljcomputing.randy.exception.GeneratorException;

/**
 * Base implementation of a generator.
 * 
 * @author James G. Willmore
 *
 */
public class BaseGenerator implements Generator {

  /** The random number generator. */
  private final transient Random rand = new Random();

  /** The buffer. */
  private final transient String[] buffer;

  /**
   * Instantiates a new abstract generator.
   *
   * @param dataSource the data source
   * @param bufferSize the buffer size
   * @throws GeneratorException the generator exception
   */
  public BaseGenerator(final DataSource dataSource, final int bufferSize)
      throws GeneratorException {
    buffer = new String[bufferSize];
    fillBuffer(dataSource, bufferSize);
  }

  /**
   * Gets the max integer for random number generation.
   *
   * @param dataSource the data source
   * @return the max int
   * @throws DataSourceException the data source exception
   */
  private static int getMaxInt(final DataSource dataSource) throws DataSourceException {
    final long maxSize = dataSource.getMaxSize();
    final Long maxLong = Long.valueOf(maxSize);
    return maxLong.intValue() - 1; //NOPMD
  }

  /**
   * Fill buffer.
   *
   * @param dataSource the data source
   * @param bufferSize the buffer size
   * @throws GeneratorException the generator exception
   */
  private void fillBuffer(final DataSource dataSource, final int bufferSize)
      throws GeneratorException {
    for (int e = 0; e < bufferSize; e++) {
      try {
        final int maxInt = getMaxInt(dataSource);
        final int nextInt = rand.nextInt(maxInt);
        buffer[e] = dataSource.read(nextInt);
      } catch (DataSourceException exception) {
        throw new GeneratorException("Exception encountered while filling buffer", exception);
      }
    }
  }

  /**
   * @see net.ljcomputing.randy.generator.Generator#generate()
   */
  @Override
  public String generate() {
    final int bufferSize = buffer.length;
    final int nextInt = rand.nextInt(bufferSize - 1);
    return buffer[nextInt];
  }

}
