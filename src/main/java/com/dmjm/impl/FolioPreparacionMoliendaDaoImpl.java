package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioPreparacionMoliendaDao;
import com.dmjm.model.FolioPreparacionMolienda;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class FolioPreparacionMoliendaDaoImpl extends Conexion implements IFolioPreparacionMoliendaDao {

	@Override
	public int returnIDGuardarFolio(int folio, Date fecha) {
		Session session = null;
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			fpc.setFolioMolienda(folio);
			fpc.setFecha(fecha);

			session.save(fpc);
			transaction.commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return fpc.getIdFolioPrep();
	}

	@Override
	public FolioPreparacionMolienda retornarFechaActual() {
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionMolienda ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionMolienda> query = session.createQuery(hql, FolioPreparacionMolienda.class);

			fpc = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return fpc;
	}

	@Override
	public int fechaFolioActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.folioMolienda FROM FolioPreparacionMolienda f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);
			Integer result = query.uniqueResult();
	        if (result != null) {
	            folio = result;
	        }
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioMoliendaActual(Date fecha) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT f.idFolioPrep FROM FolioPreparacionMolienda f WHERE f.fecha = :fecha";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("fecha", fecha);
			Integer result = query.uniqueResult();
	        if (result != null) {
	            folio = result;
	        }
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
					"UPDATE FOLIO_PREPARACION_MOLIENDA SET OBSERVACIONES = ? WHERE ID_FOLIO_PREP = ?");
			ps.setString(1, observacion);
			ps.setLong(2, folio);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			Logger.getLogger(FolioPreparacionMolienda.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<FolioPreparacionMolienda> listaFolioMolienda(int folio) {
		List<FolioPreparacionMolienda> lista = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionMolienda WHERE idFolioPrep = :folio";
			Query<FolioPreparacionMolienda> query = session.createQuery(hql, FolioPreparacionMolienda.class);
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
	public FolioPreparacionMolienda folioMoliendaMinimo() {
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionMolienda ORDER BY idFolioPrep ASC";
			Query<FolioPreparacionMolienda> query = session.createQuery(hql, FolioPreparacionMolienda.class);

			fpc = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return fpc;
	}

	@Override
	public FolioPreparacionMolienda folioMoliendaMaximo() {
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionMolienda ORDER BY idFolioPrep DESC";
			Query<FolioPreparacionMolienda> query = session.createQuery(hql, FolioPreparacionMolienda.class);

			fpc = query.setMaxResults(1).getSingleResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return fpc;
	}

	@Override
	public FolioPreparacionMolienda folioMoliendaFiltro(int folio) {
		FolioPreparacionMolienda fpc = new FolioPreparacionMolienda();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM FolioPreparacionMolienda WHERE folioMolienda = :folio";
			Query<FolioPreparacionMolienda> query = session.createQuery(hql, FolioPreparacionMolienda.class);
			query.setParameter("folio", folio);
			List<FolioPreparacionMolienda> resultados = query.setMaxResults(1).getResultList();
			if (!resultados.isEmpty()) {
			    fpc = resultados.get(0);
			} else {
			    fpc = null; 
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return fpc;
	}

}
