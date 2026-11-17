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

import com.dmjm.dao.IResumenTraspasosDao;
import com.dmjm.model.ResumenTraspasos;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class ResumenTraspasoDaoImpl extends Conexion implements IResumenTraspasosDao {
	private static final Logger LOGGER = LogManager.getLogger(ResumenTraspasoDaoImpl.class.getName());

	@Override
	public List<ResumenTraspasos> listarResumenTraspasos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM ResumenTraspasos", ResumenTraspasos.class).list();
		}
	}

	@Override
	public void guardarResumenTraspasos(ResumenTraspasos resumenTraspasos) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumenTraspasos);
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
	public void actualizarResumenTraspasos(ResumenTraspasos resumenTraspasos) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(resumenTraspasos);
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
	public void borarResumenTraspasos(ResumenTraspasos resumenTraspasos) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(resumenTraspasos);
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
	public int validarCantidadRestante(int noOperacion) {
		int valor = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT SUM(CASE "
					+ "WHEN TIPO = 'CARGA INICIAL +' THEN KG_CANTIDAD " + "WHEN TIPO = 'ENCIMADO +' THEN KG_CANTIDAD "
					+ "WHEN TIPO = 'TRASPASO LC -' THEN -KG_CANTIDAD "
					+ "WHEN TIPO = 'TRASPASO SILOS -' THEN -KG_CANTIDAD " + "ELSE 0 " + "END" + ") AS TOTAL "
					+ "FROM RESUMEN_TRASPASOS " + "WHERE ? IN (OPERACION_ORIGEN, OPERACION_DESTINO)");
			st.setInt(1, noOperacion);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					valor = rs.getInt("TOTAL");
					
					LOGGER.info("CANTIDAD " + valor + " DE LA : " + noOperacion);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTERNER LA CANTIDAD: " + ex);
		}
		return valor;
	}

	@Override
	public int validarCantidadLavdorasChicas(int noOperacion) {
		int valor = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT"
					+ "	SUM(CASE"
					+ "		WHEN TIPO = 'ENCIMADO +' THEN KG_CANTIDAD"
					+ "		WHEN TIPO = 'TRASPASO LC -' THEN  KG_CANTIDAD"
					+ "	    WHEN TIPO = 'TRASPASO SILOS -' THEN - KG_CANTIDAD"
					+ "	    ELSE 0 END) AS TOTAL"
					+ " FROM"
					+ "	RESUMEN_TRASPASOS"
					+ " WHERE"
					+ "	OPERACION_ORIGEN = ? OR OPERACION_DESTINO = ?");
			st.setInt(1, noOperacion);
			st.setInt(2, noOperacion);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					valor = rs.getInt("TOTAL");
					
					LOGGER.info("CANTIDAD " + valor + " DE LA : " + noOperacion);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTERNER LA CANTIDAD: " + ex);
		}
		return valor;
	}

}
