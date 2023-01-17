import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - a program that stores a database
 *  with all US cities in it and accordingly sorts
 *  the data in order to print out queries that ask 
 *  about population and city name order
 *  
 *  Queries answered:
 *  Fifty least populous cities in USA (Selection Sort)
 *  Fifty most populous cities in USA (Merge Sort)
 *  First fifty cities sorted by name (Insertion Sort)
 *  Last fifty cities sorted by name descending (Merge Sort)
 *  Fifty most populous cities in named state
 *  All cities matching a name sorted by population
 *  
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Kanchan Kaushik
 *	@since	14 Jan 2023
 */

public class Population {
	
	// List of cities 
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	// constructor initializes list of cities
	public Population()
	{
		cities = new ArrayList<City>(31765);
	}
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
		System.out.println("31765 cities in database\n");
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

	/* main
	 * creates an instance of the class, uses
	 * it to print the introduction, read in the 
	 * data to the list of cities, and to run the
	 * program
	 */
	public static void main(String[] args)
	{
		Population p = new Population();
		p.printIntroduction();
		p.readIn();
		p.run();
	}

	/* uses File Utils to instantiate a scanner.
	 * uses the scanner to read in the data for 
	 * each city into temporary variables,
	 * then to instantiate a new city for each index
	 * of the list using the read in data
	 */
	public void readIn()
	{
		Scanner read = FileUtils.openToRead(DATA_FILE);
		for(int i = 0; i< 31765; i++)
		{
			String aState = read.next();
			String aCity = read.next();
			String type = read.next();
			int pop = read.nextInt();
			read.nextLine();
			cities.add(new City(aCity, aState, type, pop));
		}
	}

	/* instantiates a scanner to read in user input.
	 * calls certain methods that sort the data in certain
	 * manners based on the user's input number.
	 */
	public void run()
	{
		Scanner reading = new Scanner(System.in);
		int chosen=0;
		printMenu();
		while(chosen!= 9)
		{
			chosen = Prompt.getInt("Enter selection");
			if(chosen ==1)
				fiftyLeastPop();
			else if(chosen ==2)
				fiftyMostPop();
			else if(chosen ==3)
				first50Name();
			else if(chosen ==4)
				last50Name();
			else if(chosen ==5)
				fiftyMostPopState();
			else if(chosen ==6)
				allCitiesName();
			else if(chosen ==9)
				System.out.println("\nThanks for using Population!\n");
			else
				System.out.println("ERROR: invalid selection");

			if(chosen ==1 || chosen ==2 || chosen ==3 || chosen ==4 || chosen ==5 ||
			   chosen ==6)
			   	printMenu();
		}
	}

	/* Calls the selectionSort method in the SortMethods
	 * class to sort the data by ascending population order.
	 * times how long the sort takes. prints out the top 50 
	 * cities of sorted data in a loop, prints out the elapsed time
	 */
	public void fiftyLeastPop()
	{
		SortMethods sm = new SortMethods();

		long startMillisec = System.currentTimeMillis();
		sm.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();

		System.out.println("\nFifty least populous cities");
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		for(int i = 0; i < 50; i++)
		{
			System.out.printf("%3s ", (i+1)+":");
			System.out.print((cities.get(i)).toString());
			System.out.println("");
		}

		System.out.println("\n" + "Elapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");

	}

	/* Calls the mergeSort1 method in the SortMethods
	 * class to sort the data by descending population order.
	 * times how long the sort takes. prints out the top 50 
	 * cities of sorted data in a loop, prints out the elapsed time
	 */
	public void fiftyMostPop()
	{
		SortMethods sm = new SortMethods();

		long startMillisec = System.currentTimeMillis();
		sm.mergeSort1(cities);
		long endMillisec = System.currentTimeMillis();

		System.out.println("\nFifty most populous cities");
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		for(int i = 0; i < 50; i++)
		{
			System.out.printf("%3s ", (i+1)+":");
			System.out.print((cities.get(i)).toString());
			System.out.println("");
		}

		System.out.println("\n" + "Elapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
	}

	/* Calls the insertionSort method in the SortMethods
	 * class to sort the data by ascending name order.
	 * times how long the sort takes. prints out the top 50 
	 * cities of sorted data in a loop, prints out the elapsed time
	 */
	public void first50Name()
	{
		SortMethods sm = new SortMethods();

		long startMillisec = System.currentTimeMillis();
		sm.insertionSort(cities);
		long endMillisec = System.currentTimeMillis();

		System.out.println("\nFifty cities sorted by name");
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		for(int i = 0; i < 50; i++)
		{
			System.out.printf("%3s ", (i+1)+":");
			System.out.print((cities.get(i)).toString());
			System.out.println("");
		}

		System.out.println("\n" + "Elapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
	}

	/* Calls the mergeSort2 method in the SortMethods
	 * class to sort the data by descending name order.
	 * times how long the sort takes. prints out the top 50 
	 * cities of sorted data in a loop, prints out the elapsed time
	 */
	public void last50Name()
	{
		SortMethods sm = new SortMethods();

		long startMillisec = System.currentTimeMillis();
		sm.mergeSort2(cities);
		long endMillisec = System.currentTimeMillis();

		System.out.println("\nFifty cities sorted by name descending");
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		for(int i = 0; i < 50; i++)
		{
			System.out.printf("%3s ", (i+1)+":");
			System.out.print((cities.get(i)).toString());
			System.out.println("");
		}

		System.out.println("\n" + "Elapsed Time " + (endMillisec-startMillisec) + " milliseconds\n");
	}

	/* Takes in input for user's choice of state to
	 * find the 50 most populous cities in.
	 * Calls the mergeSort1 method in the SortMethods
	 * class to sort the data by descending population order.
	 * prints out the top 50 cities that are in the specified 
	 * state (by checking the state variable of the City class)
	 * for the sorted data in a loop.
	 */
	public void fiftyMostPopState()
	{
		SortMethods sm = new SortMethods();

		Scanner typed = new Scanner(System.in);
		boolean valid = false;

		System.out.println("");
		
		String stateName = "";
		while(!valid)
		{
			stateName = Prompt.getString("Enter state name (ie. Alabama)"); 
			for(int i = 0; i < cities.size(); i++)
			{
				if(((cities.get(i)).getState()).equalsIgnoreCase(stateName))
				{
					valid=true;
				}
			}

			if(valid ==false)
				System.out.println("ERROR: "+ stateName + " is not valid");
		}

		sm.mergeSort1(cities);

		System.out.println("\nFifty most populous cities in " + stateName);
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		int i = 0;
		int count = 0;
		while(count<50)
		{
			if(((cities.get(i)).getState()).equalsIgnoreCase(stateName))
			{
				System.out.printf("%3s ", (count+1)+":");
				System.out.print((cities.get(i)).toString());
				System.out.println("");
				count++;
			}
			i++;
		}

		System.out.println("");
	}

	/* Takes in input for user's choice of city name to
	 * find all cities matching that name.
	 * Calls the mergeSort1 method in the SortMethods
	 * class to sort the data by descending population order.
	 * prints out the all the cities that match the specified
	 * name (by checking the name variable of the City class)
	 * for the sorted data in a loop.
	 */
	public void allCitiesName()
	{
		SortMethods sm = new SortMethods();

		Scanner typed = new Scanner(System.in);
		boolean valid = false;

		System.out.println("");
		
		String cityName = "";
		while(!valid)
		{
			cityName = Prompt.getString("Enter city name"); 
			for(int i = 0; i < cities.size(); i++)
			{
				if(((cities.get(i)).getName()).equalsIgnoreCase(cityName))
				{
					valid=true;
				}
			}

			if(valid ==false)
				System.out.println("ERROR: "+ cityName + " is not valid");
		}

		sm.mergeSort1(cities);

		System.out.println("\nCity " + cityName + " by population");
		System.out.printf("%-3s %-22s %-22s %-12s %12s\n", "", "State", "City", "Type", "Population");

		int i = 0;
		int count = 0;
		while(i<31765)
		{
			if(((cities.get(i)).getName()).equalsIgnoreCase(cityName))
			{
				System.out.printf("%3s ", (count+1)+":");
				System.out.print((cities.get(i)).toString());
				System.out.println("");
				count++;
			}
			i++;
		}

		System.out.println("");
	}
}