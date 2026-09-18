package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEtapasDao;
import com.dmjm.model.Etapas;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EtapasDaoImpl extends Conexion implements IEtapasDao {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(EtapasDaoImpl.class);

	@Override
	public List<Etapas> listaEtapas(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 1'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public void guardarEtapas(Etapas etapas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(etapas);
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
	public void actualizarEtapas(Etapas etapas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(etapas);
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
	public void guardarListaEtapas(List<Etapas> lista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			for (Etapas etapas : lista) {
				session.save(etapas);
			}

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
	public Etapas estado(Etapas et) {

		Etapas etapas = new Etapas();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND e.etapa=:etapa";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", et.getPreparacionPieles().getIdPreparacion());
			query.setParameter("etapa", et.getEtapa());
			etapas = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return etapas;

	}

	@Override
	public void actualizarSiguienteEtapa(Etapas e) {

		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(e);
			transaction.commit();
		} catch (HibernateException ee) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarEtapaCambioLavadora(int idPreparacion, String etapa, String lavadora) {
		try {
			ConectarSysProd();
			String sql = "UPDATE ETAPAS SET LAVADORA = ? WHERE ID_PREPARACION = ? AND ETAPA = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setString(1, lavadora);
			ps.setInt(2, idPreparacion);
			ps.setString(3, etapa);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA ETAPA: " + ex);
		}

	}

	@Override
	public List<String> listaProcesosPendientesEtapa(int idPreparacion) {
		List<String> lista = new ArrayList<String>();
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT ETAPA FROM ETAPAS WHERE ID_PREPARACION =? AND HORA_FIN IS NULL");
			st.setInt(1, idPreparacion);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					lista.add(rs.getString("ETAPA"));
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return lista;
	}

	@Override
	public List<Etapas> listaEtapas2(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 2'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas3(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 3'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas4(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 4'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public List<Etapas> listaEtapas5(int id) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND estadoEtapa='Etapa 5'";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", id);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

//	@Override
//	public void actualizarSiguienteEtapa(Date fecIni, Date horaIni, boolean estado, int idEtapa, String usuario) {
//		try {
//		    ConectarSysProd();
//		    String sql = "UPDATE ETAPAS SET DIA_INICIO = ?, HORA_INICIO = ?, ESTADO = ?, OPERADOR = ? WHERE ID_ETAPA = ?";
//
//		    PreparedStatement ps = getCnSysProd().prepareStatement(sql);
//		    ps.setDate(1, new java.sql.Date(fecIni.getTime()));
//		    ps.setTime(2, new Time(horaIni.getTime()));
//		    ps.setBoolean(3, estado);
//		    ps.setString(4, usuario);
//		    ps.setInt(5, idEtapa);
//
//		    ps.executeUpdate();
//		    CerrarSysProd();
//		} catch (SQLException ex) {
//		    LOGGER.error("ERROR AL ACTUALIZAR LA ETAPA: " + ex);
//		}
//
//	}

	@Override
	public void actualizarHoraFinEtapaAnterior(Date fecFinal, int idEtapa) {
		try {
			ConectarSysProd();
			String sql = "UPDATE ETAPAS SET HORA_FIN = ? WHERE ID_ETAPA = ?";

			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setTime(1, new Time(fecFinal.getTime()));
			ps.setInt(2, idEtapa);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA ETAPA: " + ex);
		}

	}

	@Override
	public List<Etapas> listaEtapasLavadorasChicas(int idPreparacion) {
		List<Etapas> etapas = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Etapas e WHERE e.preparacionPieles.idPreparacion = :idPreparacion AND cantidadTraspaso>0";
			Query<Etapas> query = session.createQuery(hql, Etapas.class);

			// Establecer el parámetro
			query.setParameter("idPreparacion", idPreparacion);
			etapas = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return etapas;
	}

	@Override
	public int cantidadTraspaso(int idEtapa) {
		int cantidad = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT CANTIDAD_TRASPASO FROM ETAPAS WHERE ID_ETAPA =?");
			st.setInt(1, idEtapa);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					cantidad = rs.getInt("CANTIDAD_TRASPASO");
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return cantidad;
	}

	@Override
	public int cantidadMasEncimado(int idEtapa) {
		int cantidad = 0;
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd().prepareStatement("SELECT CANTIDAD FROM ETAPAS WHERE ID_ETAPA =?");
			st.setInt(1, idEtapa);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					cantidad = rs.getInt("CANTIDAD");
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}
		return cantidad;
	}

	@Override
	public void actualizarCantidad(int nuevaCantidad, int idEtapa) {
		try {
			ConectarSysProd();
			String sql = "UPDATE ETAPAS SET CANTIDAD = ? WHERE ID_ETAPA = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);

			ps.setInt(1, nuevaCantidad);
			ps.setInt(2, idEtapa);

			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA ETAPA: " + ex);
		}

	}

	@Override
	public Etapas cantidadTraspasoEncimado(int lavadora) {

		Etapas etapas = new Etapas();
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT TOP(1) ID_ETAPA, CANTIDAD FROM ETAPAS WHERE LAVADORA = ? AND CANTIDAD >0");
			st.setInt(1, lavadora);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					
					etapas.setIdEtapa(rs.getInt("ID_ETAPA"));
					etapas.setCantidad(rs.getInt("CANTIDAD"));

				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL CONSULTAR EL FOLIO: " + ex);
		}

		return etapas;
	}

}
