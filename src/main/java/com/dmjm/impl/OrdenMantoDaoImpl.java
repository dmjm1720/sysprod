package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOrdenMantoDao;
import com.dmjm.model.OrdenMantenimiento;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoDaoImpl implements IOrdenMantoDao {

	@Override
	public List<OrdenMantenimiento> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM OrdenMantenimiento c JOIN FETCH c.folioPreparacionCocedores WHERE c.folioPreparacionCocedores.idFolioPrep = :folio";
		    Query<OrdenMantenimiento> query = session.createQuery(hql, OrdenMantenimiento.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimiento mantenimiento) {
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
	public void actualizarOrdenManto(OrdenMantenimiento OrdenMantenimiento) {
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
