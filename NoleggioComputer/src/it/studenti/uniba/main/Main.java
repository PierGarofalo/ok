package it.studenti.uniba.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import it.studenti.uniba.entity.Cliente;
import it.studenti.uniba.entity.Computer;
import it.studenti.uniba.entity.Gestione;
import it.studenti.uniba.entity.Noleggio;
import it.studenti.uniba.utility.Funzioni;

/**
 * menu che permette al dipendente dell'azienda di gestire noleggi di computer
 * 
 * @author Pierfederico Garofalo
 */
public class Main {
	public static void main(String[] args) throws IOException {

		Gestione g;
		int codPc = 0;
		int codNoleggio = 0;

		Cliente cliente;
		Computer pc;

		String esito;

		Date dataInizio;
		Date dataFine;
		Date dataRestituzione;

		// prova a caricare l'ultima istanza della gestione del noleggio, se esiste,
		// salvata su file
		try {
			g = Gestione.caricaDaFile();
		} catch (Exception e) {
			// crea una nuova gestione con un base di dati vuota
			g = new Gestione();
			JOptionPane.showMessageDialog(null, "E' stato inizializzato un autonoleggio vuoto.", "Attenzione",
					JOptionPane.WARNING_MESSAGE);
		}

		String menu = "                              GESTIONE NOLEGGIO COMPUTER                           \n"
				+ "- Gestisci noleggi\n" + "   1) Nuovo noleggio\n" + "   2) Rientro computer\n" + "\n"
				+ "- Visualizza\n" + "   3) Tutti i computer\n" + "   4) Computer diponibili nel periodo indicato\n"
				+ "   5) Noleggi attivi\n" + "\n" + "- Gestisci computer\n" + "   6) Aggiungi computer base\n"
				+ "   7) Aggiungi computer medio\n" + "   8) Aggiungi computer avanzato\n" + "   9) Cancella computer\n"
				+ "\n" + "- File\n" + "   10) Salva gestione su file\n" + "   11) Importa veicoli da file CSV\n"
				+ "   12) Esporta veicoli su file CSV\n" + "\n" + "   0) Chiudi\n" + "\n"
				+ "Scegli l'operazione da eseguire:\n";
		String stringa;

		int scelta = 1;
		do {
			stringa = JOptionPane.showInputDialog(null, menu, "Noleggio Computer", JOptionPane.PLAIN_MESSAGE);
			if (stringa == null) {
				// esce quando si preme 'Annulla'
				try {
					g.salvaSuFile();
				} catch (Exception e) {
					int risposta = JOptionPane.showConfirmDialog(null,
							"Salvataggio su file non riuscito. Uscire lo stesso?\n"
									+ "Attenzione, se confermi perderai tutti i dati che inseriti dall'ultimo salvataggio.",
							"Errore", JOptionPane.WARNING_MESSAGE);
					if (risposta == JOptionPane.OK_OPTION)
						return;
				}
				return;
			}
			try {
				scelta = Integer.parseInt(stringa);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Scegliere un numero.", "Attenzione", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			switch (scelta) {
			case 1:
				codPc = Funzioni.chiediCodiceComputer(g);

				// controlla l'esito
				if (codPc == -1) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else if (codPc == -2) {
					JOptionPane.showMessageDialog(null, "Codice insesistente", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
					break;
				}

				dataInizio = Funzioni.chiediData("Inserisci data iniziale");
				// controlla l'esito
				if (dataInizio == null) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				dataFine = Funzioni.chiediData("Inserisci data scadenza");
				// controlla l'esito
				if (dataFine == null) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				if (dataInizio.after(dataFine)) {
					JOptionPane.showMessageDialog(null, "Data di inizio e di fine noleggio incompatibili.",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					break;
				}
				if (!g.isPrenotazionePossibile(codPc, dataInizio, dataFine)) {
					JOptionPane.showMessageDialog(null, "Il computer richiesto non è disponibile nel periodo indicato.",
							"Messaggio", JOptionPane.INFORMATION_MESSAGE);
					break;
				}

				cliente = Funzioni.chiediCliente();
				// controlla l'esito
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}

				g.aggiungiPrenotazione(codPc, cliente, dataInizio, dataFine);
				JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:

				codNoleggio = Funzioni.chiediCodiceNoleggio(g);
				// controlla l'esito
				if (codNoleggio == -1) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else if (codNoleggio == -2) {
					JOptionPane.showMessageDialog(null, "Codice insesistente", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				dataRestituzione = Funzioni.chiediData("inserisci DATA di RESTITUZIONE nel formato: gg/mm/yyyy");
				esito = g.restituzioneComputer(codNoleggio, "storico_noleggi.csv");
//				n.calculatePenality(dataFine, dataRestituzione);

				// controlla l'esito dell'operazione
				if (esito != null)
					JOptionPane.showMessageDialog(null, esito, "Attenzione", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, g.getElencoTuttiComputer(), "Tutti i computer",
						JOptionPane.PLAIN_MESSAGE);
				break;
			case 4:
				dataInizio = Funzioni.chiediData("Inserisci la DATA INIZIALE nel formato: gg/mm/yyyy");
				// controlla l'esito
				if (dataInizio == null) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				dataFine = Funzioni.chiediData("Inserisci la DATA di SCADENZA nel formato: gg/mm/yyyy");

				// controlla l'esito
				if (dataFine == null) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				if (dataInizio.after(dataFine)) {
					JOptionPane.showMessageDialog(null, "Data di inizio e data di fine periodo incompatibili.",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					break;
				}
				JOptionPane.showMessageDialog(null, g.getElencoComputerDisponibili(dataInizio, dataFine),
						"Veicoli disponibili", JOptionPane.PLAIN_MESSAGE);
				break;
			case 5:
				JOptionPane.showMessageDialog(null, g.getElencoNoleggiAttivi(), "Noleggi in corso",
						JOptionPane.PLAIN_MESSAGE);
				//mostraNoleggi(g, "noleggio_computer.csv");

				break;

			case 6:
				pc = Funzioni.chiediComputerB();
				if (pc != null) {
					g.aggiungiComputer(pc);
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				break;
			case 7:
				pc = Funzioni.chiediComputerM();
				if (pc != null) {
					g.aggiungiComputer(pc);
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				break;
			case 8:
				pc = Funzioni.chiediComputerA();
				if (pc != null) {
					g.aggiungiComputer(pc);
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				break;
			case 9:
				codPc = Funzioni.chiediCodiceComputer(g);
				// controlla l'esito
				if (codPc == -1) {
					JOptionPane.showMessageDialog(null, "Operazione annullata", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				} else if (codPc == -2) {
					JOptionPane.showMessageDialog(null, "Codice insesistente", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
					break;
				}
				esito = g.cancellaComputer(codPc);

				// controlla l'esito
				if (esito != null)
					JOptionPane.showMessageDialog(null, esito, "Attenzione", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				break;
			case 10:
				try {
					g.salvaSuFile();
					JOptionPane.showMessageDialog(null, "Operazione eseguita", "Messaggio",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Operazione di salvataggio del file non riuscita", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
				}
				break;

			case 11:
				try {
					g.importaComputerDaCsv("noleggio_computer.csv");
					JOptionPane.showMessageDialog(null, "Importazione eseguita correttamente", "Attenzione",
							JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Importazione non riuscita", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
				}

				break;
			case 12:
				try {
					g.esportaComputerInCsv("noleggio_computer.csv");
					JOptionPane.showMessageDialog(null, "Esportazione eseguita correttamente", "Attenzione",
							JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Esportazione non riuscita", "Attenzione",
							JOptionPane.WARNING_MESSAGE);
				}
				break;

			default: // OPZIONE NON PREVISTA
				JOptionPane.showMessageDialog(null, "Scelta non prevista", "Attenzione", JOptionPane.WARNING_MESSAGE);
				break;
			case 0: // ESCE
				try {
					g.salvaSuFile();
				} catch (Exception e) {
					int risposta = JOptionPane.showConfirmDialog(null,
							"Salvataggio su file non riuscito. Uscire lo stesso?\n"
									+ "Attenzione, se confermi perderai tutti i dati che hai inserito dall'ultimo salvataggio.",
							"Errore", JOptionPane.WARNING_MESSAGE);
					if (risposta == JOptionPane.OK_OPTION)
						return;
				}
				break;
			}
		} while (scelta != 0);
	}

//	private static void mostraNoleggi(Gestione g, String file) throws IOException {
//		List<Noleggio> noleggi = g.getNoleggiAttivi();
//		try (FileWriter fw = new FileWriter(file, true);
//				BufferedWriter bw = new BufferedWriter(fw);
//				PrintWriter out = new PrintWriter(bw)) {
//			for (Noleggio n : noleggi) {
//				out.print(n.getComputer().getModello() + ";");
//				out.print(n.getComputer().getPrezzo() + ";");
//				out.print(n.getCliente().getCodFiscale() + ";");
//				out.println(n.getCliente().getNominativo() + ";");
//				out.println(n.getCliente().getTelefono());
//			}
//		}
//	}
}