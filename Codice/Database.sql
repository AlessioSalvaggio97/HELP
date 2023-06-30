CREATE TABLE `utente` (
`ID_U` int UNSIGNED NOT NULL,
`email` varchar(50) NOT NULL,
`password` varchar(20) NOT NULL,
`nome` varchar(20) NOT NULL,
`cognome` varchar(20),
`indirizzo` varchar(40) NOT NULL,
`ruolo` varchar(30) NOT NULL,
`telefono` varchar(13) NOT NULL
);

INSERT INTO `utente` (`ID_U`, `email`, `password`, `nome`, `cognome`, `indirizzo`, `ruolo`, `telefono`) VALUES
(1, 'primoutente@gmail.com', 'prime', 'Alessio', 'Salvaggio', 'Via Rolando Francesco, 2', 'Diocesi', 0123456789),
(2, 'secondoutente@gmail.com', 'second', 'Laura', 'Zanghì', 'Via Calatafimi, 6', 'Amministratore', 4323458790),
(3, 'terzoutente@gmail.com', 'third', 'Claudio', 'Angileri', 'Via Francesca Morvillo, 5', 'RPT', 3291453244),
(4, 'quartoutente@gmail.com', 'fourth', 'Barilla', NULL, 'Via Ernesto Basile, 30', 'Azienda', 0987654321),
(5, 'quintoutente@gmail.com', 'fifth', 'Luisa', 'Morbillo', 'Via Cesare Boldrini, 5', 'RPT', 091234567499);

CREATE TABLE `polo` (
`ID_U` int UNSIGNED NOT NULL
);

INSERT INTO `polo` (`ID_U`) VALUES
(3),
(5);

CREATE TABLE `diocesi` (
`ID_U` int UNSIGNED NOT NULL
);

INSERT INTO `diocesi` (`ID_U`) VALUES
(1);

CREATE TABLE `azienda` (
`ID_U` int UNSIGNED NOT NULL
);

INSERT INTO `azienda` (`ID_U`) VALUES
(4);

CREATE TABLE `notifica` (
`ID_N` int UNSIGNED NOT NULL,
`descrizione` varchar(100),
`ID_U` int UNSIGNED NOT NULL
);

INSERT INTO `notifica` (`ID_N`, `descrizione`, `ID_U`) VALUES
(6, 'La quantità di prodotti non corrisponde a quella promessa.', 1), /*alla diocesi*/
(7, 'La quantità di prodotti non corrisponde a quella promessa.', 2), /*all'amministratore*/
(8, 'È giorno 23, effettua la donazione.', 4); /*all'azienda donatrice*/

CREATE TABLE `famiglia` (
`ID_F` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED, /*per il polo*/
`componenti` int
);

INSERT INTO `famiglia` (`ID_F`, `ID_U`, `componenti`) VALUES
(9, 3, 2), 
(10, 3, 4),
(11, 5, 1);

CREATE TABLE `componente` (
`codice_fiscale` varchar(20) NOT NULL,
`ID_F` int UNSIGNED,
`nome` varchar(20) NOT NULL,
`cognome` varchar(20) NOT NULL,
`data_nascita` date NOT NULL,
`indirizzo` varchar(50) NOT NULL,
`bisogni` varchar(50)
);

INSERT INTO `componente` (`codice_fiscale`, `ID_F`, `nome`, `cognome`, `data_nascita`, `indirizzo`, `bisogni`) VALUES
('AR132234VSU45897', 9, 'Antonio', 'Ragusa', '2000-09-16', 'Via Salemi, 6', 'glutine'),
('FP424523VAE78900', 11, 'Francesco', 'Passalacqua', '1995-04-20', 'Via Astra, 4', 'zucchero'),
('LN901324VSA96454', 10, 'Luca', 'Nicastro', '2023-01-13', 'Via Salomone, 9', NULL ),
('AB162632VRU67822', 9, 'Anna', 'Belmonte', '2016-05-05', 'Via Rocca, 11', 'lattosio'),
('VN154678VAO82882', 10, 'Viviana', 'Norvegia', '1985-03-25', 'Via Alimena, 45', NULL ),
('LP152632VMH56433', 10, 'Lorena', 'Palla', '1999-11-14', 'Via Montemaggiore, 7', 'glutine'),
('VM273744VAU98775', 10, 'Viviana', 'Marsala', '2001-12-24', 'Via Augello, 9', 'lattosio');

CREATE TABLE `prodotto`( /* lista prodotti selezionabili*/
`ID_P` int UNSIGNED NOT NULL,
`nome_prodotto` varchar(20) NOT NULL,
`proprietà` varchar(30),
`ID_U` int UNSIGNED /*per le aziende che l'hanno selezionato*/
);

INSERT INTO `prodotto` (`ID_P`, `nome_prodotto`, `proprietà`, `ID_U`) VALUES
/*adulti*/
(12, 'Acqua', NULL, 4),
(13, 'Zucchero', NULL, NULL),
(14, 'Sale', NULL, NULL),
(15, 'Aceto', NULL, NULL),
(16, 'Pasta', NULL, 4),
(17, 'Pasta', 'Glutine', 4), 
(18, 'Riso', NULL, NULL),
(19, 'Farina', NULL, 4),
(20, 'Farina', 'Glutine', 4),
(21, 'Latte', NULL, NULL),
(22, 'Latte', 'Lattosio', NULL),
(23, 'Caffè', NULL, NULL),
(24, 'Cacao', NULL, NULL),
(25, 'Cacao', 'Zucchero', NULL),
(26, 'Tè', NULL, NULL),
(27, 'Tè', 'Zucchero', NULL),
(28, 'Crema spalmabile', NULL, NULL),
(29, 'Crema spalmabile', 'Lattosio', NULL),
(30, 'Olio', NULL, NULL),
(31, 'Confetture', NULL, NULL),
(32, 'Confetture', 'Zucchero', NULL),
(33, 'Crackers', NULL, NULL),
(34, 'Crackers', 'Glutine', NULL),
(35, 'Crackers', 'Sale', NULL),
(36, 'Conserva di pomodoro', NULL, NULL),
(37, 'Miele', NULL, NULL),
(38, 'Pesto', NULL, NULL),
(39, 'Pesto', 'Lattosio', NULL),
(40, 'Frutta in scatola', NULL, NULL),
(41, 'Frutta in scatola', 'Zucchero', NULL),
(42, 'Frutta secca', NULL, NULL),
(43, 'Biscotti', NULL, NULL),
(44, 'Biscotti', 'Zucchero', NULL),
(45, 'Biscotti', 'Glutine', NULL),
(46, 'Biscotti', 'Lattosio', NULL),
(47, 'Succo di frutta', NULL, NULL),
(48, 'Succo di frutta', 'Zucchero', NULL),
(49, 'Patatine', NULL, NULL),
(50, 'Patatine', 'Sale', NULL),
(51, 'Pesce in scatola', NULL, NULL),
(52, 'Pesce in scatola', 'Sale', NULL),
(53, 'Legumi', NULL, NULL),
/*neonati*/
(54, 'Biscotti per bambini', NULL, NULL),
(55, 'Biscotti per bambini', 'Zucchero', NULL),
(56, 'Biscotti per bambini', 'Lattosio', NULL),
(57, 'Biscotti per bambini', 'Glutine', NULL),
(58, 'Omogeneizzati', NULL, NULL),
(59, 'Omogeneizzati', 'Glutine', NULL),
(60, 'Latte per neonati', NULL, NULL),
(61, 'Latte per neonati', 'Lattosio', NULL),
(62, 'Pastina', NULL, NULL),
(63, 'Pastina', 'Glutine', NULL),
(64, 'Crema di riso', NULL, NULL),
(65, 'Crema di riso', 'Lattosio', NULL),
(66, 'Yogurt', NULL, NULL),
(67, 'Yogurt', 'Lattosio', NULL);

CREATE TABLE `richiesta` (
`ID_R` int UNSIGNED NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED NOT NULL, /*azienda*/
`quantità` float(40) NOT NULL
);

INSERT INTO `richiesta` (`ID_R`, `ID_P`, `ID_U`, `quantità`) VALUES
(68, 22, 4, 50),
(69, 23, 4, 60);

CREATE TABLE `magazzino`( /*capienza*/
`ID_M` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED NOT NULL, /*amministratore (HELP), diocesi, polo*/
`capienza_max` float(40) NOT NULL,
`capienza_attuale` float(40) NOT NULL
);

INSERT INTO `magazzino` (`ID_M`, `ID_U`, `capienza_max`, `capienza_attuale`) VALUES
(70, 2, 700, 300),
(71, 2, 900, 500);

CREATE TABLE `contiene` (
`ID` int UNSIGNED NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`ID_M` int UNSIGNED NOT NULL,
`quantità` float(40)
);

INSERT INTO `contiene` (`ID`, `ID_P`, `ID_M`, `quantità`) VALUES
(72, 22, 70, 50),
(73, 23, 71, 60);

CREATE TABLE `donazione`(
`ID_D` int UNSIGNED NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`ID_M` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED NOT NULL, /*azienda che dona*/
`data` date NOT NULL,
`quantità_d` float(40) NOT NULL,
`scadenza` date NOT NULL
);

INSERT INTO `donazione` (`ID_D`, `ID_P`, `ID_M`, `ID_U`, `data`, `quantità_d`, `scadenza`) VALUES
(74, 22, 70, 4, '2023-03-25', 40, '2023-12-19'),
(75, 23, 71, 4, '2023-04-25', 60, '2024-02-02');

CREATE TABLE `spedizione`(
`ID_Spe` int UNSIGNED NOT NULL,
`ID_D` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED NOT NULL, /*azienda che spedisce*/
`data_arrivo` date NOT NULL,
`stato` varchar(20) NOT NULL
);

INSERT INTO `spedizione` (`ID_Spe`, `ID_D`, `ID_U`, `data_arrivo`, `stato`) VALUES
(76, 74, 4, '2023-03-30', 'Consegnato!'),
(77, 75, 4, '2023-04-30', 'In consegna!');

CREATE TABLE `smistamento`(
`ID_Smi` int UNSIGNED NOT NULL, /*numero lotto*/
`ID_U` int UNSIGNED NOT NULL, /*diocesi che smista*/
`data_corrente` date DEFAULT (CURRENT_DATE),
`data_arrivo` date NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`scadenza` date NOT NULL,
`quantità_d` float(40) NOT NULL,
`stato` varchar(20) NOT NULL
);

INSERT INTO `smistamento` (`ID_Smi`, `ID_U`, `data_arrivo`, `ID_P`, `scadenza`, `quantità_d`, `stato`) VALUES
(76, 1, '2023-04-01', 22, '2023-12-19', 40, 'Consegnato!'),
(77, 1, '2023-05-06', 23, '2024-02-02', 60, 'In transito!');

CREATE TABLE `scarico` (
`ID_Scarico` int UNSIGNED NOT NULL,
`ID_M` int UNSIGNED NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`ID_U` int UNSIGNED NOT NULL, /*polo*/
`quantità_smist` float(40) NOT NULL,
`data_scarico` date NOT NULL
);

INSERT INTO `scarico` (`ID_Scarico`, `ID_M`, `ID_P`, `ID_U`, `quantità_smist`, `data_scarico`) VALUES
(78, 70, 22, 3, 40, '2023-04-05'),
(79, 71, 23, 3, 60, '2023-05-06');

CREATE TABLE `schema_di_distr` (
`ID_Dis` int UNSIGNED NOT NULL,
`ID_P` int UNSIGNED NOT NULL,
`ID_visualizzatore` int UNSIGNED NOT NULL, /*diocesi o polo*/
`ID_ricevente` int UNSIGNED, /*polo che riceve i prodotti*/
`ID_F` int UNSIGNED,
`quantità`float(40)
);

INSERT INTO `schema_di_distr` (`ID_Dis`, `ID_P`, `ID_visualizzatore`, `ID_ricevente`, `ID_F`, `quantità`) VALUES
(80, 22, 1, 3, NULL, 40), /*primo schema di distribuzione*/
(81, 22, 3, NULL, 9, 10);

/*Auto-incrementi, indici e relazioni*/
ALTER TABLE `utente`
MODIFY ID_U int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6,
ADD PRIMARY KEY (`ID_U`);

ALTER TABLE `polo`
ADD FOREIGN KEY (ID_U) REFERENCES utente(ID_U);

ALTER TABLE `diocesi`
ADD FOREIGN KEY (ID_U) REFERENCES utente(ID_U);

ALTER TABLE `azienda`
ADD FOREIGN KEY (ID_U) REFERENCES utente(ID_U);

ALTER TABLE `notifica`
MODIFY `ID_N` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9,
ADD PRIMARY KEY (`ID_N`),
ADD FOREIGN KEY (ID_U) REFERENCES utente(ID_U) ON UPDATE CASCADE;

ALTER TABLE `famiglia`
MODIFY `ID_F` int UNSIGNED AUTO_INCREMENT, AUTO_INCREMENT=12,
ADD PRIMARY KEY (`ID_F`),
ADD FOREIGN KEY (ID_U) REFERENCES polo(ID_U) ON UPDATE CASCADE;

ALTER TABLE `componente`
MODIFY `ID_F` INT UNSIGNED AUTO_INCREMENT, AUTO_INCREMENT=12,
ADD PRIMARY KEY (`codice_fiscale`),
ADD FOREIGN KEY (ID_F) REFERENCES famiglia(ID_F) ON UPDATE CASCADE;

ALTER TABLE `prodotto`
MODIFY `ID_P` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68,
ADD PRIMARY KEY (`ID_P`),
ADD FOREIGN KEY (ID_U) REFERENCES azienda(ID_U);

ALTER TABLE `richiesta`
MODIFY `ID_R` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70,
ADD PRIMARY KEY (`ID_R`),
ADD FOREIGN KEY (ID_P) REFERENCES prodotto(ID_P),
ADD FOREIGN KEY (ID_U) REFERENCES azienda(ID_U);

ALTER TABLE `magazzino`
MODIFY `ID_M` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72,
ADD PRIMARY KEY (`ID_M`),
ADD FOREIGN KEY (ID_U) REFERENCES utente(ID_U);

ALTER TABLE `contiene`
MODIFY `ID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74,
ADD PRIMARY KEY (`ID`),
ADD FOREIGN KEY (ID_M) REFERENCES magazzino(ID_M),
ADD FOREIGN KEY (ID_P) REFERENCES prodotto(ID_P);

ALTER TABLE `donazione`
MODIFY `ID_D` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76,
ADD PRIMARY KEY (`ID_D`),
ADD FOREIGN KEY (ID_M) REFERENCES magazzino(ID_M),
ADD FOREIGN KEY (ID_P) REFERENCES prodotto(ID_P),
ADD FOREIGN KEY (ID_U) REFERENCES azienda(ID_U);

ALTER TABLE `spedizione`
MODIFY `ID_Spe` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78,
ADD PRIMARY KEY (`ID_Spe`),
ADD FOREIGN KEY (ID_D) REFERENCES donazione(ID_D),
ADD FOREIGN KEY (ID_U) REFERENCES azienda(ID_U);

ALTER TABLE `scarico`
MODIFY `ID_Scarico` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80,
ADD PRIMARY KEY (`ID_Scarico`),
ADD FOREIGN KEY (ID_M) REFERENCES magazzino(ID_M),
ADD FOREIGN KEY (ID_P) REFERENCES prodotto(ID_P),
ADD FOREIGN KEY (ID_U) REFERENCES polo(ID_U);

ALTER TABLE `schema_di_distr`
MODIFY `ID_Dis` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82,
ADD PRIMARY KEY (`ID_Dis`),
ADD FOREIGN KEY (ID_P) REFERENCES prodotto(ID_P),
ADD FOREIGN KEY (ID_visualizzatore) REFERENCES utente(ID_U),
ADD FOREIGN KEY (ID_ricevente) REFERENCES polo(ID_U),
ADD FOREIGN KEY (ID_F) REFERENCES famiglia(ID_F);

ALTER TABLE `smistamento`
MODIFY `ID_Smi` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70,
ADD PRIMARY KEY (`ID_Smi`),
ADD FOREIGN KEY (`ID_U`) REFERENCES diocesi(ID_U),
ADD FOREIGN KEY (`ID_P`) REFERENCES prodotto(ID_P);