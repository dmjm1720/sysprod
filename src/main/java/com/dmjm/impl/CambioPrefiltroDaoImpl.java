package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.ICambioPrefiltroDao;
import com.dmjm.model.CambioPrefiltro;
import com.dmjm.util.HibernateUtil;

public class CambioPrefiltroDaoImpl implements ICambioPrefiltroDao {

	@Override
	public List<CambioPrefiltro> listarCambioPrefiltro(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM CambioPrefiltro c JOIN FETCH c.folioPreparacionUltraDos WHERE  c.folioPreparacionUltraDos.idFolioPrep = :folio";
			Query<CambioPrefiltro> query = session.createQuery(hql, CambioPrefiltro.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarCambioPre(CambioPrefiltro cambio) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(cambio);
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
	public void actualizarCambioPre(CambioPrefiltro cambio) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(cambio);
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
