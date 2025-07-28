package com.dmjm.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IResumenVotatorADao;
import com.dmjm.model.ResumenVotatorA;
import com.dmjm.util.HibernateUtil;

public class ResumenVotatorADaoImpl implements IResumenVotatorADao {
	private static final Logger LOGGER = LogManager.getLogger(ResumenVotatorADaoImpl.class.getName());

	@Override
	public void guardarResumen(ResumenVotatorA resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha registrado un nuevo votator";

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

			List<ResumenVotatorA> resumenes = session
					.createQuery("FROM ResumenVotatorA r WHERE r.folioPreparacionVotatorA.folioVotatorA = :folio",
							ResumenVotatorA.class)
					.setParameter("folio", folio).getResultList();

			for (ResumenVotatorA r : resumenes) {
				session.delete(r);
			}

			transaction.commit();
			LOGGER.info("VOTATOR A, FOLIO DEL RESUMEN ENCONTRADO Y BORRADO: " + folio);

		} catch (HibernateException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			LOGGER.fatal("VOTATOR A, NO SE PUDO BORRAR EL FOLIO DEL RESUMEN: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
