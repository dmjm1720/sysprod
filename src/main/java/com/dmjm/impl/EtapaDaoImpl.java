package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.model.Etapa1;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EtapaDaoImpl extends Conexion implements IEtapa1Dao {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(EtapaDaoImpl.class);

	@Override
	public List<Etapa1> listaEtapa1(int id) {
		List<Etapa1> etapa1 = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapa1 e WHERE e.preparacionPieles.idPreparacion = :idPreparacion";
			Query<Etapa1> query = session.createQuery(hql, Etapa1.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapa1 = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapa1;
	}

	@Override
	public void guardarEtapa1(Etapa1 etapa1) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(etapa1);
			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarEtapa1(Etapa1 etapa1) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.merge(etapa1);
			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void guardarListaEtapas(List<Etapa1> lista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			for (Etapa1 etapa1 : lista) {
				session.save(etapa1);
			}

			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Etapa1 estado(Etapa1 et) {

		Etapa1 etapa1 = new Etapa1();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapa1 e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND e.etapa=:etapa";
			Query<Etapa1> query = session.createQuery(hql, Etapa1.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", et.getPreparacionPieles().getIdPreparacion());
			query.setParameter("etapa", et.getEtapa());
			etapa1 = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return etapa1;

	}

	@Override
	public void actualizarSiguienteEtapa(Etapa1 e) {

		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(e);
			transaction.commit();
		} catch (HibernateException ee) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarEtapaCambioLavadora(int idPreparacion, String etapa, String lavadora) {
		try {
			ConectarSysProd();
			String sql = "UPDATE ETAPA1 SET LAVADORA = ? WHERE ID_PREPARACION = ? AND ETAPA = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setString(1, lavadora);
			ps.setInt(2, idPreparacion);
			ps.setString(3, etapa);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA ETAPA: " + ex);
		}

	}

	@Override
	public List<String> listaProcesosPendientesEtapa(int idPreparacion) {
		List<String> lista = new ArrayList<String>();
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT ETAPA FROM ETAPA1 WHERE ID_PREPARACION =? AND HORA_FIN IS NULL");
			st.setInt(1, idPreparacion);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					lista.add(rs.getString("ETAPA"));
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return lista;
	}

}
