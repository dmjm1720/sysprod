package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILavadorasDao;
import com.dmjm.impl.LavadorasDaoImpl;
import com.dmjm.model.Lavadoras;

@Named(value = "lavadorasBean")
@ViewScoped
public class LavadorasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Lavadoras> listarLavadoras;
	private Lavadoras lavadoras;
	private Lavadoras lavadorasEditar;
	
	private List<SelectItem> tiposLavadora;

	@PostConstruct
	public void init() {
		listarLavadoras = new ArrayList<>();
		lavadoras = new Lavadoras();
		lavadorasEditar = new Lavadoras();
		
		tiposLavadora = new ArrayList<>();
	    tiposLavadora.add(new SelectItem("Lavadora Grande", "Lavadora Grande"));
	    tiposLavadora.add(new SelectItem("Lavadora Chica", "Lavadora Chica"));
	    tiposLavadora.add(new SelectItem("Tambor", "Tambor"));
	}

	
	public List<SelectItem> getTiposLavadora() {
	    return tiposLavadora;
	}
	public Lavadoras getLavadoras() {
		return lavadoras;
	}

	public void setLavadoras(Lavadoras lavadoras) {
		this.lavadoras = lavadoras;
	}
	
	

	public Lavadoras getLavadorasEditar() {
		return lavadorasEditar;
	}

	public void setLavadorasEditar(Lavadoras lavadorasEditar) {
		this.lavadorasEditar = lavadorasEditar;
	}

	public List<Lavadoras> getListarLavadoras() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		listarLavadoras = lDao.listaLavadoras();
		return listarLavadoras;
	}

	public void guardar() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lavadoras.setEstado(0);
		lavadoras.setEtapa("Sin etapa");
		lDao.guardarLavadoras(lavadoras);
		lavadoras = new Lavadoras();
	}
	
	public void actualizar() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.actualizarLavadoras(lavadorasEditar);
		lavadorasEditar = new Lavadoras();
		lavadoras = new Lavadoras();
	}
	//"text: '¿Seguro que deseas borrar la lavadora No. "+ numero +"? Ten en cuenta que esta acción es irreversible y solo está permitida para equipos fuera de servicio', " +
	public void abrirPopup(String numero, String tipo) {
	    if(tipo.equals("Tambor")) {
	        tipo = "el " + tipo;
	    } else {
	        tipo = "la " + tipo;
	    }

	    PrimeFaces.current().executeScript(
	        "Swal.fire({ " +
	        "title: 'Confirmar eliminación', " +
	        "text: '¿Estás seguro que deseas eliminar " + tipo + " No. " + numero + "? Ten en cuenta que esta acción es irreversible y solo está permitida para equipos fuera de servicio', " +
	        "icon: 'warning', " +
	        "showCancelButton: true, " +
	        "confirmButtonText: 'Sí, borrar', " +
	        "cancelButtonText: 'No, cancelar', " +
	        "confirmButtonColor: '#d33', " +
	        "cancelButtonColor: '#3085d6', " +
	        "customClass: { popup: 'my-small-popup' } " + // clase personalizada
	        "}).then((result) => { " +
	        "if (result.isConfirmed) { " +
	        "   borrarLavadora(); " +   // invoca el método del bean
	        "} " +
	        "});"
	    );
	}

	
	public void borrarLavadora() {
		ILavadorasDao lDao = new LavadorasDaoImpl();
		lDao.borrarLavadora(lavadoras);
		lavadoras = new Lavadoras();
	}

}
