package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ITraspasoSilosDao;
import com.dmjm.model.TraspasoSilos;
import com.dmjm.util.HibernateUtil;

public class TraspasoDaoImpl implements ITraspasoSilosDao {

	@Override
	public List<TraspasoSilos> listaTraspasos(int idPreparacion) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM TraspasoSilos WHERE idOperacion = :idOperacion";
			Query<TraspasoSilos> query = session.createQuery(hql, TraspasoSilos.class);
			query.setParameter("idOperacion", idPreparacion);

			return query.list();
		}
	}

	@Override
	public void guardarTraspasoSilos(TraspasoSilos trasSilos) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(trasSilos);
			transaction.commit();

			String info = "Se ha registrado un nuevo traspaso a Silos";

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
	public void actualizarTraspasoSilos(TraspasoSilos trasSilos) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(trasSilos);
			transaction.commit();

			String info = "Se ha actualizar el traspaso a Silos";

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
