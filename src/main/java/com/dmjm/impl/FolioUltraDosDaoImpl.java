package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioPreparacionUltraDosDao;
import com.dmjm.model.FolioPreparacionUltraDos;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class FolioUltraDosDaoImpl extends Conexion implements IFolioPreparacionUltraDosDao {

	@Override
	public int returnIDGuardarFolio(int folio, Date fecha) {
		Session session = null;
		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			f.setFolioUltraDos(folio);
			f.setFecha(fecha);

			session.save(f);
			transaction.commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return f.getIdFolioPrep();
	}

	@Override
	public FolioPreparacionUltraDos retornarFechaActual() {
		FolioPreparacionUltraDos f = new FolioPreparacionUltraDos();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionUltraDos ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionUltraDos> query = session.createQuery(hql, FolioPreparacionUltraDos.class);

			f = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return f;
	}

	@Override
	public int fechaFolioActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.folioUltraDos FROM FolioPreparacionUltraDos f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);

			query.setParameter("fecha", fecha);
			Optional<Integer> optionalResult = Optional.ofNullable(query.uniqueResult());
			folio = optionalResult.orElse(0); // Provide a default value

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioUltraDosActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionUltraDos f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = Optional.ofNullable(query.uniqueResult()).orElse(0);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public List<FolioPreparacionUltraDos> listaDeFolioUltraDos(int folio) {
		List<FolioPreparacionUltraDos> lista = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionUltraDos WHERE idFolioPrep = :folio";
			Query<FolioPreparacionUltraDos> query = session.createQuery(hql, FolioPreparacionUltraDos.class);
			query.setParameter("folio", folio);
			lista = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return lista;
	}

	@Override
	public void guardarObservacion(int folio, String observacion) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd().prepareStatement(
					"UPDATE FOLIO_PREPARACION_ULTRA_DOS SET OBSERVACIONES = ? WHERE ID_FOLIO_PREP = ?");
			ps.setString(1, observacion);
			ps.setLong(2, folio);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			Logger.getLogger(FolioDeImportacionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
