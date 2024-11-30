package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IOperacionLavadorasDao;
import com.dmjm.model.OperacionLavadoras;
import com.dmjm.util.HibernateUtil;

public class OperacionLavadorasDaoImpl implements IOperacionLavadorasDao {

	@Override
	public List<OperacionLavadoras> listaOperacionLavadoras() {
		@SuppressWarnings("JPQLValidation")
		List<OperacionLavadoras> operacionLavadoras = (List<OperacionLavadoras>) HibernateUtil.getSessionFactory()
				.openSession().createQuery("From OperacionLavadoras").list();
		return operacionLavadoras;
	}

	@Override
	public void guardarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(operacionLavadoras);
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
	public void actualizarOperacionLavadoras(OperacionLavadoras operacionLavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(operacionLavadoras);
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
