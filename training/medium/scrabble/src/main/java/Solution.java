import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public final Map<Character, Integer> points;

    private final Map<String, Info> dico;

    public Solution(int size) {
        dico = new LinkedHashMap<>(size);
        points = new LinkedHashMap<>(26);
        points.put('u', 1);
        points.put('s', 1);
        points.put('l', 1);
        points.put('t', 1);
        points.put('r', 1);
        points.put('n', 1);
        points.put('o', 1);
        points.put('i', 1);
        points.put('a', 1);
        points.put('e', 1);
        points.put('d', 2);
        points.put('g', 2);
        points.put('p', 3);
        points.put('m', 3);
        points.put('c', 3);
        points.put('b', 3);
        points.put('y', 4);
        points.put('w', 4);
        points.put('v', 4);
        points.put('h', 4);
        points.put('f', 4);
        points.put('k', 5);
        points.put('j', 8);
        points.put('x', 8);
        points.put('q', 10);
        points.put('z', 10);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        Solution s = new Solution(N);
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
            s.addWord(W, i);
        }
        System.err.println("dico = " + s.dico);

        String LETTERS = in.nextLine();
        String word = s.find(LETTERS);
        System.err.println("word = " + word);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.err.println("Letters = " + LETTERS);
        System.out.println(word);
    }

    private String find(String letters) {
        String word = null;
        int score = 0;
        boolean isOk;
        for (String current : dico.keySet()) {
            Letters lt = new Letters(letters);
            isOk = true;
            for (int i = 0; i < current.length(); i++) {
                if (lt.containsAndRemove(current.charAt(i))) {
                    isOk = true;
                } else {
                    isOk = false;
                    break;
                }
            }
            if (isOk && letters.length() >= current.length()) {
                Info info = dico.get(current);
                if (score < info.getScore()) {
                    System.err.println("better = " + info + " old is " + word);
                    score = info.getScore();
                    word = current;
                }
            }
        }

        return word;
    }

    public void addWord(String word, int index) {
        // if (word.length() < 10)
        dico.put(word, new Info(calculPoint(word), index));
    }

    private Integer calculPoint(String word) {
        int point = 0;
        for (int i = 0; i < word.length(); i++) {
            point += points.get(word.charAt(i));
        }
        return point;
    }

    private class Letters {
        private final ArrayList<Character> characters;

        public Letters(String letters) {
            characters = new ArrayList<>();
            for (int i = 0; i < letters.length(); i++) {
                characters.add(letters.charAt(i));
            }
            System.err.println("Chars = " + characters);
        }

        public boolean containsAndRemove(Character c) {
            System.err.println("test char = " + c + " present = " + characters.contains(c));
            int i = characters.indexOf(c);
            if (i != -1) {
                characters.remove(i);
                System.err.println("Chars = " + characters);
                return true;
            }
            return false;
        }
    }

    private class Info {
        private int score;
        private int index;

        public Info(int score, int index) {
            this.score = score;
            this.index = index;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "score=" + score +
                    ", index=" + index +
                    '}';
        }
    }
}