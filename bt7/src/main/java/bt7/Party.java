package bt7;

import java.util.LinkedList;

public class Party {
    private LinkedList<Player> members = new LinkedList<>();

    public void addMember(Player player) {
        if(members.size() < 3 && !members.contains(player)) {
            members.add(player);
            System.out.println(player.getName() + " was added!");
        } else {
            System.out.println(player.getName() + " was not added...");
        }
    }
    
}
