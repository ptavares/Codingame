import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    // Set of roots
    private final Set<Integer> roots;
    // Map of roots / list of edge (relationship)
    private final Map<Integer, List<Integer>> edgesMap;

    /* Constructor, init fields */
    private Solution() {
        roots = new HashSet<>();
        edgesMap = new HashMap<>();
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence

        Solution s = new Solution();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();

            // Add x to root by default, the real roots are calculate after
            s.roots.add(x);
            // Add y as relationship (edge) of x (the local root)
            List<Integer> edges = s.edgesMap.get(x);
            if (edges == null) {
                edges = new ArrayList<>();
                s.edgesMap.put(x, edges);
            }
            edges.add(y);
        }

        // Remove all non real roots (if they are present in edges)
        for (List<Integer> edges : s.edgesMap.values()) {
            for (Integer edge : edges) {
                s.roots.remove(edge);
            }
        }

        // Count all the relationship from real roots
        int count = 0;
        for (Integer root : s.roots) {
            int tmp = s.count(root, s.edgesMap);
            if (tmp > count) count = tmp;
        }

        System.out.println(count);
    }

    private int count(Integer root, Map<Integer, List<Integer>> edgesMap) {
        // iterate over each edge if exist
        if (edgesMap.get(root) != null && !edgesMap.get(root).isEmpty()) {
            int count = 1;
            for (Integer edge : edgesMap.get(root)) {
                int c = count(edge, edgesMap);
                if (c > count)
                    count = c;
            }
            return count + 1;
        }
        return 1;
    }
}