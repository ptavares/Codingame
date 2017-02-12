import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Integer> set = new ArrayList<Integer>();
        System.err.println("Horses nb : " + N);
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            System.err.println("Horse" + i + " : " + pi);
            set.add(pi);
        }
        Collections.sort(set);
        System.err.println("List : " + set);
        int diff = Integer.MAX_VALUE;
        int previousPi = set.remove(0);
        for (Integer currentPi : set) {
            int tmp = currentPi - previousPi;
            if (tmp < diff)
                diff = tmp;
            previousPi = currentPi;
        }

        System.out.println(diff);
    }
}