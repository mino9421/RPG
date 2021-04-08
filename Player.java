package RPG;

public class Player
    {
        public String name;
        public Weapon[] backpack;               // backpack will hold weapons/items
        public Backpack[] actualBackpack;       // i think this is how we need to connect player with weapon, through backpack >>> backpack will connect to player
        public int numItems;
        public double money;

        public Player(String n, double m)
        {
            name = n;
            money = m;
            numItems = 0;                       // player starts with 0 items and each time new items inserted in backpack this variable increaments++
            backpack = new Weapon[10];          // max num of weapons is 30 + consider LoadFactor fraction
        }

        public void buy(Weapon w)
        {
            System.out.println(w.weaponName+" bought...");
            backpack[numItems] = w;             // this is the hashing part to find the item location
            numItems++;                         // adds to number of items to keep track it is max 30
            System.out.println(numItems);       // this is printing the item count wit the player
        }

        public void withdraw(double amt)        // this method take money from the player
        {
            money = money - amt;
        }

        public boolean inventoryFull()          // this method returns true if inventory is full
        {
            return (numItems == 10) ;           // before submission change to 30 as per request in docs
        }

        public void printCharacter()            // this method prints player name and coins + (backpack)
        {
            System.out.println(" Name:"+name+"\n Money:"+money);
            printBackpack();
        }

        public void printBackpack()             // this will address player and number of items the player owns + list the weapons by name
        {
             System.out.println(" "+name+", you own "+numItems+" Weapons:");
            for (int x = 0; x < numItems; x++)
            {
                 System.out.println(" "+backpack[x].weaponName);
            }
            System.out.println();
        }
    }