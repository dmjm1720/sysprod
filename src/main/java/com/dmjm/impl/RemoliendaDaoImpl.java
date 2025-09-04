package com.dmjm.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IRemoliendaDao;
import com.dmjm.model.Remolienda;
import com.dmjm.util.HibernateUtil;

public class RemoliendaDaoImpl implements IRemoliendaDao {

	@Override
	public List<Remolienda> listaRemolienda() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM Remolienda c JOIN FETCH c.folioPreparacionRemolienda ORDER BY c.folioPreparacionRemolienda.idFolioPrep DESC",
					Remolienda.class).list();
		}
	}

	@Override
	public List<Remolienda> listaFiltroRemolienda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remolienda> listaPorFechaRemolienda(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Remolienda m JOIN FETCH m.folioPreparacionMolienda WHERE m.fecha = :fecha ORDER BY m.folioPreparacionMolienda.idFolioPrep DESC";
			Query<Remolienda> query = session.createQuery(hql, Remolienda.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarMolienda(Remolienda remolienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(remolienda);
			transaction.commit();

			String info = "Se ha registrado un nuevo registro de Remolienda";

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
	public void actualizarMolienda(Remolienda remolienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(remolienda);
			transaction.commit();

			String info = "Se ha actualizado el registro de Remolienda";

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
	public void borrarMolienda(Remolienda remolienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(remolienda);
			transaction.commit();

			String info = "Se ha borrado el registrado de Remolienda";

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
