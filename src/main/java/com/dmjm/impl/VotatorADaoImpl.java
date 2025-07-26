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

import com.dmjm.dao.IVotatorADao;
import com.dmjm.model.VotatorA;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class VotatorADaoImpl extends Conexion implements IVotatorADao {

	private static final Logger LOGGER = LogManager.getLogger(VotatorADaoImpl.class.getName());

	@Override
	public List<VotatorA> listaVotator() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM VotatorA v JOIN FETCH c.folioPreparacionVotatorA ORDER BY v.folioPreparacionVotatorA.idFolioPrep DESC",
					VotatorA.class).list();
		}
	}

	@Override
	public List<VotatorA> listaFiltroVotator() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM VotatorA v JOIN FETCH v.folioPreparacionVotatorA WHERE v.hora='PROM.' ORDER BY v.folioPreparacionVotatorA.idFolioPrep DESC",
					VotatorA.class).list();
		}
	}

	@Override
	public List<VotatorA> listaPorFechaVotator(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM VotatorA v JOIN FETCH v.folioPreparacionVotatorA WHERE v.fecha = :fecha ORDER BY v.folioPreparacionVotatorA.idFolioPrep DESC";
			Query<VotatorA> query = session.createQuery(hql, VotatorA.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarVotator(VotatorA votator) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(votator);
			transaction.commit();

			String info = "Se ha registrado un nuevo votator";

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
	public void actualizarVotator(VotatorA votator) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(votator);
			transaction.commit();

			String info = "Se ha actualizado el votator";

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
	public void actualizarVotatorPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarConcentradoRefractometro(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET CONC_REFRACTOMENTRO = (SELECT COALESCE(AVG(CONC_REFRACTOMENTRO), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE VOTATOR_A SET PH = (SELECT COALESCE(AVG(PH), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarRedoxHumeda(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET REDOX_HUMEDA = (SELECT COALESCE(AVG(REDOX_HUMEDA), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarTempEntVotator(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET TEM_ENT_VOTATOR = (SELECT COALESCE(AVG(TEM_ENT_VOTATOR), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarTempSalVotator(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET TEMP_SAL_VOTATOR = (SELECT COALESCE(AVG(TEMP_SAL_VOTATOR), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarReceptorPSI(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET RECEPTOR_PSI = (SELECT COALESCE(AVG(RECEPTOR_PSI), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarSuccion(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET SUCCION = (SELECT COALESCE(AVG(SUCCION), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarDescarga(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET DESCARGA = (SELECT COALESCE(AVG(DESCARGA), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarAceite(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET ACEITE = (SELECT COALESCE(AVG(ACEITE), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarMotor(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET MOTOR = (SELECT COALESCE(AVG(MOTOR), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarBombaAlim(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET BOMBA_ALIM = (SELECT COALESCE(AVG(BOMBA_ALIM), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarRedoxSeco(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE VOTATOR_A SET REDOX_SECO = (SELECT COALESCE(AVG(REDOX_SECO), 0) FROM VOTATOR_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE VOTATOR_A SET ESTADO_MANTO=1 WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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

			String sql = "UPDATE VOTATOR_A SET ESTADO_LIMPIEZA=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE LIMPIEZA: ", ex);
		}

	}

}
