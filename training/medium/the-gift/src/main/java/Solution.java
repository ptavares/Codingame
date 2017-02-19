import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final int price;

    private final HashMap<Integer, Integer> budgets;
    private final HashMap<Integer, Integer> repartition;

    public Solution(int price) {
        this.price = price;
        budgets = new HashMap<>();
        repartition = new HashMap<>();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();
        int total = 0;
        Solution s = new Solution(C);

        for (int i = 0; i < N; i++) {
            int B = in.nextInt();
            s.addBudget(i, B);
            total += B;
        }
        if (total < C) {
            System.out.println("IMPOSSIBLE");
        } else {
            s.resolve();
            ArrayList<Integer> toAffiche = new ArrayList<>(s.repartition.values());
            System.err.println("resultat = " + toAffiche);
            Collections.sort(toAffiche);
            for (Integer integer : toAffiche) {
                System.out.println(integer);
            }
        }

    }

    public void addBudget(int donneur, int budget) {
        budgets.put(donneur, budget);
        repartition.put(donneur, 0);
    }

    private void resolve() {
        int total = 0;
        List<Integer> keys = new ArrayList<>(budgets.keySet());
        Collections.sort(keys);
        while (total != price) {
            for (Integer key : keys) {
                int value = budgets.get(key);
                if (value != 0) {
                    total += 1;
                    budgets.put(key, (value - 1));
                    repartition.put(key, repartition.get(key) + 1);
                }
                if (total == price)
                    break;
            }
        }
    }
}