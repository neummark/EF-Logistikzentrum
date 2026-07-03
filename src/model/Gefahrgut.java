package model;

public class Gefahrgut extends Frachtgut{

    public Gefahrgut(String bez){
        super(bez);
    }

    public boolean istGefahrgut(){
        return true;
    }

    public String gibInfo(){
        return this.bezeichnung + istGefahrgut();
    }
}