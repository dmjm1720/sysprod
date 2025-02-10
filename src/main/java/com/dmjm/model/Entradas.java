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
	private BigDecimal carnazaPrimera;
	private BigDecimal carnazaSegunda;
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
	private BigDecimal precioCalcCcp;
	private BigDecimal precioCalcC1;
	private BigDecimal precioCalcC2;
	private BigDecimal precioCalcCs;
	private BigDecimal precioCalcDr;
	private BigDecimal precioCalcCm;
	private BigDecimal precioCalcCo;
	private BigDecimal precioCalcPc;
	private BigDecimal precioCalcP;
	private BigDecimal precioCalcDa;
	private BigDecimal precioCalcDs;
	private BigDecimal precioCalcCdi;
	private BigDecimal precioCalcG;
	private BigDecimal precioCalcCe;
	private BigDecimal subtotal;
	private BigDecimal iva;
	private BigDecimal totalCaptura;
	private BigDecimal kgCalidadMateria;
	private String archivoCertificado;
	private String tipoMoneda;
	private Integer estatusFacturaImportacion;

	public Entradas() {
	}

	public Entradas(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Entradas(int idEntrada, BigDecimal kgRecibidos) {
		this.idEntrada = idEntrada;
		this.kgRecibidos = kgRecibidos;
	}

	public Entradas(int idEntrada, Materia materia, Preservacion preservacion, Proveedores proveedores,
			Transportista transportista, Integer tolvas, Date fechaRecepcion, String sucursal,
			Date fechaRecepcionToluca, String ticketBasculaToluca, String ticketBasculaLeon, String factura,
			String certificado, BigDecimal kgEmbarcados, BigDecimal kgNetos, BigDecimal kgBascula,
			BigDecimal kgBasculaMerma, BigDecimal kgRecibidos, BigDecimal kgEnvioLeon, String palletsRecibidos,
			BigDecimal carnazaConPelo, BigDecimal carnazaPrimera, BigDecimal carnazaSegunda, BigDecimal desbarbeRecorte,
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
			BigDecimal descuentoHumedad, BigDecimal descuentoCalcio, Date fechaImpresionContador,
			BigDecimal porcentajeMerma, BigDecimal precioCalcCcp, BigDecimal precioCalcC1, BigDecimal precioCalcC2,
			BigDecimal precioCalcCs, BigDecimal precioCalcDr, BigDecimal precioCalcCm, BigDecimal precioCalcCo,
			BigDecimal precioCalcPc, BigDecimal precioCalcP, BigDecimal precioCalcDa, BigDecimal precioCalcDs,
			BigDecimal precioCalcCdi, BigDecimal precioCalcG, BigDecimal precioCalcCe, BigDecimal subtotal,
			BigDecimal iva, BigDecimal totalCaptura, BigDecimal kgCalidadMateria, String archivoCertificado,
			String tipoMoneda, Integer estatusFacturaImportacion) {

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
		this.carnazaPrimera = carnazaPrimera;
		this.carnazaSegunda = carnazaSegunda;
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
		this.precioCalcCcp = precioCalcCcp;
		this.precioCalcC1 = precioCalcC1;
		this.precioCalcC2 = precioCalcC2;
		this.precioCalcCs = precioCalcCs;
		this.precioCalcDr = precioCalcDr;
		this.precioCalcCm = precioCalcCm;
		this.precioCalcCo = precioCalcCo;
		this.precioCalcPc = precioCalcPc;
		this.precioCalcP = precioCalcP;
		this.precioCalcDa = precioCalcDa;
		this.precioCalcDs = precioCalcDs;
		this.precioCalcCdi = precioCalcCdi;
		this.precioCalcG = precioCalcG;
		this.precioCalcCe = precioCalcCe;
		this.subtotal = subtotal;
		this.iva = iva;
		this.totalCaptura = totalCaptura;
		this.kgCalidadMateria = kgCalidadMateria;
		this.archivoCertificado = archivoCertificado;
		this.tipoMoneda = tipoMoneda;
		this.estatusFacturaImportacion = estatusFacturaImportacion;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Preservacion getPreservacion() {
		return preservacion;
	}

	public void setPreservacion(Preservacion preservacion) {
		this.preservacion = preservacion;
	}

	public Proveedores getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Integer getTolvas() {
		return tolvas;
	}

	public void setTolvas(Integer tolvas) {
		this.tolvas = tolvas;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFechaRecepcionToluca() {
		return fechaRecepcionToluca;
	}

	public void setFechaRecepcionToluca(Date fechaRecepcionToluca) {
		this.fechaRecepcionToluca = fechaRecepcionToluca;
	}

	public String getTicketBasculaToluca() {
		return ticketBasculaToluca;
	}

	public void setTicketBasculaToluca(String ticketBasculaToluca) {
		this.ticketBasculaToluca = ticketBasculaToluca;
	}

	public String getTicketBasculaLeon() {
		return ticketBasculaLeon;
	}

	public void setTicketBasculaLeon(String ticketBasculaLeon) {
		this.ticketBasculaLeon = ticketBasculaLeon;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public BigDecimal getKgEmbarcados() {
		return kgEmbarcados;
	}

	public void setKgEmbarcados(BigDecimal kgEmbarcados) {
		this.kgEmbarcados = kgEmbarcados;
	}

	public BigDecimal getKgNetos() {
		return kgNetos;
	}

	public void setKgNetos(BigDecimal kgNetos) {
		this.kgNetos = kgNetos;
	}

	public BigDecimal getKgBascula() {
		return kgBascula;
	}

	public void setKgBascula(BigDecimal kgBascula) {
		this.kgBascula = kgBascula;
	}

	public BigDecimal getKgBasculaMerma() {
		return kgBasculaMerma;
	}

	public void setKgBasculaMerma(BigDecimal kgBasculaMerma) {
		this.kgBasculaMerma = kgBasculaMerma;
	}

	public BigDecimal getKgRecibidos() {
		return kgRecibidos;
	}

	public void setKgRecibidos(BigDecimal kgRecibidos) {
		this.kgRecibidos = kgRecibidos;
	}

	public BigDecimal getKgEnvioLeon() {
		return kgEnvioLeon;
	}

	public void setKgEnvioLeon(BigDecimal kgEnvioLeon) {
		this.kgEnvioLeon = kgEnvioLeon;
	}

	public String getPalletsRecibidos() {
		return palletsRecibidos;
	}

	public void setPalletsRecibidos(String palletsRecibidos) {
		this.palletsRecibidos = palletsRecibidos;
	}

	public BigDecimal getCarnazaConPelo() {
		return carnazaConPelo;
	}

	public void setCarnazaConPelo(BigDecimal carnazaConPelo) {
		this.carnazaConPelo = carnazaConPelo;
	}

	public BigDecimal getCarnazaPrimera() {
		return carnazaPrimera;
	}

	public void setCarnazaPrimera(BigDecimal carnazaPrimera) {
		this.carnazaPrimera = carnazaPrimera;
	}

	public BigDecimal getCarnazaSegunda() {
		return carnazaSegunda;
	}

	public void setCarnazaSegunda(BigDecimal carnazaSegunda) {
		this.carnazaSegunda = carnazaSegunda;
	}

	public BigDecimal getDesbarbeRecorte() {
		return desbarbeRecorte;
	}

	public void setDesbarbeRecorte(BigDecimal desbarbeRecorte) {
		this.desbarbeRecorte = desbarbeRecorte;
	}

	public BigDecimal getCerdoMexicano() {
		return cerdoMexicano;
	}

	public void setCerdoMexicano(BigDecimal cerdoMexicano) {
		this.cerdoMexicano = cerdoMexicano;
	}

	public BigDecimal getOrejaCachete() {
		return orejaCachete;
	}

	public void setOrejaCachete(BigDecimal orejaCachete) {
		this.orejaCachete = orejaCachete;
	}

	public BigDecimal getGarra() {
		return garra;
	}

	public void setGarra(BigDecimal garra) {
		this.garra = garra;
	}

	public BigDecimal getPedaceria() {
		return pedaceria;
	}

	public void setPedaceria(BigDecimal pedaceria) {
		this.pedaceria = pedaceria;
	}

	public BigDecimal getCueroDepiladoIntegral() {
		return cueroDepiladoIntegral;
	}

	public void setCueroDepiladoIntegral(BigDecimal cueroDepiladoIntegral) {
		this.cueroDepiladoIntegral = cueroDepiladoIntegral;
	}

	public BigDecimal getPedaceriaConPelo() {
		return pedaceriaConPelo;
	}

	public void setPedaceriaConPelo(BigDecimal pedaceriaConPelo) {
		this.pedaceriaConPelo = pedaceriaConPelo;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMerma() {
		return merma;
	}

	public void setMerma(BigDecimal merma) {
		this.merma = merma;
	}

	public BigDecimal getAlcalinidad() {
		return alcalinidad;
	}

	public void setAlcalinidad(BigDecimal alcalinidad) {
		this.alcalinidad = alcalinidad;
	}

	public BigDecimal getHumedad() {
		return humedad;
	}

	public void setHumedad(BigDecimal humedad) {
		this.humedad = humedad;
	}

	public String getCoordinadorProduccion() {
		return coordinadorProduccion;
	}

	public void setCoordinadorProduccion(String coordinadorProduccion) {
		this.coordinadorProduccion = coordinadorProduccion;
	}

	public String getControlCalidad() {
		return controlCalidad;
	}

	public void setControlCalidad(String controlCalidad) {
		this.controlCalidad = controlCalidad;
	}

	public String getGerenciaProduccion() {
		return gerenciaProduccion;
	}

	public void setGerenciaProduccion(String gerenciaProduccion) {
		this.gerenciaProduccion = gerenciaProduccion;
	}

	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	public Integer getBloqueoEditar() {
		return bloqueoEditar;
	}

	public void setBloqueoEditar(Integer bloqueoEditar) {
		this.bloqueoEditar = bloqueoEditar;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public BigDecimal getCarnazaSalada() {
		return carnazaSalada;
	}

	public void setCarnazaSalada(BigDecimal carnazaSalada) {
		this.carnazaSalada = carnazaSalada;
	}

	public BigDecimal getDescarneAdherido() {
		return descarneAdherido;
	}

	public void setDescarneAdherido(BigDecimal descarneAdherido) {
		this.descarneAdherido = descarneAdherido;
	}

	public BigDecimal getDescarneSeparado() {
		return descarneSeparado;
	}

	public void setDescarneSeparado(BigDecimal descarneSeparado) {
		this.descarneSeparado = descarneSeparado;
	}

	public BigDecimal getPrecioCcp() {
		return precioCcp;
	}

	public void setPrecioCcp(BigDecimal precioCcp) {
		this.precioCcp = precioCcp;
	}

	public BigDecimal getPrecioC1() {
		return precioC1;
	}

	public void setPrecioC1(BigDecimal precioC1) {
		this.precioC1 = precioC1;
	}

	public BigDecimal getPrecioC2() {
		return precioC2;
	}

	public void setPrecioC2(BigDecimal precioC2) {
		this.precioC2 = precioC2;
	}

	public BigDecimal getPrecioCs() {
		return precioCs;
	}

	public void setPrecioCs(BigDecimal precioCs) {
		this.precioCs = precioCs;
	}

	public BigDecimal getPrecioDr() {
		return precioDr;
	}

	public void setPrecioDr(BigDecimal precioDr) {
		this.precioDr = precioDr;
	}

	public BigDecimal getPrecioCm() {
		return precioCm;
	}

	public void setPrecioCm(BigDecimal precioCm) {
		this.precioCm = precioCm;
	}

	public BigDecimal getPrecioCo() {
		return precioCo;
	}

	public void setPrecioCo(BigDecimal precioCo) {
		this.precioCo = precioCo;
	}

	public BigDecimal getPrecioPc() {
		return precioPc;
	}

	public void setPrecioPc(BigDecimal precioPc) {
		this.precioPc = precioPc;
	}

	public BigDecimal getPrecioP() {
		return precioP;
	}

	public void setPrecioP(BigDecimal precioP) {
		this.precioP = precioP;
	}

	public BigDecimal getPrecioDa() {
		return precioDa;
	}

	public void setPrecioDa(BigDecimal precioDa) {
		this.precioDa = precioDa;
	}

	public BigDecimal getPrecioDs() {
		return precioDs;
	}

	public void setPrecioDs(BigDecimal precioDs) {
		this.precioDs = precioDs;
	}

	public BigDecimal getPrecioCdi() {
		return precioCdi;
	}

	public void setPrecioCdi(BigDecimal precioCdi) {
		this.precioCdi = precioCdi;
	}

	public BigDecimal getPrecioG() {
		return precioG;
	}

	public void setPrecioG(BigDecimal precioG) {
		this.precioG = precioG;
	}

	public BigDecimal getPrecioCe() {
		return precioCe;
	}

	public void setPrecioCe(BigDecimal precioCe) {
		this.precioCe = precioCe;
	}

	public BigDecimal getCueroEnSangre() {
		return cueroEnSangre;
	}

	public void setCueroEnSangre(BigDecimal cueroEnSangre) {
		this.cueroEnSangre = cueroEnSangre;
	}

	public BigDecimal getCalculoPorcentajeMerma() {
		return calculoPorcentajeMerma;
	}

	public void setCalculoPorcentajeMerma(BigDecimal calculoPorcentajeMerma) {
		this.calculoPorcentajeMerma = calculoPorcentajeMerma;
	}

	public BigDecimal getDescuentoHumedadTa() {
		return descuentoHumedadTa;
	}

	public void setDescuentoHumedadTa(BigDecimal descuentoHumedadTa) {
		this.descuentoHumedadTa = descuentoHumedadTa;
	}

	public BigDecimal getDescuentoHumedadTb() {
		return descuentoHumedadTb;
	}

	public void setDescuentoHumedadTb(BigDecimal descuentoHumedadTb) {
		this.descuentoHumedadTb = descuentoHumedadTb;
	}

	public BigDecimal getDescuentoCalcioTa() {
		return descuentoCalcioTa;
	}

	public void setDescuentoCalcioTa(BigDecimal descuentoCalcioTa) {
		this.descuentoCalcioTa = descuentoCalcioTa;
	}

	public BigDecimal getDescuentoCalcioTb() {
		return descuentoCalcioTb;
	}

	public void setDescuentoCalcioTb(BigDecimal descuentoCalcioTb) {
		this.descuentoCalcioTb = descuentoCalcioTb;
	}

	public BigDecimal getCalculoKgMerma() {
		return calculoKgMerma;
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

	public BigDecimal getPrecioCalcCcp() {
		return precioCalcCcp;
	}

	public void setPrecioCalcCcp(BigDecimal precioCalcCcp) {
		this.precioCalcCcp = precioCalcCcp;
	}

	public BigDecimal getPrecioCalcC1() {
		return precioCalcC1;
	}

	public void setPrecioCalcC1(BigDecimal precioCalcC1) {
		this.precioCalcC1 = precioCalcC1;
	}

	public BigDecimal getPrecioCalcC2() {
		return precioCalcC2;
	}

	public void setPrecioCalcC2(BigDecimal precioCalcC2) {
		this.precioCalcC2 = precioCalcC2;
	}

	public BigDecimal getPrecioCalcCs() {
		return precioCalcCs;
	}

	public void setPrecioCalcCs(BigDecimal precioCalcCs) {
		this.precioCalcCs = precioCalcCs;
	}

	public BigDecimal getPrecioCalcDr() {
		return precioCalcDr;
	}

	public void setPrecioCalcDr(BigDecimal precioCalcDr) {
		this.precioCalcDr = precioCalcDr;
	}

	public BigDecimal getPrecioCalcCm() {
		return precioCalcCm;
	}

	public void setPrecioCalcCm(BigDecimal precioCalcCm) {
		this.precioCalcCm = precioCalcCm;
	}

	public BigDecimal getPrecioCalcCo() {
		return precioCalcCo;
	}

	public void setPrecioCalcCo(BigDecimal precioCalcCo) {
		this.precioCalcCo = precioCalcCo;
	}

	public BigDecimal getPrecioCalcPc() {
		return precioCalcPc;
	}

	public void setPrecioCalcPc(BigDecimal precioCalcPc) {
		this.precioCalcPc = precioCalcPc;
	}

	public BigDecimal getPrecioCalcP() {
		return precioCalcP;
	}

	public void setPrecioCalcP(BigDecimal precioCalcP) {
		this.precioCalcP = precioCalcP;
	}

	public BigDecimal getPrecioCalcDa() {
		return precioCalcDa;
	}

	public void setPrecioCalcDa(BigDecimal precioCalcDa) {
		this.precioCalcDa = precioCalcDa;
	}

	public BigDecimal getPrecioCalcDs() {
		return precioCalcDs;
	}

	public void setPrecioCalcDs(BigDecimal precioCalcDs) {
		this.precioCalcDs = precioCalcDs;
	}

	public BigDecimal getPrecioCalcCdi() {
		return precioCalcCdi;
	}

	public void setPrecioCalcCdi(BigDecimal precioCalcCdi) {
		this.precioCalcCdi = precioCalcCdi;
	}

	public BigDecimal getPrecioCalcG() {
		return precioCalcG;
	}

	public void setPrecioCalcG(BigDecimal precioCalcG) {
		this.precioCalcG = precioCalcG;
	}

	public BigDecimal getPrecioCalcCe() {
		return precioCalcCe;
	}

	public void setPrecioCalcCe(BigDecimal precioCalcCe) {
		this.precioCalcCe = precioCalcCe;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotalCaptura() {
		return totalCaptura;
	}

	public void setTotalCaptura(BigDecimal totalCaptura) {
		this.totalCaptura = totalCaptura;
	}

	public BigDecimal getKgCalidadMateria() {
		return kgCalidadMateria;
	}

	public void setKgCalidadMateria(BigDecimal kgCalidadMateria) {
		this.kgCalidadMateria = kgCalidadMateria;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public String getArchivoCertificado() {
		return archivoCertificado;
	}

	public void setArchivoCertificado(String archivoCertificado) {
		this.archivoCertificado = archivoCertificado;
	}

	public Integer getEstatusFacturaImportacion() {
		return estatusFacturaImportacion;
	}

	public void setEstatusFacturaImportacion(Integer estatusFacturaImportacion) {
		this.estatusFacturaImportacion = estatusFacturaImportacion;
	}

}
