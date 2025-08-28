package com.dmjm.model;


import java.util.Date;


public class Remolienda  implements java.io.Serializable {


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

    public Remolienda() {
    }

	
    public Remolienda(int idRemolienda) {
        this.idRemolienda = idRemolienda;
    }
    public Remolienda(int idRemolienda, FolioPreparacionMolienda folioPreparacionMolienda, Integer folio, Date fecha, String remoliendaReproceso, String operacion, Date moliendaHoraInicial, Date moliendaHoraFinal, Date secadoHoraInicial, Date secadoHoraFinal, Integer m100Sacos, Integer m100KgRestos, Integer m100KgTotales, Integer m60Sacos, Integer m60KgRestos, Integer m60KgTotales, Integer m30Sacos, Integer m30KgRestos, Integer m30KgTotales, Integer m8Sacos, Integer m8KgRestos, Integer m8KgTotales, Integer ghSacos, Integer ghKgRestos, Integer ghKgTotales, Integer total, Integer fechaFolioPrep) {
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
    }
   
    public int getIdRemolienda() {
        return this.idRemolienda;
    }
    
    public void setIdRemolienda(int idRemolienda) {
        this.idRemolienda = idRemolienda;
    }
    public FolioPreparacionMolienda getFolioPreparacionMolienda() {
        return this.folioPreparacionMolienda;
    }
    
    public void setFolioPreparacionMolienda(FolioPreparacionMolienda folioPreparacionMolienda) {
        this.folioPreparacionMolienda = folioPreparacionMolienda;
    }
    public Integer getFolio() {
        return this.folio;
    }
    
    public void setFolio(Integer folio) {
        this.folio = folio;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getRemoliendaReproceso() {
        return this.remoliendaReproceso;
    }
    
    public void setRemoliendaReproceso(String remoliendaReproceso) {
        this.remoliendaReproceso = remoliendaReproceso;
    }
    public String getOperacion() {
        return this.operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public Date getMoliendaHoraInicial() {
        return this.moliendaHoraInicial;
    }
    
    public void setMoliendaHoraInicial(Date moliendaHoraInicial) {
        this.moliendaHoraInicial = moliendaHoraInicial;
    }
    public Date getMoliendaHoraFinal() {
        return this.moliendaHoraFinal;
    }
    
    public void setMoliendaHoraFinal(Date moliendaHoraFinal) {
        this.moliendaHoraFinal = moliendaHoraFinal;
    }
    public Date getSecadoHoraInicial() {
        return this.secadoHoraInicial;
    }
    
    public void setSecadoHoraInicial(Date secadoHoraInicial) {
        this.secadoHoraInicial = secadoHoraInicial;
    }
    public Date getSecadoHoraFinal() {
        return this.secadoHoraFinal;
    }
    
    public void setSecadoHoraFinal(Date secadoHoraFinal) {
        this.secadoHoraFinal = secadoHoraFinal;
    }
    public Integer getM100Sacos() {
        return this.m100Sacos;
    }
    
    public void setM100Sacos(Integer m100Sacos) {
        this.m100Sacos = m100Sacos;
    }
    public Integer getM100KgRestos() {
        return this.m100KgRestos;
    }
    
    public void setM100KgRestos(Integer m100KgRestos) {
        this.m100KgRestos = m100KgRestos;
    }
    public Integer getM100KgTotales() {
        return this.m100KgTotales;
    }
    
    public void setM100KgTotales(Integer m100KgTotales) {
        this.m100KgTotales = m100KgTotales;
    }
    public Integer getM60Sacos() {
        return this.m60Sacos;
    }
    
    public void setM60Sacos(Integer m60Sacos) {
        this.m60Sacos = m60Sacos;
    }
    public Integer getM60KgRestos() {
        return this.m60KgRestos;
    }
    
    public void setM60KgRestos(Integer m60KgRestos) {
        this.m60KgRestos = m60KgRestos;
    }
    public Integer getM60KgTotales() {
        return this.m60KgTotales;
    }
    
    public void setM60KgTotales(Integer m60KgTotales) {
        this.m60KgTotales = m60KgTotales;
    }
    public Integer getM30Sacos() {
        return this.m30Sacos;
    }
    
    public void setM30Sacos(Integer m30Sacos) {
        this.m30Sacos = m30Sacos;
    }
    public Integer getM30KgRestos() {
        return this.m30KgRestos;
    }
    
    public void setM30KgRestos(Integer m30KgRestos) {
        this.m30KgRestos = m30KgRestos;
    }
    public Integer getM30KgTotales() {
        return this.m30KgTotales;
    }
    
    public void setM30KgTotales(Integer m30KgTotales) {
        this.m30KgTotales = m30KgTotales;
    }
    public Integer getM8Sacos() {
        return this.m8Sacos;
    }
    
    public void setM8Sacos(Integer m8Sacos) {
        this.m8Sacos = m8Sacos;
    }
    public Integer getM8KgRestos() {
        return this.m8KgRestos;
    }
    
    public void setM8KgRestos(Integer m8KgRestos) {
        this.m8KgRestos = m8KgRestos;
    }
    public Integer getM8KgTotales() {
        return this.m8KgTotales;
    }
    
    public void setM8KgTotales(Integer m8KgTotales) {
        this.m8KgTotales = m8KgTotales;
    }
    public Integer getGhSacos() {
        return this.ghSacos;
    }
    
    public void setGhSacos(Integer ghSacos) {
        this.ghSacos = ghSacos;
    }
    public Integer getGhKgRestos() {
        return this.ghKgRestos;
    }
    
    public void setGhKgRestos(Integer ghKgRestos) {
        this.ghKgRestos = ghKgRestos;
    }
    public Integer getGhKgTotales() {
        return this.ghKgTotales;
    }
    
    public void setGhKgTotales(Integer ghKgTotales) {
        this.ghKgTotales = ghKgTotales;
    }
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getFechaFolioPrep() {
        return this.fechaFolioPrep;
    }
    
    public void setFechaFolioPrep(Integer fechaFolioPrep) {
        this.fechaFolioPrep = fechaFolioPrep;
    }




}


