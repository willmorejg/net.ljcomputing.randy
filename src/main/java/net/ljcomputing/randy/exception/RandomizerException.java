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
 * Exception specifically related to a randomizer.
 * 
 * @author James G. Willmore
 *
 */
public class RandomizerException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 9133223011683950776L;

  /**
   * Instantiates a new randomizer exception.
   */
  public RandomizerException() {
    super();
  }

  /**
   * Instantiates a new randomizer exception.
   *
   * @param message the message
   */
  public RandomizerException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new randomizer exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public RandomizerException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new randomizer exception.
   *
   * @param cause the cause
   */
  public RandomizerException(final Throwable cause) {
    super(cause);
  }
}
