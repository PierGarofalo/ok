package it.studenti.uniba.entity;

import java.io.Serializable;

public class Computer implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributi

	private int codPc;
	private String schedaGrafica;
	private String cpu;
	private String modello;
	private String doppiaCam;
	private String touchId;
	private String vocalId;
	private int prezzo;
	private boolean prenotato; // TRUE se il veicolo risulta prenotato, FALSE altrimenti

	
	// Metodi

	/**
	 * Crea un computer con un codice identificativo fittizio uguale a zero.
	 * 
	 * @param targa   targa del veicolo
	 * @param marca   nome della casa costruttrice del veicolo
	 * @param modello modello del veicolo
	 * @param nPosti  numeri di posti del veicolo
	 */
	// base
	public Computer(String cpu, String schedaGrafica) {

		prezzo = 8;
		this.cpu = cpu;
		this.schedaGrafica = schedaGrafica;
		modello = "base";
		prenotato = false;
	}

	// medio
	public Computer(String cpu, String schedaGrafica, String doppiaCam) {

		prezzo = 15;
		this.cpu = cpu;
		this.schedaGrafica = schedaGrafica;
		modello = "medio";
		this.doppiaCam = doppiaCam;
		prenotato = false;
	}

	// avanzato
	public Computer(String cpu, String schedaGrafica, String doppiaCam, String touchId, String vocalId) {

		prezzo = 20;
		this.cpu = cpu;
		this.schedaGrafica = schedaGrafica;
		modello = "avanzato";
		this.doppiaCam = doppiaCam;
		this.touchId = touchId;
		this.vocalId = vocalId;
		prenotato = false;
	}

	/**
	 * Crea un veicolo con codice identificativo fittizio uguale a zero a partire da
	 * un vettore di stringhe con i dati.
	 * 
	 * @param dati vettore di stringhe con i dati del veicolo
	 */
	public Computer(String dati[]) {
		int i = 0;
		codPc = i++;
		try {
			modello = dati[1];
			cpu = dati[2];
			schedaGrafica = dati[3];
			doppiaCam = dati[4];
			touchId = dati[5];
			vocalId = dati[6];
			prezzo = Integer.parseInt(dati[7]);
			prenotato = Boolean.parseBoolean(dati[8]);
		} catch (NumberFormatException e) {
			modello.isEmpty();
			schedaGrafica.isEmpty();
			cpu.isEmpty();

		}
	}

	/**
	 * Imposta il codice identificativo del veicolo.
	 * 
	 * @param codVeicolo codice identificativo
	 */
	public void setCodPc(int codPc) {
		this.codPc = codPc;
	}

	/**
	 * Imposta lo stato del noleggio del veicolo
	 * 
	 * @param prenotato <strong>true</strong> se il veicolo viene prenotato,
	 *                  <strong>false</strong> se il veicolo diventa disponibile per
	 *                  il noleggio.
	 */
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}

	/**
	 * Fornisce il codice identificativo del veicolo.
	 * 
	 * @return codice identificativo
	 */
	public int getCodPc() {
		return codPc;
	}

	/**
	 * Fornisce la targa del veicolo.
	 * 
	 * @return la targa
	 */
	public String getModello() {
		return modello;
	}

	/**
	 * Fornisce la marca della casa costruttrice del veicolo.
	 * 
	 * @return la marca
	 */
	public String getCpu() {
		return cpu;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Fornisce il modello del veicolo.
	 * 
	 * @return il modello
	 */
	public String getSchedaGrafica() {
		return schedaGrafica;
	}

	public String getDoppiaCam() {
		return doppiaCam;
	}

	public void setDoppiaCam(String doppiaCam) {
		this.doppiaCam = doppiaCam;
	}

	public String getTouchId() {
		return touchId;
	}

	public void setTouchId(String touchId) {
		this.touchId = touchId;
	}

	public String getVocalId() {
		return vocalId;
	}

	public void setVocalId(String vocalId) {
		this.vocalId = vocalId;
	}

	/**
	 * Verifica se il veicolo risulta o meno prenotato.
	 * 
	 * @return <strong>true</strong> se il veicolo risulta prenotato,
	 *         <strong>false</strong> altrimenti
	 */
	public boolean isPrenotato() {
		return prenotato;
	}
	
	/**
	 * Metodo statico che restituisce una stringa con l'intestazione del file CSV
	 * con cui esportare la tabella dei veicoli.
	 * 
	 * @return stringa dell'intestazione del file CSV
	 */
	public static String getIntestazioniCSV() {
		return "NumeroSeriale;Modello;CPU;SchedaGrafica;Noleggiato;doppiaCamera;touchId;riconoscimentoVocale;prezzo";
	}

	/**
	 * Fornisce i dati del veicolo sotto forma di una stringa di testo secondo il
	 * formato di un file CSV con il punto e virgola come delimitatore.
	 * 
	 * @return stringa con i dati del veicolo separati con punti e virgola
	 */
	public String getComputerToRigaCsv() {
		return Integer.toHexString(codPc) + ";" + modello + ";" + cpu + ";" + schedaGrafica + ";" + prenotato + ";"
				+ doppiaCam + ";" + touchId + ";" + vocalId + ";" + prezzo;
	}

	@Override
	public String toString() {
		return "[numero seriale : " + Integer.toHexString(codPc) + "] " + modello + " - " + " cpu : " + cpu
				+ " - scheda grafica : " + schedaGrafica + " - " + prezzo + "$ - " + "doppia fotocamera : " + doppiaCam
				+ " - touchId : " + touchId + " - vocalId : " + vocalId;
	}

}
