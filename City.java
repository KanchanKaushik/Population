/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Kanchan Kaushik
 *	@since	14 Jan 2023
 */
public class City implements Comparable<City> {
	
	// fields that store data for the city
	private String name, state, designation;
	private int population;
	
	// constructor initializes all fields to the parameters
	public City(String n, String s, String d, int p)
	{
		name = n;
		state = s;
		designation = d;
		population = p;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other)
	 {
		if(other.population != this.population)
		 	return this.population - other.population;
		else if(!(this.state).equals(other.state))
			return (this.state).compareTo(other.state);
		else
			return (this.name).compareTo(other.name);
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other)
	 {
		if((this.state).equals(other.state) && (this.name).equals(other.name))
			return true;
		else
			return false;
	 }
	
	/**	Accessor methods */

	// returns name FV
	public String getName(){return name;}

	// returns state FV
	public String getState(){return state;}

	// returns designation FV
	public String getDesignation(){return designation;}

	// returns population FV
	public int getPop(){return population;}
	
	/**	toString */
	/*@Overriden to format the data for the city*/
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}