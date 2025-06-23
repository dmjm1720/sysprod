package com.dmjm.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IRegistroTurnosDao;
import com.dmjm.model.RegistroTurnos;
import com.dmjm.util.HibernateUtil;

public class RegistroTurnoDaoImpl implements IRegistroTurnosDao {

	@Override
	public List<RegistroTurnos> listaRegistroTurnos(Date fecha) {
		List<RegistroTurnos> lista = null;
	    Transaction t = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        t = session.beginTransaction();

	        String hql = "FROM RegistroTurnos WHERE fecha = :fecha AND descProceso = 'COCEDORES'";
			Query<RegistroTurnos> query = session.createQuery(hql, RegistroTurnos.class);
	        query.setParameter("fecha", fecha);

	        lista = query.list();
	        t.commit();
	    } catch (HibernateException e) {
	        if (t != null) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public void guardaRegistroTurnos(RegistroTurnos registro) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(registro);
			transaction.commit();
			String info = "Registro de turno agregado correctamente";

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
	public void actualizarRegistroTurnos(RegistroTurnos registro) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(registro);
			transaction.commit();
			String info = "Turno actualizado correctamente";

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
	public List<RegistroTurnos> listaRegistroTurnosEstA(Date fecha) {
		List<RegistroTurnos> lista = null;
	    Transaction t = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        t = session.beginTransaction();

	        String hql = "FROM RegistroTurnos WHERE fecha = :fecha AND descProceso = 'ESTERILIZADOR PLANTA A'";
			Query<RegistroTurnos> query = session.createQuery(hql, RegistroTurnos.class);
	        query.setParameter("fecha", fecha);

	        lista = query.list();
	        t.commit();
	    } catch (HibernateException e) {
	        if (t != null) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public List<RegistroTurnos> listaRegistroTurnosEstB(Date fecha) {
		List<RegistroTurnos> lista = null;
	    Transaction t = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        t = session.beginTransaction();

	        String hql = "FROM RegistroTurnos WHERE fecha = :fecha AND descProceso = 'ESTERILIZADOR PLANTA B'";
			Query<RegistroTurnos> query = session.createQuery(hql, RegistroTurnos.class);
	        query.setParameter("fecha", fecha);

	        lista = query.list();
	        t.commit();
	    } catch (HibernateException e) {
	        if (t != null) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public List<RegistroTurnos> listaRegistroTurnosUltraUno(Date fecha) {
		List<RegistroTurnos> lista = null;
	    Transaction t = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        t = session.beginTransaction();

	        String hql = "FROM RegistroTurnos WHERE fecha = :fecha AND descProceso = 'ULTRAFILTRACIÓN UNO'";
			Query<RegistroTurnos> query = session.createQuery(hql, RegistroTurnos.class);
	        query.setParameter("fecha", fecha);

	        lista = query.list();
	        t.commit();
	    } catch (HibernateException e) {
	        if (t != null) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public List<RegistroTurnos> listaRegistroTurnosUltraDos(Date fecha) {
		List<RegistroTurnos> lista = null;
	    Transaction t = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        t = session.beginTransaction();

	        String hql = "FROM RegistroTurnos WHERE fecha = :fecha AND descProceso = 'ULTRAFILTRACIÓN DOS'";
			Query<RegistroTurnos> query = session.createQuery(hql, RegistroTurnos.class);
	        query.setParameter("fecha", fecha);

	        lista = query.list();
	        t.commit();
	    } catch (HibernateException e) {
	        if (t != null) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    }
	    return lista;
	}

	@Override
	public void borrarRegistroTurno(RegistroTurnos registro) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(registro);
			transaction.commit();
			String info = "Registro de turno borrado correctamente";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
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

}
