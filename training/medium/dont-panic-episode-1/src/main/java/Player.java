import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private final Game[][] game;
    private final Map<Integer, Integer> elevators;
    private Point exit;

    public Player(int nbFloors, int width) {
        game = new Game[nbFloors][width];
        for (int i = 0; i < nbFloors; i++) {
            for (int j = 0; j < width; j++) {
                game[i][j] = Game.EMPTY;
            }
        }
        elevators = new HashMap<>();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        Player player = new Player(nbFloors, width);
        player.setExit(exitFloor, exitPos);

        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            player.setElevator(elevatorFloor, elevatorPos);
        }

        System.err.println(player);
        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            System.err.println("cloneFloor = " + cloneFloor);
            System.err.println("clonePos = " + clonePos);
            System.err.println("direction = " + direction);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(player.getAction(cloneFloor, clonePos, direction)); // action: WAIT or BLOCK
        }
    }

    public void setExit(int exitFloor, int exitPos) {
        game[exitFloor][exitPos] = Game.EXIT;
        exit = new Point(exitFloor, exitPos);
    }

    public void setElevator(int elevatorFloor, int elevatorPos) {
        game[elevatorFloor][elevatorPos] = Game.ELEVATOR;
        elevators.put(elevatorFloor, elevatorPos);
    }

    public String getAction(int cloneFloor, int clonePos, String direction) {
        Game action = Game.WAIT;
        Game dir = Game.valueOf(direction);
        if (cloneFloor == exit.x) {
            if (clonePos > exit.y && dir == Game.RIGHT) {
                action = Game.BLOCK;
            }
            if (clonePos < exit.y && dir == Game.LEFT) {
                action = Game.BLOCK;
            }
        }
        if (elevators.get(cloneFloor) != null) {
            if (clonePos > elevators.get(cloneFloor) && dir == Game.RIGHT) {
                action = Game.BLOCK;
            }
            if (clonePos < elevators.get(cloneFloor) && dir == Game.LEFT) {
                action = Game.BLOCK;
            }
        }
        return action.name();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < game.length; i++) {
            sb.append(Arrays.toString(game[i]));
        }
        return sb.toString();
    }

    public enum Game {
        EMPTY,
        EXIT,
        CLONE,
        ELEVATOR,
        WAIT,
        BLOCK,
        LEFT,
        RIGHT,
        NONE;
    }
}