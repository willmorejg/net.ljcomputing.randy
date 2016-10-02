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

/**
 * A record-based data source. Adds the ability to retrieve datum from a data 
 * source record based upon the position within the record. For example: the 
 * specific column of a database record.
 * 
 * @author James G. Willmore
 *
 */
public interface RecordBasedDataSource extends DataSource {
  
  /**
   * Gets the record position.
   *
   * @return the record position
   */
  int getRecordPosition();
  
  /**
   * Sets the record position.
   *
   * @param index the new record position
   */
  void setRecordPosition(int index);
}
