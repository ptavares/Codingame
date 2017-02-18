import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final List<Defibrillator> defebrilatorList;

    public Solution() {
        defebrilatorList = new ArrayList<>();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        String LAT = in.next();
        int N = in.nextInt();
        in.nextLine();
        Solution solution = new Solution();

        // Add all defebrilators
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            solution.addDefibrilator(DEFIB);
        }
        System.err.println("List = " + solution.defebrilatorList);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        String answer = solution.getClosestDefibrillatorName(LON, LAT);
        System.err.println(answer);
        System.out.println(answer);
    }

    public void addDefibrilator(String str) {
        // Split string to extract all infos
        if (str != null) {
            String[] infos = str.split(";");
            Defibrillator defebrilator = new Defibrillator();
            defebrilator.setId(infos[0]);
            defebrilator.setName(infos[1]);
            defebrilator.setAdress(infos[2]);
            defebrilator.setTel(infos[3]);
            defebrilator.setLongitude(Math.toRadians(Double.parseDouble(infos[4].replace(",", "."))));
            defebrilator.setLatitude(Math.toRadians(Double.parseDouble(infos[5].replace(",", "."))));
            defebrilatorList.add(defebrilator);
        }
    }

    public String getClosestDefibrillatorName(String longitude, String latitude) {
        double longInput = Double.parseDouble(longitude.replace(",", "."));
        double latInput = Double.parseDouble(latitude.replace(",", "."));
        // Convert to radians
        longInput = Math.toRadians(longInput);
        latInput = Math.toRadians(latInput);
        // init 
        double distance = Double.MAX_VALUE;
        String name = null;
        // Iterate over all defibrillator
        for (Defibrillator defibrillator : defebrilatorList) {
            double x = (defibrillator.getLongitude() - longInput) * Math.cos((latInput + defibrillator.getLatitude()) / 2);
            double y = defibrillator.getLatitude() - latInput;
            double distanceTmp = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0)) * 6371;
            if (distanceTmp < distance) {
                distance = distanceTmp;
                name = defibrillator.getName();
            }
        }
        return name;
    }

    private class Defibrillator {
        private String id;
        private String name;
        private String adress;
        private String tel;
        private Double longitude;
        private Double latitude;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        @Override
        public String toString() {
            return "Defibrillator{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", adress='" + adress + '\'' +
                    ", tel='" + tel + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    '}';
        }
    }
}