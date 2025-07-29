package com.dmjm.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IResumenVotatorBDao;
import com.dmjm.model.ResumenVotatorB;
import com.dmjm.util.HibernateUtil;

public class ResumenVotatorBDaoImpl implements IResumenVotatorBDao {
	private static final Logger LOGGER = LogManager.getLogger(ResumenVotatorBDaoImpl.class.getName());

	@Override
	public void guardarResumen(ResumenVotatorB resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Votato A";

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
	public void borrarResumen(int folio) {

		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			List<ResumenVotatorB> resumenes = session
					.createQuery("FROM ResumenVotatorB r WHERE r.folioPreparacionVotatorB.folioVotatorB = :folio",
							ResumenVotatorB.class)
					.setParameter("folio", folio).getResultList();

			for (ResumenVotatorB r : resumenes) {
				session.delete(r);
			}

			transaction.commit();
			LOGGER.info("VOTATOR B, FOLIO DEL RESUMEN ENCONTRADO Y BORRADO: " + folio);

		} catch (HibernateException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			LOGGER.fatal("VOTATOR B, NO SE PUDO BORRAR EL FOLIO DEL RESUMEN: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<ResumenVotatorB> listaResumen(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM ResumenVotatorB r JOIN FETCH r.folioPreparacionVotatorB WHERE r.folioPreparacionVotatorB.idFolioPrep = :folio";
		    Query<ResumenVotatorB> query = session.createQuery(hql, ResumenVotatorB.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}
}
