package Main;

import Autenticazione.Utente;
import GestioneDonazioni.*;
import Connectivity.DBMSInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale extends JFrame {

    String titolo;
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    private Utente u;
    DBMSInterface db;
    JButton notificheButton;
    
    
    public SchermataPrincipale(Utente u) {
        this.u = u;
    	this.setSize(width, heigth);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();
        switch(u.getRuolo()){
		case "Azienda":
			initAzienda();
			break;
		
		case "Diocesi":
			initDiocesi();
			break;
		case "RPT":
			initRPT();
			break;
		
		case "Amministratore":
			initAmministratore();
			break;
			
        }
    }
  //Azienda
    JButton visualizzaRichieste = new JButton("Visualizza Richieste");
  
    
    JButton aggiungiProdotto = new JButton("Aggiungi Prodotto");
    
    
    JButton visualizzaStorico = new JButton("Visualizza storico");
    
    
    JButton modificaDonazione = new JButton("Modifica Donazione");
   
    
    //Diocesi o Polo
    JButton spedizioneArrivo = new JButton("Visualizza spedizione in arrivo");
    JButton segnalaErrore = new JButton("Segnala errore");
    JButton visualizzaSchema = new JButton("Visualizza Schema");
    JButton confermaSmistamento = new JButton("Conferma smistamento");
    
    //Polo
    JButton effettuaScarico = new JButton("Effettua scarico");
    JButton sospendiPolo = new JButton("Sospendi Polo");
    JButton riabilitaPolo = new JButton("Riabilita Polo");
    JButton scaricaReport = new JButton("Scarica Report");
    JButton regFamiglia = new JButton("Registra famiglia");
    JButton elencoFamiglia = new JButton("Elenco famiglie");
    

    public void initItems() {
    	
    	
    	//Pannello superiore con il pulsante Notifiche
        JPanel headerPnl = new JPanel();
        headerPnl.setLayout(new GridLayout());

        JPanel notifichePnl = new JPanel();
        notifichePnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        headerPnl.add(notifichePnl);
        notificheButton = new JButton();
        notificheButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        notificheButton.setText("Notifiche");
        notifichePnl.add(notificheButton);

        cont.add(headerPnl, BorderLayout.NORTH);
        
        //Pannello Inferiore con il pulsante Modifica Dati
        JPanel footerPnl = new JPanel();
        footerPnl.setLayout(new GridLayout());
        
        JPanel modificaDatiPnl = new JPanel();
        modificaDatiPnl.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        footerPnl.add(modificaDatiPnl);
        JButton modificaButton = new JButton();
        modificaButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        modificaButton.setText("Modifica Dati");
        modificaDatiPnl.add(modificaButton);

        cont.add(footerPnl, BorderLayout.SOUTH);
    }
    
    
    public void initAzienda() {
    	JPanel gst_Donazioni = new JPanel(new GridLayout(4,1,10,10));
    	initGstDonazioni(gst_Donazioni);
    	
    	visualizzaRichieste.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SchermataPrincipale.this.setVisible(false);
        		GestoreVisualizzaRichieste ges_r = new GestoreVisualizzaRichieste(SchermataPrincipale.this, u, db);
        	}
        });
    	
    	aggiungiProdotto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SchermataPrincipale.this.setVisible(false);
        		GestoreAggiungiProdotto ges_a = new GestoreAggiungiProdotto(SchermataPrincipale.this, u, db);
        	}
        });
    	
    	visualizzaStorico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SchermataPrincipale.this.setVisible(false);
        		GestoreVisualizzaStorico ges_s = new GestoreVisualizzaStorico(SchermataPrincipale.this, u, db);
        	}
        });
    	
    	 modificaDonazione.addActionListener(new ActionListener() {
    	    	public void actionPerformed(ActionEvent e) {
    	    		SchermataPrincipale.this.setVisible(false);
    	    		GestoreModificaDonazione ges_r = new GestoreModificaStorico(SchermataPrincipale.this, u, db);
    	    	}
    	    });
    }
    
    public void intDiocesi() {
    	JPanel gst_Smistamenti = new JPanel(new GridLayout(4,1,10,10));
    	initGstSmistamenti(gst_Smistamenti);
    }
    
    private void initGstDonazioni(JPanel g) {
    	g.add(visualizzaRichieste);
    	g.add(aggiungiProdotto);
    	g.add(visualizzaStorico);
    	g.add(modificaDonazione);
    }
    
    private void initGstSmistamenti(JPanel g) {
    	g.add(spedizioneArrivo);
    	g.add(segnalaErrore);
    	g.add(visualizzaSchema);
    	g.add(confermaSmistamento);
    }

}
