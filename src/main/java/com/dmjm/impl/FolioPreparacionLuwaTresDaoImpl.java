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

import com.dmjm.dao.IFolioPreparacionLuwaTresDao;
import com.dmjm.model.FolioPreparacionLuwaTres;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionLuwaTresDaoImpl extends Conexion implements IFolioPreparacionLuwaTresDao {

	@Override
	public int returnIDGuardarFolio(int folio, Date fecha) {
		Session session = null;
		FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			f.setFolioLuwaTres(folio);
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
	public FolioPreparacionLuwaTres retornarFechaActual() {
		FolioPreparacionLuwaTres f = new FolioPreparacionLuwaTres();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionLuwaTres ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionLuwaTres> query = session.createQuery(hql, FolioPreparacionLuwaTres.class);

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
			String hql = "SELECT f.folioLuwaTres FROM FolioPreparacionLuwaTres f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioLuwaTresActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionLuwaTres f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = Optional.ofNullable(query.uniqueResult()).orElse(0);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void guardarObservacion(int folio, String observacion) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd().prepareStatement(
					"UPDATE FOLIO_PREPARACION_LUWA_TRES SET OBSERVACIONES = ? WHERE ID_FOLIO_PREP = ?");
			ps.setString(1, observacion);
			ps.setLong(2, folio);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			Logger.getLogger(FolioDeImportacionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<FolioPreparacionLuwaTres> listaFolioLuwaTres(int folio) {
		List<FolioPreparacionLuwaTres> lista = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionLuwaTres WHERE idFolioPrep = :folio";
			Query<FolioPreparacionLuwaTres> query = session.createQuery(hql, FolioPreparacionLuwaTres.class);
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

}
