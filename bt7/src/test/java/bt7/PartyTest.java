package bt7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PartyTest {

    @Test
    public void testAddMember() {
        Party party = new Party();
        Player player = new Player("Test Player");

        // addMemberが例外をスローしないことを確認
        party.addMember(player);
        assertEquals("Test Player", player.getName());
    }

    @Test
    public void testAddMemberMultiple() {
        Party party = new Party();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");

        // 最大3人まで追加可能
        party.addMember(player1);
        party.addMember(player2);
        party.addMember(player3);

        assertEquals("Player 1", player1.getName());
        assertEquals("Player 2", player2.getName());
        assertEquals("Player 3", player3.getName());
    }

    @Test
    public void testAddMemberOverLimit() {
        Party party = new Party();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");

        // 3人まで追加
        party.addMember(player1);
        party.addMember(player2);
        party.addMember(player3);

        // 4人目は追加できない（例外はスローされない）
        party.addMember(player4);

        assertEquals("Player 1", player1.getName());
        assertEquals("Player 4", player4.getName());
    }

    @Test
    public void testAddMemberDuplicate() {
        Party party = new Party();
        Player player = new Player("Test Player");

        // 同じプレイヤーを2回追加（2回目は追加されない）
        party.addMember(player);
        party.addMember(player);

        assertEquals("Test Player", player.getName());
    }
}

