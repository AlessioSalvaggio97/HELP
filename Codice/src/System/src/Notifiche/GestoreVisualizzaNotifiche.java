package Notifiche;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneSmistamenti.Notifica;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestoreVisualizzaNotifiche {
	private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
	private SchermataVisualizzaNotifiche visNotifiche;

	public GestoreVisualizzaNotifiche(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		visualizzaNotifiche();
	}

    public void visualizzaNotifiche(){

        ArrayList<Notifica> notifiche = db.getNotifiche(u.getID_U());

		visNotifiche = new SchermataVisualizzaNotifiche(notifiche, this);
		visNotifiche.setVisible(true);
    }
}