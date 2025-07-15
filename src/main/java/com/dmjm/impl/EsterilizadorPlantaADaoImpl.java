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

import com.dmjm.dao.IEsterilizdorPlantaADao;
import com.dmjm.model.EsterilizadorPlantaA;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EsterilizadorPlantaADaoImpl extends Conexion implements IEsterilizdorPlantaADao {
	private static final Logger LOGGER = LogManager.getLogger(EsterilizadorPlantaADaoImpl.class.getName());

	@Override
	public List<EsterilizadorPlantaA> listaEsterilizador() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM EsterilizadorPlantaA c JOIN FETCH c.folioPreparacionEstA ORDER BY c.folioPreparacionEstA.idFolioPrep DESC",
					EsterilizadorPlantaA.class).list();
		}
	}



	@Override
	public List<EsterilizadorPlantaA> listaFiltroEsterilizador() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM EsterilizadorPlantaA c JOIN FETCH c.folioPreparacionEstA WHERE c.hora='PROM.' ORDER BY c.folioPreparacionEstA.idFolioPrep DESC",
					EsterilizadorPlantaA.class).list();
		}
	}

	@Override
	public List<EsterilizadorPlantaA> listaPorFechaEsterilizador(Date fecha) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM EsterilizadorPlantaA c JOIN FETCH c.folioPreparacionEstA WHERE c.fecha = :fecha ORDER BY c.folioPreparacionEstA.idFolioPrep DESC";
			Query<EsterilizadorPlantaA> query = session.createQuery(hql, EsterilizadorPlantaA.class);
			query.setParameter("fecha", fecha);

			return query.list();
		}
	}

	@Override
	public void guardarEsterilizador(EsterilizadorPlantaA esterilizador) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(esterilizador);
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
	public void actualizarEsterilizador(EsterilizadorPlantaA esterilizador) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(esterilizador);
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
	public void actualizarEsterilizadorPromedio(String operacion, int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A SET OPERACION=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
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
	public void actualizarPromedioGrenetina(int folio) {

		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET PORCENTAJE = (SELECT COALESCE(AVG(PORCENTAJE), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionVapor(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET PRESION_VAPOR = (SELECT COALESCE(AVG(PRESION_VAPOR), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresionVacio(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET PRESION_VACIO = (SELECT COALESCE(AVG(PRESION_VACIO), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPrecalentador(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET PRECALENTADOR = (SELECT COALESCE(AVG(PRECALENTADOR), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioEsterilizador(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET ESTERILIZADOR = (SELECT COALESCE(AVG(ESTERILIZADOR), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioValDiv(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET VALVULA_DIVERSORA = (SELECT COALESCE(AVG(VALVULA_DIVERSORA), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioBombaAlim(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET BOMBA_ALIM = (SELECT COALESCE(AVG(BOMBA_ALIM), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioTimpoEst(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET TIEMPO_EST = (SELECT COALESCE(AVG(TIEMPO_EST), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioBombaSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET BOMBA_SALIDA = (SELECT COALESCE(AVG(BOMBA_SALIDA), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioFlujoLitros(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET FLUJO_LITROS_HORA = (SELECT COALESCE(AVG(PRESION_VAPOR), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioPresioSistema(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET PRESION_SISTEMA = (SELECT COALESCE(AVG(PRESION_SISTEMA), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioRedoxEntrada(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET REDOX_PPM_ENTRADA = (SELECT COALESCE(AVG(REDOX_PPM_ENTRADA), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarPromedioRedoxSalida(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A  SET REDOX_PPM_SALIDA = (SELECT COALESCE(AVG(REDOX_PPM_SALIDA), 0) FROM ESTERILIZADOR_PLANTA_A WHERE ID_FOLIO_PREP=? AND HORA NOT IN ('PROM.')) WHERE HORA='PROM.' AND ID_FOLIO_PREP=?";
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
	public void actualizarLimpieza(int folio, int estado) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A SET ESTADO_LIMPIEZA=? WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setInt(2, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE MANTENIMIENTO: ", ex);
		}
		
	}



	@Override
	public void actualizarManto(int folio) {
		try {
			ConectarSysProd();

			String sql = "UPDATE ESTERILIZADOR_PLANTA_A SET ESTADO_MANTO=1 WHERE HORA='PROM.' AND ID_FOLIO_PREP=?;";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, folio);

			ps.executeUpdate();

			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR EL ESTADO DE MANTENIMIENTO: ", ex);
		}
		
	}

}
