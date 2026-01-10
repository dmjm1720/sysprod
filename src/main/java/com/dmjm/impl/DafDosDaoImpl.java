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

import com.dmjm.dao.IDafDosDao;
import com.dmjm.model.DafDos;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class DafDosDaoImpl extends Conexion implements IDafDosDao {
	private static final Logger LOGGER = LogManager.getLogger(DafDosDaoImpl.class.getName());
	@Override
	public List<DafDos> listaDaf() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM DafDos l JOIN FETCH l.folioPreparacionDafDos ORDER BY l.folioPreparacionDafDos.idFolioPrep DESC",
					DafDos.class).list();
		}
	}

	@Override
	public List<DafDos> listaFiltroDaf() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM DafDos l JOIN FETCH l.folioPreparacionDafDos WHERE l.hora='PROM.' ORDER BY l.folioPreparacionDafDos.idFolioPrep DESC",
					DafDos.class).list();
		}
	}

	@Override
	public List<DafDos> listaPorFechaDaf(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM DafDos l JOIN FETCH l.folioPreparacionDafDos WHERE l.fecha = :fecha ORDER BY l.folioPreparacionDafDos.idFolioPrep DESC";
			Query<DafDos> query = session.createQuery(hql, DafDos.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarDaf(DafDos daf) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(daf);
			transaction.commit();

			String info = "Se ha registrado un nuevo DAF";

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
	public void actualizarDaf(DafDos daf) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(daf);
			transaction.commit();

			String info = "Se ha actualizado el registro del DAF";

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
	public void actualizarDafPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarConcentradoPorcentaje(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET CONC_PORCENTAJE = (SELECT COALESCE(AVG(CONC_PORCENTAJE), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarFlujoGrenetina(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET FLUJO_GRENETINA = (SELECT COALESCE(AVG(FLUJO_GRENETINA), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE DAF_DOS SET PH = (SELECT COALESCE(AVG(PH), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarNTUEntrada(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET NTU_ENTRADA = (SELECT COALESCE(AVG(NTU_ENTRADA), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarNTUSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET NTU_SALIDA = (SELECT COALESCE(AVG(NTU_SALIDA), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPresionAire(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET PRESION_AIRE = (SELECT COALESCE(AVG(PRESION_AIRE), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarFrecBombaFlolucolante(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE DAF_DOS SET FREC_BOMBA_FLOLUCOLANTE = (SELECT COALESCE(AVG(FREC_BOMBA_FLOLUCOLANTE), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE DAF_DOS SET VALVULA_REG_PRESION = (SELECT COALESCE(AVG(VALVULA_REG_PRESION), 0) FROM DAF_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE DAF_DOS SET ESTADO_MANTO=1 WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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

			String sql = "UPDATE DAF_DOS SET ESTADO_LIMPIEZA=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	            "FROM DafDos v " +
	            "WHERE v.folioPreparacionDafDos.idFolioPrep = :idFolio " +
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
					"SELECT v.hora " + "FROM DafDos v " + "WHERE v.folioPreparacionDafDos.idFolioPrep = :folio "
							+ "AND v.operacion = :operacion " + "AND v.hora <> 'PROM.' " + "ORDER BY v.idLuwa ASC",
					String.class).setParameter("folio", folio).setParameter("operacion", operacion).setMaxResults(1)
					.uniqueResult(); // o .list().stream().findFirst().orElse(null)
		}
	}

	@Override
	public String obtenerUltimaHora(String operacion, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"SELECT v.hora " + "FROM DafDos v " + "WHERE v.folioPreparacionDafDos .idFolioPrep = :folio "
							+ "AND v.operacion = :operacion " + "AND v.hora <> 'PROM.' " + "ORDER BY v.idLuwa DESC",
					String.class).setParameter("folio", folio).setParameter("operacion", operacion).setMaxResults(1)
					.uniqueResult(); // o .list().stream().findFirst().orElse(null)
		}
	}

	

}
