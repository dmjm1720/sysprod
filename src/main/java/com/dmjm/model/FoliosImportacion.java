package com.dmjm.model;

import java.io.Serializable;

public class FoliosImportacion  implements Serializable {


	private static final long serialVersionUID = 1L;
	private int idFolio;
     private Integer year;
     private Integer enero;
     private Integer febrero;
     private Integer marzo;
     private Integer abril;
     private Integer mayo;
     private Integer junio;
     private Integer julio;
     private Integer agosto;
     private Integer septiembre;
     private Integer octubre;
     private Integer noviembre;
     private Integer diciembre;

    public FoliosImportacion() {
    }

	
    public FoliosImportacion(int idFolio) {
        this.idFolio = idFolio;
    }
    public FoliosImportacion(int idFolio, Integer year, Integer enero, Integer febrero, Integer marzo, Integer abril, Integer mayo, Integer junio, Integer julio, Integer agosto, Integer septiembre, Integer octubre, Integer noviembre, Integer diciembre) {
       this.idFolio = idFolio;
       this.year = year;
       this.enero = enero;
       this.febrero = febrero;
       this.marzo = marzo;
       this.abril = abril;
       this.mayo = mayo;
       this.junio = junio;
       this.julio = julio;
       this.agosto = agosto;
       this.septiembre = septiembre;
       this.octubre = octubre;
       this.noviembre = noviembre;
       this.diciembre = diciembre;
    }
   
    public int getIdFolio() {
        return this.idFolio;
    }
    
    public void setIdFolio(int idFolio) {
        this.idFolio = idFolio;
    }
    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getEnero() {
        return this.enero;
    }
    
    public void setEnero(Integer enero) {
        this.enero = enero;
    }
    public Integer getFebrero() {
        return this.febrero;
    }
    
    public void setFebrero(Integer febrero) {
        this.febrero = febrero;
    }
    public Integer getMarzo() {
        return this.marzo;
    }
    
    public void setMarzo(Integer marzo) {
        this.marzo = marzo;
    }
    public Integer getAbril() {
        return this.abril;
    }
    
    public void setAbril(Integer abril) {
        this.abril = abril;
    }
    public Integer getMayo() {
        return this.mayo;
    }
    
    public void setMayo(Integer mayo) {
        this.mayo = mayo;
    }
    public Integer getJunio() {
        return this.junio;
    }
    
    public void setJunio(Integer junio) {
        this.junio = junio;
    }
    public Integer getJulio() {
        return this.julio;
    }
    
    public void setJulio(Integer julio) {
        this.julio = julio;
    }
    public Integer getAgosto() {
        return this.agosto;
    }
    
    public void setAgosto(Integer agosto) {
        this.agosto = agosto;
    }
    public Integer getSeptiembre() {
        return this.septiembre;
    }
    
    public void setSeptiembre(Integer septiembre) {
        this.septiembre = septiembre;
    }
    public Integer getOctubre() {
        return this.octubre;
    }
    
    public void setOctubre(Integer octubre) {
        this.octubre = octubre;
    }
    public Integer getNoviembre() {
        return this.noviembre;
    }
    
    public void setNoviembre(Integer noviembre) {
        this.noviembre = noviembre;
    }
    public Integer getDiciembre() {
        return this.diciembre;
    }
    
    public void setDiciembre(Integer diciembre) {
        this.diciembre = diciembre;
    }




}


