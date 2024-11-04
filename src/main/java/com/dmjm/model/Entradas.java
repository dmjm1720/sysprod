package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class Entradas implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idEntrada;
	private Materia materia;
	private Preservacion preservacion;
	private Proveedores proveedores;
	private Transportista transportista;
	private Integer tolvas;
	private Date fechaRecepcion;
	private String sucursal;
	private Date fechaRecepcionToluca;
	private String ticketBasculaToluca;
	private String ticketBasculaLeon;
	private String factura;
	private String certificado;
	private BigDecimal kgEmbarcados;
	private BigDecimal kgNetos;
	private BigDecimal kgBascula;
	private BigDecimal kgBasculaMerma;
	private BigDecimal kgRecibidos;
	private BigDecimal kgEnvioLeon;
	private String palletsRecibidos;
	private BigDecimal carnazaConPelo;
	private BigDecimal carnzaPrimera;
	private BigDecimal carnzaSegunda;
	private BigDecimal desbarbeRecorte;
	private BigDecimal cerdoMexicano;
	private BigDecimal orejaCachete;
	private BigDecimal garra;
	private BigDecimal pedaceria;
	private BigDecimal cueroDepiladoIntegral;
	private BigDecimal pedaceriaConPelo;
	private BigDecimal total;
	private BigDecimal merma;
	private BigDecimal alcalinidad;
	private BigDecimal humedad;
	private String coordinadorProduccion;
	private String controlCalidad;
	private String gerenciaProduccion;
	private Date fechaLiberacion;
	private Integer bloqueoEditar;
	private Integer estado;
	private BigDecimal carnazaSalada;
	private BigDecimal descarneAdherido;
	private BigDecimal descarneSeparado;
	private BigDecimal precioCcp;
	private BigDecimal precioC1;
	private BigDecimal precioC2;
	private BigDecimal precioCs;
	private BigDecimal precioDr;
	private BigDecimal precioCm;
	private BigDecimal precioCo;
	private BigDecimal precioPc;
	private BigDecimal precioP;
	private BigDecimal precioDa;
	private BigDecimal precioDs;
	private BigDecimal precioCdi;
	private BigDecimal precioG;
	private BigDecimal precioCe;
	private BigDecimal cueroEnSangre;
	private BigDecimal calculoPorcentajeMerma;
	private BigDecimal descuentoHumedadTa;
	private BigDecimal descuentoHumedadTb;
	private BigDecimal descuentoCalcioTa;
	private BigDecimal descuentoCalcioTb;
	private BigDecimal calculoKgMerma;
	private BigDecimal descuentoHumedad;
	private BigDecimal descuentoCalcio;
	private Date fechaImpresionContador;
	private BigDecimal porcentajeMerma;

	public Entradas() {
	}

	public Entradas(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Entradas(int idEntrada, Materia materia, Preservacion preservacion, Proveedores proveedores,
			Transportista transportista, Integer tolvas, Date fechaRecepcion, String sucursal,
			Date fechaRecepcionToluca, String ticketBasculaToluca, String ticketBasculaLeon, String factura,
			String certificado, BigDecimal kgEmbarcados, BigDecimal kgNetos, BigDecimal kgBascula,
			BigDecimal kgBasculaMerma, BigDecimal kgRecibidos, BigDecimal kgEnvioLeon, String palletsRecibidos,
			BigDecimal carnazaConPelo, BigDecimal carnzaPrimera, BigDecimal carnzaSegunda, BigDecimal desbarbeRecorte,
			BigDecimal cerdoMexicano, BigDecimal orejaCachete, BigDecimal garra, BigDecimal pedaceria,
			BigDecimal cueroDepiladoIntegral, BigDecimal pedaceriaConPelo, BigDecimal total, BigDecimal merma,
			BigDecimal alcalinidad, BigDecimal humedad, String coordinadorProduccion, String controlCalidad,
			String gerenciaProduccion, Date fechaLiberacion, Integer bloqueoEditar, Integer estado,
			BigDecimal carnazaSalada, BigDecimal descarneAdherido, BigDecimal descarneSeparado, BigDecimal precioCcp,
			BigDecimal precioC1, BigDecimal precioC2, BigDecimal precioCs, BigDecimal precioDr, BigDecimal precioCm,
			BigDecimal precioCo, BigDecimal precioPc, BigDecimal precioP, BigDecimal precioDa, BigDecimal precioDs,
			BigDecimal precioCdi, BigDecimal precioG, BigDecimal precioCe, BigDecimal cueroEnSangre,
			BigDecimal calculoPorcentajeMerma, BigDecimal descuentoHumedadTa, BigDecimal descuentoHumedadTb,
			BigDecimal descuentoCalcioTa, BigDecimal descuentoCalcioTb, BigDecimal calculoKgMerma,
			BigDecimal descuentoHumedad, BigDecimal descuentoCalcio, Date fechaImpresionContador, BigDecimal porcentajeMerma) {

		this.idEntrada = idEntrada;
		this.materia = materia;
		this.preservacion = preservacion;
		this.proveedores = proveedores;
		this.transportista = transportista;
		this.tolvas = tolvas;
		this.fechaRecepcion = fechaRecepcion;
		this.sucursal = sucursal;
		this.fechaRecepcionToluca = fechaRecepcionToluca;
		this.ticketBasculaToluca = ticketBasculaToluca;
		this.ticketBasculaLeon = ticketBasculaLeon;
		this.factura = factura;
		this.certificado = certificado;
		this.kgEmbarcados = kgEmbarcados;
		this.kgNetos = kgNetos;
		this.kgBascula = kgBascula;
		this.kgBasculaMerma = kgBasculaMerma;
		this.kgRecibidos = kgRecibidos;
		this.kgEnvioLeon = kgEnvioLeon;
		this.palletsRecibidos = palletsRecibidos;
		this.carnazaConPelo = carnazaConPelo;
		this.carnzaPrimera = carnzaPrimera;
		this.carnzaSegunda = carnzaSegunda;
		this.desbarbeRecorte = desbarbeRecorte;
		this.cerdoMexicano = cerdoMexicano;
		this.orejaCachete = orejaCachete;
		this.garra = garra;
		this.pedaceria = pedaceria;
		this.cueroDepiladoIntegral = cueroDepiladoIntegral;
		this.pedaceriaConPelo = pedaceriaConPelo;
		this.total = total;
		this.merma = merma;
		this.alcalinidad = alcalinidad;
		this.humedad = humedad;
		this.coordinadorProduccion = coordinadorProduccion;
		this.controlCalidad = controlCalidad;
		this.gerenciaProduccion = gerenciaProduccion;
		this.fechaLiberacion = fechaLiberacion;
		this.bloqueoEditar = bloqueoEditar;
		this.estado = estado;
		this.carnazaSalada = carnazaSalada;
		this.descarneAdherido = descarneAdherido;
		this.descarneSeparado = descarneSeparado;
		this.precioCcp = precioCcp;
		this.precioC1 = precioC1;
		this.precioC2 = precioC2;
		this.precioCs = precioCs;
		this.precioDr = precioDr;
		this.precioCm = precioCm;
		this.precioCo = precioCo;
		this.precioPc = precioPc;
		this.precioP = precioP;
		this.precioDa = precioDa;
		this.precioDs = precioDs;
		this.precioCdi = precioCdi;
		this.precioG = precioG;
		this.precioCe = precioCe;
		this.cueroEnSangre = cueroEnSangre;
		this.calculoPorcentajeMerma = calculoPorcentajeMerma;
		this.descuentoHumedadTa = descuentoHumedadTa;
		this.descuentoHumedadTb = descuentoHumedadTb;
		this.descuentoCalcioTa = descuentoCalcioTa;
		this.descuentoCalcioTb = descuentoCalcioTb;
		this.calculoKgMerma = calculoKgMerma;
		this.descuentoHumedad = descuentoHumedad;
		this.descuentoCalcio = descuentoCalcio;
		this.fechaImpresionContador = fechaImpresionContador;
		this.porcentajeMerma = porcentajeMerma;
	}

	public int getIdEntrada() {
		return this.idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Preservacion getPreservacion() {
		return this.preservacion;
	}

	public void setPreservacion(Preservacion preservacion) {
		this.preservacion = preservacion;
	}

	public Proveedores getProveedores() {
		return this.proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public Transportista getTransportista() {
		return this.transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Integer getTolvas() {
		return this.tolvas;
	}

	public void setTolvas(Integer tolvas) {
		this.tolvas = tolvas;
	}

	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFechaRecepcionToluca() {
		return this.fechaRecepcionToluca;
	}

	public void setFechaRecepcionToluca(Date fechaRecepcionToluca) {
		this.fechaRecepcionToluca = fechaRecepcionToluca;
	}

	public String getTicketBasculaToluca() {
		return this.ticketBasculaToluca;
	}

	public void setTicketBasculaToluca(String ticketBasculaToluca) {
		this.ticketBasculaToluca = ticketBasculaToluca;
	}

	public String getTicketBasculaLeon() {
		return this.ticketBasculaLeon;
	}

	public void setTicketBasculaLeon(String ticketBasculaLeon) {
		this.ticketBasculaLeon = ticketBasculaLeon;
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getCertificado() {
		return this.certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public BigDecimal getKgEmbarcados() {
		return this.kgEmbarcados;
	}

	public void setKgEmbarcados(BigDecimal kgEmbarcados) {
		this.kgEmbarcados = kgEmbarcados;
	}

	public BigDecimal getKgNetos() {
		return this.kgNetos;
	}

	public void setKgNetos(BigDecimal kgNetos) {
		this.kgNetos = kgNetos;
	}

	public BigDecimal getKgBascula() {
		return this.kgBascula;
	}

	public void setKgBascula(BigDecimal kgBascula) {
		this.kgBascula = kgBascula;
	}

	public BigDecimal getKgBasculaMerma() {
		return this.kgBasculaMerma;
	}

	public void setKgBasculaMerma(BigDecimal kgBasculaMerma) {
		this.kgBasculaMerma = kgBasculaMerma;
	}

	public BigDecimal getKgRecibidos() {
		return this.kgRecibidos;
	}

	public void setKgRecibidos(BigDecimal kgRecibidos) {
		this.kgRecibidos = kgRecibidos;
	}

	public BigDecimal getKgEnvioLeon() {
		return this.kgEnvioLeon;
	}

	public void setKgEnvioLeon(BigDecimal kgEnvioLeon) {
		this.kgEnvioLeon = kgEnvioLeon;
	}

	public String getPalletsRecibidos() {
		return this.palletsRecibidos;
	}

	public void setPalletsRecibidos(String palletsRecibidos) {
		this.palletsRecibidos = palletsRecibidos;
	}

	public BigDecimal getCarnazaConPelo() {
		return this.carnazaConPelo;
	}

	public void setCarnazaConPelo(BigDecimal carnazaConPelo) {
		this.carnazaConPelo = carnazaConPelo;
	}

	public BigDecimal getCarnzaPrimera() {
		return this.carnzaPrimera;
	}

	public void setCarnzaPrimera(BigDecimal carnzaPrimera) {
		this.carnzaPrimera = carnzaPrimera;
	}

	public BigDecimal getCarnzaSegunda() {
		return this.carnzaSegunda;
	}

	public void setCarnzaSegunda(BigDecimal carnzaSegunda) {
		this.carnzaSegunda = carnzaSegunda;
	}

	public BigDecimal getDesbarbeRecorte() {
		return this.desbarbeRecorte;
	}

	public void setDesbarbeRecorte(BigDecimal desbarbeRecorte) {
		this.desbarbeRecorte = desbarbeRecorte;
	}

	public BigDecimal getCerdoMexicano() {
		return this.cerdoMexicano;
	}

	public void setCerdoMexicano(BigDecimal cerdoMexicano) {
		this.cerdoMexicano = cerdoMexicano;
	}

	public BigDecimal getOrejaCachete() {
		return this.orejaCachete;
	}

	public void setOrejaCachete(BigDecimal orejaCachete) {
		this.orejaCachete = orejaCachete;
	}

	public BigDecimal getGarra() {
		return this.garra;
	}

	public void setGarra(BigDecimal garra) {
		this.garra = garra;
	}

	public BigDecimal getPedaceria() {
		return this.pedaceria;
	}

	public void setPedaceria(BigDecimal pedaceria) {
		this.pedaceria = pedaceria;
	}

	public BigDecimal getCueroDepiladoIntegral() {
		return this.cueroDepiladoIntegral;
	}

	public void setCueroDepiladoIntegral(BigDecimal cueroDepiladoIntegral) {
		this.cueroDepiladoIntegral = cueroDepiladoIntegral;
	}

	public BigDecimal getPedaceriaConPelo() {
		return this.pedaceriaConPelo;
	}

	public void setPedaceriaConPelo(BigDecimal pedaceriaConPelo) {
		this.pedaceriaConPelo = pedaceriaConPelo;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMerma() {
		return this.merma;
	}

	public void setMerma(BigDecimal merma) {
		this.merma = merma;
	}

	public BigDecimal getAlcalinidad() {
		return this.alcalinidad;
	}

	public void setAlcalinidad(BigDecimal alcalinidad) {
		this.alcalinidad = alcalinidad;
	}

	public BigDecimal getHumedad() {
		return this.humedad;
	}

	public void setHumedad(BigDecimal humedad) {
		this.humedad = humedad;
	}

	public String getCoordinadorProduccion() {
		return this.coordinadorProduccion;
	}

	public void setCoordinadorProduccion(String coordinadorProduccion) {
		this.coordinadorProduccion = coordinadorProduccion;
	}

	public String getControlCalidad() {
		return this.controlCalidad;
	}

	public void setControlCalidad(String controlCalidad) {
		this.controlCalidad = controlCalidad;
	}

	public String getGerenciaProduccion() {
		return this.gerenciaProduccion;
	}

	public void setGerenciaProduccion(String gerenciaProduccion) {
		this.gerenciaProduccion = gerenciaProduccion;
	}

	public Date getFechaLiberacion() {
		return this.fechaLiberacion;
	}

	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	public Integer getBloqueoEditar() {
		return this.bloqueoEditar;
	}

	public void setBloqueoEditar(Integer bloqueoEditar) {
		this.bloqueoEditar = bloqueoEditar;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public BigDecimal getCarnazaSalada() {
		return this.carnazaSalada;
	}

	public void setCarnazaSalada(BigDecimal carnazaSalada) {
		this.carnazaSalada = carnazaSalada;
	}

	public BigDecimal getDescarneAdherido() {
		return this.descarneAdherido;
	}

	public void setDescarneAdherido(BigDecimal descarneAdherido) {
		this.descarneAdherido = descarneAdherido;
	}

	public BigDecimal getDescarneSeparado() {
		return this.descarneSeparado;
	}

	public void setDescarneSeparado(BigDecimal descarneSeparado) {
		this.descarneSeparado = descarneSeparado;
	}

	public BigDecimal getPrecioCcp() {
		return this.precioCcp;
	}

	public void setPrecioCcp(BigDecimal precioCcp) {
		this.precioCcp = precioCcp;
	}

	public BigDecimal getPrecioC1() {
		return this.precioC1;
	}

	public void setPrecioC1(BigDecimal precioC1) {
		this.precioC1 = precioC1;
	}

	public BigDecimal getPrecioC2() {
		return this.precioC2;
	}

	public void setPrecioC2(BigDecimal precioC2) {
		this.precioC2 = precioC2;
	}

	public BigDecimal getPrecioCs() {
		return this.precioCs;
	}

	public void setPrecioCs(BigDecimal precioCs) {
		this.precioCs = precioCs;
	}

	public BigDecimal getPrecioDr() {
		return this.precioDr;
	}

	public void setPrecioDr(BigDecimal precioDr) {
		this.precioDr = precioDr;
	}

	public BigDecimal getPrecioCm() {
		return this.precioCm;
	}

	public void setPrecioCm(BigDecimal precioCm) {
		this.precioCm = precioCm;
	}

	public BigDecimal getPrecioCo() {
		return this.precioCo;
	}

	public void setPrecioCo(BigDecimal precioCo) {
		this.precioCo = precioCo;
	}

	public BigDecimal getPrecioPc() {
		return this.precioPc;
	}

	public void setPrecioPc(BigDecimal precioPc) {
		this.precioPc = precioPc;
	}

	public BigDecimal getPrecioP() {
		return this.precioP;
	}

	public void setPrecioP(BigDecimal precioP) {
		this.precioP = precioP;
	}

	public BigDecimal getPrecioDa() {
		return this.precioDa;
	}

	public void setPrecioDa(BigDecimal precioDa) {
		this.precioDa = precioDa;
	}

	public BigDecimal getPrecioDs() {
		return this.precioDs;
	}

	public void setPrecioDs(BigDecimal precioDs) {
		this.precioDs = precioDs;
	}

	public BigDecimal getPrecioCdi() {
		return this.precioCdi;
	}

	public void setPrecioCdi(BigDecimal precioCdi) {
		this.precioCdi = precioCdi;
	}

	public BigDecimal getPrecioG() {
		return this.precioG;
	}

	public void setPrecioG(BigDecimal precioG) {
		this.precioG = precioG;
	}

	public BigDecimal getPrecioCe() {
		return this.precioCe;
	}

	public void setPrecioCe(BigDecimal precioCe) {
		this.precioCe = precioCe;
	}

	public BigDecimal getCueroEnSangre() {
		return this.cueroEnSangre;
	}

	public void setCueroEnSangre(BigDecimal cueroEnSangre) {
		this.cueroEnSangre = cueroEnSangre;
	}

	public BigDecimal getCalculoPorcentajeMerma() {
		return this.calculoPorcentajeMerma;
	}

	public void setCalculoPorcentajeMerma(BigDecimal calculoPorcentajeMerma) {
		this.calculoPorcentajeMerma = calculoPorcentajeMerma;
	}

	public BigDecimal getDescuentoHumedadTa() {
		return this.descuentoHumedadTa;
	}

	public void setDescuentoHumedadTa(BigDecimal descuentoHumedadTa) {
		this.descuentoHumedadTa = descuentoHumedadTa;
	}

	public BigDecimal getDescuentoHumedadTb() {
		return this.descuentoHumedadTb;
	}

	public void setDescuentoHumedadTb(BigDecimal descuentoHumedadTb) {
		this.descuentoHumedadTb = descuentoHumedadTb;
	}

	public BigDecimal getDescuentoCalcioTa() {
		return this.descuentoCalcioTa;
	}

	public void setDescuentoCalcioTa(BigDecimal descuentoCalcioTa) {
		this.descuentoCalcioTa = descuentoCalcioTa;
	}

	public BigDecimal getDescuentoCalcioTb() {
		return this.descuentoCalcioTb;
	}

	public void setDescuentoCalcioTb(BigDecimal descuentoCalcioTb) {
		this.descuentoCalcioTb = descuentoCalcioTb;
	}

	public BigDecimal getCalculoKgMerma() {
		return this.calculoKgMerma;
	}

	public void setCalculoKgMerma(BigDecimal calculoKgMerma) {
		this.calculoKgMerma = calculoKgMerma;
	}

	public BigDecimal getDescuentoHumedad() {
		return descuentoHumedad;
	}

	public void setDescuentoHumedad(BigDecimal descuentoHumedad) {
		this.descuentoHumedad = descuentoHumedad;
	}

	public BigDecimal getDescuentoCalcio() {
		return descuentoCalcio;
	}

	public void setDescuentoCalcio(BigDecimal descuentoCalcio) {
		this.descuentoCalcio = descuentoCalcio;
	}

	public Date getFechaImpresionContador() {
		return fechaImpresionContador;
	}

	public void setFechaImpresionContador(Date fechaImpresionContador) {
		this.fechaImpresionContador = fechaImpresionContador;
	}

	public BigDecimal getPorcentajeMerma() {
		return porcentajeMerma;
	}

	public void setPorcentajeMerma(BigDecimal porcentajeMerma) {
		this.porcentajeMerma = porcentajeMerma;
	}
	
	

}
