package com.dmjm.model;


import java.io.Serializable;
import java.util.Date;

public class CambioPrefiltro  implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idPrefiltro;
     private FolioPreparacionUltraDos folioPreparacionUltraDos;
     private Date horaInicio;
     private Date horaFinal;

    public CambioPrefiltro() {
    }

	
    public CambioPrefiltro(int idPrefiltro) {
        this.idPrefiltro = idPrefiltro;
    }
    public CambioPrefiltro(int idPrefiltro, FolioPreparacionUltraDos folioPreparacionUltraDos, Date horaInicio, Date horaFinal) {
       this.idPrefiltro = idPrefiltro;
       this.folioPreparacionUltraDos = folioPreparacionUltraDos;
       this.horaInicio = horaInicio;
       this.horaFinal = horaFinal;
    }
   
    public int getIdPrefiltro() {
        return this.idPrefiltro;
    }
    
    public void setIdPrefiltro(int idPrefiltro) {
        this.idPrefiltro = idPrefiltro;
    }
    public FolioPreparacionUltraDos getFolioPreparacionUltraDos() {
        return this.folioPreparacionUltraDos;
    }
    
    public void setFolioPreparacionUltraDos(FolioPreparacionUltraDos folioPreparacionUltraDos) {
        this.folioPreparacionUltraDos = folioPreparacionUltraDos;
    }
    public Date getHoraInicio() {
        return this.horaInicio;
    }
    
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }
    public Date getHoraFinal() {
        return this.horaFinal;
    }
    
    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }




}


