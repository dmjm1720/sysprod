package com.dmjm.impl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IEntradasImportacionDao;
import com.dmjm.model.EntradasImportacion;
import com.dmjm.util.HibernateUtil;

public class EntradasImportacionDaoImpl implements IEntradasImportacionDao {

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

}
