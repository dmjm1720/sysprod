package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class EsterilizadorPlantaA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idEsterilizdor;
	private FolioPreparacionEstA folioPreparacionEstA;
	private String hora;
	private String operacion;
	private BigDecimal porcentaje;
	private BigDecimal presionVapor;
	private BigDecimal presionVacio;
	private BigDecimal precalentador;
	private BigDecimal esterilizador;
	private BigDecimal valvulaDiversora;
	private Integer bombaAlim;
	private BigDecimal tiempoEst;
	private BigDecimal bombaSalida;
	private Integer flujoLitrosHora;
	private BigDecimal presionSistema;
	private Integer redoxPpmEntrada;
	private Integer redoxPpmSalida;
	private Integer folioEsterilizador;
	private Date fecha;

	public EsterilizadorPlantaA() {
	}

	public EsterilizadorPlantaA(int idEsterilizdor) {
		this.idEsterilizdor = idEsterilizdor;
	}

	public EsterilizadorPlantaA(int idEsterilizdor, FolioPreparacionEstA folioPreparacionEstA, String hora,
			String operacion, BigDecimal porcentaje, BigDecimal presionVapor, BigDecimal presionVacio,
			BigDecimal precalentador, BigDecimal esterilizador, BigDecimal valvulaDiversora, Integer bombaAlim,
			BigDecimal tiempoEst, BigDecimal bombaSalida, Integer flujoLitrosHora, BigDecimal presionSistema,
			Integer redoxPpmEntrada, Integer redoxPpmSalida, Integer folioEsterilizador, Date fecha) {
		this.idEsterilizdor = idEsterilizdor;
		this.folioPreparacionEstA = folioPreparacionEstA;
		this.hora = hora;
		this.operacion = operacion;
		this.porcentaje = porcentaje;
		this.presionVapor = presionVapor;
		this.presionVacio = presionVacio;
		this.precalentador = precalentador;
		this.esterilizador = esterilizador;
		this.valvulaDiversora = valvulaDiversora;
		this.bombaAlim = bombaAlim;
		this.tiempoEst = tiempoEst;
		this.bombaSalida = bombaSalida;
		this.flujoLitrosHora = flujoLitrosHora;
		this.presionSistema = presionSistema;
		this.redoxPpmEntrada = redoxPpmEntrada;
		this.redoxPpmSalida = redoxPpmSalida;
		this.folioEsterilizador = folioEsterilizador;
		this.fecha = fecha;
	}

	public int getIdEsterilizdor() {
		return this.idEsterilizdor;
	}

	public void setIdEsterilizdor(int idEsterilizdor) {
		this.idEsterilizdor = idEsterilizdor;
	}

	public FolioPreparacionEstA getFolioPreparacionEstA() {
		return this.folioPreparacionEstA;
	}

	public void setFolioPreparacionEstA(FolioPreparacionEstA folioPreparacionEstA) {
		this.folioPreparacionEstA = folioPreparacionEstA;
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

	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getPresionVapor() {
		return this.presionVapor;
	}

	public void setPresionVapor(BigDecimal presionVapor) {
		this.presionVapor = presionVapor;
	}

	public BigDecimal getPresionVacio() {
		return this.presionVacio;
	}

	public void setPresionVacio(BigDecimal presionVacio) {
		this.presionVacio = presionVacio;
	}

	public BigDecimal getPrecalentador() {
		return this.precalentador;
	}

	public void setPrecalentador(BigDecimal precalentador) {
		this.precalentador = precalentador;
	}

	public BigDecimal getEsterilizador() {
		return this.esterilizador;
	}

	public void setEsterilizador(BigDecimal esterilizador) {
		this.esterilizador = esterilizador;
	}

	public BigDecimal getValvulaDiversora() {
		return this.valvulaDiversora;
	}

	public void setValvulaDiversora(BigDecimal valvulaDiversora) {
		this.valvulaDiversora = valvulaDiversora;
	}

	public Integer getBombaAlim() {
		return this.bombaAlim;
	}

	public void setBombaAlim(Integer bombaAlim) {
		this.bombaAlim = bombaAlim;
	}

	public BigDecimal getTiempoEst() {
		return this.tiempoEst;
	}

	public void setTiempoEst(BigDecimal tiempoEst) {
		this.tiempoEst = tiempoEst;
	}

	public BigDecimal getBombaSalida() {
		return this.bombaSalida;
	}

	public void setBombaSalida(BigDecimal bombaSalida) {
		this.bombaSalida = bombaSalida;
	}

	public Integer getFlujoLitrosHora() {
		return this.flujoLitrosHora;
	}

	public void setFlujoLitrosHora(Integer flujoLitrosHora) {
		this.flujoLitrosHora = flujoLitrosHora;
	}

	public BigDecimal getPresionSistema() {
		return this.presionSistema;
	}

	public void setPresionSistema(BigDecimal presionSistema) {
		this.presionSistema = presionSistema;
	}

	public Integer getRedoxPpmEntrada() {
		return this.redoxPpmEntrada;
	}

	public void setRedoxPpmEntrada(Integer redoxPpmEntrada) {
		this.redoxPpmEntrada = redoxPpmEntrada;
	}

	public Integer getRedoxPpmSalida() {
		return this.redoxPpmSalida;
	}

	public void setRedoxPpmSalida(Integer redoxPpmSalida) {
		this.redoxPpmSalida = redoxPpmSalida;
	}

	public Integer getFolioEsterilizador() {
		return folioEsterilizador;
	}

	public void setFolioEsterilizador(Integer folioEsterilizador) {
		this.folioEsterilizador = folioEsterilizador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
