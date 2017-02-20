import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final Map<String, Dial> dials;

    private Solution() {
        dials = new HashMap<>();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Solution s = new Solution();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            System.err.println("Phone = " + telephone);
            s.addPhone(telephone);
        }

        System.err.println("Solution = " + s);
        System.err.println("total = " + s.getTotal());

        // The number of elements (referencing a number) stored in the structure.
        System.out.println(s.getTotal());
    }

    public void addPhone(String phone) {
        Dial result = dials.get(phone.substring(0, 1));
        if (result == null) {
            result = new Dial();
            dials.put(phone.substring(0, 1), result);
        }
        result.addPhone(phone);
    }

    public int getTotal() {
        int total = 0;
        for (String s : dials.keySet()) {
            total += dials.get(s).getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "dials=" + dials +
                '}';
    }

    private class Dial {
        private HashMap<String, Dial> dials;

        public Dial() {
            dials = new HashMap<>();
        }

        public int getTotal() {
            int total = 0;
            total += dials.size();
            for (Dial phone : dials.values()) {
                total += phone.getTotal();
            }
            return total;
        }

        public void addPhone(String phone) {
            if (phone.length() > 0) {
                Dial result = dials.get(phone.substring(0, 1));
                if (result == null) {
                    result = new Dial();
                    dials.put(phone.substring(0, 1), result);
                }
                result.addPhone(phone.substring(1));
            }
        }
    }
}