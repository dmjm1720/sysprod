package com.dmjm.bean;

import com.dmjm.impl.TransportistaDaoImpl;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.dao.ITransportistaDao;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.Proveedores;
import com.dmjm.model.Transportista;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "transportistaBean")
@ViewScoped
public class TransportistaBean implements Serializable {

    private List<Transportista> listarTransportista;
    private Transportista transportista;

    @PostConstruct
    public void init() {
        listarTransportista = new ArrayList<>();
        transportista = new Transportista();

    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public List<Transportista> getListarTransportista() {
        ITransportistaDao eDao = new TransportistaDaoImpl();
        listarTransportista = eDao.listarTransportista();
        return listarTransportista;
    }

    public void guardar() {
        ITransportistaDao eDao = new TransportistaDaoImpl();
        eDao.guardarTransportista(transportista);
        transportista = new Transportista(); 
    }
    
    public void actualizar() {
        ITransportistaDao eDao = new TransportistaDaoImpl();
        eDao.actualizarTransportista(transportista);
        transportista = new Transportista(); 
    }
    

}
