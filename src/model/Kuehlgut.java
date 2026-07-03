package model;

public class Kuehlgut extends Frachtgut{

    double akteulleTemp;
    double minimalTemp;
    double maximalTemp;
    boolean tempNichtEingehalten;

    public Kuehlgut(String bez){
        super(bez);
    }

    public Kuehlgut(String bez, double aktT, double minT, double maxT){
        super(bez);
        bez = bezeichnung;
        aktT = akteulleTemp;
        minT = minimalTemp;
        maxT = maximalTemp;
    }

    public double gibAkteulleTemp(){
        return akteulleTemp;
    }
    public double gibMaximalTemp(){
        return maximalTemp;
    }
    public double gibMinimalTemp(){
        return minimalTemp;
    }

    public void pruefeTemp(){
        if (akteulleTemp<=maximalTemp &&  akteulleTemp>=minimalTemp){
            tempNichtEingehalten = false;
        } else {
            tempNichtEingehalten = true;
        }
    }

    public boolean gibObTempEngehaltenWurde(){
        return tempNichtEingehalten;
    }

    public boolean istKuehlgut(){
        return true;
    }

    @Override
    public String gibInfo() {
        return super.gibInfo() + istKuehlgut();
    }
}
