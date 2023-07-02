package Autenticazione;

import Connectivity.DBMSInterface;

public class GestoreRecuperoPassword {
    private ModuloRecuperoPassword mr;
    private ModuloOTP mOTP;
    private ModuloNuovaPassword mnp;
    private DBMSInterface db;
    private ModuloLogin login;
    private boolean isRegistered;
    private Utente u;
    private PannelloErroreMail pannErrMail;
    private PannelloErroreOTP pannErrOTP;
    private PannelloErroreRequisiti pannErrReq;


    public GestoreRecuperoPassword(ModuloLogin login, DBMSInterface db){
    this.login = login;
    this.db = db;

    gestisciRecupero();
    }

    public void gestisciRecupero(){
        mr = new ModuloRecuperoPassword(db);
        
        isRegistered = db.verificaCredenziali(email);

        while(isRegistered==false){
            pannErrMail = new PannelloErroreMail();
            mr = new ModuloRecuperoPassword(db);
            isRegistered = db.verificaCredenziali(email);
        }


        mOTP = new ModuloOTP();
        isRegistered = verificaOTP(otp); 

        while(isCorrect==false){
            pannErrOTP = new PannelloErroreOTP();
            mOTP = new ModuloOTP();
            isCorrect = db.verificaOTP(otp);
        }

        mnp = new ModuloNuovaPassword();
        checkRequirement = verificaRequisiti(password);

        while(checkRequirement == false){
            pannErrReq = new PannelloErroreRequisiti();
            mnp = new ModuloNuovaPassword();
            checkRequirement = db.verificaRequisiti;
        }

        moduloLogin.setVisible(true);

    }


}
