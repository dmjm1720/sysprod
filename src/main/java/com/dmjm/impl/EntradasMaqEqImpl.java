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

import com.dmjm.dao.IEntradasMaqEqDao;
import com.dmjm.model.EntradasMaquinariaEquipo;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class EntradasMaqEqImpl extends Conexion implements IEntradasMaqEqDao {

	private static final Logger LOGGER = LogManager.getLogger(EntradasMaqEqImpl.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
	@Override
	public List<EntradasMaquinariaEquipo> listarEntradas() {
		List<EntradasMaquinariaEquipo> entradas = null;

		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			Transaction tx = session.beginTransaction(); // Iniciar transacción
			Query<EntradasMaquinariaEquipo> query = session.createQuery(
					"FROM EntradasMaquinariaEquipo ORDER BY idMaqEq DESC", EntradasMaquinariaEquipo.class);
			entradas = query.list();
			tx.commit(); // Confirmar la transacción
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entradas;
	}

	@Override
	public void guardarEntradasImportacion(EntradasMaquinariaEquipo entradasMaquinariaEquipo) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(entradasMaquinariaEquipo);
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
	public void actualizarEntradasImportacion(EntradasMaquinariaEquipo entradasMaquinariaEquipo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(entradasMaquinariaEquipo);
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
					.prepareStatement("UPDATE ENTRADAS_MAQUINARIA_EQUIPO SET ESTADO_IMPRESION=1 WHERE ID_MAQ_EQ=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "SE HA LIBERADO LA ENTRADA DE MAQUINARIA Y EQUIPO"));
			LOGGER.info("ESTADO DE IMPRESIÓN ACTUALIZADA DE MAQ Y EQ POR EL USUARIO: " + us.getNombre()
					+ " ID ENTRADA: " + id);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA FECHA DE IMPRESIÓN: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}
		
	}

}
