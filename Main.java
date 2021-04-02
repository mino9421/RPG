/*
    Authors:
        Armen Levon Armen - 101-281-931
        Zion Henry        - 101-232-420

*/
package RPG;

import java.util.Scanner;

public class Main {
    
    public static int getInteger(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextInt()) 
        {
            sc.nextLine(); //clear the invalid input ...
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
    

    public static void addWeapons(ArrayManager h,Scanner sc)
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

		while (weaponName.compareTo("end") != 0) {
			
			if (h.get(weaponName) == null) {

				weaponRange = getInteger(sc, "Please enter the Range of the Weapon (0-10):");
				weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
				weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
				weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
				Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);

                // make hash table object, insert w into the hash.
                // insert the hashed object into a table

				quantity = getInteger(sc, "Please enter the quantity in stock:");
				h.put(w, quantity);
				System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
				weaponName = sc.next();
			}

			else {
				
                System.out.println("The weapon "+weaponName+" already exists");
				ShopItem s = h.get(weaponName);
				s.numberInStock += getInteger(sc, "Please the amount of this item you would like ot add: ");
                System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
				weaponName = sc.next();
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
            if (si != null)
            {

                    p.buy(si.item);
                    p.withdraw(si.item.cost);
                    si.numberInStock--;

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
        pname=sc.next();
        Player pl= new Player(pname,45);
        ArrayManager ht= new ArrayManager(101);
        addWeapons(ht,sc);
        showRoom(ht, pl,sc);
        pl.printCharacter();

    }



}


/*
    Player can NOT buy an item if
        - do not have enough coins
        - backpack is or will be overweight if bought
        - backpack is is full (might be similar to the point above)

    Player starts with 45 coins
    backpack must be implemented as an object and uses a hash table implemented as sep.chaining to hold the items (weapons) bought
    max number of items backpack can hold is 30
    max weight backpack can take is 90 pounds.


    in main program ask user for a NAME for the player
    next...
    present a menu with functionality
    1. add items to the shop 
    2. delete items from the shop
    3. buy from the shop - Allows the player to buy items from the shop. Only items that are in stock (quantity > 0) should be displayed
    4. view backpack - list of all the items in the player's backpack
    5. view player - show(print)
                        - the player's name
                        - the amount of coins they have
                        - a list of all the items in their backpack
    6. exit

*/