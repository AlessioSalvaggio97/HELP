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
    private String emailInserita;
    private boolean isCorrect;
    private boolean checkRequirement;
    private String passwordInserita;

    public GestoreRecuperoPassword(ModuloLogin login, DBMSInterface db) {
        this.login = login;
        this.db = db;

        gestisciRecupero();

    }

    public void gestisciRecupero() {
        mr = new ModuloRecuperoPassword(db, this);
        System.out.println("Gestisci recupero");
        mr.setVisible(true);
    }

    public void gestisciMail() {

        System.out.println("Gestisci mail");
        emailInserita = mr.getEmail();

        System.out.println(emailInserita);
        Object[] credentials = db.verificaCredenziali(emailInserita);
        System.out.println(credentials.toString());

        if (credentials == null) { // la verificaCredenziali ritorna NULL se non c'è una tabella con quell'email
            isRegistered = false;
        } else
            isRegistered = true;

        while (isRegistered == false) {
            pannErrMail = new PannelloErroreMail();
            mr = new ModuloRecuperoPassword(db, this);
        }

        mOTP = new ModuloOTP(this);

    }

    public void gestisciOTP(int OTPInserito) {
        isCorrect = db.verificaOTP(OTPInserito, emailInserita);

        while (isCorrect == false) {
            pannErrOTP = new PannelloErroreOTP();
            mOTP = new ModuloOTP(this);
            isCorrect = db.verificaOTP(OTPInserito, emailInserita);
        }

        mnp = new ModuloNuovaPassword();
        passwordInserita = mnp.getPassword();
        checkRequirement = verificaRequisiti(passwordInserita);

        while (checkRequirement == false) {
            pannErrReq = new PannelloErroreRequisiti();
            mnp = new ModuloNuovaPassword();
            passwordInserita = mnp.getPassword();
            checkRequirement = verificaRequisiti(passwordInserita);
        }

        login.setVisible(true);
    }

    public boolean verificaRequisiti(String passwordInserita) {
        return true;
    }

}
