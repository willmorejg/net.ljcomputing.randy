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

package net.ljcomputing.randy.randomizer;

import java.io.IOException;

import net.ljcomputing.randy.ApplicationProperties;
import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.exception.DataSourceException;
import net.ljcomputing.randy.exception.GeneratorException;
import net.ljcomputing.randy.exception.RandomizerException;
import net.ljcomputing.randy.factory.DataSourceFactory;
import net.ljcomputing.randy.generator.BaseGenerator;
import net.ljcomputing.randy.generator.Generator;

/**
 * <p>Implementation of a data randomizer.</p>
 * <p> This implementation relies on the 
 * <b>applications.properties</b>, which configures the data source URI 
 * and buffer size. Configuration outside of the code allows the implementation 
 * classes to focus on the actual implementation of returning random data 
 * and loose coupling.</p>
 * 
 * <p>{@link net.ljcomputing.randy.factory.DataSourceFactory} 
 * returns the actual data source implementation based upon the URI provided.</p>
 * 
 * @author James G. Willmore
 *
 */
public class Randomizer {

  /** The generator. */
  protected final transient Generator generator;

  /**
   * Instantiates a new abstract randomizer.
   *
   * @param propertiesPrefix the properties prefix
   * @throws RandomizerException the randomizer exception
   */
  public Randomizer(final String propertiesPrefix) throws RandomizerException {
    try {
      final ApplicationProperties applicationProps = new ApplicationProperties(propertiesPrefix);
      
      final int bufferSize = applicationProps.getBufferSize();
      final String dataSourceUri = applicationProps.getUri();
      final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
      final DataSource dataSource = dataSourceFactory.getDataSource(dataSourceUri);
      
      this.generator = new BaseGenerator(bufferSize);
      this.generator.fillBuffer(dataSource);
    } catch (DataSourceException | IOException | GeneratorException exception) {
      throw new RandomizerException("Exception encountered instaniating randomizer", exception);
    }
  }
}
