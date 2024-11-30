package com.dmjm.impl;

import com.dmjm.dao.IProveedoresDao;
import com.dmjm.model.Proveedores;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

public class ProveedoresDaoImpl extends Conexion implements IProveedoresDao {

    @Override
    public List<Proveedores> listarProveedores() {
        @SuppressWarnings("JPQLValidation")
        List<Proveedores> proveedores = (List<Proveedores>) HibernateUtil.getSessionFactory().openSession().createQuery("From Proveedores WHERE estado=1  ORDER BY nombre").list();
        return proveedores;
    }

    @Override
    public void guardarProveedores(Proveedores proveedores) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(proveedores);
            transaction.commit();

			String info = "Se ha registrado un nuevo Proveedor";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void actualizarProveedores(Proveedores proveedores) {
    	 Session session = null;
         try {

             session = HibernateUtil.getSessionFactory().openSession();

             Transaction transaction = session.beginTransaction();
             session.update(proveedores);
             transaction.commit();
             String info = "Proveedor actualizado";

 			PrimeFaces.current()
 					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
 							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
 							+ "  timer: 8000\n" + "})");
         } catch (HibernateException e) {
        	 System.err.println(e.getMessage());
             session.getTransaction().rollback();
         } finally {
             if (session != null) {
                 session.close();
             }
         }
    }

    @Override
    public List<String> completeProveedor(String nombre) throws SQLException {
        List<String> resultRFC = new ArrayList<>();
        List<String> listarTodo = new ArrayList<>();

        ConectarSysProd();

        PreparedStatement st = getCnSysProd().prepareStatement("SELECT DISTINCT (NOMBRE) FROM PROVEEDORES WHERE NOMBRE LIKE '" + nombre + "%'");
        ResultSet rs = st.executeQuery();
        listarTodo = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            listarTodo.add("No hay resultados para tu búsqueda");
        } else {
            while (rs.next()) {
                listarTodo.add(rs.getString("NOMBRE"));
            }
        }
        for (int i = 0; i < listarTodo.size(); i++) {
            resultRFC.add(listarTodo.get(i));
        }

        CerrarSysProd();
        return resultRFC;
    }

    @Override
    public int buscarProveedor(String nombre) throws SQLException {
        ConectarSysProd();
        PreparedStatement st = getCnSysProd().prepareStatement("SELECT ID_PROVEEDOR FROM PROVEEDORES WHERE NOMBRE = '" + nombre + "'");
        ResultSet rs = st.executeQuery();
        int proveedor = 0;
        if (!rs.isBeforeFirst()) {

        } else {
            while (rs.next()) {
                proveedor = rs.getInt("ID_PROVEEDOR");
            }
        }

        CerrarSysProd();
        return proveedor;
    }

	

	@Override
	public void borrarProveedores(Proveedores proveedores) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(proveedores);
            transaction.commit();
            String info = "El proveedor se ha dado de baja correctamente";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
        } catch (HibernateException e) {
       	 System.err.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
		
	}

   

    

}
