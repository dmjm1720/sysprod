package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOrdenMantoEsterilizadorPalantaBDao;
import com.dmjm.model.OrdenMantenimientoEstB;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoEsterilizadorPlantaBImpl implements IOrdenMantoEsterilizadorPalantaBDao {

	@Override
	public List<OrdenMantenimientoEstB> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimientoEstB c JOIN FETCH c.folioPreparacionEstB WHERE c.folioPreparacionEstB.idFolioPrep = :folio";
		    Query<OrdenMantenimientoEstB> query = session.createQuery(hql, OrdenMantenimientoEstB.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoEstB mantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(mantenimiento);
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
	public void actualizarOrdenManto(OrdenMantenimientoEstB OrdenMantenimiento) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(OrdenMantenimiento);
			transaction.commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
