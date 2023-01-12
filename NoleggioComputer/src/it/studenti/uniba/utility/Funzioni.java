package it.studenti.uniba.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import it.studenti.uniba.entity.Cliente;
import it.studenti.uniba.entity.Computer;
import it.studenti.uniba.entity.Gestione;
import it.studenti.uniba.entity.Noleggio;

/**
 * Presenti metodi statici che permettono il funzionamento del programma
 * 
 * @author Pierfederico Garofalo
 * @version 1.0
 */
public class Funzioni {

	/**
	 * Chiede all'utente di inserire il codice del computer e ne verifica
	 * l'esistenza
	 * 
	 * @param g la gestione del noleggio di computer
	 * @return codicePc esistente, -1 operazione annullata, -2 codicePc inesistente
	 */
	public static int chiediCodiceComputer(Gestione g) {
		int nCodice;
		String n;
		do
			try {
				n = JOptionPane.showInputDialog(null, "Inserisci il codice del computer: ", "Richiesta codice",
						JOptionPane.PLAIN_MESSAGE);
				// operazione annullata
				if (n == null) {
					return -1;
				}
				nCodice = Integer.parseInt(n);
				// check sull'esistenza del codice
				if (!g.isComputer(nCodice))
					// codice inesistente
					return -2;
				else
					// codice esistente
					return nCodice;
			} catch (NumberFormatException e) {
				// codice di tipo non intero
				JOptionPane.showMessageDialog(null,
						"Formato numerico errato. Il codice dei computer è un numero intero.", "Attenzione",
						JOptionPane.WARNING_MESSAGE);
			}
		while (true);
	}

	/**
	 * Chiede all'utente di inserire il codice di un noleggio in corso,
	 * controllandone l'esistenza.
	 * 
	 * @param g la gestione del noleggio di computer
	 * @return codiceNoleggio esistente, -1 operazione annullata, -2 codiceNoleggio
	 *         inesistente
	 */
	public static int chiediCodiceNoleggio(Gestione g) {
		int codNoleggio;
		String n;
		do
			try {
				n = JOptionPane.showInputDialog(null, "Insierici il codice del noleggio: ", "Richiesta codice",
						JOptionPane.PLAIN_MESSAGE);
				// se l'utente annulla l'operazione di input
				if (n == null) {
					return -1;
				}
				codNoleggio = Integer.parseInt(n);
				// controlla l'esistenza del codice
				if (!g.isNoleggio(codNoleggio))
					// se il codice inserito non esiste
					return -2;
				else
					// se il codice inserito esiste
					return codNoleggio;
			} catch (NumberFormatException e) {
				// se il codice inserito non è un numero intero
				JOptionPane.showMessageDialog(null,
						"Formato numerico errato. Il codice dei noleggi è un numero intero.", "Attenzione",
						JOptionPane.WARNING_MESSAGE);
			}
		while (true);
	}

	/**
	 * Chiede all'utente di inserire i dati del cliente che effettua il noleggio.
	 * 
	 * @return {@link Cliente} inserito o <strong>null</strong> se l'utente annulla
	 *         l'inserimento
	 */
	public static Cliente chiediCliente() {
		// si procura i dati del cliente da aggiungere
		String codFiscale = JOptionPane.showInputDialog("Inserisci il codice fiscale");
		if (codFiscale == null)
			return null;
		String nominativo = JOptionPane.showInputDialog("Inserisci il nominativo");
		if (nominativo == null)
			return null;
		String telefono = JOptionPane.showInputDialog("Inserisci il numero di telefono");
		if (telefono == null)
			return null;
		return new Cliente(codFiscale, nominativo, telefono);
	}

	/**
	 * Chiede all'utente di inserire una data come un testo nel formato gg/mm/aaaa.
	 * 
	 * @param msg stringa di testo da visualizzare nella finestra di dialogo
	 * @return la data convertita nel <strong>tipo Date</strong> o
	 *         <strong>null</strong> se l'utente annulla l'inserimento
	 */
	public static Date chiediData(String msg) {
		String s;
		Date d = new Date();
		do {
			s = JOptionPane.showInputDialog(null, msg, dateToString(d));
			if (s == null)
				return null;
			// converte la stringa della data in un oggetto di classe Date
			try {
				DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
				// imposta che i calcoli di conversione della data siano rigorosi
				formatoData.setLenient(false);
				d = formatoData.parse(s);
				return d;
			} catch (ParseException e) {
				System.out.println("Formato data non valido.");
			}
		} while (true);
	}

	/**
	 * Chiede all'utente di inserire i dati di un veicolo.
	 * 
	 * @return {@link Veicolo} inserito o <strong>null</strong> se l'utente annulla
	 *         l'inserimento
	 */
	public static Computer chiediComputerB() {
		// si procura i dati del veicolo da aggiungere

		String cpu = JOptionPane.showInputDialog("Inserisci la cpu");
		if (cpu == null)
			return null;
		String schedaGrafica = JOptionPane.showInputDialog("Inserisci la scheda grafica");
		if (schedaGrafica == null)
			return null;

		Computer pc = new Computer(cpu, schedaGrafica);
		return pc;
	}

	public static Computer chiediComputerM() {
		// si procura i dati del veicolo da aggiungere

		String cpu = JOptionPane.showInputDialog("Inserisci la cpu");
		if (cpu == null)
			return null;
		String schedaGrafica = JOptionPane.showInputDialog("Inserisci la scheda grafica");
		if (schedaGrafica == null)
			return null;
		String dCam = JOptionPane.showInputDialog("Inserisci la doppia camera");
		if (dCam == null)
			return null;

		Computer pc = new Computer(cpu, schedaGrafica, dCam);
		return pc;
	}

	public static Computer chiediComputerA() {
		// si procura i dati del veicolo da aggiungere

		String cpu = JOptionPane.showInputDialog("Inserisci la cpu");
		if (cpu == null)
			return null;
		String schedaGrafica = JOptionPane.showInputDialog("Inserisci la scheda grafica");
		if (schedaGrafica == null)
			return null;
		String dCam = JOptionPane.showInputDialog("Inserisci la doppia camera");
		if (dCam == null)
			return null;
		String touch = JOptionPane.showInputDialog("Inserisci touchId");
		if (touch == null)
			return null;
		String vocal = JOptionPane.showInputDialog("Inserisci vocalId");
		if (vocal == null)
			return null;

		Computer pc = new Computer(cpu, schedaGrafica, dCam, touch, vocal);
		return pc;
	}

	/**
	 * Converte una data di tipo Date in una stringa
	 * 
	 * @param d data di tipo Date da convertire
	 * @return stringa della data
	 */
	public static String dateToString(Date d) {
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		return formatoData.format(d);
	}
}