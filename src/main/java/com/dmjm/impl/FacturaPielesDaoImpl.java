package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IFacturaPielesDao;
import com.dmjm.model.FacturasPieles;
import com.dmjm.model.Lavadoras;
import com.dmjm.util.HibernateUtil;

public class FacturaPielesDaoImpl implements IFacturaPielesDao {

	@Override
	public List<FacturasPieles> listaFacturaPieles() {
		 @SuppressWarnings("JPQLValidation")
	        List<FacturasPieles> facturaPieles = (List<FacturasPieles>) HibernateUtil.getSessionFactory().openSession().createQuery("From FacturasPieles").list();
	        return facturaPieles;
	}

	@Override
	public void guardarFacturasPieles(FacturasPieles facturasPieles) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(facturasPieles);
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
	public void actualizarFacturasPieles(FacturasPieles facturasPieles) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(facturasPieles);
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
