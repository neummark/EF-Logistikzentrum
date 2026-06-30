package model;

public class Frachtgut {

    protected String bezeichnung;

    public Frachtgut(String bez){
        this.bezeichnung = bez;
    }


    public boolean istGefahrgut(){
        return false;
    }
    /**
     * Es wird die Bezeichnung zurückgegeben.
     * @return Bezeichnung des Frachtgut
     */
    public String gibInfo(){
        return this.bezeichnung;
    }
}
