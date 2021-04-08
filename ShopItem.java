package RPG;

public class ShopItem {                         // this is the class that will hold the shop market
    Weapon item;                                // item is the object for the weapon that will be placed on market
    int numberInStock;                          // this variable will hold the quantity (not sure !?).
    
    public ShopItem(Weapon w, int nInStock){
        item=w;
        numberInStock=nInStock;
    }
}