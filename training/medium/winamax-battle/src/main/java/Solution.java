import java.util.LinkedList;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final LinkedList<String> cardsP1;
    private final LinkedList<String> cardsP2;
    private final LinkedList<String> cardsOnTableP1;
    private final LinkedList<String> cardsOnTableP2;

    public Solution() {
        cardsP1 = new LinkedList<>();
        cardsP2 = new LinkedList<>();
        cardsOnTableP1 = new LinkedList<>();
        cardsOnTableP2 = new LinkedList<>();
    }

    public int getCardValue(String cardIn) {
        int value = -1;
        String card = cardIn.substring(0, 1);
        if (cardIn.length() == 3) {
            card = cardIn.substring(0, 2);
        }
        try {
            value = Integer.parseInt(card);
        } catch (NumberFormatException nfe) {
            if ("J".equals(card))
                value = 11;
            else if ("Q".equals(card))
                value = 12;
            else if ("K".equals(card))
                value = 13;
            else if ("A".equals(card))
                value = 14;
        }

        return value;
    }

    int getWinner(String card1, String card2) {
        if (getCardValue(card1) > getCardValue(card2))
            return 1;
        else if (getCardValue(card1) < getCardValue(card2))
            return 2;
        else
            return 0;
    }

    public static void main(String args[]) {

        Solution game = new Solution();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            game.cardsP1.addLast(cardp1);
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            game.cardsP2.addLast(cardp2);
        }


        int rounds = 0;
        while (true) {

            // The game ends when one player no longer has cards
            if (game.cardsP1.isEmpty()) {
                // P1 loose
                System.out.println("2 " + rounds);
                return;
            } else if (game.cardsP2.isEmpty()) {
                // P2 loose
                System.out.println("1 " + rounds);
                return;
            }

            boolean isWar;

            do {

                // default game round
                isWar = false;

                // Each player put a card on table
                String cardP1 = game.cardsP1.removeFirst();
                String cardP2 = game.cardsP2.removeFirst();
                game.cardsOnTableP1.addLast(cardP1);
                game.cardsOnTableP2.addLast(cardP2);

                // Play game round
                int roundWinner = game.getWinner(cardP1, cardP2);
                System.err.println("cardP1 = " + cardP1 + " cardP2 = " + cardP2 + " " + " winner = " + roundWinner);

                // Analyse Winner
                if (roundWinner == 1) {

                    game.cardsOnTableP1.forEach(game.cardsP1::addLast);
                    game.cardsOnTableP2.forEach(game.cardsP1::addLast);

                    game.cardsOnTableP1.clear();
                    game.cardsOnTableP2.clear();
                } else if (roundWinner == 2) {

                    game.cardsOnTableP1.forEach(game.cardsP2::addLast);
                    game.cardsOnTableP2.forEach(game.cardsP2::addLast);

                    game.cardsOnTableP1.clear();
                    game.cardsOnTableP2.clear();
                } else {
                    // Case War Battle

                    // 1.  Enough cards ?
                    if (game.cardsP1.size() < 4 || game.cardsP2.size() < 4) {
                        System.out.println("PAT");
                        return;
                    }

                    // Place 3 cards face down
                    for (int i = 0; i < 3; ++i) {
                        game.cardsOnTableP1.addLast(game.cardsP1.removeFirst());
                        game.cardsOnTableP2.addLast(game.cardsP2.removeFirst());
                    }

                    // Draw the next cards
                    isWar = true;
                }


            } while (isWar);

            rounds++;
        }

    }
}