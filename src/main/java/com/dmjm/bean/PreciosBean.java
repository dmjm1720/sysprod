package com.dmjm.bean;

import com.dmjm.dao.IMateriaDao;
import com.dmjm.dao.IPreciosDao;
import com.dmjm.dao.IProveedoresDao;
import com.dmjm.impl.MateriaDaoImpl;
import com.dmjm.impl.PreciosDaoImpl;
import com.dmjm.impl.ProveedoresDaoImpl;
import com.dmjm.model.Materia;
import com.dmjm.model.Precios;
import com.dmjm.model.Proveedores;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named(value = "preciosBean")
@ViewScoped
public class PreciosBean implements Serializable {

    private List<Precios> listarPrecios;
    private Precios precios;

    private String filterProveedor;
    private String filterMateria;

    private Proveedores proveedores;
    private Materia materia;

    @PostConstruct
    public void init() {
        listarPrecios = new ArrayList<>();
        precios = new Precios();
        proveedores = new Proveedores();
        materia = new Materia();
        precios.setFechaActualizacion(new Date());
    }

    public List<Precios> getListarPrecios() {
        IPreciosDao pDao = new PreciosDaoImpl();
        listarPrecios = pDao.listarPrecios();
        return listarPrecios;
    }

    public Precios getPrecios() {
        return precios;
    }

    public void setPrecios(Precios precios) {
        this.precios = precios;
    }

    public String getFilterProveedor() {
        return filterProveedor;
    }

    public void setFilterProveedor(String filterProveedor) {
        this.filterProveedor = filterProveedor;
    }

    public String getFilterMateria() {
        return filterMateria;
    }

    public void setFilterMateria(String filterMateria) {
        this.filterMateria = filterMateria;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void guardar() throws SQLException {
        IPreciosDao pDao = new PreciosDaoImpl();
        proveedores.setIdProveedor(buscarProveedor(filterProveedor));
        materia.setIdMateria(buscarMateria(filterMateria));

        precios.setProveedores(proveedores);
        precios.setMateria(materia);

        pDao.guardarPrecios(precios);
        precios = new Precios();
        proveedores = new Proveedores();
        materia = new Materia();
        filterMateria = null;
        filterProveedor = null;
        
        
        
        String info = "SE HA ACTUALIZADO EL PRECIO CORRECTAMENTE";
        PrimeFaces.current()
                .executeScript("Swal.fire({\n"
                        + "  position: 'top-center',\n"
                        + "  icon: 'success',\n"
                        + "  title: '¡Aviso!',\n"
                        + "  text: '" + info + "',\n"
                        + "  showConfirmButton: false,\n"
                        + "  timer: 8000\n"
                        + "})");
    }

    //**DATOS DEL PROVEEDOR, NOMBRE**//
    public List<String> buscarNombreProveedor(String nombre) throws SQLException {
        IProveedoresDao pDao = new ProveedoresDaoImpl();
        return pDao.completeProveedor(nombre);
    }

    //**DATOS DEL PROVEEDOR, ID**//
    public int buscarProveedor(String nombre) throws SQLException {
        IProveedoresDao pDao = new ProveedoresDaoImpl();
        return pDao.buscarProveedor(nombre);
    }

    //**DATOS DE LA MATERIA PRIMA, NOMBRE**//
    public List<String> buscarNombreMateria(String nombre) throws SQLException {
        IMateriaDao mDao = new MateriaDaoImpl();
        return mDao.completeMateria(nombre);
    }

    //**DATOS DE LA MATERIA PRIMA, ID**//
    public int buscarMateria(String nombre) throws SQLException {
        IMateriaDao mDao = new MateriaDaoImpl();
        return mDao.buscarMateria(nombre);
    }

}