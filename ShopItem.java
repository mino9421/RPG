package RPG;

public class ShopItem {
    Weapon item;
    int numberInStock;
    
    public ShopItem(Weapon w, int nInStock){
        item=w;
        numberInStock=nInStock;
    }
}