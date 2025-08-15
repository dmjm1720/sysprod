package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICambioPrefiltroDao;
import com.dmjm.model.CambioPrefiltro;
import com.dmjm.util.HibernateUtil;

public class CambioPrefiltroDaoImpl implements ICambioPrefiltroDao {

	@Override
	public List<CambioPrefiltro> listarCambioPrefiltro(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM CambioPrefiltro c JOIN FETCH c.folioPreparacionUltraDos WHERE  c.folioPreparacionUltraDos.idFolioPrep = :folio";
			Query<CambioPrefiltro> query = session.createQuery(hql, CambioPrefiltro.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarCambioPre(CambioPrefiltro cambio) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(cambio);
			transaction.commit();
			String info = "Se ha registrado un nuevo prefiltro";

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
	public void actualizarCambioPre(CambioPrefiltro cambio) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(cambio);
			transaction.commit();
			String info = "Se ha actualizado la información del prefiltro";

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
	public void borrarrCambioPre(CambioPrefiltro cambio) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(cambio);
			transaction.commit();
			String info = "Se ha borrado la información del  prefiltro";

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
