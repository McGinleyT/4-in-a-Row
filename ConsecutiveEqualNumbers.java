//Thomas McGinley
//6/16/2023

import java.util.*;

public class ConsecutiveEqualNumbers {
	
	// PRINT ARRAY------------------------------------------------------------------------
	public static void Print_Array(int[][] arr, int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n\n");//formatting
	}

	// SEARCH-----------------------------------------------------------------------------
	public static int Search(int[][] arr, int rows, int cols) {
		int consecutiveValue = -1;
		boolean found = false;
		for (int y = 0; y < rows && !found; y++) {
			for (int x = 0; x < cols && !found; x++) {
				
				//System.out.print("Row: " + (y+1) + " Col: " + (x+1) + " -");	//DEBUG
				if (x + 3 <= cols-1 && arr[y][x + 1] == arr[y][x] && arr[y][x] == arr[y][x + 2]
						&& arr[y][x] == arr[y][x + 3])// right
				{
					found = true;
					consecutiveValue = arr[y][x];
				}
				
				//System.out.print("\\");	//DEBUG
				if (y + 3 <= rows-1 && x + 3 <= cols-1 && arr[y + 1][x + 1] == arr[y][x]
						&& arr[y][x] == arr[y + 2][x + 2] && arr[y][x] == arr[y + 3][x + 3])// down right
				{
					found = true;
					consecutiveValue = arr[y][x];
				}
				
				//System.out.print("|");	//DEBUG
				if (y + 3 <= rows-1 && arr[y + 1][x] == arr[y][x] && arr[y][x] == arr[y + 2][x]
						&& arr[y][x] == arr[y + 3][x])// down
				{
					found = true;
					consecutiveValue = arr[y][x];
				}

				//System.out.println("/");	//DEBUG
				if (y + 3 <= rows-1 && x - 3 >= 0 && arr[y + 1][x - 1] == arr[y][x] && arr[y][x] == arr[y + 2][x - 2]
						&& arr[y][x] == arr[y + 3][x - 3])// down left
				{
					found = true;
					consecutiveValue = arr[y][x];
				}
			}
		}

		return consecutiveValue;// if nothing found, returns -1
	}

	// MENU-------------------------------------------------------------------------------
	public static int Menu(Scanner userSel) {
		int menuSel = 0;

		System.out.println("---MAIN MENU---");
		System.out.println("1) Check array");
		System.out.println("2) Fill array");
		System.out.println("3) Randomize array");
		System.out.println("4) Change array size");
		System.out.println("5) Print array");
		System.out.println("6) Quit");
		while (menuSel < 1 || menuSel > 6) {
			System.out.print("Enter menu choice: ");
			menuSel = userSel.nextInt();
		}
		return menuSel;
	}

	// MAIN-------------------------------------------------------------------------------
	public static void main(String[] args) {
		Scanner userIn = new Scanner(System.in);
		int checkValue = 0; // used to hold an int from scanner to make sure it's a good value

		final int SIZE = 32;
		int grid[][] = new int[SIZE][SIZE];
		int rows = 0, cols = 0, consecutiveValue = 0;
		boolean continueRunning = true;

		System.out.print("Enter number of rows (4-32): ");
		rows = userIn.nextInt();
		while (rows > SIZE || rows < 4) {
			System.out.print("Number of rows out of range. Enter new number: ");
			rows = userIn.nextInt();
		}

		System.out.print("Enter number of columns: ");
		cols = userIn.nextInt();
		while (cols > SIZE || cols < 4) {
			System.out.print("Number of columns out of range. Enter new number: ");
			cols = userIn.nextInt();
		}

		while (continueRunning) {
			switch (Menu(userIn)) {
			case 1:
				Print_Array(grid, rows, cols);
				consecutiveValue = Search(grid, rows, cols);
				if (consecutiveValue == -1) {
					System.out.println("Did not find four consecutive occurences");
				} else {
					System.out.println("Found four consecutive occurences of the number " + consecutiveValue + "\n");
				}

				break;

			case 2:
				System.out.println("Enter a single digit number (0-9) for each grid spot.");
				for (int y = 0; y < rows; y++) {
					for (int x = 0; x < cols; x++) {
						do {
							System.out.print("[" + y + "," + x + "]: ");
							checkValue = userIn.nextInt();
						} while (checkValue < 0 || checkValue > 9);
						grid[y][x] = checkValue;
					}
				}
				break;

			case 3:
				for (int y = 0; y < rows; y++) {
					for (int x = 0; x < cols; x++) {
						grid[y][x] = (int) (Math.random() * 10);
					}
				}
				System.out.println("Grid was filled with random numbers!\n");
				break;

			case 4:
				System.out.print("Enter number of rows: ");
				rows = userIn.nextInt();
				System.out.print("Enter number of columns: ");
				cols = userIn.nextInt();
				break;

			case 5:
				Print_Array(grid, rows, cols);
				break;
			
			default:
				continueRunning = false;
				System.out.println("Thank you!");
				break;
			}
		}
		userIn.close();
		System.exit(0);
	}
}
