package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.ILavadorasEstatusOperacionDao;
import com.dmjm.model.LavadorasEstatusOperacion;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class LavadorasEstatusOperacionDaoImpl extends Conexion implements ILavadorasEstatusOperacionDao {

	private static final Logger LOGGER = LogManager.getLogger(LavadorasEstatusOperacionDaoImpl.class.getName());

	@Override
	public List<LavadorasEstatusOperacion> listarLavadorasEstatusOperacion() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM LavadorasEstatusOperacion", LavadorasEstatusOperacion.class).list();
		}
	}

	@Override
	public void guardarLavadorasEstatusOperacion(LavadorasEstatusOperacion lavadorasEstatusOperacion) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(lavadorasEstatusOperacion);
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
	public void actualizarLavadoraTrue(String columna, int operacion) {
		try {
			ConectarSysProd();

			// Validar que la columna es una de las permitidas
			Set<String> columnasValidas = Set.of("LAVADORA_GRANDE_26", "LAVADORA_GRANDE_27", "LAVADORA_GRANDE_28",
					"LAVADORA_GRANDE_29", "LAVADORA_GRANDE_30", "LAVADORA_GRANDE_31", "LAVADORA_GRANDE_32",
					"LAVADORA_GRANDE_33", "LAVADORA_GRANDE_34", "LAVADORA_GRANDE_35", "LAVADORA_GRANDE_36",
					"LAVADORA_GRANDE_37", "TAMBOR_4", "TAMBOR_5", "TAMBOR_6", "TAMBOR_7", "LAVADORA_CHICA_10",
					"LAVADORA_CHICA_11", "LAVADORA_CHICA_12", "LAVADORA_CHICA_13", "LAVADORA_CHICA_14",
					"LAVADORA_CHICA_15", "LAVADORA_CHICA_16");

			if (!columnasValidas.contains(columna)) {
				throw new IllegalArgumentException("Columna no permitida: " + columna);
			}

			String sql = "UPDATE LAVADORAS_ESTATUS_OPERACION SET " + columna + " = 1 WHERE OPERACION = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, operacion);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTATUS DE LA LAVADORA: ", ex);
		}
	}

	@Override
	public void actualizarLavadoraFalse(String columna, int operacion) {
		try {
			ConectarSysProd();

			// Validar que la columna es una de las permitidas
			Set<String> columnasValidas = Set.of("LAVADORA_GRANDE_26", "LAVADORA_GRANDE_27", "LAVADORA_GRANDE_28",
					"LAVADORA_GRANDE_29", "LAVADORA_GRANDE_30", "LAVADORA_GRANDE_31", "LAVADORA_GRANDE_32",
					"LAVADORA_GRANDE_33", "LAVADORA_GRANDE_34", "LAVADORA_GRANDE_35", "LAVADORA_GRANDE_36",
					"LAVADORA_GRANDE_37", "TAMBOR_4", "TAMBOR_5", "TAMBOR_6", "TAMBOR_7", "LAVADORA_CHICA_10",
					"LAVADORA_CHICA_11", "LAVADORA_CHICA_12", "LAVADORA_CHICA_13", "LAVADORA_CHICA_14",
					"LAVADORA_CHICA_15", "LAVADORA_CHICA_16");

			if (!columnasValidas.contains(columna)) {
				throw new IllegalArgumentException("Columna no permitida: " + columna);
			}

			String sql = "UPDATE LAVADORAS_ESTATUS_OPERACION SET " + columna + " = 0 WHERE OPERACION = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, operacion);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTATUS DE LA LAVADORA: ", ex);
		}
	}

	@Override
	public int validarRegistro(int operacion) {
		int valor = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT OPERACION FROM LAVADORAS_ESTATUS_OPERACION WHERE OPERACION=?");
			st.setInt(1, operacion);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
				valor = -1;
			} else {
				while (rs.next()) {
					valor = rs.getInt("OPERACION");
					LOGGER.info("OPERACIÓN REGISTRADA ANTERIORMENTE: " + operacion);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTERNER EL CERTIFICADO: " + ex);
		}
		return valor;
	}

}
