package model;

public class Kuehlgut extends Frachtgut{
    private double aktuelleTemp;
    private double minimalTemp;
    private double maximalTemp;
    private boolean tempNichtEingehalten;

    public Kuehlgut(String bez, double akT, double minT, double maxT){
        super(bez);
        aktuelleTemp = akT;
        minimalTemp = minT;
        maximalTemp = maxT;
    }

    public double gibAktuelleTemp(){
        return aktuelleTemp;
    }

    public double gibMinimalTemp(){
        return minimalTemp;
    }

    public double gibMaximaleTemp(){
        return maximalTemp;
    }

    public void pruefeTemp(){
        if(aktuelleTemp > maximalTemp || aktuelleTemp < minimalTemp){
            tempNichtEingehalten = true;
        } else {
            tempNichtEingehalten = false;
        }
    }

    public boolean gibObTempEingehaltenWurde(){
        return tempNichtEingehalten;
    }

    public boolean istKuehlgit(){
        return true;
    }

    public String gibInfo(){
        String info = "Bei diesem Objekt hadelt es sich um ein Kuehlgut, die Bezeichnung dieses Objektes lautet ";
        return info + bezeichnung;
    }
}
