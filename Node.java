package RPG;

public class Node {

    public Weapon data;
    public Node next;
    
    public Node(Weapon key)
    {
        data = key;
        next = null;
    }
}
