package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IOrdenMantenimientoMoliendaDao;
import com.dmjm.model.OrdenMantenimientoMolienda;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoMoliendaDaoImpl implements IOrdenMantenimientoMoliendaDao {

	
	@Override
	public List<OrdenMantenimientoMolienda> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimientoMolienda c JOIN FETCH c.folioPreparacionMolienda WHERE c.folioPreparacionMolienda.idFolioPrep = :folio";
		    Query<OrdenMantenimientoMolienda> query = session.createQuery(hql, OrdenMantenimientoMolienda.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}
	




	@Override
	public void guardarOrdenManto(OrdenMantenimientoMolienda orden) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(orden);
			transaction.commit();
			String info = "Se ha guardado el registro mantenimiento";

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
	public void actualizarOrdenManto(OrdenMantenimientoMolienda orden) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(orden);
			transaction.commit();
			String info = "Se ha acualizado el registro mantenimiento";

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
	public void borrarrOrdenManto(OrdenMantenimientoMolienda orden) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(orden);
			transaction.commit();
			String info = "Se ha borrado el registro mantenimiento";

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

}
