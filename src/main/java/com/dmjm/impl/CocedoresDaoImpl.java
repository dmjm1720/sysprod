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

import com.dmjm.dao.ICocedoresDao;
import com.dmjm.model.Cocedores;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class CocedoresDaoImpl extends Conexion implements ICocedoresDao {
	private static final Logger LOGGER = LogManager.getLogger(CocedoresDaoImpl.class.getName());

	@Override
	public List<Cocedores> listaCocedores() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM Cocedores c JOIN FETCH c.folioPreparacionCocedores ORDER BY c.folioPreparacionCocedores.idFolioPrep DESC",
					Cocedores.class).list();
		}
	}

	@Override
	public void guardarCocedores(Cocedores cocedores) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(cocedores);
			transaction.commit();

			String info = "Se ha registrado un nuevo cocedor";

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
	public void actualizarCocedores(Cocedores cocedores) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(cocedores);
			transaction.commit();

			String info = "Se ha actualizado el cocedor";

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
	public void actualizarPromedioGrados(int folio) {

		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET GRADOS = (SELECT COALESCE(AVG(GRADOS), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujo(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET FLUJO = (SELECT COALESCE(AVG(FLUJO), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioPH(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET PH = (SELECT COALESCE(AVG(PH), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioNTU(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET NTU = (SELECT COALESCE(AVG(NTU), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioCalcios(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CALCIOS = (SELECT COALESCE(AVG(CALCIOS), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioViscocidad(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET VISCOCIDAD = (SELECT COALESCE(AVG(VISCOCIDAD), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioCondensacion(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONDENSACION = (SELECT COALESCE(ROUND(AVG(CONDENSACION), 0),0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConcentrado(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONCENTRADO = (SELECT COALESCE(AVG(CONCENTRADO), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc01(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_01 = (SELECT COALESCE(AVG(TEMP_COC_01), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc01(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_01 = (SELECT COALESCE(AVG(CONC_COC_01), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc02(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_02 = (SELECT COALESCE(AVG(TEMP_COC_02), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc02(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_02 = (SELECT COALESCE(AVG(CONC_COC_02), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc03(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_03 = (SELECT COALESCE(AVG(TEMP_COC_03), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc03(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_03 = (SELECT COALESCE(AVG(CONC_COC_03), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc04(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_04 = (SELECT COALESCE(AVG(TEMP_COC_04), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc04(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_04 = (SELECT COALESCE(AVG(CONC_COC_04), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc05(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_05 = (SELECT COALESCE(AVG(TEMP_COC_05), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc05(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_05 = (SELECT COALESCE(AVG(CONC_COC_05), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc06(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_06 = (SELECT COALESCE(AVG(TEMP_COC_06), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc06(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_06 = (SELECT COALESCE(AVG(CONC_COC_06), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc07(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_07 = (SELECT COALESCE(AVG(TEMP_COC_07), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc07(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_07 = (SELECT COALESCE(AVG(CONC_COC_07), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc08(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_08 = (SELECT COALESCE(AVG(TEMP_COC_08), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc08(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_08 = (SELECT COALESCE(AVG(CONC_COC_08), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc09(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_09 = (SELECT COALESCE(AVG(TEMP_COC_09), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc09(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_09 = (SELECT COALESCE(AVG(CONC_COC_09), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioTempCoc10(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET TEMP_COC_10 = (SELECT COALESCE(AVG(TEMP_COC_10), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioConCoc10(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET CONC_COC_10 = (SELECT COALESCE(AVG(CONC_COC_10), 0) FROM COCEDORES WHERE ID_FOLIO_PREP=? AND HORA_LIMITES_ESPECIFICOS NOT IN ('PROM.')) WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public List<Cocedores> listaFiltroCocedores() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM Cocedores c JOIN FETCH c.folioPreparacionCocedores WHERE c.horaLimitesEspecificos='PROM.' ORDER BY c.folioPreparacionCocedores.idFolioPrep DESC",
					Cocedores.class).list();
		}
	}

	@Override
	public List<Cocedores> listaPorFechaCocedores(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Cocedores c JOIN FETCH c.folioPreparacionCocedores WHERE c.fecha = :fecha ORDER BY c.folioPreparacionCocedores.idFolioPrep DESC";
			Query<Cocedores> query = session.createQuery(hql, Cocedores.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}

	}

	@Override
	public void actualizarCocedoresPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET OPERACION=? WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarManto(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET ESTADO_MANTO=1 WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE MANTENIMIENTO: ", ex);
		}
	}

	@Override
	public void actualizarPromediosPorFila(int folio) {
		try {
			ConectarSysProd();
			if (getCnSysProd() == null) {
			    LOGGER.error("La conexión a la base de datos es nula.");
			    return;
			}

			String sql = "UPDATE COCEDORES SET CONCENTRADO = (SELECT AVG(VALOR) AS PROM FROM "
					+ "(SELECT CONC_COC_01 AS VALOR FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_01 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_02 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_02 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_03 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_03 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_04 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_04 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_05 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_05 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_06 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_06 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_07 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_07 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_08 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_08 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_09 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_09 IS NOT NULL "
					+ "UNION ALL "
					+ "SELECT CONC_COC_10 FROM COCEDORES WHERE ID_COCEDOR = ? AND CONC_COC_10 IS NOT NULL) AS DATOS) WHERE ID_COCEDOR = ?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			  // Asignar parámetros
	        for (int i = 1; i <= 11; i++) {
	            ps.setInt(i, folio);
	        }

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL PROMEDIO: ", ex);
		}

	}

	@Override
	public void actualizarLimpieza(int folio, int estado) {
		try {
			ConectarSysProd();

			String sql = "UPDATE COCEDORES SET ESTADO_LIMPIEZA=? WHERE HORA_LIMITES_ESPECIFICOS='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE MANTENIMIENTO: ", ex);
		}
		
	}

	

}
