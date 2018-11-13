import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Uczen {


    private String imie = "";
    private  String wynikRekrutacji = "";

    private List<String> preferencje = new ArrayList();
    private List<String> szkola = new ArrayList<>();

    private Map<Integer, String> prefSzkola = new HashMap<>();
    private  Map<String, Integer> szkolaPref = new HashMap<>();


    private int punkty = 0;

    private boolean isSatisfied = false;




    ///////////////////////////
    ////SETTERS
    ///////////////////////////
    public void SetImie(String imie) {
        this.imie = imie;
    }


    public void SetPreferencje(List<String> preferencje) {
        this.preferencje = preferencje;
    }

    public void SetSzkola(List<String> szkola) {
        this.szkola = szkola;
    }

    public void SetPunkty(int punkty) {
        this.punkty = punkty;
    }

    public void SetPrefSzkola(int pref, String szkola) {
        this.prefSzkola.put(pref, szkola);

    }

    public void SetSzkolaPref(String szkola, int pref) {
        this.szkolaPref.put(szkola, pref);

    }

    public void SetWynikRekrutacji(String wynikRekrutacji) {
        this.wynikRekrutacji = wynikRekrutacji;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
    ////////////////////////
    //GETTERS
    ///////////////////


     public String GetImie() {
        return this.imie;
    }


    public List<String>GetPreferencje() {
        return this.preferencje;
    }

    public List<String>GetSzkola() {
        return this.szkola;
    }
    public int GetPunkty() {
        return this.punkty;
    }

    public Map<Integer, String> GetPrefSzkola() {
        return prefSzkola;

    }


    public Map<String, Integer> GetSzkolaPref() {
        return szkolaPref;

    }

    public String GetWynikRekrutacji() {
        return wynikRekrutacji;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }
}
