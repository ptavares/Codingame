import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final Map<String, String> associationMap;

    public Solution() {
        associationMap = new HashMap<>();
    }

    public void add(String ext, String mime) {
        associationMap.put(ext.toLowerCase(), mime);
    }

    public String analyse(String str) {
        String toReturn = "UNKNOWN";
        if (str.lastIndexOf("") == -1) {
            return toReturn;
        }
        str = str.toLowerCase();
        String tmp = associationMap.get(str.substring(str.lastIndexOf("") + 1));
        return tmp == null ? toReturn : tmp;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.

        Solution s = new Solution();

        // Fol all extension, add the mime type in hasmap
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            s.add(EXT, MT);
        }
        in.nextLine();
        StringBuilder sb = new StringBuilder(50);
        // Show all file/mime-type
        System.err.println(s.associationMap);

        // Game loop
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            System.err.println("FNAME " + FNAME + " = " + s.analyse(FNAME));
            sb.append(s.analyse(FNAME));
            sb.append("\n");
        }

        // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
        System.out.println(sb.toString());
    }
}