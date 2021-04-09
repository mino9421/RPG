package RPG;

public class ArrayManager {
    
    int maxItems;               // records the max size of the table
    int numItems;               // records number of items in the list
	ShopItem[] table;
	double loadFactor;

    //hashtable itself - this is the quadratic part also shop can hold 80 items and we need to take into consideration the LoadFactor 80% (I think we have to increase the table size by 20%)
    // not sure about the question's wording (different types of items).

    public ArrayManager(int max,double lf){      //overloaded when there are 2 arguments(int, double) passed
        table = new ShopItem[max];                    // this will create a new array called table with the int passed to the function
        maxItems = max;                            // this will assign tableSize to max
        loadFactor = lf;                            // load factor assigning to the double passed in function
		numItems = 0; // numItems is a counter to keep track of (active) items in the table
        
        
    }
                        //tableSize
    public ArrayManager(int max){                //overloaded when there is one argument(int) passed
        table = new ShopItem[max];                    
        maxItems = max;                    
		loadFactor = 0.75; // load factor is default 0.75(75%) when no argument is passed to it 

		numItems = 0;
       
        
    }
    
	public ArrayManager() { //overloaded when arguments are not passed | current sample in main
		table = new ShopItem[20]; // default (value for the table is 20 when nothing is passed to the function)
        
		maxItems = 20; // default
		loadFactor = 0.75; // default
		numItems = 0;
        
		
	}

    private int hashFunctionThreeWords(String name, double weight, double cost){          // this will work for accountNum if it was INT
        int value = 0;                              
        int weight1 = 1;                                                                                    
        for (int x = 0; x < name.length(); x++){       
            value += (name.charAt(x) - 'a' + 1) * weight1;  
            weight1++;                               
        }

		value += weight;  // int are already unique deterministically
		value += cost;                  
        
        return value % maxItems;                  
    }

    public void put(String name, int range, int damage, double weight, double cost ,int quantity)
	{
		if (numItems/maxItems < loadFactor){
            int count = 1;
            int startLoc = hashFunctionThreeWords(name, weight, cost);             // change this function name according to the requirements given
			int loc = startLoc;
			// (table[loc]!=null && table[loc].compareTo("DELETED")!=0)
			while (table[loc] != null && (!table[loc].item.weaponName.equals("DELETED"))) {
				loc = (startLoc + count * count) % maxItems;
				count++;
			}
			Weapon w = new Weapon(name, range, damage, weight, cost);//weapon/int
			table[loc] = new ShopItem(w, quantity);
			table[loc].empty = ""; //we are here
			table[loc].numberInStock += quantity;
            numItems++;
        }
// if the above code was not excuted in an else statement we can print to notify player with the error, but this is the method that can just do it and we can check for the useability before calling this method
	}



    public int search(String name, double weight, double cost) {
		int count = 1;

		int startLoc = hashFunctionThreeWords(name, weight, cost);              // change this function name according to the requirements given
		int loc = startLoc;

        // (table[loc]!=null && table[loc].compareTo(obj.accountName)!=0)
        
		while (table[loc] != null && !table[loc].item.weaponName.equals(name) && table[loc].item.weight!=weight&& table[loc].item.cost!=cost)
        {                          // this needs to be revisited depending on the requirements
			loc = (startLoc + count * count) % maxItems;
			count++;
		}
		if (table[loc] == null)
			return -1;
        // obj.otherVariable = "changed2";                                                                      // this line can change the passed object member value
        return loc;
	}

// we are here
    public ShopItem get(String key)
    {
        int location = 0; //gets location in table based on key
        
        while (location <maxItems && ( table[location] == null ||!table[location].item.weaponName.equals(key)))   // findout why is table location == null
        {  // not empty and not item
               
            location++;
        }

        if (location<maxItems)
        {
            return table[location];
        }
        return null;
    }


    public boolean delete(String name, double weight, double cost){
        
        int count = 1;
        int startLoc = hashFunctionThreeWords(name, weight, cost);          // change this function name according to the requirements given
        int loc = startLoc; 
        // startLoc above can be replaced with a search call. When the value returns -1 it means the position is null or deleted which leads to the if statement below (alternative code)

		// (table[loc]!=null && table[loc].compareTo(obj.accountName)!=0)

        while (table[loc] != null && !table[loc].item.weaponName.equals(name) && table[loc].item.weight!=weight&& table[loc].item.cost!=cost)
		{
			loc = (startLoc + count * count) % maxItems;
			count++;
		}
		if (table[loc].item.weaponName != null)
        {
			return false;// this can check for -1 and if it is true confirms item has been deleted and decrements numItems (alternative code)
		}
        table[loc].empty = "DELETED"; 
		numItems--;
		return true;
    } 
    
	public void printTable() {
		System.out.println("Hash Table Contents");
		for (int x = 0; x < maxItems; x++) {
			if (table[x] != null) {
				System.out.println(x + " - " + table[x].item.weaponName);
			} else {
				System.out.println(x + " - " + "EMPTY");
			}
		}
		System.out.println("");
	}


}
