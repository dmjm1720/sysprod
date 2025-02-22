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

import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class OperacionLavadorasDaoImpl extends Conexion implements IOperacionLavadorasDao {
	private static final Logger LOGGER = LogManager.getLogger(OperacionLavadorasDaoImpl.class);

	@Override
	public List<OperacionLavadoras> listaOperacionLavadoras(int id) {

		List<OperacionLavadoras> operacionLavadoras = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM OperacionLavadoras o " + "JOIN FETCH o.lavadoras l "
					+ "WHERE o.preparacionPieles.idPreparacion = :idPreparacion";
			Query<OperacionLavadoras> query = session.createQuery(hql, OperacionLavadoras.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			operacionLavadoras = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return operacionLavadoras;

	}

	@Override
	public void guardarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(operacionLavadoras);
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
	public void actualizarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(operacionLavadoras);
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
	public OperacionLavadoras listaOperacionLavadorasEtapa(int id) {

		OperacionLavadoras operacionLavadoras = new OperacionLavadoras();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM OperacionLavadoras o " + "JOIN FETCH o.lavadoras l "
					+ "WHERE o.preparacionPieles.idPreparacion = :idPreparacion";
			Query<OperacionLavadoras> query = session.createQuery(hql, OperacionLavadoras.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			operacionLavadoras = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return operacionLavadoras;
	}

	@Override
	public OperacionLavadoras operacionLavadoraId(int id) {
		OperacionLavadoras operacionLavadoras = new OperacionLavadoras();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM OperacionLavadoras o " + "JOIN FETCH o.lavadoras l "
					+ "WHERE o.preparacionPieles.idPreparacion = :idPreparacion";
			Query<OperacionLavadoras> query = session.createQuery(hql, OperacionLavadoras.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			operacionLavadoras = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return operacionLavadoras;
	}

	@Override
	public void actualizarCambioLavadora(int idPreparacion, int idLavadora) {
		try {
			ConectarSysProd();
			String sql = "UPDATE OPERACION_LAVADORAS SET ID_LAVADORA = ? WHERE ID_PREPARACION_PIELES = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, idLavadora);
			ps.setInt(2, idPreparacion);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA OPERACION_LAVADORAS: " + ex);
		}

	}

	@Override
	public int obtenerIdLavadora(int idPreparacion) {
		int idLavadora = 0;
		try {
			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT ID_LAVADORA FROM OPERACION_LAVADORAS WHERE ID_PREPARACION_PIELES = ?");
			st.setInt(1, idPreparacion);

			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {

			} else {
				while (rs.next()) {
					idLavadora = rs.getInt("ID_LAVADORA");
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTENER EL ID_LAVADORA" + "ERROR: " + ex);
		}
		return idLavadora;

	}

}
