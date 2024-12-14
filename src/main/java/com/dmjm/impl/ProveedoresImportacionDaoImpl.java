package com.dmjm.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IProveedoresImportacionDao;
import com.dmjm.model.ProveedoresImportacion;
import com.dmjm.util.HibernateUtil;

public class ProveedoresImportacionDaoImpl implements IProveedoresImportacionDao {

	@Override
	public List<ProveedoresImportacion> listaProvImp() {
		@SuppressWarnings("JPQLValidation")
        List<ProveedoresImportacion> proveedores = (List<ProveedoresImportacion>) HibernateUtil.getSessionFactory().openSession().createQuery("From ProveedoresImportacion").list();
        return proveedores;
	}

	@Override
	public void guardarProvImp(ProveedoresImportacion proveedoresImportacion) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(proveedoresImportacion);
            transaction.commit();

			String info = "Se ha registrado un nuevo proveedor de importación";

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
	public void actualizarProvImp(ProveedoresImportacion proveedoresImportacion) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(proveedoresImportacion);
            transaction.commit();

			String info = "Se ha actualizado el proveedor de importación";

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
	public List<String> completeProveedorImp(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int buscarProvImp(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void borrarProvImp(ProveedoresImportacion proveedoresImportacion) {
		// TODO Auto-generated method stub
		
	}

}
