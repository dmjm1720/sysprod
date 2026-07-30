package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IResumenLavadorasDao;
import com.dmjm.model.ResumenLavadoras;
import com.dmjm.util.HibernateUtil;

public class ResumenLavadorasDaoImpl implements IResumenLavadorasDao {

	@Override
	public List<ResumenLavadoras> listaResumenLavadoras() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM ResumenLavadoras", ResumenLavadoras.class).getResultList();
		}
	}

	@Override
	public void guardarResumenLavadoras(ResumenLavadoras lavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(lavadoras);
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
	public void actualizarResumenLavadoras(ResumenLavadoras lavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(lavadoras);
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
