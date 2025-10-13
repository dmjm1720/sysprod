package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IVoboMoliendaDao;
import com.dmjm.model.VoboMolienda;
import com.dmjm.util.HibernateUtil;

public class VoboDaoImpl implements IVoboMoliendaDao {

	@Override
	public List<VoboMolienda> listaVoboMolienda(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM VoboMolienda c JOIN FETCH c.folioPreparacionMolienda WHERE c.folioPreparacionMolienda.idFolioPrep = :folio";
		    Query<VoboMolienda> query = session.createQuery(hql, VoboMolienda.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarVoboMolienda(VoboMolienda vobo) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(vobo);
			transaction.commit();
			String info = "Se ha guardado el registro de Vo.Bo.";

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
	public void actualizarVoboMolienda(VoboMolienda vobo) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(vobo);
			transaction.commit();
			String info = "Se ha actualizado el registro de Vo.Bo.";

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
	public void borrarVoboMolienda(VoboMolienda vobo) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(vobo);
			transaction.commit();
			String info = "Se ha borrado el registro de Vo.Bo.";

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
	public VoboMolienda molienda(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM VoboMolienda c JOIN FETCH c.folioPreparacionMolienda WHERE c.folioPreparacionMolienda.idFolioPrep = :folio";
		    Query<VoboMolienda> query = session.createQuery(hql, VoboMolienda.class);
		    query.setParameter("folio", folio);

		    return query.uniqueResult();
		}
	}

}
