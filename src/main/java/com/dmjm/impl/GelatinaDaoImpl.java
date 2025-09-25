package com.dmjm.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IGelatinaDao;
import com.dmjm.model.GelatinaPorMoler;
import com.dmjm.model.Molienda;
import com.dmjm.util.HibernateUtil;

public class GelatinaDaoImpl implements IGelatinaDao {

	private static final Logger LOGGER = LogManager.getLogger(GelatinaDaoImpl.class.getName());
	
	@Override
	public List<GelatinaPorMoler> listaGeltatina(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM GelatinaPorMoler m JOIN FETCH m.folioPreparacionMolienda WHERE m.folio = :folio";
			Query<GelatinaPorMoler> query = session.createQuery(hql, GelatinaPorMoler.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarGelatina(GelatinaPorMoler gelPorMoler) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(gelPorMoler);
			transaction.commit();

			String info = "Se ha guardado un nuevo registro de Gelatina por Moler";

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
	public void actualizarGelatina(GelatinaPorMoler gelPorMoler) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(gelPorMoler);
			transaction.commit();

			String info = "Se ha actualizado el registra de Gelatina por Moler";

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
	public void borrarGelatina(GelatinaPorMoler gelPorMoler) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(gelPorMoler);
			transaction.commit();

			String info = "Se ha borrado el registro de Gelatina por Moler";

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
