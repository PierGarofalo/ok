package it.studenti.uniba.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Registra i dati della prenotazione di un noleggio di un {@link Veicolo} da
 * parte di un {@link Cliente}.
 * 
 * @author Luigi Menchise (www.labsquare.it)
 * @version 1.0
 */
public class Noleggio implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributi
	private int codNoleggio;
	private int codPc; // codice del veicolo noleggiato
	private Cliente cliente; // riferimento al cliente che noleggia il veicolo
	private Date dataInizio;
	private Date dataFine;
	private int penalty = 5;
	private Computer computer;
	public Date dataRestituzione;

	// Metodi

	public Noleggio(int codNoleggio, int codPc, Cliente cliente, Date dataInizio, Date dataFine) {
		this.codNoleggio = codNoleggio;
		this.codPc = codPc;
		this.cliente = cliente;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public Noleggio(int codNoleggio, int codPc, Cliente cliente, Date dataInizio, Date dataFine,
			Date dataRestituzione) {
		this.codNoleggio = codNoleggio;
		this.codPc = codPc;
		this.cliente = cliente;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.dataRestituzione = dataRestituzione;
	}

	public int getCodPc() {
		return codPc;
	}

	public int getCodNoleggio() {
		return codNoleggio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Computer getComputer() {
		return computer;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public Date getDataRestituzione() {
		return dataRestituzione;
	}

	public int calculatePenality(Date returnDate, Date endDate) {
		if (returnDate.after(endDate)) {
			int daysLate = endDate.compareTo(returnDate);
			return daysLate * penalty;
		}
		return 0;
	}

}