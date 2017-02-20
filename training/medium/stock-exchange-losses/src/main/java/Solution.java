import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int lost = 0;

        System.err.println("n = " + n);
        List<Integer> list = new ArrayList<>(n);
        int max = 0;

        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (i == 0) {
                max = v;
            } else {
                if (v - max < lost) {
                    lost = v - max;
                }
                if (v > max) {
                    max = v;
                }
            }
            list.add(v);
        }
        System.err.println("list = " + list);
        System.err.println("lost = " + lost);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(lost);
    }
}