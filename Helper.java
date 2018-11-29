import java.util.List;

public class Helper {

    public static void ComputeResults(List<Student> studentsList, List<School> schoolsList, boolean[] isSatisfied) {
        int endCondition = 0;
        while (endCondition < studentsList.size()) {
            for (int i = 0; i < studentsList.size(); i++) {

                if (schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent.equals("")) {
                    schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent = studentsList.get(i).getName();
                    isSatisfied[IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)] = true;
                }
                else {
                    if (studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getPoints()
                            < studentsList.get(i).getPoints()) {

                        isSatisfied[IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)] = false;
                        studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getSortedSchools().remove(0);
                        schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent = studentsList.get(i).getName();
                        schoolsList.get(IndexOf(studentsList.get(i).getName(), schoolsList)).currentStudent = "";
                    }
                    else if (studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getPoints()
                            > studentsList.get(i).getPoints()) {

                        studentsList.get(i).getSortedSchools().remove(0);
                    }
                }
            }
            endCondition = endCondition(studentsList, isSatisfied);
        }
    }

//HELPER FUNCTIONS//

    public static int endCondition(List<Student> list, boolean[] isSatisfied) {
        int endCondition = 0;
        for (int j = 0; j < list.size(); j++) {
            if (isSatisfied[j] == true) endCondition++;
        }
        return endCondition;
    }
    public static int IndexOfSchool(String s, List<School> list){
        for(int i = 0; i < list.size();i++) {
            if(list.get(i).getSchoolName().equals(s)) return i;
        }
        return -1;
    }

    public static int IndexOfSchoolString(String s, List<String> list){
        for(int i = 0; i < list.size();i++) {
            if(list.get(i).equals(s)) return i;
        }
        return -1;
    }
    public static int IndexOfStudent(String s, List<Student> list) {

        for(int i = 0; i < list.size();i++) {
            if(list.get(i).getName().equals(s)) return i;
        }
        return -1;
    }
    public static int IndexOf(String s, List<School> list) {

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).currentStudent.equals(s)) return i;
        }
        return -1;
    }


    public static void ShowResults(List<School> list) {
        System.out.println("szkola: " + list.get(0).getSchoolName());
        System.out.println("wynik: " + list.get(0).currentStudent + "\n");
        System.out.println("szkola: " + list.get(1).getSchoolName());
        System.out.println("wynik: " + list.get(1).currentStudent + "\n");
        System.out.println("szkola: " + list.get(2).getSchoolName());
        System.out.println("wynik: " + list.get(2).currentStudent);
    }
}





