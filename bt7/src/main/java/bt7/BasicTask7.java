package bt7;

public class BasicTask7 {
    public static void main(String[] args) {
        Party party = new Party();
        Player p1 = new Player("Booch");
        Player p2 = new Player("Jacobson");
        Player p3 = new Player("Rumbaugh");
        Player p4 = new Player("Harel");
        party.addMember(p1);
        party.addMember(p2);
        party.addMember(p3);
        party.addMember(p4);
        p1.addItem(new Item("BoochMethod"));
        p1.addItem(new Item("UP"));
        p1.addItem(new Item("RUP"));
        p1.addItem(new Item("UML"));
        p1.addItem(new Item("DesignPattern"));
    }
}
