package com.dmjm.impl;

import com.dmjm.dao.IEntradasDao;
import com.dmjm.model.Entradas;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EntradasDaoImpl extends Conexion implements IEntradasDao {

	private static final Logger LOGGER = LogManager.getLogger(EntradasDaoImpl.class.getName());
	Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");

	@SuppressWarnings("unchecked")
	@Override
	public List<Entradas> listarEntradas() {
		List<Entradas> entradas = (List<Entradas>) HibernateUtil.getSessionFactory().openSession()
				.createQuery("From Entradas ORDER BY idEntrada DESC").list();
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
	public void actualizarFechaImpresion(int idEntrada, String fecha) {
		try {
			ConectarSysProd();
			PreparedStatement ps = getCnSysProd().prepareStatement(
					"UPDATE ENTRADAS SET FECHA_IMPRESION_CONTADOR='" + fecha + "' WHERE ID_ENTRADA=" + idEntrada + "");
			ps.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "¡AVISO!", "FECHA DE IMPRESIÓN ACTUALIZADA"));
			LOGGER.info("FECHA DE IMPRESIÓN ACTUALIZADA: " + fecha + " POR EL USUARIO: " + us.getNombre() + " NO. DE TOLVA: " + idEntrada);
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

}
