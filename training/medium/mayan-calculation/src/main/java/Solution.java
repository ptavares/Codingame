import java.math.BigInteger;
import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();

        System.err.println("L = " + L + " H = " + H);
        MayaNumber mayaNumber = new MayaNumber(L, H);
        for (int i = 0; i < H; i++) {
            String numeral = in.next();
            mayaNumber.initReferentiel(i, numeral);
        }
        System.err.println("mayaNumber = " + mayaNumber);

        int S1 = in.nextInt();
        List<String> num1 = new ArrayList<>(S1);
        for (int i = 0; i < S1; i++) {
            String num1Line = in.next();
            num1.add(num1Line);
        }
        int S2 = in.nextInt();
        List<String> num2 = new ArrayList<>(S2);
        for (int i = 0; i < S2; i++) {
            String num2Line = in.next();
            num2.add(num2Line);
        }
        int value1 = mayaNumber.findValueFor(num1);
        int value2 = mayaNumber.findValueFor(num2);
        System.err.println("value1 = " + value1);
        System.err.println("value2 = " + value2);

        String operation = in.next();
        System.err.println("operation = " + operation);
        BigInteger result = calculate(value1, value2, operation);
        System.err.println("result = " + result);
        List<Integer> toPrint = mayaNumber.decompResult(result);
        System.err.println("toPrint = " + toPrint);
        for (Integer integer : toPrint) {
            System.out.println(mayaNumber.toStringNum(integer));
        }
    }

    private static BigInteger calculate(int value1, int value2, String operation) {
        if ("+".equals(operation))
            return new BigInteger(String.valueOf(value1)).add(new BigInteger(String.valueOf(value2)));
        if ("-".equals(operation))
            return new BigInteger(String.valueOf(value1)).subtract(new BigInteger(String.valueOf(value2)));

        if ("*".equals(operation))
            return new BigInteger(String.valueOf(value1)).multiply(new BigInteger(String.valueOf(value2)));
        return new BigInteger(String.valueOf(value1)).divide(new BigInteger(String.valueOf(value2)));
    }

    public static String[] splitAfterNChars(String input, int splitLen) {
        return input.split(String.format("(?<=\\G.{%1$d})", splitLen));
    }

    private static class MayaNumber {
        private final int width;
        private final int height;

        private final HashMap<Integer, List<String>> representation;
        private final HashMap<Integer, Integer> hashValue;

        public MayaNumber(int width, int height) {
            this.width = width;
            this.height = height;
            representation = new HashMap<>();
            hashValue = new HashMap<>();
            for (int i = 0; i < 20; i++) {
                representation.put(i, new ArrayList<>(height));
            }
        }

        public void initReferentiel(int height, String str) {
            String[] parts = splitAfterNChars(str, width);
            for (int i = 0; i < parts.length; i++) {
                representation.get(i).add(parts[i]);
            }
            if (this.height == (height + 1)) {
                System.err.println("Init hashvalue");
                for (Integer integer : representation.keySet()) {
                    int hashcode = 0;
                    List<String> value = representation.get(integer);
                    for (String s : value) {
                        hashcode += s.hashCode();
                    }
                    hashValue.put(hashcode, integer);
                }
            }
        }

        public int findValueFor(List<String> numRepresentation) {
            int puissance = (numRepresentation.size() / height) - 1;
            int value = 0;
            int hashCode = 0;
            for (int i = 0; i < numRepresentation.size(); i++) {
                hashCode += numRepresentation.get(i).hashCode();
                if ((i + 1) % height == 0) {
                    System.err.println("hashCode = " + hashCode);
                    value += hashValue.get(hashCode) * Math.pow(20, puissance);
                    hashCode = 0;
                    puissance--;
                }
            }
            return value;
        }

        public List<Integer> decompResult(BigInteger result) {
            BigInteger k;
            BigInteger m = result;
            BigInteger twenty = new BigInteger("20");
            LinkedList<Integer> decomp = new LinkedList<>();

            while (m.intValue() != 0) {
                //System.err.println("k = " + m % 20);
                k = m.remainder(twenty);
                //System.err.println("--> " + k);
                decomp.addFirst(k.intValue());

                m = m.subtract(k).divide(twenty);
            }
            if (decomp.size() == 0) {
                decomp.add(0);
            }
            return decomp;
        }

        @Override
        public String toString() {
            return "MayaNumber{" +
                    "width=" + width +
                    ", height=" + height +
                    ",\nrepresentation=" + representation +
                    ",\nhashValue=" + hashValue +
                    '}';
        }

        public String toStringNum(Integer integer) {
            StringBuilder sb = new StringBuilder();
            for (String s : representation.get(integer)) {
                sb.append(s).append("\n");
            }
            return sb.toString().trim();
        }
    }
}