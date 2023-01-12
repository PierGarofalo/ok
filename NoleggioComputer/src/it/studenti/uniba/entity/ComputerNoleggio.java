package it.studenti.uniba.entity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * {@link ComputerNoleggio} composto massimo da 100 pc di classe
 * {@link Computer}.
 * 
 * @author Pierfederico Garofalo
 * @version 1.0
 */
public class ComputerNoleggio implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributi

	public static final int MAX_PC = 100; // numero massimo di veicoli che l'autonoleggio potrà contenere
	private Computer[] pc; // insieme dei veicoli che compongono l'autonoleggio
	private int numPc; // n. tot. di veicoli presenti nell'autonoleggio
	private int codPc; // ulimo codice generato
	private String cliente;

	// Metodi

	/**
	 * Crea un autonoleggio vuoto.
	 */
	public ComputerNoleggio() {
		codPc = 0; // inizializza il contatore per il CODICE DEI VEICOLI da gestire ad
					// AUTOINCREMENTO all'inserimento di un nuovo veicolo
		numPc = 0; // N. dei VEICOLI PRESENTI nell'autonoleggio e PRIMA POSIZIONE LIBERA
					// NELL'AUTONOLEGGIO
		pc = new Computer[MAX_PC]; // ISTANZIA il vettore dell'AUTONOLEGGIO (autonoleggio vuoto)

	}

	/**
	 * Fornisce la posizione occupata da un veicolo nell'autonoleggio.
	 * 
	 * @param codVeicolo codice del veicolo
	 * @return indice di posizione occupata, oppure -1 se il veicolo non esiste
	 */
	public int getPosizionePc(int codPc) {
		for (int i = 0; i < numPc; i++) {
			if (pc[i].getCodPc() == codPc) {
				return i;
			}
		}
		return -1; // codVeicolo non trovato
	}

	/**
	 * Fornisce un Veicolo a partire dal suo codice.
	 * 
	 * @param codVeicolo codice identificativo del veicolo nell'autonoleggio
	 * @return il veicolo
	 */
	public Computer getComputer(int codPc) {
		int pos = getPosizionePc(codPc);
		return pc[pos];
	}

	/**
	 * Aggiunge un nuovo veicolo all'autonoleggio assegnando al veicolo un codice
	 * identificativo numerico autoincrementale.
	 * 
	 * @param veicolo il veicolo da aggiungere
	 */
	public void aggiungiComputer(Computer computer) {
		codPc++;// gestisce l'AUTOINCREMENTO del codice del veicolo

		computer.setCodPc(codPc);
		pc[numPc] = computer; // aggiunge il veicolo all'autonoleggio
		numPc++;
	}

	/**
	 * Cancella un veicolo dall'autonoleggio a partire dal suo codice
	 * <strong>NOTA</strong> - Si accerta dell'esistenza del codice del veicolo ed
	 * effettua un "ricompattamento" del vettore dei veicoli eliminando lo spazio
	 * vuoto creato nel vettore con la cancellazione del veicolo.
	 * 
	 * @param codVeicolo codice identificativo del veicolo
	 * @return <strong>stringa di messaggio</strong> con la descrizione dell'errore,
	 *         se il codice del veicolo non esiste, <strong>null</strong> altrimenti
	 */
	public String cancellaComputer(int codPc) {
		int pos; // indice della posizione del veicolo da cancellare
		pos = getPosizionePc(codPc);
		// se il codice è inesistente
		if (pos < 0)
			return "Attenzione: codice computer inesistente.";
		else {
			// crea un nuovo vettore di Veicoli
			Computer[] v = new Computer[ComputerNoleggio.MAX_PC];
			int j = 0; // indice del nuovo vettore creato
			for (int i = 0; i < numPc; i++) {
				if (i != pos) {
					// "carica" il Veicolo nel nuovo vettore
					v[j] = pc[i];
					j++;
				}
			}
			// aggiorna il riferimento con quello del nuovo vettore
			pc = v;
			numPc = j;
			return null;
		}
	}

	/**
	 * Porta un veicolo nello stato 'prenotato'<br>
	 * <strong>NOTA</strong> - Se un veicolo si trova nello stato 'prenotato',
	 * questa operazione NON è da considerarsi non conforme e NON viene segnalata
	 * (un veicolo può registrare più prenotazioni che rigauardano periodi diversi)
	 * 
	 * @param codVeicolo codice del veicolo da noleggiare
	 * @return <strong>stringa di messaggio</strong> con la descrizione dell'errore,
	 *         se codVeicolo è inesistente, <strong>null</strong> altrimenti
	 */
	public String setComputerPrenotato(int codPc) {
		int pos = this.getPosizionePc(codPc);
		if (pos < 0)
			return "Attenzione: codice computer inesistente.";
		else {
			// imposta lo stato su 'prenotato'
			pc[pos].setPrenotato(true);
			return null;
		}
	}

	/**
	 * Esegue il rientro di un veicolo nell'autonoleggio, eventualmente portandolo
	 * nello stato 'non prenotato'<br>
	 * <strong>NOTA</strong> - Se un veicolo si trova nello stato 'non prenotato',
	 * questa operazione è da considerarsi non conforme e viene segnalata.
	 * 
	 * @param codVeicolo codice del veicolo che rientra
	 * @return <strong>stringa di messaggio</strong> con la descrizione dell'errore,
	 *         se il veicolo non esiste o non risulta essere in noleggio,
	 *         <strong>null</strong> altrimenti
	 */
	public String setComputerNonPrenotato(int codPc) {
		// si procura la posizione del veicolo nel vettore dei veicoli
		int pos = this.getPosizionePc(codPc);
		// se il codice non esiste
		if (pos < 0)
			return "Attenzione: codice computer inesistente.";
		else {
			// se il veicolo non risulta noleggiato
			if (!pc[pos].isPrenotato())
				return "Attenzione: il computer non risulta essere in noleggio.";
			else {
				// imposta lo stato 'non prenotato'
				pc[pos].setPrenotato(false);
				return null;
			}
		}
	}

	/**
	 * Verifica l'esistenza di un veicolo a partire dal suo codice.
	 * 
	 * @param codVeicolo codice del veicolo
	 * @return <strong>true</strong> se il veicolo esiste, <strong>false</strong>
	 *         altrimenti
	 */
	public boolean isComputer(int codPc) {
		int pos = getPosizionePc(codPc);
		if (pos < 0)
			return false;
		else
			return true;
	}

	/**
	 * Restituisce l'elenco dei veicoli che non risultano prenotati.
	 * 
	 * @return stringa con l'elenco dei veicoli senza prenotazioni
	 */
	public String getComputerSenzaPrenotazioni() {
		String s = "";
		for (int i = 0; i < numPc; i++) {
			if (!pc[i].isPrenotato()) {
				s += pc[i].toString() + '\n';
			}
		}
		return s;
	}

	/**
	 * Restituisce l'elenco di tutti i veicoli dell'autonoleggio.
	 * 
	 * @return stringa dell'elenco
	 */
	public String getTuttiComputer() {
		String s = "";
		for (int i = 0; i < numPc; i++) {
			s += pc[i].toString() + '\n';
		}
		return s;
	}

	/**
	 * Esporta la tabella dei veicoli dell'autonoleggio in un file CSV<br>
	 * <strong>NOTA</strong> - Il file CSV esportato ha anche la riga di
	 * intestazione.
	 * 
	 * @param nomeFile nome del file in cui esportare
	 * @throws java.io.IOException in caso di errori di scrittura sul file
	 */
	public void esportaInCsv(String nomeFile) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(nomeFile));
		out.println(Computer.getIntestazioniCSV());
		for (int i = 0; i < numPc; i++) {
			out.println(pc[i].getComputerToRigaCsv());
		}
		out.flush();
		out.close();
	}

	/**
	 * Importa veicoli da <strong>fonti di dati</strong> diverse (as es. fogli
	 * elettronici, database, altre applicazioni): aggiunge nuovi veicoli
	 * all'autonoleggio importandoli da un file in formato CSV situato nella
	 * directory corrente<br>
	 * Esso ha l'<strong>intestazione</strong> e utilizza come carattere
	 * delimitatore il punto e virgola.<br>
	 * <br>
	 * <strong>NOTA</strong> - Il file CSV deve avere la riga dell'intestazione.
	 * <strong>Suggerimento</strong>Per saper qual è la struttura corretta del file
	 * CSV, eseguire prima un'esportazione in CSV.
	 * 
	 * @throws java.io.FileNotFoundException in caso il file da cui importare non è
	 *                                       disponibile o è insesistente
	 */
	public void importaDaCsv(String nomeFile) throws FileNotFoundException {
		Computer pc;
		String riga;
		// apre il file in lettura
		Scanner in = new Scanner(new FileReader(nomeFile));
		// salta la riga dell'intestazione
		in.nextLine();
		while (in.hasNextLine()) {
			// legge ciascuna riga
			riga = in.nextLine();
			// splitta i dati della riga in un vettore di stringhe
			String[] dati = riga.split(";");
			if (dati.length > 1) {
				// crea un nuovo veicolo a partire dati contenuti nel vettore di stringhe
				// generato
				pc = new Computer(dati);
				// aggiunge il veicolo all'autonoleggio
				this.aggiungiComputer(pc);
			}
		}
		in.close();
	}

	public String getCliente() {

		return cliente;
	}
}