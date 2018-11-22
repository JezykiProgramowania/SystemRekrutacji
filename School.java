import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
    private List<String> preferredStudents;
    private String schoolName = "";
    public String currentStudent = "";





    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public School(List<String> preferredStudents, String schoolName) {
        this.preferredStudents = preferredStudents;
        this.schoolName = schoolName;
    }

    public List<String> getPreferredStudents() {
        return preferredStudents;
    }

    public void setPreferredStudents(String school) {
        this.preferredStudents.add(school);
    }



}
