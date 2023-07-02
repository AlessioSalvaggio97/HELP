package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import java.util.List;

public class GestoreScaricaReport {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
	private List<Report> elencoReport;
	private Report copiaReport;
	private SchermataScaricaReport ssr;

    public GestoreScaricaReport(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		
		gestisciScaricaReport();
	}

	public void gestisciScaricaReport(){
		elencoReport = db.getDatiScarico(u); //id_report, nome prodotto, proprietà, quantità, numeroFamiglieRitiro

		ssr = new SchermataScaricaReport(elencoReport, this);
		ssr.setVisible(true);

	}

	public void scaricaReport(){
		copiaReport = db.scaricaReport(u);


		return copiaReport; //invece dovrebbe effettuare il download
	}
}