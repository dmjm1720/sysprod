package com.dmjm.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IBitacoraPreciosDao;
import com.dmjm.model.BitacoraPrecios;
import com.dmjm.util.HibernateUtil;

public class BitacoraPreciosDaoImpl implements IBitacoraPreciosDao {

	@Override
	public void guardarBitacoraPrecios(BitacoraPrecios bitPrecios) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(bitPrecios);
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
