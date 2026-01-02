package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IDafUnoPrepFlolucolanteDao;
import com.dmjm.model.DafUnoPrepFlolucolante;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class DafUnoPrepFlolucolanteDaoImpl extends Conexion implements IDafUnoPrepFlolucolanteDao {
	private static final Logger LOGGER = LogManager.getLogger(DafUnoPrepFlolucolanteDaoImpl.class.getName());
	@Override
	public List<DafUnoPrepFlolucolante> listaDafUnoPrepFlolucolante() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM DafUnoPrepFlolucolante", DafUnoPrepFlolucolante.class).list();
		}
	}

	@Override
	public void guardarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(daf);
			transaction.commit();

			String info = "Se ha registrado un nueva preparación";

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
	public void actualizarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(daf);
			transaction.commit();

			String info = "Se ha actualizado el registro de preparación";

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
	public void borrarDafUnoPrepFlolucolante(DafUnoPrepFlolucolante daf) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(daf);
			transaction.commit();

			String info = "Se ha borrado el registro de preparación";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
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
	public int folioMax() {
		int folio = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT COALESCE(MAX(FOLIO_DAF), 0) + 1 AS FOLIO FROM  DAF_UNO_PREP_FLOLUCOLANTE;");
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					folio = rs.getInt("FOLIO");
					LOGGER.info("EL FOLIO PREPARACIÓN ES: " + folio);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return folio;
	}

	@Override
	public void actualizarFolio(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioPreparacion = :folioPreparacion WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioPreparacion", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int buscarFolio(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioPreparacion), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public List<Integer> listarFolios(int year) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT d.folioDaf FROM DafUnoPrepFlolucolante d WHERE  year(d.fecha)= :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			return query.list();

		}
	}

}
