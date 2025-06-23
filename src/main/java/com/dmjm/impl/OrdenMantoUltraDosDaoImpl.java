package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOrdenMantoUltraDosDao;
import com.dmjm.model.OrdenMantenimientoUltraDos;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoUltraDosDaoImpl implements IOrdenMantoUltraDosDao {

	@Override
	public List<OrdenMantenimientoUltraDos> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimientoUltraDos c JOIN FETCH c.folioPreparacionUltraDos WHERE c.folioPreparacionUltraDos.idFolioPrep = :folio";
		    Query<OrdenMantenimientoUltraDos> query = session.createQuery(hql, OrdenMantenimientoUltraDos.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoUltraDos mantenimiento) {
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
	public void actualizarOrdenManto(OrdenMantenimientoUltraDos OrdenMantenimiento) {
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
