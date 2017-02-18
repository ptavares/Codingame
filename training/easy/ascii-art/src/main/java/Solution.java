import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?";

    private Map<Character, ArrayList<String>> ascii;

    public Solution(int h) {
        ascii = new HashMap<>();
        for (int i = 0; i < allChars.length(); ++i) {
            ascii.put(allChars.charAt(i), new ArrayList<>(h));
        }
    }

    public void addRow(int l, String row) {
        int start = 0;
        for (int i = 0; i < allChars.length(); ++i) {
            ascii.get(allChars.charAt(i)).add(row.substring(start, start + l));
            start = start + l;
        }
    }

    public String toString(String str, int H) {
        StringBuilder sb = new StringBuilder();
        str = str.toUpperCase();
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < str.length(); ++i) {
                ArrayList<String> l = ascii.get(str.charAt(i));
                if (l == null) {
                    l = ascii.get('?');
                }
                sb.append(l.get(h));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        in.nextLine();
        String T = in.nextLine();

        Solution s = new Solution(H);

        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            System.err.println("i = " + i + " ROW = " + ROW);
            s.addRow(L, ROW);
        }

        System.out.println(s.toString(T, H));
    }
}