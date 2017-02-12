import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        in.nextLine();
        String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526
        int diff = Integer.MAX_VALUE;
        if (temps.length() > 0) {
            List<Integer> tempsInt = new ArrayList<>();
            Arrays.asList(temps.split(" ")).stream().forEach(elt -> tempsInt.add(new Integer(elt)));
            Collections.sort(tempsInt);
            System.err.println(tempsInt);
            int tmpFinal = Integer.MAX_VALUE;
            for (Integer temp : tempsInt) {
                if (temp < 0 && (0 - temp) < diff) {
                    diff = (0 - temp);
                    tmpFinal = temp;
                }
                if (temp > 0 && temp <= diff) {
                    diff = temp;
                    tmpFinal = temp;
                }
            }
            diff = tmpFinal;
        }
        System.out.println((diff == Integer.MAX_VALUE) ? 0 : diff);
    }
}