package com.dmjm.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class FolioPreparacionVotatorB  implements Serializable {

	private static final long serialVersionUID = 1L;
     private int idFolioPrep;
     private Date fecha;
     private Integer folioVotatorB;
     private String observaciones;
     private Set resumenVotatorBs = new HashSet(0);
     private Set ordenMantenimientoVotatorBs = new HashSet(0);
     private Set votatorBs = new HashSet(0);
     private Set limpiezaVotatorBs = new HashSet(0);

    public FolioPreparacionVotatorB() {
    }

	
    public FolioPreparacionVotatorB(int idFolioPrep) {
        this.idFolioPrep = idFolioPrep;
    }
    public FolioPreparacionVotatorB(int idFolioPrep, Date fecha, Integer folioVotatorB, String observaciones, Set resumenVotatorBs, Set ordenMantenimientoVotatorBs, Set votatorBs, Set limpiezaVotatorBs) {
       this.idFolioPrep = idFolioPrep;
       this.fecha = fecha;
       this.folioVotatorB = folioVotatorB;
       this.observaciones = observaciones;
       this.resumenVotatorBs = resumenVotatorBs;
       this.ordenMantenimientoVotatorBs = ordenMantenimientoVotatorBs;
       this.votatorBs = votatorBs;
       this.limpiezaVotatorBs = limpiezaVotatorBs;
    }
   
    public int getIdFolioPrep() {
        return this.idFolioPrep;
    }
    
    public void setIdFolioPrep(int idFolioPrep) {
        this.idFolioPrep = idFolioPrep;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getFolioVotatorB() {
        return this.folioVotatorB;
    }
    
    public void setFolioVotatorB(Integer folioVotatorB) {
        this.folioVotatorB = folioVotatorB;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Set getResumenVotatorBs() {
        return this.resumenVotatorBs;
    }
    
    public void setResumenVotatorBs(Set resumenVotatorBs) {
        this.resumenVotatorBs = resumenVotatorBs;
    }
    public Set getOrdenMantenimientoVotatorBs() {
        return this.ordenMantenimientoVotatorBs;
    }
    
    public void setOrdenMantenimientoVotatorBs(Set ordenMantenimientoVotatorBs) {
        this.ordenMantenimientoVotatorBs = ordenMantenimientoVotatorBs;
    }
    public Set getVotatorBs() {
        return this.votatorBs;
    }
    
    public void setVotatorBs(Set votatorBs) {
        this.votatorBs = votatorBs;
    }
    public Set getLimpiezaVotatorBs() {
        return this.limpiezaVotatorBs;
    }
    
    public void setLimpiezaVotatorBs(Set limpiezaVotatorBs) {
        this.limpiezaVotatorBs = limpiezaVotatorBs;
    }




}


