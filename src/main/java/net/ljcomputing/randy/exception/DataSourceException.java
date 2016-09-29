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

package net.ljcomputing.randy.exception;

/**
 * Exception specifically related to a data source.
 * 
 * @author James G. Willmore
 *
 */
public class DataSourceException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6615666618329845885L;

  /**
   * Instantiates a new data source exception.
   */
  public DataSourceException() {
    super();
  }

  /**
   * Instantiates a new data source exception.
   *
   * @param message the message
   */
  public DataSourceException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new data source exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public DataSourceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new data source exception.
   *
   * @param cause the cause
   */
  public DataSourceException(final Throwable cause) {
    super(cause);
  }
}
