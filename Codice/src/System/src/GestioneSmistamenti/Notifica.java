package GestioneSmistamenti;

public class Notifica {
    private int ID_N;
    private int ID_U;
    private String descrizione;

    // Costruttore della classe
    public Notifica(int ID_N, int ID_U, String descrizione) {
        this.ID_N = ID_N;
        this.ID_U = ID_U;
        this.descrizione = descrizione;
    }

    // Metodo per impostare l'ID della notifica
    public void setID_N(int ID_N) {
        this.ID_N = ID_N;
    }

    // Metodo per ottenere l'ID della notifica
    public int getID_N() {
        return ID_N;
    }

    // Metodo per impostare l'ID dell'utente
    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    // Metodo per ottenere l'ID dell'utente
    public int getID_U() {
        return ID_U;
    }

    // Metodo per impostare la descrizione della notifica
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Metodo per ottenere la descrizione della notifica
    public String getDescrizione() {
        return descrizione;
    }

    // Metodo per rappresentare la notifica come una stringa
    @Override
    public String toString() {
        return "ID notifica: " + ID_N + " | ID utente: " + ID_U + " | Descrizione=" + descrizione + "";
    }
}
