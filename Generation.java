import java.util.Scanner;

public class Generation {

	public static void main(String[] args) {

		System.out.println("Map Generation");
		System.out.println("Square Map Generation, height/length? : ");
		Scanner stdin = new Scanner(System.in);

		int dimensions;
		try {
			dimensions = stdin.nextInt();
		} catch (Exception e) {
			dimensions = 0;
			System.out.println("Input not an int.");
			System.exit(0);
		}

		System.out.println("First tile will be randomized, possibly can decide it based off of a seed in the future?");

		// create map based off of given dimensions
		int[][] newMap = new int[dimensions][dimensions];

		// initialize first map value to a random 'object' (for now using int's,
		// later object)
		newMap[0][0] = randNum(1, 10);
		System.out.println(newMap[0][0]);
		System.out.println("Fill whole array with preinitialized values for testing, THIS IS TEMP. REMOVE AFTER");
		// remove me
		
			for(int i = 0; i < newMap.length; i++) {
				for(int j = 0; j < newMap.length; j++) {
					newMap[j][i] = randNum(1, 10);
					//System.out.println(newMap[j][i]);
				}
			}
			
		
		// end remove me

		printMap(newMap);
		
		System.out.println("Get surroundings and print them out @POS (2,2)");
		int[] outerNums = getSurroundings(newMap, 1, 0);
		printArray(outerNums);

		stdin.close();
	}

	// return a random number between the 2 arguments min and max. Inclusive for
	// min and max
	public static int randNum(int min, int max) {
		int rand = min + (int) (Math.random() * (max - min + 1));
		return rand;
	}

	// assuming of square array, not ragged
	// print contents of our map
	// in terms of thinking about this like axis, we're printing from bottom to
	// top,.. can change this, just a note.
	public static void printMap(int[][] map) {
		System.out.println("Map Contents: ");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.println("At (" + j + "," + i + ") : " + map[j][i]);
			}
		}
	}

	/*
	 * So, we want to decide to next 'object' 
	 * 0,0 1,0 2,0 3,0 
	 * 0,1 1,1 2,1 3,1 
	 * 0,2 1,2 2,2 3,2 
	 * 0,3 1,3 2,3 3,3
	 * 
	 * NOTE: I didn't think of this at all: when we're inputting the new values, the only existing value to base our new value off of would
	 * be the previous one. so rather than just having 0,0 pre-initialized; we need a random amount 0 to dimension-1*dimension-1 to be
	 * intialized. that way we have some values to base shit off of rather than just the previous one, keep the preinitialized in an array
	 * (exemptions to not overwrite the values)
	 * 
	 * let's do look at 2,2 since it's fairly in center - we need to check the values of the others around it, 
	 * so that's: 1,1; 2,1; 3,1; 1,2; 3,2; 1,3; 2,3; 3,3;
	 * 
	 * NOTE: only "set a value" if one isn't already set.
	 * ======
	 * above below
	 * 0, -1
	 * 0, +1
	 * -----
	 * left side
	 * -1, +1
	 * -1, 0
	 * -1, -1
	 * -----
	 * right side
	 * +1, +1
	 * +1, 0
	 * +1, -1
	 */
	
	// eight values to check
	
	public static int[] getSurroundings(int[][] map, int currentX, int currentY) { 
		int[] values = {20,20,20,20,20,20,20,20};
		int count = 0;
		//int value = 0; I don't think I need this anymore
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				
				if(i == 0 && j == 0) {
					System.out.println("I SHOULD SEE THIS ONLY ONCE" + i + "" + j);

					// do nothing
				}
				else {
					try {
						System.out.println("At " + (currentX + j) + " and " + (currentY + i));
						values[count] = map[currentX + j][currentY + i];
					}
					catch(Exception e) {
						System.out.println("Ate " + (currentX + j) + " and " + (currentY + i));
						values[count] = 0; // since it doesnt exist, point is probably -1, -1 or something out of bounds of the array.
					}
					
					count++;
				}
				
			}
		}
		return values;
	}
	
	// print array contents, specifically using this just to see whether getSurroundings is working correctly
	public static void printArray(int[] outer) {
		for(int i = 0; i < outer.length; i++) {
			System.out.println("SURROUNDINGS: " + i + " : " + outer[i]);
		}
	}
	
}
