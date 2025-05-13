package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IFolioCocedores;
import com.dmjm.model.FolioCocedores;
import com.dmjm.util.HibernateUtil;

public class FoliosCocedoresDaoImpl implements IFolioCocedores {

	@Override
	public List<FolioCocedores> listaFolioCocedores() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM FolioCocedores", FolioCocedores.class).list();
		}
	}

	@Override
	public void guardarFolioCocedores(FolioCocedores fc) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(fc);
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
	public void actualizarFolioCocedores(FolioCocedores fc) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(fc);
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
