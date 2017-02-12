import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static final String ZERO = "00 0";
    private static final String UN = "0 0";

    private static final String ADD_ONE = "0";

    private final String toEncode;
    private Character current;

    public Solution(String toEncode) {
        this.toEncode = toEncode;
    }

    public String analyse() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < toEncode.length(); ++i) {
            char c = toEncode.charAt(i);
            sb.append(unaire(c));
        }

        return sb.toString();
    }

    private String unaire(Character c) {
        if (current == null) {
            current = c;
            return toEncode(c);
        }
        if (c == current) {
            current = c;
            return ADD_ONE;
        }
        if (c != current) {
            current = c;
            return " " + toEncode(c);
        }
        return null;
    }

    private String toEncode(Character c) {
        if (c == '1') return UN;
        else return ZERO;
    }

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        byte[] infoBin = null;
        infoBin = MESSAGE.getBytes("UTF-8");

        StringBuilder sb = new StringBuilder();

        for (byte b : infoBin) {
            String bin = Integer.toBinaryString(b);
            if (bin.length() < 7)
                bin = "0" + bin;
            System.err.println("c:" + (char) b + "-> "
                    + bin);
            sb.append(bin);
        }
        Solution s = new Solution(sb.toString());
        String result = s.analyse();
        System.out.println(result);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "toEncode='" + toEncode + '\'' +
                '}';
    }
}