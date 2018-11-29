
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {




    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("data.txt"));  //opening the file stream
        scanner.useDelimiter(System.lineSeparator());
        Pattern pattern = Pattern.compile(";"); //sets the pattern for student separation in the file

        Scanner scanner2 = new Scanner(new File("schools.txt"));


        List<Student> studentsList = new LinkedList<>(); // list of students objects
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
            s.setSortedSchools(new ArrayList<String>(s.getPreferredSchool().values())); // sorting the keys so that the lowest key is first
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
      for(int i = 0; i < isSatisfied.length; i++) {
          isSatisfied[i] = false;
      }

      Helper help = new Helper();


        /////////////////////////////////////////////
        // ALGORYTM START
        //////////////////////////////////////////////
        help.ComputeResults(studentsList,schoolsList,isSatisfied);

        /////////////////////////////////////////
        //WYNIK DZIALANIA PROGRAMU
        /////////////////////////
        help.ShowResults(schoolsList);
    }
        }
