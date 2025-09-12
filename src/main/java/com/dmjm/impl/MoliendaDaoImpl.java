package com.dmjm.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IMoliendaDao;
import com.dmjm.model.Molienda;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class MoliendaDaoImpl extends Conexion implements IMoliendaDao {

	@Override
	public List<Molienda> listaMolienda() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM Molienda c JOIN FETCH c.folioPreparacionMolienda ORDER BY c.folioPreparacionMolienda.idFolioPrep DESC",
					Molienda.class).list();
		}
	}

	@Override
	public List<Molienda> listaFiltroMolienda() {
		return null;
	}

	@Override
	public List<Molienda> listaPorFechaMolienda(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Molienda m JOIN FETCH m.folioPreparacionMolienda WHERE m.fecha = :fecha ORDER BY m.folioPreparacionMolienda.idFolioPrep DESC";
			Query<Molienda> query = session.createQuery(hql, Molienda.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}

	}

	@Override
	public void guardarMolienda(Molienda molienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(molienda);
			transaction.commit();

			String info = "Se ha guardado un nuevo registro de Molienda";

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
	public void actualizarMolienda(Molienda molienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(molienda);
			transaction.commit();

			String info = "Se ha actualizado el registro de Molienda";

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
	public void borrarMolienda(Molienda molienda) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(molienda);
			transaction.commit();

			String info = "Se ha borrado el registrado de Molienda";

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
