package RPG;

public class Backpack {
    public int numItems;
    public int maxItems;
    public double currWeight;
	public double maxWeight;

	public SeparateChaining[] list;

	public Backpack() {
        this.numItems = 0;
		this.maxItems = 30;
        this.currWeight = 0;
        this.maxWeight = 90;
        
        list = new SeparateChaining[maxItems];
    }

    private int hashFunction(Weapon data) {
        return (int)(  data.damage + data.range + data.cost + data.weight) % maxItems;
    }

    public void buyWeapon(Weapon item) {
        int index = hashFunction(item);
       
            if (list[index] == null) {
                list[index] = new SeparateChaining();
            }
			list[index].addFirst(item);
			currWeight += item.weight;
            numItems++;
         
    }

	

	public boolean itemCheck() {
        if(numItems + 1 > maxItems){
            System.out.println(("Backpack has too much stuff!"));
            return false;
        }
        return true;
    }




    public void showBackpack() {
        for (SeparateChaining ll : list) {
            if (ll == null) System.out.println("EMPTY");
            else ll.printItems();
        }
    }

    // private int hashFunction(Weapon data) {
       
    //     return (int)(data.damage + data.range + data.cost + data.weight) % maxItems;
    // }


    // public boolean buy(Weapon item) {
	// 	int index = hashFunction(item);
	// 	if (itemCheck()) {

	// 		if (list[index] == null) {
	// 			list[index] = new SeparateChaining();

	// 			list[index].addFirst(item);
	// 			currWeight += item.weight;
	// 			numItems++;
	// 			return true;
	// 		}
	// 	}
	// 	return false;
    // }

	// public boolean itemCheck() {
    //     if(numItems + 1 > maxItems){
    //         System.out.println(("Backpack cant hold any more items."));
    //         return false;
    //     }
    //     return true;
    // }




    // public void showBackpack() {
    //     for (SeparateChaining ll : list) {
    //         if (ll == null) System.out.println("EMPTY");
    //         else ll.printItems();
    //     }
    // }
}
