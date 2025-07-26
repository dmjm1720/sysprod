package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dmjm.dao.IFolioProcesosDao;
import com.dmjm.model.FolioProcesos;
import com.dmjm.util.HibernateUtil;

public class FolioProcesosDaoImpl implements IFolioProcesosDao {

	@Override
	public List<FolioProcesos> listaFolioProcesos() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM FolioProcesos", FolioProcesos.class).list();
		}
	}

	@Override
	public void guardarFolioProcesos(FolioProcesos f) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(f);
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
	public void actualizarFolioProcesos(FolioProcesos f) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(f);
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
	public int buscarFolio(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioEstPa), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolio(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioEstPa = :folioEstPa WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioEstPa", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int buscarFolioB(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioEstPb), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolioB(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioEstPb = :folioEstPb WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioEstPb", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int buscarFolioUltraDos(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioUltraDos), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolioUltraDos(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioUltraDos = :folioUltraDos WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioUltraDos", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int buscarFolioUltraUno(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioUltraUno), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolioUltraUno(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioUltraUno = :folioUltraUno WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioUltraUno", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int buscarFolioVotatorA(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioVotatorA), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolioVotatorA(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioVotatorA = :folioVotatorA WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioVotatorA", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int buscarFolioVotatorB(int year) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(f.folioVotatorB), 0) + 1 FROM FolioProcesos f WHERE f.year = :year";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			query.setParameter("year", year);

			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public void actualizarFolioVotatorb(int year, int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();

			String hql = "UPDATE FolioProcesos f SET f.folioVotatorB = :folioVotatorB WHERE f.year = : year";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			query.setParameter("folioVotatorA", folio);
			query.setParameter("year", year);

			query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

}
