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

import com.dmjm.dao.IEtapasDao;
import com.dmjm.model.Etapas;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EtapasDaoImpl extends Conexion implements IEtapasDao {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(EtapasDaoImpl.class);

	@Override
	public List<Etapas> listaEtapas(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 1'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public void guardarEtapas(Etapas etapas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(etapas);
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
	public void actualizarEtapas(Etapas etapas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.merge(etapas);
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
	public void guardarListaEtapas(List<Etapas> lista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			for (Etapas etapas : lista) {
				session.save(etapas);
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
	public Etapas estado(Etapas et) {

		Etapas etapas = new Etapas();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND e.etapa=:etapa";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", et.getPreparacionPieles().getIdPreparacion());
			query.setParameter("etapa", et.getEtapa());
			etapas = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return etapas;

	}

	@Override
	public void actualizarSiguienteEtapa(Etapas e) {

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
			String sql = "UPDATE ETAPAS SET LAVADORA = ? WHERE ID_PREPARACION = ? AND ETAPA = ?";
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
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT ETAPA FROM ETAPAS WHERE ID_PREPARACION =? AND HORA_FIN IS NULL");
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

	@Override
	public List<Etapas> listaEtapas2(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 2'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas3(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 3'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas4(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 4'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas5(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 5'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

}
