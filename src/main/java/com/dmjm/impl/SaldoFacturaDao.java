package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.ISaldoFacturaDao;
import com.dmjm.model.SaldoFactura;
import com.dmjm.util.HibernateUtil;

public class SaldoFacturaDao implements ISaldoFacturaDao {

	@Override
	public List<SaldoFactura> listaSaldoFactura() {
		 @SuppressWarnings("JPQLValidation")
	        List<SaldoFactura> saldoFactura = (List<SaldoFactura>) HibernateUtil.getSessionFactory().openSession().createQuery("From SaldoFactura").list();
	        return saldoFactura;
	}

	@Override
	public void guardarSaldoFactura(SaldoFactura saldoFactura) {
		 Session session = null;
	        try {

	            session = HibernateUtil.getSessionFactory().openSession();

	            Transaction transaction = session.beginTransaction();
	            session.save(saldoFactura);
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
	public void actualizarSaldoFactura(SaldoFactura saldoFactura) {
		 Session session = null;
	        try {

	            session = HibernateUtil.getSessionFactory().openSession();

	            Transaction transaction = session.beginTransaction();
	            session.update(saldoFactura);
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
