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

CREATE TABLE `notifica` (
`ID_N` int UNSIGNED NOT NULL,
`descrizione` varchar(100),
`ID_U` int NOT NULL
);

INSERT INTO `notifica` (`ID_N`, `descrizione`, `ID_U`) VALUES
(6, 'La quantità di prodotti non corrisponde a quella promessa.', 1), /*alla diocesi*/
(7, 'La quantità di prodotti non corrisponde a quella promessa.', 2), /*all'amministratore*/
(8, 'È giorno 23, effettua la donazione.', 4); /*all'azienda donatrice*/

CREATE TABLE `famiglia` (
`ID_F` int UNSIGNED NOT NULL,
`ID_U` int NOT NULL, /*per il polo*/
`componenti` int NOT NULL
);

INSERT INTO `famiglia` (`ID_F`, `ID_U`, `componenti`) VALUES
(9, 3, 2), 
(10, 3, 4),
(11, 5, 1);

CREATE TABLE `componente` (
`codice_fiscale` varchar(20) NOT NULL,
`ID_F` int NOT NULL,
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

CREATE TABLE `prodotto`(
`ID_P` int UNSIGNED NOT NULL,
`nome_prodotto` varchar(20) NOT NULL,
`proprietà` varchar(30) /*fare prodotti generali o specifici?*/
);

INSERT INTO `prodotto` (`ID_P`, `nome_prodotto`, `Proprietà`) VALUES
/*adulti*/
(12, 'Acqua', NULL),
(13, 'Zucchero', NULL),
(14, 'Sale', NULL),
(15, 'Aceto', NULL),
(16, 'Pasta', NULL),
(17, 'Pasta', 'Glutine'),
(18, 'Riso', NULL),
(19, 'Farina', NULL),
(20, 'Farina', 'Glutine'),
(21, 'Latte', NULL),
(22, 'Latte', 'Lattosio'),
(23, 'Caffè', NULL),
(24, 'Cacao', NULL),
(25, 'Cacao', 'Zucchero'),
(26, 'Tè', NULL),
(27, 'Tè', 'Zucchero'),
(28, 'Crema spalmabile', NULL),
(29, 'Crema spalmabile', 'Lattosio'),
(30, 'Olio', NULL),
(31, 'Confetture', NULL),
(32, 'Confetture', 'Zucchero'),
(33, 'Crackers', NULL),
(34, 'Crackers', 'Glutine'),
(35, 'Crackers', 'Sale'),
(36, 'Conserva di pomodoro', NULL),
(37, 'Miele', NULL),
(38, 'Pesto', NULL),
(39, 'Pesto', 'Lattosio'),
(40, 'Frutta in scatola', NULL),
(41, 'Frutta in scatola', 'Zucchero'),
(42, 'Frutta secca', NULL),
(43, 'Biscotti', NULL),
(44, 'Biscotti', 'Zucchero'),
(45, 'Biscotti', 'Glutine'),
(46, 'Biscotti', 'Lattosio'),
(47, 'Succo di frutta', NULL),
(48, 'Succo di frutta', 'Zucchero'),
(49, 'Patatine', NULL),
(50, 'Patatine', 'Sale'),
(51, 'Pesce in scatola', NULL),
(52, 'Pesce in scatola', 'Sale'),
(53, 'Legumi', NULL),
/*neonati*/
(54, 'Biscotti per bambini', NULL),
(55, 'Biscotti per bambini', 'Zucchero'),
(56, 'Biscotti per bambini', 'Lattosio'),
(57, 'Biscotti per bambini', 'Glutine'),
(58, 'Omogeneizzati', NULL),
(59, 'Omogeneizzati', 'Glutine'),
(60, 'Latte per neonati', NULL),
(61, 'Latte per neonati', 'Lattosio'),
(62, 'Pastina', NULL),
(63, 'Pastina', 'Glutine'),
(64, 'Crema di riso', NULL),
(65, 'Crema di riso', 'Lattosio'),
(66, 'Yogurt', NULL),
(67, 'Yogurt', 'Lattosio');

CREATE TABLE `richiesta` (
`ID_R` int UNSIGNED NOT NULL,
`ID_P` varchar(20) NOT NULL,
`quantità` float(40) NOT NULL
);

INSERT INTO `richiesta` (`ID_R`, `ID_P`, `quantità`) VALUES
(68, 22, 50),
(69, 23, 60);

/* CREATE TABLE `magazzino` ( /*prodotti presenti
`ID_M` int UNSIGNED NOT NULL,
`ID_P` int NOT NULL
/*quantità prodotto poi va con la relazione con prodotto
); */

CREATE TABLE `magazzino`( /*capienza*/
`ID_M` int UNSIGNED NOT NULL,
`capienza_max` float(40) NOT NULL,
`capienza_attuale` float(40) NOT NULL
);

INSERT INTO `magazzino` (`ID_M`, `capienza_max`, `capienza_attuale`) VALUES
(70, 700, 300),
(71, 900, 500);

CREATE TABLE `contiene` (
`ID` int UNSIGNED NOT NULL,
`ID_P` int NOT NULL,
`ID_M` int NOT NULL,
`quantità` float(40)
);

INSERT INTO `contiene` (`ID`, `ID_P`, `ID_M`, `quantità`) VALUES
(72, 22, 70, 50),
(73, 23, 71, 60);


/*Rivedere schema di distribuzione*/

CREATE TABLE `donazione`(
`ID_D` int UNSIGNED NOT NULL,
`ID_P` int NOT NULL,
`data` date NOT NULL,
`quantità_d` float(40) NOT NULL,
`scadenza` date NOT NULL
);

INSERT INTO `donazione` (`ID_D`, `ID_P`, `data`, `quantità_d`, `scadenza`) VALUES
(74, 22, '2023-03-25', 40, '2023-12-19'),
(75, 23, '2023-04-25', 60, '2024-02-02');

CREATE TABLE `spedizione`(
`ID_S` int UNSIGNED NOT NULL,
`data_arrivo` date NOT NULL,
`ID_P` int NOT NULL,
`scadenza` date NOT NULL,
`quantità_d` float(40) NOT NULL,
`stato` varchar(20) NOT NULL
);

INSERT INTO `spedizione` (`ID_S`, `data_arrivo`, `ID_P`, `scadenza`, `quantità_d`, `stato`) VALUES
(76, '2023-04-01', 22, '2023-12-19', 40, 'Consegnato!'),
(77, '2023-05-06', 23, '2024-02-02', 60, 'In transito!');

CREATE TABLE `scarico` (
`ID_Scarico` int UNSIGNED NOT NULL,
`ID_M` int NOT NULL,
`ID_P` int NOT NULL,
`ID_U` int NOT NULL,
`quantità_smist` float(40) NOT NULL,
`data_scarico` date NOT NULL
);

INSERT INTO `scarico` (`ID_Scarico`, `ID_M`, `ID_P`, `ID_U`, `quantità_smist`, `data_scarico`) VALUES
(78, 70, 22, 3, 40, '2023-04-05'),
(79, 71, 23, 3, 60, '2023-05-06');

/*Indici*/
ALTER TABLE `utente`
ADD PRIMARY KEY (`ID_U`);

ALTER TABLE `notifica`
ADD PRIMARY KEY (`ID_N`),
ADD KEY (`ID_U`);

ALTER TABLE `famiglia`
ADD PRIMARY KEY (`ID_F`),
ADD KEY (`ID_U`);

ALTER TABLE `componente`
ADD PRIMARY KEY (`codice_fiscale`),
ADD KEY (`ID_F`);

ALTER TABLE `prodotto`
ADD PRIMARY KEY (`ID_P`);

ALTER TABLE `richiesta`
ADD PRIMARY KEY (`ID_R`),
ADD KEY (`ID_P`);

ALTER TABLE `magazzino`
ADD PRIMARY KEY (`ID_M`);

ALTER TABLE `contiene`
ADD PRIMARY KEY (`ID`),
ADD KEY (`ID_M`),
ADD KEY (`ID_P`);

ALTER TABLE `donazione`
ADD PRIMARY KEY (`ID_D`),
ADD KEY (`ID_P`);

ALTER TABLE `spedizione`
ADD PRIMARY KEY (`ID_S`),
ADD KEY (`ID_P`);

ALTER TABLE `scarico`
ADD PRIMARY KEY (`ID_Scarico`),
ADD KEY (`ID_M`),
ADD KEY (`ID_P`),
ADD KEY (`ID_U`);

/*Auto increment*/

ALTER TABLE `utente`
MODIFY `ID_U` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `notifica`
MODIFY `ID_N` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

ALTER TABLE `famiglia`
MODIFY `ID_F` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

ALTER TABLE `prodotto`
MODIFY `ID_P` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

ALTER TABLE `richiesta`
MODIFY `ID_R` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

ALTER TABLE `magazzino`
MODIFY `ID_M` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

ALTER TABLE `contiene`
MODIFY `ID` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

ALTER TABLE `donazione`
MODIFY `ID_D` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

ALTER TABLE `spedizione`
MODIFY `ID_S` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

ALTER TABLE `scarico`
MODIFY `ID_Scarico` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

