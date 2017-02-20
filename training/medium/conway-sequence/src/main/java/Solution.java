import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final int origine;

    private Solution(int origine) {
        this.origine = origine;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int L = in.nextInt();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        Solution s = new Solution(R);
        List<Integer> result = s.evaluate(L);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : result) {
            sb.append(integer).append(" ");
        }
        System.out.println(sb.toString().substring(0, sb.length() - 1));
    }

    private List<Integer> evaluate(int indexLigne) {
        List<List<Integer>> list = new ArrayList<>(indexLigne);
        list.add(Collections.singletonList(origine));
        int i = 0;
        while (i < indexLigne) {
            System.err.println("i = " + i + " indexLigne = " + indexLigne);
            System.err.println("list = " + list);
            List<Integer> toAdd = new ArrayList<>();
            List<Integer> toAnalyse = list.get(i);
            int current = toAnalyse.get(0);
            int nbCurrent = 0;
            for (Integer integer : toAnalyse) {
                if (current == integer) {
                    nbCurrent += 1;
                } else {
                    toAdd.add(nbCurrent);
                    toAdd.add(current);
                    current = integer;
                    nbCurrent = 1;
                }
            }
            toAdd.add(nbCurrent);
            toAdd.add(current);

            list.add(toAdd);
            i++;
        }
        return list.get(indexLigne - 1);
    }
}