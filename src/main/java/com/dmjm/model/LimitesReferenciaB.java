package com.dmjm.model;


import java.util.Date;


public class LimitesReferenciaB  implements java.io.Serializable {


     private int idLimite;
     private Integer folioLr;
     private Date fecha;
     private String presionVapor;
     private String velTapete;

    public LimitesReferenciaB() {
    }

	
    public LimitesReferenciaB(int idLimite) {
        this.idLimite = idLimite;
    }
    public LimitesReferenciaB(int idLimite, Integer folioLr, Date fecha, String presionVapor, String velTapete) {
       this.idLimite = idLimite;
       this.folioLr = folioLr;
       this.fecha = fecha;
       this.presionVapor = presionVapor;
       this.velTapete = velTapete;
    }
   
    public int getIdLimite() {
        return this.idLimite;
    }
    
    public void setIdLimite(int idLimite) {
        this.idLimite = idLimite;
    }
    public Integer getFolioLr() {
        return this.folioLr;
    }
    
    public void setFolioLr(Integer folioLr) {
        this.folioLr = folioLr;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getPresionVapor() {
        return this.presionVapor;
    }
    
    public void setPresionVapor(String presionVapor) {
        this.presionVapor = presionVapor;
    }
    public String getVelTapete() {
        return this.velTapete;
    }
    
    public void setVelTapete(String velTapete) {
        this.velTapete = velTapete;
    }




}


