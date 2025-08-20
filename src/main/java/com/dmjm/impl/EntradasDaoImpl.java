package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEntradasDao;
import com.dmjm.model.Entradas;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EntradasDaoImpl extends Conexion implements IEntradasDao {

	private static final Logger LOGGER = LogManager.getLogger(EntradasDaoImpl.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");


	@Override
	public List<Entradas> listarEntradas() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Entradas> query = session.createQuery("FROM Entradas ORDER BY idEntrada DESC", Entradas.class);
		List<Entradas> entradas = query.list();
		session.close();
		return entradas;
	}

	@Override
	public void guardarEntradas(Entradas entradas) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(entradas);
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
	public void actualizarEntradas(Entradas entradas) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(entradas);
			session.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "INFORMACIÓN ACTUALIZADA CORRECTAMENTE"));
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void actualizarFechaImpresion(int idEntrada, String fecha, int tolva) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS SET FECHA_IMPRESION_CONTADOR=? WHERE ID_ENTRADA=?");
			ps.setString(1, fecha);
			ps.setInt(2, idEntrada);
			ps.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "FECHA DE IMPRESIÓN ACTUALIZADA"));
			LOGGER.info("FECHA DE IMPRESIÓN ACTUALIZADA: " + fecha + " POR EL USUARIO: " + us.getNombre()
					+ " NO. DE TOLVA: " + tolva);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA FECHA DE IMPRESIÓN: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	@Override
	public Entradas validarFechaImpresion(int idEntrada) {
		Entradas entradas = new Entradas();
		entradas = (Entradas) HibernateUtil.getSessionFactory().openSession()
				.createQuery("From Entradas WHERE idEntrada=" + idEntrada + "").uniqueResult();
		return entradas;
	}

	@Override
	public void actualizarPerfilCoord(Entradas entradas) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(entradas);
			session.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "INFORMACIÓN ACTUALIZADA CORRECTAMENTE"));
		} catch (HibernateException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Entradas obtenerIdEntrada(String factura) {
		Entradas entrada = new Entradas();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			//String hql = "FROM Entradas WHERE factura = :factura";
			String hql = "FROM Entradas WHERE ticketBasculaToluca = :ticketBasculaToluca";
			Query<Entradas> query = session.createQuery(hql, Entradas.class);

			// Establecer el parámetro

			query.setParameter("ticketBasculaToluca", factura);
			entrada = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return entrada;
	}

	@Override
	public void actualizarNombreArchivoCert(int id, String nombre) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS SET ARCHIVO_CERTIFICADO=? WHERE ID_ENTRADA=?");
			ps.setString(1, nombre);
			ps.setInt(2, id);
			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.info("ERROR AL ACTUALIZAR EL NOMBRE DEL ARCHIVO: " + ex);
		}

	}

	@Override
	public String nombreArchivoCert(int id) {
		String certificado = "";
		try {

			ConectarSysProd();
			PreparedStatement st = getCnSysProd()
					.prepareStatement("SELECT ARCHIVO_CERTIFICADO FROM ENTRADAS WHERE ID_ENTRADA=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (!rs.isBeforeFirst()) {
			} else {
				while (rs.next()) {
					certificado = rs.getString("ARCHIVO_CERTIFICADO");
					LOGGER.info("NOMBRE DEL CERTIFICADO: " + certificado);
				}
			}

			CerrarSysProd();

		} catch (SQLException ex) {
			LOGGER.error("ERROR AL OBTERNER EL CERTIFICADO: " + ex);
		}
		return certificado;
	}

	@Override
	public List<Entradas> listarEntradasImportacion() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Entradas> query = session.createQuery("FROM Entradas WHERE tipoMoneda='USD' AND estatusFacturaImportacion IS NULL ORDER BY factura ASC", Entradas.class);
		List<Entradas> entradas = query.list();
		session.close();
		return entradas;
	}

	@Override
	public Entradas buscarEntradaPorFactura(String factura) {
		Entradas entrada = new Entradas();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			String hql = "FROM Entradas WHERE factura = :factura";
			Query<Entradas> query = session.createQuery(hql, Entradas.class);

			// Establecer el parámetro

			query.setParameter("factura", factura);
			entrada = query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return entrada;
	}

	@Override
	public void actualizarFacturaImportacion(String factura) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS SET ESTATUS_FACTURA_IMPORTACION=1 WHERE FACTURA=?");
			ps.setString(1, factura);
			ps.executeUpdate();
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA FACTURA:" + factura+ " ERROR: " + ex);
		}
		
	}

	@Override
	public List<Entradas> listarEntradasFactura() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Entradas> query = session.createQuery("FROM Entradas WHERE ticketBasculaToluca IS NOT NULL ORDER BY ticketBasculaToluca DESC", Entradas.class);
		List<Entradas> entradas = query.list();
		session.close();
		return entradas;
	}

	@Override
	public List<Entradas> listarEntradasPrecios() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Entradas> query = session.createQuery("FROM Entradas WHERE estado=2 ORDER BY idEntrada DESC", Entradas.class);
		List<Entradas> entradas = query.list();
		session.close();
		return entradas;
	}

}
