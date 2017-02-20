import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {


    private final Range width;
    private final Range height;
    private final int nbTurns;
    private Point position;
    private Point previousPosition;

    public Player(int width, int height, int nbTurns) {
        this.width = new Range(0, width);
        this.height = new Range(0, height);
        this.nbTurns = nbTurns;
        position = new Point();
        previousPosition = null;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        Player player = new Player(W, H, N);
        player.setPosition(X0, Y0);

        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            // Add Direction
            Direction[] dir = new Direction[bombDir.length()];
            for (int i = 0; i < bombDir.length(); i++) {
                dir[i] = Direction.valueOf("" + bombDir.charAt(i));
            }
            System.out.println(player.nexMove(dir).getPositionAsString());
        }
    }


    public Player setPosition(int x, int y) {
        position.setLocation(x, y);
        return this;
    }

    public String getPositionAsString() {
        return position.x + " " + position.y;
    }

    public Player nexMove(Direction... directions) {
        int y = position.y;
        int x = position.x;
        System.err.println("Direction = " + Arrays.toString(directions));
        for (Direction direction : directions) {
            if (direction == Direction.D || direction == Direction.U) {
                y = direction.calcNextMove(position, previousPosition, height);
            } else {
                x = direction.calcNextMove(position, previousPosition, width);
            }
        }
        Point tmp = new Point(x, y);
        if (previousPosition == null) previousPosition = new Point();
        previousPosition.setLocation(position);
        position.setLocation(tmp);
        return this;
    }

    public enum Direction {
        U {
            @Override
            public int calcNextMove(Point position, Point previousPosition, Range range) {
                int y;
                System.err.println("position = " + position);
                System.err.println("previousPosition = " + previousPosition);
                System.err.println("range = " + range);
                range.setEnd(position.y);
                if (previousPosition == null) {
                    y = position.y / 2;
                } else {
                    if (range.getStart() != range.getEnd())
                        y = (range.getStart() + position.y) / 2;
                    else
                        y = position.y;
                }
                System.err.println("y = " + y);
                return y;
            }
        },
        D {
            @Override
            public int calcNextMove(Point position, Point previousPosition, Range range) {
                int y;
                System.err.println("position = " + position);
                System.err.println("previousPosition = " + previousPosition);
                System.err.println("range = " + range);
                range.setStart(position.y);

                if (previousPosition == null) {
                    y = (position.y + range.getEnd()) / 2;
                } else {
                    if (range.getStart() != range.getEnd())
                        y = (range.getEnd() + position.y) / 2;
                    else
                        y = position.y;
                }
                System.err.println("y = " + y);
                return y;
            }


        },
        R {
            @Override
            public int calcNextMove(Point position, Point previousPosition, Range range) {
                int x;
                System.err.println("position = " + position);
                System.err.println("previousPosition = " + previousPosition);
                System.err.println("range = " + range);
                range.setStart(position.x);

                if (previousPosition == null) {
                    x = (position.x + range.getEnd()) / 2;
                } else {
                    if (range.getStart() != range.getEnd())
                        x = (range.getEnd() + position.x) / 2;
                    else
                        x = position.x;
                }
                System.err.println("x = " + x);
                return x;
            }

        },
        L {
            @Override
            public int calcNextMove(Point position, Point previousPosition, Range range) {
                int x;
                System.err.println("position = " + position);
                System.err.println("previousPosition = " + previousPosition);
                System.err.println("range = " + range);
                range.setEnd(position.x);
                if (previousPosition == null) {
                    x = position.x / 2;
                } else {
                    if (range.getStart() != range.getEnd())
                        x = (range.getStart() + position.x) / 2;
                    else
                        x = position.x;
                }
                System.err.println("x = " + x);
                return x;
            }

        };

        public abstract int calcNextMove(Point position, Point previousPosition, Range range);
    }

    public class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}