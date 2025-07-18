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

import com.dmjm.dao.IUltraFiltracionUnoDao;
import com.dmjm.model.UltrafiltracionUno;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class UltraFiltracionUnoDaoImpl extends Conexion implements IUltraFiltracionUnoDao {

	private static final Logger LOGGER = LogManager.getLogger(UltraFiltracionUnoDaoImpl.class.getName());

	@Override
	public List<UltrafiltracionUno> listaUltrafiltracion() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM UltrafiltracionUno c JOIN FETCH c.folioPreparacionUltraUno ORDER BY c.folioPreparacionUltraUno.idFolioPrep DESC",
					UltrafiltracionUno.class).list();
		}
	}

	@Override
	public List<UltrafiltracionUno> listaFiltroUltrafiltracion() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM UltrafiltracionUno c JOIN FETCH c.folioPreparacionUltraUno WHERE c.hora='PROM.' ORDER BY c.folioPreparacionUltraUno.idFolioPrep DESC",
					UltrafiltracionUno.class).list();
		}
	}

	@Override
	public List<UltrafiltracionUno> listaPorFechaUltrafiltracion(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM UltrafiltracionUno c JOIN FETCH c.folioPreparacionUltraUno WHERE c.fecha = :fecha ORDER BY c.folioPreparacionUltraUno.idFolioPrep DESC";
			Query<UltrafiltracionUno> query = session.createQuery(hql, UltrafiltracionUno.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarUltrafiltracion(UltrafiltracionUno ultra) {
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
	public void actualizarUltrafiltracion(UltrafiltracionUno ultra) {
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

			String sql = "UPDATE ULTRAFILTRACION_UNO SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioFlujoEnt(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET FLUJO_ENTRADA = (SELECT COALESCE(AVG(FLUJO_ENTRADA), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET TEMP_01 = (SELECT COALESCE(AVG(TEMP_01), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET CONC_ENTRADA = (SELECT COALESCE(AVG(CONC_ENTRADA), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET TEMP_02 = (SELECT COALESCE(AVG(TEMP_02), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET PRESION_PSI = (SELECT COALESCE(AVG(PRESION_PSI), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET FLUJO_PERM = (SELECT COALESCE(AVG(FLUJO_PERM), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET CONC_SALIDA = (SELECT COALESCE(AVG(CONC_SALIDA), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET FLUJO_SALIDA = (SELECT COALESCE(AVG(FLUJO_SALIDA), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO  SET CONC_PERMEADO = (SELECT COALESCE(AVG(CONC_PERMEADO), 0) FROM ULTRAFILTRACION_UNO WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO SET ESTADO_MANTO=1 WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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

			String sql = "UPDATE ULTRAFILTRACION_UNO SET ESTADO_LIMPIEZA=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
