import javax.swing.JOptionPane;

public class Bag {

	public final class ArrayBag<Song> {

	    private final Song[] bag;
	    private int numberOfEntries;
	    private static final int DEFAULT_CAPACITY = 25;
	    
	    private boolean initialized = false;
	    private static final int MAX_CAPACITY = 10000;

	    public ArrayBag() {
	        this(DEFAULT_CAPACITY);
	    } 

	    public ArrayBag(int desiredCapacity) {
	        if (desiredCapacity <= MAX_CAPACITY) {

	            @SuppressWarnings("unchecked")
	            Song[] tempBag = (Song[]) new Object[desiredCapacity]; 
	            bag = tempBag;
	            numberOfEntries = 0;
	            initialized = true;
	        }
	        else
	            throw new IllegalStateException("Attempt to create a bag " +
	                                            "whose capacity exceeds " +
	                                            "allowed maximum.");
	    } // end constructor

	    @SuppressWarnings("unchecked")
		public void add(Object newEntry) {
	        /**checkInitialization();
	        boolean result = true;
	        if (isArrayFull()) {
	            result = false;
	        } else { // Assertion: result is true here
	            bag[numberOfEntries] = newEntry;
	            numberOfEntries++;
	        } // end if**/
	    	try {
	    	      bag[numberOfEntries] = (Song) newEntry;
	    	      numberOfEntries++;
	    	    } catch (ArrayIndexOutOfBoundsException n) {
	    	      JOptionPane.showMessageDialog(null, "Array is full, element will not be added");
	    	    }
	    } // end add


	    private void checkInitialization()
	    {
	        if (!initialized)
	             throw new SecurityException("ArrayBag object is not initialized properly.");
	    }
	    

	    public Song[] toArray() {
	        // the cast is safe because the new array contains null entries
	        @SuppressWarnings("unchecked")
	        Song[] result = (Song[]) new Object[numberOfEntries]; // unchecked cast
	        for (int index = 0; index < numberOfEntries; index++) {
	            result[index] = bag[index];
	        } // end for
	        return result;
	    } // end toArray
	   
	    private boolean isArrayFull() {
	        return numberOfEntries >= bag.length;
	    } // end isArrayFull

	    public boolean isEmpty() {
	        return numberOfEntries == 0;
	    } // end isEmpty

	
	    public int getCurrentSize() {
	        return numberOfEntries;
	    } // end getCurrentSize


	    public int getFrequencyOf(Song anEntry) {
	        checkInitialization();
	        int counter = 0;
	        for (int index = 0; index < numberOfEntries; index++) {
	            if (anEntry.equals(bag[index])) {
	                counter++;
	            } // end if
	        } // end for
	        return counter;
	    } // end getFrequencyOf


	    public boolean contains(Song anEntry) {
	        checkInitialization();
	        return getIndexOf(anEntry) > -1;
	    } // end contains

	    /** Removes all entries from this bag. */
	    public void clear() {
	        while (!isEmpty()) {
	            remove();
	        }
	    } // end clear


	    public Song remove() {
	        checkInitialization();
	        
	        int x = (int) (Math.random() * (numberOfEntries - 1));//makes this guaranteed to be less or equal to number of entries - 1.
	        Song result = removeEntry(x);
	        return result;
	    } // end remove


	    public boolean remove(Song anEntry) {
	        checkInitialization();
	        int index = getIndexOf(anEntry);
	        Song result = removeEntry(index);
	        return anEntry.equals(result);
	    } // end remove

	    private Song removeEntry(int givenIndex) {
	        Song result = null;
	        if (!isEmpty() && (givenIndex >= 0)) {
	            result = bag[givenIndex];                   // entry to remove
	            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
	            bag[numberOfEntries - 1] = null;            // remove last entry
	           numberOfEntries--;
	         } // end if
	        return result;
	    } // end removeEntry
	    
	    

	    public String toString() {

	        String result = "Bag{Size:" + numberOfEntries + " ";
	        

	        for (int index = 0; index < numberOfEntries; index++) {
	            result += "[" + bag[index] + "] ";
	        } // end for

	        result += "}";
	        return result;
	    } // end toArray

	    private int getIndexOf(Song anEntry) {
	        int where = -1;
	        boolean stillLooking = true;
	        int index = 0;
	        while ( stillLooking && (index < numberOfEntries)) {
	            if (anEntry.equals(bag[index])) {
	                stillLooking = false;
	                where = index;
	            } // end if
	            index++;
	        } // end for
	        
	        // Assertion: If where > -1, anEntry is in the array bag, and it
	        // equals bag[where]; otherwise, anEntry is not in the array
	        return where;
	    } // end getIndexOf

	}

}
