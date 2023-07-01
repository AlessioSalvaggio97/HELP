package GestioneDonazioni;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TempoBoundary {
    private String data;
    private Date d;
    private SimpleDateFormat sdf;

    public TempoBoundary() {
        d = new Date(); // La data attuale

        sdf = new SimpleDateFormat("dd"); //prende solo i giorni (controllo del giorno 23)
        data = sdf.format(d); // Formattazione della data attuale nel formato desiderato
        System.out.println(data);
    }

    public String chiediData() {
        return data;
    }
}
