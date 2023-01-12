package it.studenti.uniba.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Un cliente che effettua un {@link Noleggio}.
 * 
 * @author Pierfederico Garofalo
 * @version 1.0
 */
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributi

	private int codId;
	private String codFiscale;
	private String nominativo;
	private String telefono;

	// Metodi
	

	public int getCodId() {
		return codId;
	}

	
	public void setCodId(int codId) {
		this.codId = codId;
	}

	public Cliente(String nominativo, String telefono, String codFiscale) {
		this.codFiscale = codFiscale;
		this.nominativo = nominativo;
		this.telefono = telefono;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public String getNominativo() {
		return nominativo;
	}

	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo statico che restituisce una stringa con l'intestazione del file CSV
	 * con cui esportare la tabella dei veicoli.
	 * 
	 * @return stringa dell'intestazione del file CSV
	 */
	public static String getIntestazioniClienteCSV() {
		return ";CodiceFiscale;Nominativo;telefono";
	}

	/**
	 * Fornisce i dati del veicolo sotto forma di una stringa di testo secondo il
	 * formato di un file CSV con il punto e virgola come delimitatore.
	 * 
	 * @return stringa con i dati del veicolo separati con punti e virgola
	 */
	public String getClienteToRigaCsv() {
		return ";" + codFiscale + ";" + nominativo + ";" + telefono;
	}
	
	@Override
	public String toString() {
		return codFiscale + " - " + nominativo + " - " + "tel " + telefono;
	}
}
