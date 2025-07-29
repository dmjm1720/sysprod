package com.dmjm.model;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FolioPreparacionVotatorA  implements Serializable {

	private static final long serialVersionUID = 1L;
     private int idFolioPrep;
     private Date fecha;
     private Integer folioVotatorA;
     private String observaciones;
     private Set resumenVotatorAs = new HashSet(0);
     private Set votatorAs = new HashSet(0);
     private Set limpiezaVotatorAs = new HashSet(0);
     private Set ordenMantenimientoVotatorAs = new HashSet(0);

    public FolioPreparacionVotatorA() {
    }

	
    public FolioPreparacionVotatorA(int idFolioPrep) {
        this.idFolioPrep = idFolioPrep;
    }
    public FolioPreparacionVotatorA(int idFolioPrep, Date fecha, Integer folioVotatorA, String observaciones, Set resumenVotatorAs, Set votatorAs, Set limpiezaVotatorAs, Set ordenMantenimientoVotatorAs) {
       this.idFolioPrep = idFolioPrep;
       this.fecha = fecha;
       this.folioVotatorA = folioVotatorA;
       this.observaciones = observaciones;
       this.resumenVotatorAs = resumenVotatorAs;
       this.votatorAs = votatorAs;
       this.limpiezaVotatorAs = limpiezaVotatorAs;
       this.ordenMantenimientoVotatorAs = ordenMantenimientoVotatorAs;
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
    public Integer getFolioVotatorA() {
        return this.folioVotatorA;
    }
    
    public void setFolioVotatorA(Integer folioVotatorA) {
        this.folioVotatorA = folioVotatorA;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Set getResumenVotatorAs() {
        return this.resumenVotatorAs;
    }
    
    public void setResumenVotatorAs(Set resumenVotatorAs) {
        this.resumenVotatorAs = resumenVotatorAs;
    }
    public Set getVotatorAs() {
        return this.votatorAs;
    }
    
    public void setVotatorAs(Set votatorAs) {
        this.votatorAs = votatorAs;
    }
    public Set getLimpiezaVotatorAs() {
        return this.limpiezaVotatorAs;
    }
    
    public void setLimpiezaVotatorAs(Set limpiezaVotatorAs) {
        this.limpiezaVotatorAs = limpiezaVotatorAs;
    }
    public Set getOrdenMantenimientoVotatorAs() {
        return this.ordenMantenimientoVotatorAs;
    }
    
    public void setOrdenMantenimientoVotatorAs(Set ordenMantenimientoVotatorAs) {
        this.ordenMantenimientoVotatorAs = ordenMantenimientoVotatorAs;
    }




}


