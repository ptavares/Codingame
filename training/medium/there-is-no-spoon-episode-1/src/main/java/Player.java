import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();

        MicroCell microCell = new MicroCell(width, height);

        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            System.err.println("line = " + line);
            microCell.addLine(line, i);
        }
        System.err.println("microCell = \n" + microCell);
        //System.err.println("matrice = " + microCell.matrice);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String neigbors = microCell.getNeighbors(i, j);
                if (neigbors.length() > 0)
                    System.out.println(neigbors);
            }
        }
    }

    private static class MicroCell {
        private final Point[][] microCell;

        private final List<List<Point>> matrice;

        private int with;
        private int height;
        private Point out = new Point(-1, -1);

        public MicroCell(int with, int height) {
            this.with = with;
            this.height = height;
            matrice = new ArrayList<>(with);
            for (int i = 0; i < height; i++)
                matrice.add(new ArrayList<>(with));

            microCell = new Point[with][height];
        }

        public void addLine(String str, int height) {
            for (int i = 0; i < microCell.length; ++i) {
                Point p = microCell[i][height];
                if (p == null) microCell[i][height] = new Point(-1, -1);
                if (str.charAt(i) == '0') {
                    microCell[i][height].setLocation(i, height);
                }
            }
            List<Point> list = matrice.get(height);
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    list.add(new Point(i, height));
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Point[] aMicroCell : microCell) {
                for (Point anAMicroCell : aMicroCell) sb.append(anAMicroCell).append(" ");
                sb.append("\n");
            }
            return sb.toString();
        }

        public String getNeighbors(int i, int j) {
            StringBuilder sb = new StringBuilder();
            Point p = microCell[i][j];
            if (p.getX() == -1) {
                sb.append("");
            } else {
                sb.append((int) p.getX()).append(" ").append((int) p.getY()).append(" ");
                int left = i + 1;
                int down = j + 1;
                Point leftPoint = getNextLeftPoint(left, with, j);
                sb.append((int) leftPoint.getX()).append(" ").append((int) leftPoint.getY()).append(" ");
                Point downPoint = getNextDownPoint(down, height, i);
                sb.append((int) downPoint.getX()).append(" ").append((int) downPoint.getY()).append(" ");
            }
            return sb.toString();
        }

        public Point getNextLeftPoint(int startX, int sizeX, int y) {
            for (int i = startX; i < sizeX; i++) {
                if (microCell[i][y].getX() != -1)
                    return microCell[i][y];
            }
            return out;
        }

        public Point getNextDownPoint(int startY, int sizeY, int x) {
            for (int i = startY; i < sizeY; i++) {
                if (microCell[x][i].getY() != -1)
                    return microCell[x][i];
            }
            return out;
        }
    }
}