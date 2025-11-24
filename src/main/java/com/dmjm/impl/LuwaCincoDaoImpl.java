package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILuwaCincoDao;
import com.dmjm.model.LuwaCinco;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class LuwaCincoDaoImpl extends Conexion implements ILuwaCincoDao {

	private static final Logger LOGGER = LogManager.getLogger(LuwaCincoDaoImpl.class.getName());

	@Override
	public List<LuwaCinco> listaLuwa() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM LuwaCinco l JOIN FETCH l.folioPreparacionLuwaCinco ORDER BY l.folioPreparacionLuwaCinco.idFolioPrep DESC",
					LuwaCinco.class).list();
		}
	}

	@Override
	public List<LuwaCinco> listaFiltroLuwa() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM LuwaCinco l JOIN FETCH l.folioPreparacionLuwaCinco WHERE l.hora='PROM.' ORDER BY l.folioPreparacionLuwaCinco.idFolioPrep DESC",
					LuwaCinco.class).list();
		}
	}

	@Override
	public List<LuwaCinco> listaPorFechaLuwa(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LuwaCinco l JOIN FETCH l.folioPreparacionLuwaCinco WHERE l.fecha = :fecha ORDER BY l.folioPreparacionLuwaCinco.idFolioPrep DESC";
			Query<LuwaCinco> query = session.createQuery(hql, LuwaCinco.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarLuwa(LuwaCinco luwa) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(luwa);
			transaction.commit();

			String info = "Se ha registrado un nuevo Luwa";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarLuwa(LuwaCinco luwa) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(luwa);
			transaction.commit();

			String info = "Se ha actualizado el registrado del Luwa";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarLuwaPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setString(1, operacion);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarEntradaConcentrado(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET ENTRADA_CONC = (SELECT COALESCE(AVG(ENTRADA_CONC), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarConcentradoPorcentaje(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET CONC_PORCENTAJE = (SELECT COALESCE(AVG(CONC_PORCENTAJE), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarPH(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET PH = (SELECT COALESCE(AVG(PH), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarTempSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET TEMP_SALIDA = (SELECT COALESCE(AVG(TEMP_SALIDA), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarRedox(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET REDOX = (SELECT COALESCE(AVG(REDOX), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarPresionBombaVacio(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET PRESION_BOMBA_VACIO = (SELECT COALESCE(AVG(PRESION_BOMBA_VACIO), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarValvulaRegPresion(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET VALVULA_REG_PRESION = (SELECT COALESCE(AVG(VALVULA_REG_PRESION), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarCondensadorTemp(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET CONDESADOR_TEMP = (SELECT COALESCE(AVG(CONDESADOR_TEMP), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarCorrienteMotor(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET CORRIENTE_MOTOR = (SELECT COALESCE(AVG(CORRIENTE_MOTOR), 0) FROM LUWA_CINCO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

	@Override
	public void actualizarManto(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET ESTADO_MANTO=1 WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE MANTENIMIENTO: ", ex);
		}

	}

	@Override
	public void actualizarLimpieza(int folio, int estado) {
		try {
			ConectarSysProd();

			String sql = "UPDATE LUWA_CINCO SET ESTADO_LIMPIEZA=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE LIMPIEZA: ", ex);
		}

	}

	@Override
	public List<String> listarOperaciones(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery(
	            "SELECT DISTINCT v.operacion " +
	            "FROM LuwaCinco v " +
	            "WHERE v.folioPreparacionLuwaCinco.idFolioPrep = :idFolio " +
	            "AND v.hora <> 'PROM.' " +
	            "AND v.operacion IS NOT NULL",
	            String.class)
	            .setParameter("idFolio", folio)
	            .list();
	    }
	}

	@Override
	public String obtenerPrimeraHora(String operacion, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT v.hora " + "FROM LuwaCinco v " + "WHERE v.folioPreparacionLuwaCinco.idFolioPrep = :folio "
							+ "AND v.operacion = :operacion " + "AND v.hora <> 'PROM.' " + "ORDER BY v.idLuwa ASC",
					String.class).setParameter("folio", folio).setParameter("operacion", operacion).setMaxResults(1)
					.uniqueResult(); // o .list().stream().findFirst().orElse(null)
		}
	}

	@Override
	public String obtenerUltimaHora(String operacion, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT v.hora " + "FROM LuwaCinco v " + "WHERE v.folioPreparacionLuwaCinco.idFolioPrep = :folio "
							+ "AND v.operacion = :operacion " + "AND v.hora <> 'PROM.' " + "ORDER BY v.idLuwa DESC",
					String.class).setParameter("folio", folio).setParameter("operacion", operacion).setMaxResults(1)
					.uniqueResult(); // o .list().stream().findFirst().orElse(null)
		}
	}

}
