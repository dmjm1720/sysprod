package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IOrdenMantoLuwaTresDao;
import com.dmjm.model.OrdenMantenimientoLuwaTres;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoLuwaTresDaoImpl implements IOrdenMantoLuwaTresDao {

	@Override
	public List<OrdenMantenimientoLuwaTres> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM OrdenMantenimientoLuwaTres c JOIN FETCH c.folioPreparacionLuwaTres WHERE c.folioPreparacionLuwaTres.idFolioPrep = :folio";
			Query<OrdenMantenimientoLuwaTres> query = session.createQuery(hql, OrdenMantenimientoLuwaTres.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoLuwaTres mantenimiento) {
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
	public void actualizarOrdenManto(OrdenMantenimientoLuwaTres OrdenMantenimiento) {
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
	public void borrarOrdenManto(OrdenMantenimientoLuwaTres mantenimiento) {
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
