package com.dmjm.model;

public class VoboMolienda implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idVobo;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private int idUsuarioUno;
	private String voboUno;
	private int idUsuarioDos;
	private String voboDos;
	private int idUsuarioTres;
	private String voboTres;

	public VoboMolienda() {
	}

	public VoboMolienda(int idVobo) {
		this.idVobo = idVobo;
	}
	
	
	

	public VoboMolienda(int idVobo, FolioPreparacionMolienda folioPreparacionMolienda, int idUsuarioUno,
			String voboUno, int idUsuarioDos, String voboDos, int idUsuarioTres, String voboTres) {
		this.idVobo = idVobo;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.idUsuarioUno = idUsuarioUno;
		this.voboUno = voboUno;
		this.idUsuarioDos = idUsuarioDos;
		this.voboDos = voboDos;
		this.idUsuarioTres = idUsuarioTres;
		this.voboTres = voboTres;
	}

	public int getIdVobo() {
		return idVobo;
	}

	public void setIdVobo(int idVobo) {
		this.idVobo = idVobo;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public int getIdUsuarioUno() {
		return idUsuarioUno;
	}

	public void setIdUsuarioUno(int idUsuarioUno) {
		this.idUsuarioUno = idUsuarioUno;
	}

	public String getVoboUno() {
		return voboUno;
	}

	public void setVoboUno(String voboUno) {
		this.voboUno = voboUno;
	}

	public int getIdUsuarioDos() {
		return idUsuarioDos;
	}

	public void setIdUsuarioDos(int idUsuarioDos) {
		this.idUsuarioDos = idUsuarioDos;
	}

	public String getVoboDos() {
		return voboDos;
	}

	public void setVoboDos(String voboDos) {
		this.voboDos = voboDos;
	}

	public int getIdUsuarioTres() {
		return idUsuarioTres;
	}

	public void setIdUsuarioTres(int idUsuarioTres) {
		this.idUsuarioTres = idUsuarioTres;
	}

	public String getVoboTres() {
		return voboTres;
	}

	public void setVoboTres(String voboTres) {
		this.voboTres = voboTres;
	}

}
