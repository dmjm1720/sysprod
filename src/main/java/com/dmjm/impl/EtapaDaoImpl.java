package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.bean.LoginBean;
import com.dmjm.dao.IEtapa1Dao;
import com.dmjm.model.Etapa1;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EtapaDaoImpl extends Conexion implements IEtapa1Dao {
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
			System.out.println("ID "+etapa1.getIdEtapa());
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		
		
//		String estado = "";
//		try {
//
//			ConectarSysProd();
//			PreparedStatement st = getCnSysProd().prepareStatement(
//					"SELECT ESTADO FROM ETAPA1 WHERE ID_PREPARACION=" + et.getPreparacionPieles().getIdPreparacion()
//							+ " AND ETAPA ='" + et.getEtapa() + "' AND ESTADO=0");
//			ResultSet rs = st.executeQuery();
//
//			if (!rs.isBeforeFirst()) {
//				estado = "2";
//			} else {
//				while (rs.next()) {
//					estado = rs.getString("ESTADO");
//					LOGGER.info("EL ESTADO DE LA CONSULTA ES: " + estado);
//				}
//			}
//
//			CerrarSysProd();
//
//		} catch (SQLException ex) {
//			LOGGER.error("ERROR AL CONSULTAR EL ESTADO: " + ex);
//		}
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
//		try {
//			ConectarSysProd();
//			PreparedStatement ps = getCnSysProd()
//					.prepareStatement("UPDATE ETAPA1 SET ESTADO = 'TRUE', DIA_INICIO="+ e.getDiaInicio() + ", HORA_INICIO="+ e.getHoraInicio() +" WHERE ID_PREPARACION="
//							+ e.getPreparacionPieles().getIdPreparacion() + " AND ETAPA ='" + e.getEtapa() + "'");
//			ps.executeUpdate();
//			CerrarSysProd();
//			LOGGER.info("SE HA ACTUALIZADO EL ESTADO DE LA ETAPA " + e.getEtapa() + " ID_PREPARACION: "
//					+ e.getPreparacionPieles().getIdPreparacion());
//		} catch (SQLException ex) {
//			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE LA ETAPA: " + ex);
//		}

	}

}
