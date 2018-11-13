
import java.io.*;
import java.util.*;
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


            if (licznik == 0) ((LinkedList<Uczen>) listaUczniow).getLast().SetImie(scanner.next()); // sets name

            if (licznik == 1) ((LinkedList<Uczen>) listaUczniow).getLast().SetPunkty(scanner.nextInt()); // sets points

            if (licznik > 1)
                ((LinkedList<Uczen>) listaUczniow).getLast().SetSzkolaPref(scanner.next(), scanner.nextInt()); // stores school preference as a map

            licznik++;
        }


        int counter = 0;
        List<Integer> min = new ArrayList<>();
        for(Uczen uczen : listaUczniow) {

            min.add(Collections.min(uczen.GetSzkolaPref().values()));

        }
        while(counter < 1) {
            for (int i = 0; i < listaUczniow.size(); i++) {
                for (int j = 0; j < listaUczniow.size(); j++) {
                    // check for highest scores first, then preference


                    if (listaUczniow.get(i).GetSzkolaPref().get("szkola1").equals(listaUczniow.get(j).GetSzkolaPref().get("szkola1"))) {
                        if (!listaUczniow.get(j).isSatisfied() || !listaUczniow.get(i).isSatisfied()) {

                            if(listaUczniow.get(i).isSatisfied()) listaUczniow.get(j).SetWynikRekrutacji("szkola1");

                            if(!listaUczniow.get(i).isSatisfied() && listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty()) listaUczniow.get(j).SetWynikRekrutacji("szkola1");

                            if(listaUczniow.get(j).GetPunkty() < listaUczniow.get(i).GetPunkty() && listaUczniow.get(i).isSatisfied() && listaUczniow.get(i).GetWynikRekrutacji().equals("szkola1")) {
                                int temp = min.get(j);
                                min.set(j, temp++);
                            }

                            if(listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty() && listaUczniow.get(i).isSatisfied())  {

                                listaUczniow.get(i).setSatisfied(false);
                                int temp = min.get(j);
                                
                                min.set(i, temp++);

                                listaUczniow.get(i).SetWynikRekrutacji("");
                                listaUczniow.get(j).SetWynikRekrutacji("szkola1");
                            }

                                if (listaUczniow.get(j).GetSzkolaPref().get("szkola1") == min.get(j)) {

                                    listaUczniow.get(j).setSatisfied(true);




                            }
                        }
                    }


                    if (listaUczniow.get(i).GetSzkolaPref().get("szkola2").equals(listaUczniow.get(j).GetSzkolaPref().get("szkola2"))) {
                        if (!listaUczniow.get(j).isSatisfied() || !listaUczniow.get(i).isSatisfied()) {

                            if(listaUczniow.get(i).isSatisfied()) listaUczniow.get(j).SetWynikRekrutacji("szkola2");
                            if(!listaUczniow.get(i).isSatisfied() && listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty()) listaUczniow.get(j).SetWynikRekrutacji("szkola2");
                            if(listaUczniow.get(j).GetPunkty() < listaUczniow.get(i).GetPunkty() && !listaUczniow.get(i).isSatisfied()) {
                                int temp = min.get(j);
                                min.set(j, temp++);
                                listaUczniow.get(i).SetWynikRekrutacji("szkola2");
                            }
                            if(listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty() && listaUczniow.get(i).isSatisfied())  {

                                listaUczniow.get(i).setSatisfied(false);
                                int temp = min.get(i);
                                min.set(i, temp++);
                                listaUczniow.get(i).SetWynikRekrutacji("");
                                listaUczniow.get(j).SetWynikRekrutacji("szkola2");
                            }
                                if (listaUczniow.get(j).GetSzkolaPref().get("szkola2") == min.get(j)) {
                                    listaUczniow.get(j).setSatisfied(true);




                            }
                        }
                    }

                    if (listaUczniow.get(i).GetSzkolaPref().get("szkola3").equals(listaUczniow.get(j).GetSzkolaPref().get("szkola3"))) {
                        if (!listaUczniow.get(j).isSatisfied() || !listaUczniow.get(i).isSatisfied()) {

                            if(listaUczniow.get(i).isSatisfied()) listaUczniow.get(j).SetWynikRekrutacji("szkola3");

                            if(!listaUczniow.get(i).isSatisfied() && listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty()) listaUczniow.get(j).SetWynikRekrutacji("szkola3");

                            if(listaUczniow.get(j).GetPunkty() < listaUczniow.get(i).GetPunkty() && !listaUczniow.get(i).isSatisfied()) {
                                int temp = min.get(i);
                                min.set(j, temp++);
                                listaUczniow.get(i).SetWynikRekrutacji("szkola3");
                            }
                            if(listaUczniow.get(j).GetPunkty() > listaUczniow.get(i).GetPunkty() && listaUczniow.get(i).isSatisfied())  {

                                listaUczniow.get(i).setSatisfied(false);
                                int temp = min.get(i);
                                min.set(i, temp++);
                                listaUczniow.get(i).SetWynikRekrutacji("");
                                listaUczniow.get(j).SetWynikRekrutacji("szkola3");
                            }

                                if (listaUczniow.get(j).GetSzkolaPref().get("szkola3") == min.get(j)) {
                                     listaUczniow.get(j).setSatisfied(true);

                            }
                        }
                    }

/*
                    // now check for preference
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola1") < listaUczniow.get(i).GetSzkolaPref().get("szkola1") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola1");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola1").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);
                    }
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola2") < listaUczniow.get(i).GetSzkolaPref().get("szkola2") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola2");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola2").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);

                    }
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola3") < listaUczniow.get(i).GetSzkolaPref().get("szkola3") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola3");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola3").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);

                    }*/
                }

            }
        counter++;
        }
                /////////////////////
                ////////////////
                //TESTY
                ////////////////////
        for(Uczen uczen : listaUczniow) {
            uczen.GetSzkolaPref().remove("szkola1");
        }

                System.out.println("to jest uczen nr 1: " + listaUczniow.get(1).GetImie());
                System.out.println("to jest uczen nr 0: " + listaUczniow.get(0).GetSzkolaPref().get("szkola1"));
                System.out.println("wynik rekrutacji uczen nr 0: " + listaUczniow.get(0).GetWynikRekrutacji());
                System.out.println("wynik rekrutacji uczen nr 1: " + listaUczniow.get(1).GetWynikRekrutacji());
                System.out.println("wynik rekrutacji uczen nr 2: " + listaUczniow.get(2).GetWynikRekrutacji());
                System.out.println("min value: " + min);


            }
        }
