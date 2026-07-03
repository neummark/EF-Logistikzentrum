package control;

import model.*;

public class Logistikzentrum {

    private String bezeichnung;
    private int anzahlFrachtgueter;
    private int anzahlFahrzeuge;

    //Datenstruktur Array zur Verwaltung von vielen Objekten gleicher Art
    private Frachtgut[] alleGueter;
    private Fahrzeug[] alleFahrzeuge;

    public Logistikzentrum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        anzahlFrachtgueter = (int)(Math.random()*10 + 5);
        anzahlFahrzeuge = (int)(Math.random()*10 + 1);
        erstelleTestDaten();
    }

    /**
     * Erstellt Daten und verwaltet diese in einem Array.
     */
    private void erstelleTestDaten(){
        String[] standardGueter = {"Mehl","Nudeln","Wasser"};
        String[] kuehlGueter = {"Eis", "Obst", "Ersatzfleischprodukt", "Milch"};
        String[] gefahrGueter = {"Feuerwerk","Hochprozentiges", "Eier"};

        alleGueter = new Frachtgut[anzahlFrachtgueter];
        for(int i = 0; i < alleGueter.length; i++){
            int id = (int)(Math.random()*1024); //ID zum Unterscheiden gleichartiger Objekte
            int art = (int)(Math.random()*3);
            if(art == 0){
                int welchesGut = (int)(Math.random()* standardGueter.length);
                alleGueter[i] = new Standardgut(standardGueter[welchesGut] + " ID: "+id);
            }
            if(art == 1){
                int welchesGut = (int)(Math.random()* kuehlGueter.length);
                alleGueter[i] = new Kuehlgut(kuehlGueter[welchesGut] + " ID: "+id);
            }
            if(art == 2){
                int welchesGut = (int)(Math.random()* gefahrGueter.length);
                alleGueter[i] = new Gefahrgut(gefahrGueter[welchesGut] + " ID: "+id);
            }
            System.out.println("Erstellt: "+alleGueter[i].gibInfo());
        }

        //Erstellen von Fahrzeugen
        alleFahrzeuge = new Fahrzeug[anzahlFahrzeuge];
        for(int i = 0; i < alleFahrzeuge.length; i++){
            int id = (int)(Math.random()*128 + 2000);
            boolean kuehl = false;
            boolean sicher = false;
            if((int)(Math.random()*2) == 1){
                kuehl = true;
            }
            if((int)(Math.random()*2) == 1){
                sicher = true;
            }
            alleFahrzeuge[i] = new Fahrzeug("FZ-"+id, kuehl, sicher);
            System.out.println("Erstellt: "+alleFahrzeuge[i].gibInfo());
        }
    }

    /**
     * Es wird das entsprchende gut-Objekt aus dem Array aller Gueter entfernt.
     * @param gut - das zu entfernende Frachtgut
     */
    private void entferneGut(Frachtgut gut){
        for(int i = 0; i < alleGueter.length; i++){
            if(alleGueter[i] == gut){
                alleGueter[i] = null;
            }
        }
    }

    /**
     * Für alle (noch vorhandenen) Frachtgut-Objekte wird jedes Fahrzeug-Objekt geprüft.
     * Sollte eine Kombination aus Frachtgut-Objekt und Fahrzeug-Objekt passen (entsprechende Prüf-Methode liefert also den Wert true),
     * so wird das Frachtgut-Objekt auf das Fahrzeug-Objekt verfrachtet (entsprechende Methode wird aufgerufen).
     */
    public void verarbeiteWarteschlange(){
        int alleGuetter = anzahlFrachtgueter;
        int alleFahrzeuge = anzahlFahrzeuge;
        for(int i = alleGuetter; i >= 0; i--){
            for(int index = alleFahrzeuge-1; index >= 0; index--){
                if(pruefeObKombiPasst(gibFrachtgut(i),gibFahrzeug(index))){
                    verfrachte(gibFrachtgut(i),gibFahrzeug(index));
                }
            }
        }
    }

    /**
     * Falls beide übergebenen Objekte nicht null sind, wird geprüft, ob das Fahrzeug noch ein Frachtgut laden kann. Falls nein, wird false zurückgegeben.
     *  Falls ja, wird geprüft, ob das Frachtgut ein Gefahrgut ist und das Fahrzeug sicher ist. Falls ja, wird true zurückgegeben.
     *  Falls das Frachtgut kein Gefahrgut ist, wird geprüft, ob es sich um ein Kühlgut handelt und das Fahrzeug kühlen kann. Falls ja, wird true zurückgegeben.
     *  Falls das Frachtgut weder ein Gefahrgut noch ein Kühlgut ist, wird einfach so true zurückgegeben (das Fahrzeug hat ja Platz).
     * @param gut
     * @param fahrzeug
     * @return Gibt an, ob das Frachgut auf das Fahrzeug passt
     */
    private boolean pruefeObKombiPasst(Frachtgut gut, Fahrzeug fahrzeug){
        if(gut == null || fahrzeug == null){
            return false;
        }

        if(fahrzeug.gibFrachtgut() == null){
            if(gut.istGefahrgut() && fahrzeug.istSicher()){
                return true;
            } else if(gut.istKuehlgut() && fahrzeug.kannKeuhlen()){
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Falls beide übergebenen Objekte nicht null sind,
     * wird das Frachtgut auf das Fahrzeug gesetzt
     * und das Attribut anzahlFrachtgueter wird um 1 kleiner.
     *
     * Intern wird das Frachtgut aus der Datenstruktur entfernt
     * (hierum müssen Sie sich nicht kümmern).
     * @param gut - zu verfrachten
     * @param fahrzeug - gewähltes Fahrzeug
     */
    public void verfrachte(Frachtgut gut, Fahrzeug fahrzeug){
        if(gut != null && fahrzeug != null){
            fahrzeug.setzeFrachtgut(gut);

            entferneGut(gut); //dieser Befehl muss hier stehen
        }
    }

    /**
     * Falls es ein Frachtgut-Objekt zum Index index gibt,
     * wird eine Referenz darauf zurückgegeben,
     * ansonsten wird null zurückgegeben.
     * @param index - index zum Frachtgut-Objekt
     * @return Frachtgut-Objekt, falls vorhanden
     */
    public Frachtgut gibFrachtgut(int index){
        if(0 <= index && index <= alleGueter.length){
            return alleGueter[index];
        }
        return null;
    }

    /**
     * Falls es ein Fahrzeug-Objekt zum Index index gibt,
     * wird eine Referenz darauf zurückgegeben,
     * ansonsten wird null zurückgegeben.
     * @param index - index zum Fahrzeug-Objekt
     * @return Fahrzeug-Objekt, falls vorhanden
     */
    public Fahrzeug gibFahrzeug(int index){
        if(0 <= index && index <= alleFahrzeuge.length){
            return alleFahrzeuge[index];
        }
        return null;
    }
}
