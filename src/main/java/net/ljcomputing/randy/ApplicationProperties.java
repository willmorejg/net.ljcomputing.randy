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

package net.ljcomputing.randy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The generator properties as defined in the Properties file.
 * 
 * @author James G. Willmore
 *
 */
public class ApplicationProperties extends Properties {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 5743926053514456487L;

  /** The Constant PROPERTIES_FILENAME. */
  private static final String PROP_FILENAME = "application.properties";

  /** The uri. */
  private static final String URI = ".uri";

  /** The buffer size. */
  private static final String BUFFER_SIZE = ".bufferSize";

  /** The record position. */
  private static final String RECORD_POSITION = ".recordPosition";

  /** The properties prefix. */
  private final transient String propertiesPrefix;

  /**
   * Instantiates a new data source properties.
   *
   * @param propertiesPrefix the properties prefix
   * @throws IOException IOException
   */
  public ApplicationProperties(final String propertiesPrefix) throws IOException {
    super();
    this.propertiesPrefix = propertiesPrefix;

    final Thread thread = Thread.currentThread(); //NOPMD
    final ClassLoader classLoader = thread.getContextClassLoader();
    final InputStream inStream = classLoader.getResourceAsStream(PROP_FILENAME);

    load(inStream);
  }

  /**
   * Gets the uri.
   *
   * @return the uri
   */
  public String getUri() {
    return getProperty(propertiesPrefix + URI);
  }

  /**
   * Gets the buffer size.
   *
   * @return the buffer size
   */
  public int getBufferSize() {
    final String recPos = getProperty(propertiesPrefix + BUFFER_SIZE);
    return Integer.valueOf(recPos);
  }

  /**
   * Gets the record position.
   *
   * @return the record position
   */
  public int getRecordPosition() {
    final String recPos = getProperty(propertiesPrefix + RECORD_POSITION);
    return Integer.valueOf(recPos);
  }
}
