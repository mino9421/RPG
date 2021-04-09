package RPG;

public class Weapon
    {
        public String weaponName; 
        public int range;
        public int damage;
        public double weight;
        public double cost;
		String empty = "";
		int quantity;
        public Weapon(String n, int rang, int dam, double w, double c)
		{
			weaponName = n;
			damage = dam;
			range = rang;
			weight = w;
			cost = c;
			empty = n;
		}
		
    }