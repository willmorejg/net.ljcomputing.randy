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

import org.junit.Test;

import net.ljcomputing.randy.exception.RandomizerException;
import net.ljcomputing.randy.randomizer.GenderRandomizer;
import net.ljcomputing.randy.randomizer.UsStatesRamdomizer;

/**
 * @author James G. Willmore
 *
 */
public class UsStatesRamdomizerImplTest { //NOPMD

  /**
   * Test method for 
   * {@link net.ljcomputing.randy.randomizer.impl.UsStatesRamdomizerImpl#state()}.
   * @throws RandomizerException Randomizer Exception
   */
  @Test
  public void testState() {
    try {
      final UsStatesRamdomizer rand = new UsStatesRamdomizerImpl(); //NOPMD

      for (int s = 0; s < 10; s++) {
        System.out.println(rand.state()); //NOPMD
      }
      
      final GenderRandomizer gen = new GenderRandomizerImpl(); //NOPMD

      for (int s = 0; s < 10; s++) {
        System.out.println(gen.gender()); //NOPMD
      }
    } catch (RandomizerException exception) {
      exception.printStackTrace(); //NOPMD
    }
  }
  
  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(final String ... args) {
    try {
      final UsStatesRamdomizer rand = new UsStatesRamdomizerImpl(); //NOPMD

      for (int s = 0; s < 10; s++) {
        System.out.println(rand.state()); //NOPMD
      }
    } catch (RandomizerException exception) {
      exception.printStackTrace(); //NOPMD
    }
  }

}
