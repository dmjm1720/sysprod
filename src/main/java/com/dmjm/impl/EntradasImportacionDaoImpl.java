package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEntradasImportacionDao;
import com.dmjm.model.EntradasImportacion;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EntradasImportacionDaoImpl extends Conexion implements IEntradasImportacionDao {

	
	private static final Logger LOGGER = LogManager.getLogger(EntradasImportacionDaoImpl.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	@Override
	public List<EntradasImportacion> listarEntradas() {
		List<EntradasImportacion> entradas = null;

		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction tx = session.beginTransaction(); // Iniciar transacción
			Query<EntradasImportacion> query = session.createQuery(
					"FROM EntradasImportacion ORDER BY idEntradaImportacion DESC", EntradasImportacion.class);
			entradas = query.list();
			tx.commit(); // Confirmar la transacción
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entradas;
	}

	@Override
	public void guardarEntradasImportacion(EntradasImportacion entradasImportacion) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(entradasImportacion);
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
	public void actualizarEntradasImportacion(EntradasImportacion entradasImportacion) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(entradasImportacion);
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
	public void actualizarEstadoImpresion(int id) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd()
					.prepareStatement("UPDATE ENTRADAS_IMPORTACION SET ESTADO_IMPRESION=1 WHERE ID_ENTRADA_IMPORTACION=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "SE HA LIBERADO LA ENTRADA DE TOLVA DE  IMPORTACIÓN"));
			LOGGER.info("ESTADO DE IMPRESIÓN ACTUALIZADA DE IMPORTACIÓN POR EL USUARIO: " + us.getNombre()
					+ " ID ENTRADA: " + id);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA FECHA DE IMPRESIÓN: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}
		
	}

}
