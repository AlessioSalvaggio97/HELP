package GestioneFamiglie;

public class Famiglia {

    private int ID_F;
    private int ID_U;
    private String componenti;

    public Famiglia(int ID_F, int ID_U, String componenti) {
        this.ID_F = ID_F;
        this.ID_U = ID_U;
        this.componenti = componenti;
    }

    public int getID_F() {
        return ID_F;
    }

    public void setID_F(int ID_F) {
        this.ID_F = ID_F;
    }

    public int getID_U() {
        return ID_U;
    }

    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    public String getComponenti() {
        return componenti;
    }

    public void setComponenti(String componenti) {
        this.componenti = componenti;
    }
}
