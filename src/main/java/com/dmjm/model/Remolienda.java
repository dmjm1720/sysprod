package com.dmjm.model;

import java.util.Date;

public class Remolienda implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idRemolienda;
	private FolioPreparacionMolienda folioPreparacionMolienda;
	private Integer folio;
	private Date fecha;
	private String remoliendaReproceso;
	private String operacion;
	private Date moliendaHoraInicial;
	private Date moliendaHoraFinal;
	private Date secadoHoraInicial;
	private Date secadoHoraFinal;
	private Integer m100Sacos;
	private Integer m100KgRestos;
	private Integer m100KgTotales;
	private Integer m60Sacos;
	private Integer m60KgRestos;
	private Integer m60KgTotales;
	private Integer m30Sacos;
	private Integer m30KgRestos;
	private Integer m30KgTotales;
	private Integer m8Sacos;
	private Integer m8KgRestos;
	private Integer m8KgTotales;
	private Integer ghSacos;
	private Integer ghKgRestos;
	private Integer ghKgTotales;
	private Integer total;
	private Integer fechaFolioPrep;
	private Integer kg100;
	private Integer kg60;
	private Integer kg30;
	private Integer kg8;
	private Integer kg200;
	private String lote;
	private String seleccion;
	private Integer m200Sacos;
	private Integer m200KgRestos;
	private Integer m200KgTotales;

	public Remolienda() {

		// Inicialización de sacos y restos en 0
		this.m100Sacos = 0;
		this.m100KgRestos = 0;
		this.m100KgTotales = 0;

		this.m60Sacos = 0;
		this.m60KgRestos = 0;
		this.m60KgTotales = 0;

		this.m30Sacos = 0;
		this.m30KgRestos = 0;
		this.m30KgTotales = 0;

		this.m8Sacos = 0;
		this.m8KgRestos = 0;
		this.m8KgTotales = 0;

		this.ghSacos = 0;
		this.ghKgRestos = 0;
		this.ghKgTotales = 0;

		this.m200Sacos = 0;
		this.m200KgRestos = 0;
		this.m200KgTotales = 0;

		this.total = 0;
	}

	public Remolienda(int idRemolienda) {
		this.idRemolienda = idRemolienda;
	}

	public Remolienda(int idRemolienda, FolioPreparacionMolienda folioPreparacionMolienda, Integer folio, Date fecha,
			String remoliendaReproceso, String operacion, Date moliendaHoraInicial, Date moliendaHoraFinal,
			Date secadoHoraInicial, Date secadoHoraFinal, Integer m100Sacos, Integer m100KgRestos,
			Integer m100KgTotales, Integer m60Sacos, Integer m60KgRestos, Integer m60KgTotales, Integer m30Sacos,
			Integer m30KgRestos, Integer m30KgTotales, Integer m8Sacos, Integer m8KgRestos, Integer m8KgTotales,
			Integer ghSacos, Integer ghKgRestos, Integer ghKgTotales, Integer total, Integer fechaFolioPrep,
			Integer kg100, Integer kg60, Integer kg30, Integer kg8, Integer kg200, String lote, String seleccion,
			Integer m200Sacos, Integer m200KgRestos, Integer m200KgTotales) {
		super();
		this.idRemolienda = idRemolienda;
		this.folioPreparacionMolienda = folioPreparacionMolienda;
		this.folio = folio;
		this.fecha = fecha;
		this.remoliendaReproceso = remoliendaReproceso;
		this.operacion = operacion;
		this.moliendaHoraInicial = moliendaHoraInicial;
		this.moliendaHoraFinal = moliendaHoraFinal;
		this.secadoHoraInicial = secadoHoraInicial;
		this.secadoHoraFinal = secadoHoraFinal;
		this.m100Sacos = m100Sacos;
		this.m100KgRestos = m100KgRestos;
		this.m100KgTotales = m100KgTotales;
		this.m60Sacos = m60Sacos;
		this.m60KgRestos = m60KgRestos;
		this.m60KgTotales = m60KgTotales;
		this.m30Sacos = m30Sacos;
		this.m30KgRestos = m30KgRestos;
		this.m30KgTotales = m30KgTotales;
		this.m8Sacos = m8Sacos;
		this.m8KgRestos = m8KgRestos;
		this.m8KgTotales = m8KgTotales;
		this.ghSacos = ghSacos;
		this.ghKgRestos = ghKgRestos;
		this.ghKgTotales = ghKgTotales;
		this.total = total;
		this.fechaFolioPrep = fechaFolioPrep;
		this.kg100 = kg100;
		this.kg60 = kg60;
		this.kg30 = kg30;
		this.kg8 = kg8;
		this.kg200 = kg200;
		this.lote = lote;
		this.seleccion = seleccion;
		this.m200Sacos = m200Sacos;
		this.m200KgRestos = m200KgRestos;
		this.m200KgTotales = m200KgTotales;
	}

	public int getIdRemolienda() {
		return idRemolienda;
	}

	public void setIdRemolienda(int idRemolienda) {
		this.idRemolienda = idRemolienda;
	}

	public FolioPreparacionMolienda getFolioPreparacionMolienda() {
		return folioPreparacionMolienda;
	}

	public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
		this.folioPreparacionMolienda = folioPreparacionMolienda;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getRemoliendaReproceso() {
		return remoliendaReproceso;
	}

	public void setRemoliendaReproceso(String remoliendaReproceso) {
		this.remoliendaReproceso = remoliendaReproceso;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Date getMoliendaHoraInicial() {
		return moliendaHoraInicial;
	}

	public void setMoliendaHoraInicial(Date moliendaHoraInicial) {
		this.moliendaHoraInicial = moliendaHoraInicial;
	}

	public Date getMoliendaHoraFinal() {
		return moliendaHoraFinal;
	}

	public void setMoliendaHoraFinal(Date moliendaHoraFinal) {
		this.moliendaHoraFinal = moliendaHoraFinal;
	}

	public Date getSecadoHoraInicial() {
		return secadoHoraInicial;
	}

	public void setSecadoHoraInicial(Date secadoHoraInicial) {
		this.secadoHoraInicial = secadoHoraInicial;
	}

	public Date getSecadoHoraFinal() {
		return secadoHoraFinal;
	}

	public void setSecadoHoraFinal(Date secadoHoraFinal) {
		this.secadoHoraFinal = secadoHoraFinal;
	}

	public Integer getM100Sacos() {
		return m100Sacos;
	}

	public void setM100Sacos(Integer m100Sacos) {
		this.m100Sacos = m100Sacos;
	}

	public Integer getM100KgRestos() {
		return m100KgRestos;
	}

	public void setM100KgRestos(Integer m100KgRestos) {
		this.m100KgRestos = m100KgRestos;
	}

	public Integer getM100KgTotales() {
		return m100KgTotales;
	}

	public void setM100KgTotales(Integer m100KgTotales) {
		this.m100KgTotales = m100KgTotales;
	}

	public Integer getM60Sacos() {
		return m60Sacos;
	}

	public void setM60Sacos(Integer m60Sacos) {
		this.m60Sacos = m60Sacos;
	}

	public Integer getM60KgRestos() {
		return m60KgRestos;
	}

	public void setM60KgRestos(Integer m60KgRestos) {
		this.m60KgRestos = m60KgRestos;
	}

	public Integer getM60KgTotales() {
		return m60KgTotales;
	}

	public void setM60KgTotales(Integer m60KgTotales) {
		this.m60KgTotales = m60KgTotales;
	}

	public Integer getM30Sacos() {
		return m30Sacos;
	}

	public void setM30Sacos(Integer m30Sacos) {
		this.m30Sacos = m30Sacos;
	}

	public Integer getM30KgRestos() {
		return m30KgRestos;
	}

	public void setM30KgRestos(Integer m30KgRestos) {
		this.m30KgRestos = m30KgRestos;
	}

	public Integer getM30KgTotales() {
		return m30KgTotales;
	}

	public void setM30KgTotales(Integer m30KgTotales) {
		this.m30KgTotales = m30KgTotales;
	}

	public Integer getM8Sacos() {
		return m8Sacos;
	}

	public void setM8Sacos(Integer m8Sacos) {
		this.m8Sacos = m8Sacos;
	}

	public Integer getM8KgRestos() {
		return m8KgRestos;
	}

	public void setM8KgRestos(Integer m8KgRestos) {
		this.m8KgRestos = m8KgRestos;
	}

	public Integer getM8KgTotales() {
		return m8KgTotales;
	}

	public void setM8KgTotales(Integer m8KgTotales) {
		this.m8KgTotales = m8KgTotales;
	}

	public Integer getGhSacos() {
		return ghSacos;
	}

	public void setGhSacos(Integer ghSacos) {
		this.ghSacos = ghSacos;
	}

	public Integer getGhKgRestos() {
		return ghKgRestos;
	}

	public void setGhKgRestos(Integer ghKgRestos) {
		this.ghKgRestos = ghKgRestos;
	}

	public Integer getGhKgTotales() {
		return ghKgTotales;
	}

	public void setGhKgTotales(Integer ghKgTotales) {
		this.ghKgTotales = ghKgTotales;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getFechaFolioPrep() {
		return fechaFolioPrep;
	}

	public void setFechaFolioPrep(Integer fechaFolioPrep) {
		this.fechaFolioPrep = fechaFolioPrep;
	}

	public Integer getKg100() {
		return kg100;
	}

	public void setKg100(Integer kg100) {
		this.kg100 = kg100;
	}

	public Integer getKg60() {
		return kg60;
	}

	public void setKg60(Integer kg60) {
		this.kg60 = kg60;
	}

	public Integer getKg30() {
		return kg30;
	}

	public void setKg30(Integer kg30) {
		this.kg30 = kg30;
	}

	public Integer getKg8() {
		return kg8;
	}

	public void setKg8(Integer kg8) {
		this.kg8 = kg8;
	}

	public Integer getKg200() {
		return kg200;
	}

	public void setKg200(Integer kg200) {
		this.kg200 = kg200;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public Integer getM200Sacos() {
		return m200Sacos;
	}

	public void setM200Sacos(Integer m200Sacos) {
		this.m200Sacos = m200Sacos;
	}

	public Integer getM200KgRestos() {
		return m200KgRestos;
	}

	public void setM200KgRestos(Integer m200KgRestos) {
		this.m200KgRestos = m200KgRestos;
	}

	public Integer getM200KgTotales() {
		return m200KgTotales;
	}

	public void setM200KgTotales(Integer m200KgTotales) {
		this.m200KgTotales = m200KgTotales;
	}

	

}
