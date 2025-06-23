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

import com.dmjm.dao.IUltraFiltracionDosDao;
import com.dmjm.model.UltrafiltracionDos;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class UltraFiltracionDosDaoImpl extends Conexion implements IUltraFiltracionDosDao {
	
	private static final Logger LOGGER = LogManager.getLogger(UltraFiltracionDosDaoImpl.class.getName());

	@Override
	public List<UltrafiltracionDos> listaUltrafiltracion() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM UltrafiltracionDos c JOIN FETCH c.folioPreparacionUltraDos ORDER BY c.folioPreparacionUltraDos.idFolioPrep DESC",
					UltrafiltracionDos.class).list();
		}
	}

	@Override
	public List<UltrafiltracionDos> listaFiltroUltrafiltracion() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM UltrafiltracionDos c JOIN FETCH c.folioPreparacionUltraDos WHERE c.hora='PROM.' ORDER BY c.folioPreparacionUltraDos.idFolioPrep DESC",
					UltrafiltracionDos.class).list();
		}
	}

	@Override
	public List<UltrafiltracionDos> listaPorFechaUltrafiltracion(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM UltrafiltracionDos c JOIN FETCH c.folioPreparacionUltraDos WHERE c.fecha = :fecha ORDER BY c.folioPreparacionUltraDos.idFolioPrep DESC";
			Query<UltrafiltracionDos> query = session.createQuery(hql, UltrafiltracionDos.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarUltrafiltracion(UltrafiltracionDos ultra) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(ultra);
			transaction.commit();

			String info = "Se ha guardado un nuevo registro";

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
	public void actualizarUltrafiltracion(UltrafiltracionDos ultra) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(ultra);
			transaction.commit();

			String info = "Se ha actualizado el registro";

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
	public void actualizarUltrafiltracionPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioPresionEntrada(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_ENTRADA = (SELECT COALESCE(AVG(PRESION_ENTRADA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_SALIDA = (SELECT COALESCE(AVG(PRESION_SALIDA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoEnt(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_ENT = (SELECT COALESCE(AVG(FLUJO_ENT), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTemp01(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET TEMP_01 = (SELECT COALESCE(AVG(TEMP_01), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioConcEnt(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET CONC_ENTRADA = (SELECT COALESCE(AVG(CONC_ENTRADA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PH = (SELECT COALESCE(AVG(PH), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionBar(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_BAR = (SELECT COALESCE(AVG(PRESION_BAR), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPorcentajeBomba(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PORCENTAJE_BOMBA = (SELECT COALESCE(AVG(PORCENTAJE_BOMBA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTemp02(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET TEMP_02 = (SELECT COALESCE(AVG(TEMP_02), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionPSI01(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_PSI_01 = (SELECT COALESCE(AVG(PRESION_PSI_01), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoPerm01(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_PERM_01 = (SELECT COALESCE(AVG(FLUJO_PERM_01), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTemp03(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET TEMP_03 = (SELECT COALESCE(AVG(TEMP_03), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionPSI02(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_PSI_02 = (SELECT COALESCE(AVG(PRESION_PSI_02), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoPerm02(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_PERM_02 = (SELECT COALESCE(AVG(FLUJO_PERM_02), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTemp04(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET TEMP_04 = (SELECT COALESCE(AVG(TEMP_04), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionPSI03(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_PSI_03 = (SELECT COALESCE(AVG(PRESION_PSI_03), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoPerm03(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_PERM_03 = (SELECT COALESCE(AVG(FLUJO_PERM_03), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTemp05(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET TEMP_05 = (SELECT COALESCE(AVG(TEMP_05), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionPSI04(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET PRESION_PSI_04 = (SELECT COALESCE(AVG(PRESION_PSI_04), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoPerm04(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_PERM_04 = (SELECT COALESCE(AVG(FLUJO_PERM_04), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioConcSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET CONC_SALIDA = (SELECT COALESCE(AVG(CONC_SALIDA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioRatio(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET RATIO = (SELECT COALESCE(AVG(RATIO), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET FLUJO_SALIDA = (SELECT COALESCE(AVG(FLUJO_SALIDA), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioConcPermeado(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_DOS  SET CONC_PERMEADO = (SELECT COALESCE(AVG(CONC_PERMEADO), 0) FROM ULTRAFILTRACION_DOS WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL FOLIO: ", ex);
		}

	}

}
