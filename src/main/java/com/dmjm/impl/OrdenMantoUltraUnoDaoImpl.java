package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IOrdenMantoUltraUnoDao;
import com.dmjm.model.OrdenMantenimientoUltraUno;
import com.dmjm.util.HibernateUtil;

public class OrdenMantoUltraUnoDaoImpl implements IOrdenMantoUltraUnoDao {

	@Override
	public List<OrdenMantenimientoUltraUno> listaOrdenManto(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM OrdenMantenimientoUltraUno c JOIN FETCH c.folioPreparacionUltraUno WHERE c.folioPreparacionUltraUno.idFolioPrep = :folio";
			Query<OrdenMantenimientoUltraUno> query = session.createQuery(hql, OrdenMantenimientoUltraUno.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarOrdenManto(OrdenMantenimientoUltraUno mantenimiento) {
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
	public void actualizarOrdenManto(OrdenMantenimientoUltraUno OrdenMantenimiento) {
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
