package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IOrdenMantoVotatorADao;
import com.dmjm.model.OrdenMantenimientoVotatorA;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoVotatorADaoImpl implements IOrdenMantoVotatorADao {

	@Override
	public List<OrdenMantenimientoVotatorA> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimientoVotatorA c JOIN FETCH c.folioPreparacionVotatorA WHERE c.folioPreparacionVotatorA.idFolioPrep = :folio";
		    Query<OrdenMantenimientoVotatorA> query = session.createQuery(hql, OrdenMantenimientoVotatorA.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoVotatorA mantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(mantenimiento);
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
	public void actualizarOrdenManto(OrdenMantenimientoVotatorA OrdenMantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(OrdenMantenimiento);
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
	public void borrarOrdenManto(OrdenMantenimientoVotatorA mantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(mantenimiento);
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
