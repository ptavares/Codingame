import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private final List<Point> surface;

    public Player(int surfaceN) {
        surface = new ArrayList<>(surfaceN);
    }

    public void addPoint(int x, int y) {
        surface.add(new Point(x, y));
    }

    public List<Point> getLandingSite() {
        Point first = null;
        Point second = null;
        for (Point p : surface) {
            if (first == null) {
                first = p;
            } else {
                if (p.getY() == first.getY() && (p.getX() - first.getX()) >= 1000) {
                    second = p;
                    break;
                } else {
                    first = p;
                }
            }
        }
        return Arrays.asList(first, second);
    }

    public int getLandingSiteAltitude(List<Point> landingSite) {
        return (int) landingSite.get(0).getY();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.

        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.

        }

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            // Ep01 - KISS mode :)
            if (vSpeed < -39)
                System.out.println("0 4");
            else System.out.println("0 0");
        }
    }
}