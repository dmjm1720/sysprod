package com.dmjm.dao;



public interface IFoliosDao {

    int folioMax(int year, String mes);
    
    void actualizarFolio(int year, String mes, int folio);
    
    void habilitarFolio();
    
    void deshabilitarFolio();
    
    int validarFolioMesAnt();
}
