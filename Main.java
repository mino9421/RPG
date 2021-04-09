/*
    Authors:
        Armen Levon Armen - 101-281-931
        Zion Henry        - 101-232-420

*/
package RPG;
import java.util.Scanner;

public class Main {
    
    public static void menuPrint(Scanner menu)
    {
        int menuController;
        
        do
        {
            System.out.println("1.Add Item To Shop");
            System.out.println("2.Delete Item From Shop");
            System.out.println("3.Buy From The Shop");
            System.out.println("4.View Backpack");
            System.out.println("5.View Player");
            System.out.println("6.Exit");
            
            menuController = getInteger(menu);


            switch (menuController) {
                case 1:
                    System.out.println("You chose 1.Add Item To Shop");  
                    break;
                case 2:
                    System.out.println("You chose 2.Delete Item From Shop");
                    break;
                case 3:
                    System.out.println("You chose 3.Buy From The Shop");
                    break;
                case 4:
                    System.out.println("You chose 4.View Backpack");
                    break;
                case 5:
                    System.out.println("You chose 5.View Player");
                    break;
                case 6:
                    System.out.println("You chose to exit");
                    break;
                default:
                    break;
            }
        }
        while(menuController != 6);
    }

    public static int getInteger(Scanner sc){
        // System.out.print(message);
        while (!sc.hasNextInt()) 
        {
            sc.nextLine();                  //clear the invalid input ...
            // System.out.print(message);
        }
        return sc.nextInt();
    }

    public static int getInteger(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextInt()) 
        {
            sc.nextLine();                  //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextInt();
    }
    
    public static double getDouble(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextDouble()) 
        {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextDouble();
    }
    

    public static void addWeapons(ArrayManager h,Scanner sc)                            // after entering player name this is being called
	{
		System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");   
		String weaponName;
		int weaponRange;
		int weaponDamage;
		double weaponWeight;
		double weaponCost;
		int quantity;
		System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
		weaponName = sc.next();

		while (weaponName.compareTo("end") != 0) { // this will run until user enters "end"

			    weaponRange = getInteger(sc, "Please enter the Range of the Weapon (0-10):");
				weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
				weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
				weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
				Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
			
				if (h.table[h.search(weaponName, weaponWeight, weaponCost)] == null) { 

					// make hash table object, insert w into the hash.
					// insert the hashed object into a table
					// not sure when to insert the object yet

                    // if(h.search(weaponName, weaponWeight, weaponCost) == -2)
                    // {

                    // }
					quantity = getInteger(sc, "Please enter the quantity in stock:");
					h.put(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost, quantity);

					System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
					weaponName = sc.next();
				}
               
                else 
				{
                    System.out.println("The weapon " + h.table[h.search(weaponName, weaponWeight, weaponCost)].item.weaponName + " already exists");
                    h.table[h.search(weaponName,weaponWeight, weaponCost)].numberInStock += 
                                    getInteger(sc, "Please the amount of this item you would like to add: ");

                    System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
                    weaponName = sc.next();
                }
		}
	}

    public static void deleteWeapon(ArrayManager h, Scanner sc)
    {
        System.out.println("***********WELCOME TO THE WEAPON DELETING MENU*********");
        
        String weaponName;
		System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
		weaponName = sc.next();
		if (h.get(weaponName) == null)
        {
			System.out.println("This item does not exist!");
		} 
		else
        {
            if (h.delete(weaponName) == false)
            {
                System.out.println("Item was not deleted!");
            }
            else
            {
                System.out.println("Item deleted");
            }
        }

		
    }

    public static void showRoomMenu(ArrayManager ht,Player p){ 
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        ht.printTable();
        System.out.println("You have "+p.money+" money.");
        System.out.println("Please select a weapon to buy('end' to quit):");
    }
    
    public static void showRoom(ArrayManager ht, Player p,Scanner sc)
    {
        String choice;
        showRoomMenu(ht,p);
        choice=sc.next();
        while (choice.compareTo("end") != 0 && !p.inventoryFull())
        {
			ShopItem si = ht.get(choice);

            if (si != null && si.item.weaponName != "DELETED")
			{
				if (p.money >= si.item.cost) { // checking if the player has enough coins ()
					if (p.weight + si.item.weight <= 90 && p.numItems + 1 <= 30) {
						if ((si.numberInStock - 1) > 0) {

							p.buy(si.item);
							p.withdraw(si.item.cost);
							si.numberInStock--;
						}
						else
                        {
                            System.out.println("****The item is out of stock****");
                        }
					}
					else
                    {
                         System.out.println("Player backpack is currently full");
                    }
					
				}
				else
                {
					System.out.println("You do not have enough coins to make this purchase or this item was deleted.");
                }
                    // we need to make sure the player has enough coins and space in backpack
                 

            }
            else
            {
                System.out.println(" ** "+choice+" not found!! **" );
            }
            showRoomMenu(ht,p);
            choice = sc.next();
        }
        System.out.println("");
    }
    
    public static void main(String[] args)
    {


        Scanner sc = new Scanner(System.in);
        String pname;
    
        System.out.println("Please enter Player name:");
        pname=sc.next();                                    // user enters player name here
    
        Player pl= new Player(pname,45);                    // the player object is being created with name and default money passed 45.
        int menuController;
        Scanner menu = new Scanner(System.in);
        
        ArrayManager ht= new ArrayManager(11);

        do
        {
            System.out.println("1.Add Item To Shop");
            System.out.println("2.Delete Item From Shop");
            System.out.println("3.Buy From The Shop");
            System.out.println("4.View Backpack");
            System.out.println("5.View Player");
            System.out.println("6.Exit");
            
            menuController = getInteger(menu);


            switch (menuController) {
                case 1:
                    System.out.println("**You chose 1.Add Item To Shop**");
                    addWeapons(ht, sc);
                    break;
                case 2:
                    System.out.println("**You chose 2.Delete Item From Shop**");
                    deleteWeapon(ht, sc);
                    break;
                case 3:
                    System.out.println("**You chose 3.Buy From The Shop**");
                    showRoom(ht, pl, sc);
                    break;
                case 4:
                    System.out.println("**You chose 4.View Backpack**");
                    pl.printBackpack();
                    break;
                case 5:
                    System.out.println("**You chose 5.View Player**");
                    pl.printCharacter();
                    break;
                case 6:
                    System.out.println("**You chose to exit**");
                    break;
                default:
                    System.out.println("***Please choose a valid option(1-6)***");
                    break;
            }
        }
        while(menuController != 6);
        System.out.println("*****Game has been terminated*****");
    }
}


/*
    Player can NOT buy an item if
        - do not have enough coins                                          incomplete
        - backpack is or will be overweight if bought                       this part is working only on 10 items break need to implement weight limit (compared to below has to say backpack will overflow if we added)
        - backpack is is full (might be similar to the point above)         this has to say backpack is full already

    Player starts with 45 coins
    backpack must be implemented as an object and uses a hash table implemented as sep. chaining to hold the items (weapons) bought
    max number of items backpack can hold is 30                             max num of items in backpack 30
    max weight backpack can take is 90 pounds.                              backpack max weight pounds 90


    in main program ask user for a NAME for the player                      done
    next...
    present a menu with functionality                                       need to create a menuPrint method
    1. add items to the shop                                                unknown
    2. delete items from the shop                                           unknown
    3. buy from the shop - Allows the player to buy items from the shop.
    Only items that are in stock (quantity > 0) should be displayed         incomplete/ needs to set limit when quantity > 0
    4. view backpack - list of all the items in the player's backpack       done     
    5. view player - show(print)                                            done   
                        - the player's name                                 
                        - the amount of coins they have                     
                        - a list of all the items in their backpack         
    6. exit                                                                 done

*/