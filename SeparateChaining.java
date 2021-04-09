package RPG;

public class SeparateChaining {
    private Node head;

    public SeparateChaining() {
        this.head = null;
    }

    public void addFirst(Weapon item) {
        Node temp = head;
        Node newNode = new Node(item);
        head = newNode;
        head.next = temp;
    }

    public void printItems() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data.weaponName + " => ");
            curr = curr.next;
        }
        System.out.println();
    }
}
