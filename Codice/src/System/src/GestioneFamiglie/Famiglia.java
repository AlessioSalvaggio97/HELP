package GestioneFamiglie;

import java.util.List;

public class Famiglia {

    private int ID_F;
    private int ID_U;
    private int componenti;

    public Famiglia(int ID_F, int ID_U, int componenti) {
        this.ID_F = ID_F;
        this.ID_U = ID_U;
        this.componenti = componenti;
    }

    public class Componente {
        private String nome;
        private String cognome;
        private String codice;
        private String data;
        private String indirizzo;
        private String bisogni;

        // Costruttore
        public Componente(String nome, String cognome, String codice, String data,
                String indirizzo, String bisogni) {
            this.nome = nome;
            this.cognome = cognome;
            this.codice = codice;
            this.data = data;
            this.indirizzo = indirizzo;
            this.bisogni = bisogni;
        }

        // Metodi get e set

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getCodice() {
            return codice;
        }

        public void setCodice(String codice) {
            this.codice = codice;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getIndirizzo() {
            return indirizzo;
        }

        public void setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
        }

        public String getBisogni() {
            return bisogni;
        }

        public void setBisogni(String bisogni) {
            this.bisogni = bisogni;
        }
    }

    public void setComponenti(List<Componente> componenti) {
        this.componenti = componenti.size();
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

    public int getComponenti() {
        return componenti;
    }

    public void setComponenti(int componenti) {
        this.componenti = componenti;
    }
}
