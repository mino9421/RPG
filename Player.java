package RPG;

public class Player
    {
        public String name;
        public Weapon[] backpack;               // backpack will hold weapons/items

		public int numItems;
		
		public double money;

		public double weight = 0;

        public Player(String n, double m)
        {
            name = n;
            money = m;
            numItems = 0;                       // player starts with 0 items and each time new items inserted in backpack this variable increaments++
            backpack = new Weapon[30];          // max num of weapons is 30 + consider LoadFactor fraction
        }

        public void buy(Weapon w)
        {
            System.out.println(w.weaponName+" bought...");
            backpack[numItems] = w;             // this is the hashing part to find the item location
			numItems++;
            weight += w.weight;
            // adds to number of items to keep track it is max 30
			System.out.println(numItems);
            
			// this is printing the item count wit the player
            printBackpack();
        }

        public void withdraw(double amt)        // this method take money from the player
        {
            money = money - amt;
        }

        public boolean inventoryFull()          // this method returns true if inventory is full
        {
            return (numItems == 30) ;           // before submission change to 30 as per request in docs
        }

        public void printCharacter()            // this method prints player name and coins + (backpack)
        {
            System.out.println(" Name:"+name+"\n Money:"+money);
            printBackpack();
        }

        public void printBackpack()             // this will address player and number of items the player owns + list the weapons by name
        {
             System.out.println(" "+name+", you own "+numItems+", and your backpack weighs:"+ weight+" Weapons:");
            for (int x = 0; x < numItems; x++)
            {
                 System.out.println(" "+backpack[x].weaponName);
            }
            System.out.println();
        }
    }