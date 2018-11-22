
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Main {




    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("data.txt"));  //opening the file stream
        scanner.useDelimiter(System.lineSeparator());
        Pattern pattern = Pattern.compile(";"); //sets the pattern for student separation in the file

        Scanner scanner2 = new Scanner(new File("schools.txt"));


        List<Student> studentsList = new LinkedList<>(); // list of students
        List<School> schoolsList = new LinkedList<>(); // list of school objects
        List<String> schools = new LinkedList<>(); // list of schools




        int counter = 0;
        while (scanner.hasNext()) {

            if (scanner.hasNext(pattern)) {
                // if one student's info ends (which is signalled by a semicolon in data.txt) create a new Student
                // Object and add it to the Students list
                ((LinkedList<Student>) studentsList).addLast(new Student());

                scanner.next(); // skip the semicolon
                counter = 0; // reset the counter to allow for name and points to be set
                continue;
            }


            if (counter == 0) ((LinkedList<Student>) studentsList).getLast().setName(scanner.next()); // sets name

            if (counter == 1) ((LinkedList<Student>) studentsList).getLast().setPoints(scanner.nextInt()); // sets points

            if (counter > 1)
                ((LinkedList<Student>) studentsList).getLast().setPreferredSchool(scanner.nextInt(), scanner.next()); // stores school preference as a map

            counter++;
        }


        while (scanner2.hasNext()) {
            ((LinkedList<String>) schools).add(scanner2.next());
        }



        for (Student s : studentsList) {
            s.setSortedSchools(new ArrayList<String>(s.getPreferredSchool().values())); // sorting the keys so that the lowest jey is first
        }



         Map<Integer, String>pointsToNameMap = new TreeMap<>();

        int c = 0;
        while(c < studentsList.size()) {
            pointsToNameMap.put(studentsList.get(c).getPoints(), studentsList.get(c).getName());
            c++;
        }

        int a = 0;
        while (a < schools.size()) {
            ((LinkedList<School>) schoolsList).addLast(new School(new ArrayList<>(pointsToNameMap.values()), schools.get(a)));
            a++;
        }

        //////////////////////////////////////////////////////////////////////////////
        boolean [] isSatisfied = new boolean[studentsList.size()];
      for(int i = 0; i < isSatisfied.length;i++) {
          isSatisfied[i] = false;
      }


        /////////////////////////////////////////////
        // ALGORYTM START
        //////////////////////////////////////////////
        ComputeResults(studentsList,schoolsList,isSatisfied);

        /////////////////////////////////////////
        //WYNIK DZIALANIA PROGRAMU
        /////////////////////////
        ShowResults(schoolsList);


         /////////////////////
         ////////////////
         //TESTY
        ////////////////////
/*
                 System.out.println("mapa przed usunieciem " + studentsList.get(0).getSortedSchools());
                studentsList.get(0).getSortedSchools().remove(0);
                System.out.println("mapa po usunieciu " + studentsList.get(0).getSortedSchools().get(0));
                System.out.println("imie ucznia nr 0 " + studentsList.get(0).getName());
                System.out.println("punkty ucznia nr 0 " + studentsList.get(0).getPoints());
                System.out.println(" preferencje ucznia nr 0: " + studentsList.get(0).getPreferredSchool());
                System.out.println(" posortowane preferencje ucznia nr 0: " + studentsList.get(0).getSortedSchools());
                System.out.println(" lista mozliwych szkol:  " + schools);
                System.out.println(" pointsToName:  " + pointsToNameMap);
                System.out.println(" preferredStudents:  " + schoolsList.get(0).getPreferredStudents());
                int n = IndexOfSchoolString("szkola1", schools);
                int x = IndexOfSchool(studentsList.get(0).getSortedSchools().get(0), schoolsList);
                System.out.println("IndexOF = " + x);
                System.out.println("eewwrgrrgrgrw = " +schoolsList.get(1).getSchoolName());
                System.out.println("schoolname = " + schoolsList.get(0).getSchoolName());
*/
    }




    public static void ComputeResults(List<Student> studentsList, List<School> schoolsList, boolean[] isSatisfied) {
        int endCondition = 0;
        while (endCondition < studentsList.size()) {
            for (int i = 0; i < studentsList.size(); i++) {
                if (schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent.equals("")) {
                    schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent = studentsList.get(i).getName();
                    isSatisfied[IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)] = true;
                } else {
                    if (studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getPoints()
                            < studentsList.get(i).getPoints()) {
                        isSatisfied[IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)] = false;
                        studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getSortedSchools().remove(0);
                        schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent = studentsList.get(i).getName();
                        schoolsList.get(IndexOf(studentsList.get(i).getName(), schoolsList)).currentStudent = "";
                    } else if (studentsList.get(IndexOfStudent(schoolsList.get(IndexOfSchool(studentsList.get(i).getSortedSchools().get(0), schoolsList)).currentStudent, studentsList)).getPoints()
                            > studentsList.get(i).getPoints()) {

                        studentsList.get(i).getSortedSchools().remove(0);
                    }
                }
            }
            endCondition = endCondition(studentsList, isSatisfied);
        }
    }

//HELPER FUNCTIONS
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
