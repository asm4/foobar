package google.foobar.level3;

import java.math.BigInteger;

/**
 Bomb, Baby!
 ===========

 You're so close to destroying the LAMBCHOP doomsday device you can taste it! But in order to do so, you need to deploy special self-replicating bombs designed for you by the brightest scientists on Bunny Planet. There are two types: Mach bombs (M) and Facula bombs (F). The bombs, once released into the LAMBCHOP's inner workings, will automatically deploy to all the strategic points you've identified and destroy them at the same time.

 But there's a few catches. First, the bombs self-replicate via one of two distinct processes:
 Every Mach bomb retrieves a sync unit from a Facula bomb; for every Mach bomb, a Facula bomb is created;
 Every Facula bomb spontaneously creates a Mach bomb.

 For example, if you had 3 Mach bombs and 2 Facula bombs, they could either produce 3 Mach bombs and 5 Facula bombs, or 5 Mach bombs and 2 Facula bombs. The replication process can be changed each cycle.

 Second, you need to ensure that you have exactly the right number of Mach and Facula bombs to destroy the LAMBCHOP device. Too few, and the device might survive. Too many, and you might overload the mass capacitors and create a singularity at the heart of the space station - not good!

 And finally, you were only able to smuggle one of each type of bomb - one Mach, one Facula - aboard the ship when you arrived, so that's all you have to start with. (Thus it may be impossible to deploy the bombs to destroy the LAMBCHOP, but that's not going to stop you from trying!)

 You need to know how many replication cycles (generations) it will take to generate the correct amount of bombs to destroy the LAMBCHOP. Write a function answer(M, F) where M and F are the number of Mach and Facula bombs needed. Return the fewest number of generations (as a string) that need to pass before you'll have the exact number of bombs necessary to destroy the LAMBCHOP, or the string "impossible" if this can't be done! M and F will be string representations of positive integers no larger than 10^50. For example, if M = "2" and F = "1", one generation would need to pass, so the answer would be "1". However, if M = "2" and F = "4", it would not be possible.

 Languages
 =========

 To provide a Python solution, edit solution.py
 To provide a Java solution, edit solution.java

 Test cases
 ==========

 Inputs:
 (string) M = "2"
 (string) F = "1"
 Output:
 (string) "1"

 Inputs:
 (string) M = "4"
 (string) F = "7"
 Output:
 (string) "4"
 */
public class BombBaby {
	public static final BigInteger STARTING_M = BigInteger.ONE;
	public static final BigInteger STARTING_F = BigInteger.ONE;

	public static String answer(String M, String F) {
		BigInteger curM = new BigInteger(M);
		BigInteger curF = new BigInteger(F);
		BigInteger generations = new BigInteger("0");

		while (curM.compareTo(STARTING_M) > 0 || curF.compareTo(STARTING_F) > 0) {
			if (curM.compareTo(STARTING_M) < 0 || curF.compareTo(STARTING_F) < 0) {
				return "impossible";
			}
			if (curM.compareTo(curF) > 0) {
				BigInteger diff = curM.subtract(curF);
				BigInteger increment = diff.divide(curF);
				if (diff.mod(curF).compareTo(BigInteger.ZERO) != 0 || diff.compareTo(BigInteger.ZERO) == 0) {
					increment = increment.add(BigInteger.ONE);
				}
				curM = curM.subtract(increment.multiply(curF));
				generations = generations.add(increment);
			} else {
				BigInteger diff = curF.subtract(curM);
				BigInteger increment = diff.divide(curM);
				if (diff.mod(curM).compareTo(BigInteger.ZERO) != 0 || diff.compareTo(BigInteger.ZERO) == 0) {
					increment = increment.add(BigInteger.ONE);
				}
				curF = curF.subtract(increment.multiply(curM));
				generations = generations.add(increment);
			}
		}

		return generations.toString();
	}

	public static void main(String[] args) {
		assert answer("2", "1").equals("1");
		assert answer("4", "7").equals("4");
	}
}