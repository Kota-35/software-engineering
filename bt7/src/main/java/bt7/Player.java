package bt7;

import java.util.LinkedList;

public class Player {
    private String name;
    private LinkedList<Item> items = new LinkedList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addItem(Item item) {
        if(items.size() < 4 && !items.contains(item)) {
            items.add(item);
            System.out.println(this.name + " got " + item.getName() + "!");
        } else {
            System.out.println(this.name + " could not have " + item.getName() + "...");
        }
    }

    public String getName() {
        return name;
    }
    
}
