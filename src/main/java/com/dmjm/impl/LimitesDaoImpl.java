package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILimitesDao;
import com.dmjm.model.LimitesEspecificosA;
import com.dmjm.model.LimitesEspecificosB;
import com.dmjm.model.LimitesReferenciaA;
import com.dmjm.model.LimitesReferenciaB;
import com.dmjm.model.Usuarios;
import com.dmjm.util.HibernateUtil;
import com.dmjm.util.Password;

public class LimitesDaoImpl implements ILimitesDao {

	@Override
	public List<LimitesEspecificosA> limitesEspecificosA() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM LimitesEspecificosA ORDER BY idLimite DESC", LimitesEspecificosA.class).list();
		}
	}

	@Override
	public void guardarLimitesEspA(LimitesEspecificosA limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha registrado un nuevo límite";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void actualizarLimitesEspA(LimitesEspecificosA limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(limites);
			transaction.commit();

			String info = "Se ha actualizado el límite especifíco";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<LimitesEspecificosB> limitesEspecificosB() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM LimitesEspecificosB ORDER BY idLimite DESC", LimitesEspecificosB.class).list();
		}
	}

	@Override
	public void guardarLimitesEspB(LimitesEspecificosB limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha registrado un nuevo límite";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarLimitesEspB(LimitesEspecificosB limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha actualizado el límite especifíco";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<LimitesReferenciaA> limitesReferenciaA() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM LimitesReferenciaA", LimitesReferenciaA.class).list();
		}
	}

	@Override
	public void guardarLimitesRefA(LimitesReferenciaA limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha registrado un nuevo límite de referencia";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarLimitesRefA(LimitesReferenciaA limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha actualzado el límite de referencia";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<LimitesReferenciaB> limitesReferenciaB() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM LimitesReferenciaB", LimitesReferenciaB.class).list();
		}
	}

	@Override
	public void guardarLimitesRefB(LimitesReferenciaB limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limites);
			transaction.commit();

			String info = "Se ha registrado un nuevo límite de referencia";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void actualizarLimitesRefB(LimitesReferenciaB limites) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(limites);
			transaction.commit();

			String info = "Se ha actualizado el límite de referencia";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public int folioLim(String tabla) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(folioLm), 0) + 1 FROM " + tabla + "";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public int folioRef(String tabla) {
		int folio = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT COALESCE(MAX(folioLr), 0) + 1 FROM " + tabla + "";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			folio = query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return folio;
	}

	@Override
	public LimitesEspecificosA limEspA(int folioLm) {
		LimitesEspecificosA limites = new LimitesEspecificosA();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesEspecificosA WHERE folioLm=:folioLm";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);
		q.setParameter("folioLm", folioLm);

		return (LimitesEspecificosA) q.uniqueResult();
	}

	@Override
	public LimitesEspecificosB limEspB(int folioLm) {
		LimitesEspecificosB limites = new LimitesEspecificosB();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesEspecificosB WHERE folioLm=:folioLm";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);
		q.setParameter("folioLm", folioLm);

		return (LimitesEspecificosB) q.uniqueResult();
	}

	@Override
	public LimitesReferenciaA limRefA(int folioLr) {
		LimitesReferenciaA limites = new LimitesReferenciaA();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesReferenciaA WHERE folioLr=:folioLr";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);
		q.setParameter("folioLr", folioLr);

		return (LimitesReferenciaA) q.uniqueResult();
	}

	@Override
	public LimitesReferenciaB limRefB(int folioLr) {
		LimitesReferenciaB limites = new LimitesReferenciaB();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesReferenciaB WHERE folioLr=:folioLr";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);
		q.setParameter("folioLr", folioLr);

		return (LimitesReferenciaB) q.uniqueResult();
	}

	@Override
	public LimitesEspecificosA limEspA() {
		LimitesEspecificosA limites = new LimitesEspecificosA();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesEspecificosA ORDER BY idLimite DESC ";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);

		return (LimitesEspecificosA) q.uniqueResult();
	}

	@Override
	public LimitesEspecificosB limEspB() {
		LimitesEspecificosB limites = new LimitesEspecificosB();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesEspecificosB ORDER BY idLimite DESC";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);

		return (LimitesEspecificosB) q.uniqueResult();
	}

	@Override
	public LimitesReferenciaA limRefA() {
		LimitesReferenciaA limites = new LimitesReferenciaA();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesReferenciaA ORDER BY idLimite DESC";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);


		return (LimitesReferenciaA) q.uniqueResult();
	}

	@Override
	public LimitesReferenciaB limRefB() {
		LimitesReferenciaB limites = new LimitesReferenciaB();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM LimitesReferenciaB ORDER BY idLimite DESC";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);


		return (LimitesReferenciaB) q.uniqueResult();
	}

}
