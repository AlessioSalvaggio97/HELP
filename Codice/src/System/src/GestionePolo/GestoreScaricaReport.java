package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreScaricaReport {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
	private List<Report> elencoReport;
	private Report copiaReport;

    public GestoreScaricaReport(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		
		gestisciScaricaReport();
	}

	public void gestisciScaricaReport(){
		elencoReport = db.getDatiScarico(); //id_report, nome prodotto, proprietà, quantità, numeroFamiglieRitiro

		ssr = new SchermataScaricaReport(elencoReport, this);

	}

	public void scaricaReport(){


		return copiaReport
	}
}