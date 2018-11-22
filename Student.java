import java.util.*;

public class Student {


    private String name = "";
    private int points = 0;
    private Map<Integer, String> PreferredSchool = new TreeMap<>();
    private List<String> sortedSchools = new ArrayList<>();









    public List<String> getSortedSchools() {
        return sortedSchools;
    }

    public void setSortedSchools(List<String> sortedSchools) {
        this.sortedSchools = sortedSchools;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Map<Integer, String> getPreferredSchool() {
        return PreferredSchool;
    }

    public void setPreferredSchool(int pref, String school) {
        PreferredSchool.put(pref, school);
    }
}
