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
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import net.ljcomputing.randy.data.DataSource;
import net.ljcomputing.randy.data.file.DataSourceType;
import net.ljcomputing.randy.exception.GeneratorException;
import net.ljcomputing.randy.exception.RandomizerException;
import net.ljcomputing.randy.generator.BaseGenerator;
import net.ljcomputing.randy.generator.Generator;

/**
 * @author James G. Willmore
 *
 */
public abstract class AbstractRandomizer { //NOPMD

  /** The Constant PROPERTIES_FILENAME. */
  private static final String PROP_FILENAME = "application.properties";

  /** The Constant PROPERTY_URL. */
  private static final String PROP_URL = ".url";

  /** The Constant PROPERTY_BUFFER_SIZE. */
  private static final String PROP_BUFFER_SIZE = ".bufferSize";

  /** The properties. */
  protected static final transient Properties PROPERTIES = new Properties();

  /** The generator. */
  protected final transient Generator generator;

  /**
   * Instantiates a new abstract randomizer.
   *
   * @param propertiesPrefix the properties prefix
   * @throws RandomizerException the randomizer exception
   */
  public AbstractRandomizer(final String propertiesPrefix) throws RandomizerException {
    initProperties();

    try {
      final String url = PROPERTIES.getProperty(propertiesPrefix + PROP_URL);
      final String bufferSizeProp = PROPERTIES.getProperty(propertiesPrefix + PROP_BUFFER_SIZE);
      final Integer bufferSize = Integer.parseInt(bufferSizeProp);
      final DataSourceType dsType = DataSourceType.getType(url);
      final DataSource dataSource = dsType.getDataSourceImpl().getConstructor(String.class)
          .newInstance(url);
      this.generator = new BaseGenerator(dataSource, bufferSize);
    } catch (GeneratorException | InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException exception) {
      throw new RandomizerException("Exception encountered instaniating randomizer", exception);
    }
  }

  /**
   * Inits the properties.
   *
   * @throws RandomizerException the randomizer exception
   */
  private static void initProperties() throws RandomizerException {
    final Thread thread = Thread.currentThread(); //NOPMD
    final ClassLoader classLoader = thread.getContextClassLoader(); //NOPMD
    final InputStream inStream = classLoader.getResourceAsStream(PROP_FILENAME); //NOPMD

    try {
      PROPERTIES.load(inStream);
    } catch (IOException exception) {
      throw new RandomizerException("Randomizer exception", exception);
    }
  }
}
