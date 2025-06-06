package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ILimpiezaEsterilizadorPlantaBDao;
import com.dmjm.model.LimpiezaEstB;
import com.dmjm.util.HibernateUtil;

public class LimpiezaEsterilizadorPlantaBDaoImpl implements ILimpiezaEsterilizadorPlantaBDao {

	@Override
	public List<LimpiezaEstB> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaEstB c JOIN FETCH c.folioPreparacionEstB WHERE  c.folioPreparacionEstB.idFolioPrep = :folio";
			Query<LimpiezaEstB> query = session.createQuery(hql, LimpiezaEstB.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarLimpieza(LimpiezaEstB limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limpieza);
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
	public void actualizarLimpieza(LimpiezaEstB limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(limpieza);
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
