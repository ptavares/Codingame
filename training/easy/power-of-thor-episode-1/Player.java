import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position

        int nbMvtY = initialTY - lightY;
        int nbMvtX = initialTX - lightX;

        System.err.println(nbMvtY);
        System.err.println(nbMvtX);

        // game loop
        while (true) {
            String move = "";
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            if (nbMvtY > 0) {
                move = move + "N";
                nbMvtY = nbMvtY - 1;
            } else if (nbMvtY < 0) {
                move = move + "S";
                nbMvtY = nbMvtY + 1;
            }

            if (nbMvtX > 0) {
                move = move + "W";
                nbMvtX = nbMvtX - 1;
            } else if (nbMvtX < 0) {
                move = move + "E";
                nbMvtX = nbMvtX + 1;
            }

            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(move);
        }
    }
}