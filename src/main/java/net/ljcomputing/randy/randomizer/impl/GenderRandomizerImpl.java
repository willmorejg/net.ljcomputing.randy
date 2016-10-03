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

package net.ljcomputing.randy.randomizer.impl;

import net.ljcomputing.randy.exception.RandomizerException;
import net.ljcomputing.randy.randomizer.GenderRandomizer;
import net.ljcomputing.randy.randomizer.Randomizer;

/**
 * @author James G. Willmore
 *
 */
public class GenderRandomizerImpl extends Randomizer implements GenderRandomizer {
  
  /** The Constant PROPERTY_PREFIX. */
  private static final String PROPERTY_PREFIX = "random.gender";
  
  /**
   * Instantiates a new gender randomizer impl.
   *
   * @throws RandomizerException the randomizer exception
   */
  public GenderRandomizerImpl() throws RandomizerException {
    super(PROPERTY_PREFIX);
  }

  /**
   * @see net.ljcomputing.randy.randomizer.GenderRandomizer#gender()
   */
  @Override
  public String gender() throws RandomizerException {
    return generator.generate();
  }

}
