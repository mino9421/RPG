package RPG;

public class ArrayManager {
    
    int maxItems;               // records the max size of the table
    int numItems;               // records number of items in the list
    ShopItem[] table;           
    //hashtable itself - this is the quadratic part also shop can hold 80 items and we need to take into consideration the LoadFactor 80% (I think we have to increase the table size by 20%)
    // not sure about the question's wording (different types of items).

    public ArrayManager(int size)
    {
        maxItems = size;                            // this is preferred to be a prime number
        numItems = 0;
        table = new ShopItem[maxItems];             // giving the table the size passed to the constructor
    }

    public void put(Weapon item,int quantity)
	{
		if (numItems < maxItems) {                              // if number of items is smaller than max items then >>
			table[numItems] = new ShopItem(item, quantity);     // 
			numItems++;
		}
// if the above code was not excuted in an else statement we can print to notify player with the error, but this is the method that can just do it and we can check for the useability before calling this method
	}



    public ShopItem get(String key)                 // need to understand this !!!!!!!!!!!
    {
        int location = 0;                                                                           //gets location in table based on key
        // location less than numitems && cant understand this segment
        while (location < numItems && key.compareTo(table[location].item.weaponName) != 0)           // not empty and not item
        {
            location++;
        }
        if (location<numItems){                                                                 // if location is smaller than number of items in arrayManager
            return table[location];                                                             // just return the object
        }
        return null;
    }

    public void printTable()                                                                    // this is a print method for the item stats
    {
        int count = 0;
        for (int x = 0; x < numItems; x++)
        {
                System.out.println("Name: " +table[x].item.weaponName+"   Damage:"+table[x].item.damage+"    Cost:"+table[x].item.cost+"     Quantity in stock:"+table[x].numberInStock);
        }
    }
}
