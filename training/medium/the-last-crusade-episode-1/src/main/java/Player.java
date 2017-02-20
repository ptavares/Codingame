import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private final int width;
    private final int height;
    private final ROOM[][] lab;

    public Player(int width, int height) {
        this.lab = new ROOM[width][height];
        this.width = width;
        this.height = height;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        in.nextLine();

        Player p = new Player(W, H);

        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            System.err.println("Line = " + LINE);
            p.addGridLine(i, LINE);
        }

        System.err.println("--> " + p.toString());

        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            String next = p.calculNextPos(XI, YI, POS);
            System.err.println("next moove to : " + next);
            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(next);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                sb.append(lab[j][i]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String calculNextPos(int x, int y, String direction) {
        DIRECTION dir = DIRECTION.valueOf(direction);
        ROOM current = lab[x][y];
        Set<MOVE> moves = current.getExits();
        DIRECTION toGo = DIRECTION.NONE;
        for (MOVE move : moves) {
            if (dir == move.getFrom()) {
                toGo = move.getTo();
            }
        }

        return toGo.addTo(x, y);
    }

    private void addGridLine(int height, String line) {
        String[] rooms = line.split(" ");
        for (int i = 0; i < width; ++i)
            lab[i][height] = ROOM.valueOf("TYPE_" + rooms[i]);

    }

    public enum ROOM {
        TYPE_0(MOVE.NONE),
        TYPE_1(MOVE.T_D, MOVE.L_D, MOVE.R_D),
        TYPE_2(MOVE.L_R, MOVE.R_L),
        TYPE_3(MOVE.T_D),
        TYPE_4(MOVE.T_L, MOVE.R_D, MOVE.L_NONE),
        TYPE_5(MOVE.L_D, MOVE.T_R, MOVE.R_NONE),
        TYPE_6(MOVE.L_R, MOVE.R_L, MOVE.T_NONE),
        TYPE_7(MOVE.T_D, MOVE.R_D),
        TYPE_8(MOVE.R_D, MOVE.L_D),
        TYPE_9(MOVE.T_D, MOVE.L_D),
        TYPE_10(MOVE.T_L, MOVE.L_NONE),
        TYPE_11(MOVE.T_R, MOVE.R_NONE),
        TYPE_12(MOVE.R_D),
        TYPE_13(MOVE.L_D);
        private final Set<MOVE> exits;

        ROOM(MOVE... moves) {
            exits = new HashSet<>(Arrays.asList(moves));
        }

        public Set<MOVE> getExits() {
            return exits;
        }
    }

    public enum DIRECTION {
        TOP {
            @Override
            public String addTo(int x, int y) {
                return "" + x + " " + (y - 1);
            }
        },
        LEFT {
            @Override
            public String addTo(int x, int y) {
                return "" + (x - 1) + " " + (y);
            }
        },
        RIGHT {
            @Override
            public String addTo(int x, int y) {
                return "" + (x + 1) + " " + (y);
            }
        },
        DOWN {
            @Override
            public String addTo(int x, int y) {
                return "" + x + " " + (y + 1);
            }
        },
        NONE {
            @Override
            public String addTo(int x, int y) {
                return "" + x + " " + y;
            }
        };

        public abstract String addTo(int x, int y);
    }

    public enum MOVE {
        NONE(DIRECTION.NONE, DIRECTION.NONE),
        L_NONE(DIRECTION.LEFT, DIRECTION.NONE),
        R_NONE(DIRECTION.RIGHT, DIRECTION.NONE),
        T_NONE(DIRECTION.TOP, DIRECTION.NONE),
        L_D(DIRECTION.LEFT, DIRECTION.DOWN),
        T_D(DIRECTION.TOP, DIRECTION.DOWN),
        T_L(DIRECTION.TOP, DIRECTION.LEFT),
        T_R(DIRECTION.TOP, DIRECTION.RIGHT),
        R_D(DIRECTION.RIGHT, DIRECTION.DOWN),
        L_R(DIRECTION.LEFT, DIRECTION.RIGHT),
        R_L(DIRECTION.RIGHT, DIRECTION.LEFT);

        private final DIRECTION from;
        private final DIRECTION to;

        MOVE(DIRECTION from, DIRECTION to) {
            this.from = from;
            this.to = to;
        }

        public DIRECTION getFrom() {
            return from;
        }

        public DIRECTION getTo() {
            return to;
        }
    }
}