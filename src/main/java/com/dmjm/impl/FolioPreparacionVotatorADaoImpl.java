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

import com.dmjm.dao.IFolioPreparacionVotatorADao;
import com.dmjm.model.FolioPreparacionVotatorA;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionVotatorADaoImpl extends Conexion implements IFolioPreparacionVotatorADao {

	@Override
	public int returnIDGuardarFolio(int folio) {
		Session session = null;
		FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			f.setFolioVotatorA(folio);
			f.setFecha(new Date());
			
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
	public FolioPreparacionVotatorA retornarFechaActual() {
		FolioPreparacionVotatorA f = new FolioPreparacionVotatorA();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionVotatorA ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionVotatorA> query = session.createQuery(hql, FolioPreparacionVotatorA.class);


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
			String hql = "SELECT f.folioVotatorA FROM FolioPreparacionVotatorA f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioVotatorAActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionVotatorA f WHERE f.fecha = :fecha";
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
					"UPDATE FOLIO_PREPARACION_VOTATOR_A SET OBSERVACIONES = ? WHERE ID_FOLIO_PREP = ?");
			ps.setString(1, observacion);
			ps.setLong(2, folio);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			Logger.getLogger(FolioDeImportacionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<FolioPreparacionVotatorA> listaFolioVotatorA(int folio) {
		List<FolioPreparacionVotatorA> lista = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionVotatorA WHERE idFolioPrep = :folio";
			Query<FolioPreparacionVotatorA> query = session.createQuery(hql, FolioPreparacionVotatorA.class);
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
