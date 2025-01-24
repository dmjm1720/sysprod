package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.dmjm.dao.ITransportistaDao;
import com.dmjm.impl.TransportistaDaoImpl;
import com.dmjm.model.Transportista;

@Named(value = "transportistaBean")
@ViewScoped
public class TransportistaBean implements Serializable {

	private static final long serialVersionUID = 2920888710459272063L;
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
		ITransportistaDao transDao = new TransportistaDaoImpl();
		listarTransportista = transDao.listarTransportista();
		return listarTransportista;
	}

	public void guardar() {
		ITransportistaDao transDao = new TransportistaDaoImpl();
		transportista.setEstado(1);
		transDao.guardarTransportista(transportista);
		transportista = new Transportista();
	}

	public void actualizar() {	
		ITransportistaDao transDao = new TransportistaDaoImpl();
		transDao.actualizarTransportista(transportista);
		transportista = new Transportista();
	}

	public void bajaTransportista() {
		ITransportistaDao transDao = new TransportistaDaoImpl();
		transportista.setEstado(0);
		transDao.borrarTransportista(transportista);
		transportista = new Transportista();
	}
}
