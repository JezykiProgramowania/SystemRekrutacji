
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader plik = new FileReader("dane.txt");
        BufferedReader bufor = new BufferedReader(plik);
        String readLine = "";


        Scanner scanner = new Scanner(new File("dane.txt"));  //opening the file stream
        scanner.useDelimiter(System.lineSeparator());

        int licznik = 0;
        List<Uczen> listaUczniow = new LinkedList<>(); // list of students
        Pattern pattern = Pattern.compile(";"); //sets the pattern for student separation in the file
        while (scanner.hasNext()) {

            if (scanner.hasNext(pattern)) {
                // if one student's info ends (which is signalled by a semicolon in dane.txt) create a new Student
                // Object and add it to the Students list
                ((LinkedList<Uczen>) listaUczniow).addLast(new Uczen());

                scanner.next(); // skip the semicolon
                licznik = 0; // reset the counter to allow for name and points to be set
                continue;
            }


            if(licznik == 0) ((LinkedList<Uczen>) listaUczniow).getLast().SetImie(scanner.next()); // sets name

            if(licznik == 1)((LinkedList<Uczen>) listaUczniow).getLast().SetPunkty(scanner.nextInt()); // sets points

            if(licznik > 1)((LinkedList<Uczen>) listaUczniow).getLast().SetPrefSzkola(scanner.nextInt(), scanner.next()); // stores school preference as a map

            licznik++;
        }

        /////////////////////
        ////////////////
        //TESTY
        ////////////////////
       System.out.println("to jest uczen nr 0: " + listaUczniow.get(1).GetImie());
        System.out.println("to jest uczen nr 0: " + listaUczniow.get(0).GetPrefSzkola().get(1));
        //System.out.println("to jest uczen nr 1: " + listaUczniow.get(1).GetPreferencje());

    }
}
