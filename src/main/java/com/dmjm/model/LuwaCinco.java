package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class LuwaCinco implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idLuwa;
	private FolioPreparacionLuwaCinco folioPreparacionLuwaCinco;
	private String hora;
	private String operacion;
	private BigDecimal entradaConc;
	private BigDecimal concPorcentaje;
	private BigDecimal ph;
	private Integer tempSalida;
	private Integer redox;
	private Integer presionBombaVacio;
	private BigDecimal valvulaRegPresion;
	private Integer condesadorTemp;
	private BigDecimal corrienteMotor;
	private String estadoA;
	private String estadoR;
	private Integer folioLuwa;
	private Date fecha;
	private Boolean estadoAR;
	private Boolean estadoManto;
	private Boolean estadoLimpieza;

	public LuwaCinco() {
	}

	public LuwaCinco(int idLuwa) {
		this.idLuwa = idLuwa;
	}

	public LuwaCinco(int idLuwa, FolioPreparacionLuwaCinco folioPreparacionLuwaCinco, String hora, String operacion,
			BigDecimal entradaConc, BigDecimal concPorcentaje, BigDecimal ph, Integer tempSalida, Integer redox,
			Integer presionBombaVacio, BigDecimal valvulaRegPresion, Integer condesadorTemp, BigDecimal corrienteMotor,
			String estadoA, String estadoR, Integer folioLuwa, Date fecha, Boolean estadoAR, Boolean estadoManto,
			Boolean estadoLimpieza) {
		this.idLuwa = idLuwa;
		this.folioPreparacionLuwaCinco = folioPreparacionLuwaCinco;
		this.hora = hora;
		this.operacion = operacion;
		this.entradaConc = entradaConc;
		this.concPorcentaje = concPorcentaje;
		this.ph = ph;
		this.tempSalida = tempSalida;
		this.redox = redox;
		this.presionBombaVacio = presionBombaVacio;
		this.valvulaRegPresion = valvulaRegPresion;
		this.condesadorTemp = condesadorTemp;
		this.corrienteMotor = corrienteMotor;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.folioLuwa = folioLuwa;
		this.fecha = fecha;
		this.estadoAR = estadoAR;
		this.estadoManto = estadoManto;
		this.estadoLimpieza = estadoLimpieza;
	}

	public int getIdLuwa() {
		return this.idLuwa;
	}

	public void setIdLuwa(int idLuwa) {
		this.idLuwa = idLuwa;
	}

	public FolioPreparacionLuwaCinco getFolioPreparacionLuwaCinco() {
		return this.folioPreparacionLuwaCinco;
	}

	public void setFolioPreparacionLuwaCinco(FolioPreparacionLuwaCinco folioPreparacionLuwaCinco) {
		this.folioPreparacionLuwaCinco = folioPreparacionLuwaCinco;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getEntradaConc() {
		return this.entradaConc;
	}

	public void setEntradaConc(BigDecimal entradaConc) {
		this.entradaConc = entradaConc;
	}

	public BigDecimal getConcPorcentaje() {
		return this.concPorcentaje;
	}

	public void setConcPorcentaje(BigDecimal concPorcentaje) {
		this.concPorcentaje = concPorcentaje;
	}

	public BigDecimal getPh() {
		return this.ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public Integer getTempSalida() {
		return this.tempSalida;
	}

	public void setTempSalida(Integer tempSalida) {
		this.tempSalida = tempSalida;
	}

	public Integer getRedox() {
		return this.redox;
	}

	public void setRedox(Integer redox) {
		this.redox = redox;
	}

	public Integer getPresionBombaVacio() {
		return this.presionBombaVacio;
	}

	public void setPresionBombaVacio(Integer presionBombaVacio) {
		this.presionBombaVacio = presionBombaVacio;
	}

	public BigDecimal getValvulaRegPresion() {
		return this.valvulaRegPresion;
	}

	public void setValvulaRegPresion(BigDecimal valvulaRegPresion) {
		this.valvulaRegPresion = valvulaRegPresion;
	}

	public Integer getCondesadorTemp() {
		return this.condesadorTemp;
	}

	public void setCondesadorTemp(Integer condesadorTemp) {
		this.condesadorTemp = condesadorTemp;
	}

	public BigDecimal getCorrienteMotor() {
		return this.corrienteMotor;
	}

	public void setCorrienteMotor(BigDecimal corrienteMotor) {
		this.corrienteMotor = corrienteMotor;
	}

	public String getEstadoA() {
		return this.estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoR() {
		return this.estadoR;
	}

	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}

	public Integer getFolioLuwa() {
		return this.folioLuwa;
	}

	public void setFolioLuwa(Integer folioLuwa) {
		this.folioLuwa = folioLuwa;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstadoAR() {
		return this.estadoAR;
	}

	public void setEstadoAR(Boolean estadoAR) {
		this.estadoAR = estadoAR;
	}

	public Boolean getEstadoManto() {
		return this.estadoManto;
	}

	public void setEstadoManto(Boolean estadoManto) {
		this.estadoManto = estadoManto;
	}

	public Boolean getEstadoLimpieza() {
		return this.estadoLimpieza;
	}

	public void setEstadoLimpieza(Boolean estadoLimpieza) {
		this.estadoLimpieza = estadoLimpieza;
	}

}
