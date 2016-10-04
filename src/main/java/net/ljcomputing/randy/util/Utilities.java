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

package net.ljcomputing.randy.util;

/**
 * Utility functionality.
 * 
 * @author James G. Willmore
 *
 */
public final class Utilities {
  
  /**
   * Instantiates a new utilities.
   */
  private Utilities(){
    // punt
  }

  /**
   * Convert the URI to determine the underlying data source 
   * (the file to use as the CSV data source).
   *
   * @param theUri the data source URI
   * @return the string
   */
  public static String convertUri(final String theUri) {
    final StringBuilder result = new StringBuilder();

    if (theUri != null) {
      final String rawUri = theUri;
      final int colonIdx = rawUri.indexOf(':'); //NOPMD
      final String newUri = rawUri.substring(colonIdx + 1); //NOPMD
      result.append(newUri);
    }

    return result.toString();
  }
}
