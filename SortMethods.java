import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts a List of cities in varying order.
 *
 *	@author Kanchan Kaushik
 *	@since	14 Jan 2023
 */
public class SortMethods {
	
	// temporary list of cities for merge sort
	
	
	/**
	 *	Swaps two City objects in List arr
	 *	@param arr		list of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp= arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
			
	}
	
	/**
	 *	Selection Sort algorithm - in ascending population order 
	 *  compares the City objects using compareTo for population order
	 *	@param arr		list of City objects to sort
	 */
	public void selectionSort(List<City> arr) 
	{
		for(int outer = arr.size()-1; outer>0; outer--)
		{
			int swap1 = 0;
			for(int inner = 0; inner<=outer; inner++)
			{
				if((arr.get(swap1)).compareTo(arr.get(inner))<0)
					swap1 = inner;
			}
			swap(arr, swap1, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending name order 
	 *  compares the city names using compareTo for strings, and if 
	 *  they are the same, compares the populations using compareTo
	 *  on the City objects
	 *	@param arr		list of City objects to sort
	 */
	public void insertionSort(List<City> arr) {
		for(int i = 1; i < arr.size(); i++)
		{
			City temp= arr.get(i);
			int ind = i;
			while(ind>0 &&
             ((temp.getName()).compareTo((arr.get(ind-1)).getName())<0 
             || ((temp.getName()).compareTo((arr.get(ind-1)).getName())==0 && temp.compareTo(arr.get(ind-1))>0) ) )
			{
				swap(arr, ind-1, ind);
				ind--;
			}
			arr.set(ind,temp);
		}
	}

	
	
	/**
	 *	Merge Sort 1 - in descending population order
	 *  initializes the temporary list, calls the recursive method
	 *	@param arr		list of City objects to sort*/
	 
	public void mergeSort1(List<City> arr) 
	{
		int n = arr.size();
		recursiveSort1(arr, 0, n-1);
	}
	
	/* recursive helper method: sorts a[from],...., a[to]
     * compares the City objects using compareTo for population order
	 * @param a 		list of city objects to sort
	 * @param from		the starting point of the section of the list
	 * @param to 		the ending point of the section of the array
	 */
	public void recursiveSort1(List<City> a, int from, int to)
	{
		if(to - from <2) // base case: 1 or 2 elements
		{
			if(to>from && (a.get(to)).compareTo(a.get(from))>0)
			{
				//swap a[to] and a[from]
				City aTemp = a.get(to);
				a.set(to, a.get(from));
				a.set(from, aTemp);
			}
		}
		else //recursive case
		{
			int middle = (from+to)/2;
			recursiveSort1(a, from, middle);
			recursiveSort1(a, middle+1, to);
			merge1(a, from, middle, to);
		}
	}

	/* merges a[from]...a[middle] and a[middle+1] ...a[to]
	 * into one sorted array a[from]...a[to]
	 * compares the City objects using compareTo for population order
	 * @param a 		list of city objects to sort
	 * @param from		the starting point of the section of the list
	 * @param middle 	the mid point of the section of the list
	 * @param to 		the ending point of the section of the array
	 */
	public void merge1(List<City> a, int from, int middle, int to)
	{
		List<City> temp = new ArrayList<City>();
		int i = from, j = middle +1, k=from;

		// while both arrays have elements left unprocessed:
		while(i<=middle && j <=to)
		{
			if((a.get(i)).compareTo(a.get(j))>0)
			{
				temp.add(a.get(i)); // or simply temp[k] = a[i++]
				i++;
			}
			else
			{
				temp.add(a.get(j));
				j++;
			}
			k++;
		}

		// copy the tail of the first half, if any, into temp:
		while(i<= middle)
		{
			temp.add(a.get(i)); // or simply temp[k++] = a[i++]
			i++;
			k++;
		}

		//copy the tail of the second half, if any, into temp:
		while(j<= to)
		{
			temp.add(a.get(j)); // or simply temp[k++] = a[j++]
			j++;
			k++;
		}

		// copy temp back into a
		for(k= 0; k<temp.size(); k++)
			a.set(from+k, temp.get(k));
	}




    /**
	 *	Merge Sort 2 - in descending name order
	 *  initializes the temporary list, calls the recursive method
	 *	@param arr		list of City objects to sort*/
	 
	public void mergeSort2(List<City> arr) 
	{
		int n = arr.size();
		recursiveSort2(arr, 0, n-1);
	}
	

	/* recursive helper method: sorts a[from],...., a[to]
	 * compares the city names using compareTo for strings, and if 
	 * they are the same, compares the populations using compareTo
	 * on the City objects
	 * @param a 		list of city objects to sort
	 * @param from		the starting point of the section of the list
	 * @param to 		the ending point of the section of the array
	 */
	public void recursiveSort2(List<City> a, int from, int to)
	{
		if(to - from <2) // base case: 1 or 2 elements
		{
			if(to>from && (((a.get(to)).getName()).compareTo((a.get(from)).getName())>0
                || (((a.get(to)).getName()).compareTo((a.get(from)).getName())==0 && (a.get(to)).compareTo(a.get(from))<0))) 
            {
				//swap a[to] and a[from]
				City aTemp = a.get(to);
				a.set(to, a.get(from));
				a.set(from, aTemp);
			}
		}
		else //recursive case
		{
			int middle = (from+to)/2;
			recursiveSort2(a, from, middle);
			recursiveSort2(a, middle+1, to);
			merge2(a, from, middle, to);
		}
	}

	/* merges a[from]...a[middle] and a[middle+1] ...a[to]
	 * into one sorted array a[from]...a[to]
	 * compares the city names using compareTo for strings, and if 
	 * they are the same, compares the populations using compareTo
	 * on the City objects
	 * @param a 		list of city objects to sort
	 * @param from		the starting point of the section of the list
	 * @param middle 	the mid point of the section of the list
	 * @param to 		the ending point of the section of the array
	 */
	public void merge2(List<City> a, int from, int middle, int to)
	{
		List<City> temp = new ArrayList<City>();
		int i = from, j = middle +1, k=from;

		// while both arrays have elements left unprocessed:
		while(i<=middle && j <=to)
		{
			if(((a.get(i)).getName()).compareTo((a.get(j)).getName())>0
                || (((a.get(i)).getName()).compareTo((a.get(j)).getName())==0 && (a.get(i)).compareTo(a.get(j))<0))
			{
				temp.add(a.get(i)); // or simply temp[k] = a[i++]
				i++;
			}
			else
			{
				temp.add(a.get(j));
				j++;
			}
			k++;
		}

		// copy the tail of the first half, if any, into temp:
		while(i<= middle)
		{
			temp.add(a.get(i)); // or simply temp[k++] = a[i++]
			i++;
			k++;
		}

		//copy the tail of the second half, if any, into temp:
		while(j<= to)
		{
			temp.add(a.get(j)); // or simply temp[k++] = a[j++]
			j++;
			k++;
		}

		// copy temp back into a
		for(k= 0; k<temp.size(); k++)
			a.set(from+k, temp.get(k));
	}
}




