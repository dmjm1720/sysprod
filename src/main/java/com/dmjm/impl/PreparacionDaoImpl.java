package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IPreparacionPielesDao;
import com.dmjm.model.PreparacionPieles;
import com.dmjm.util.HibernateUtil;

public class PreparacionDaoImpl implements IPreparacionPielesDao {

	@Override
	public List<PreparacionPieles> listaPreparacionPieles(int folio) {
		@SuppressWarnings("unchecked")
		List<PreparacionPieles> preparacion = (List<PreparacionPieles>) HibernateUtil.getSessionFactory().openSession()
				.createQuery("FROM PreparacionPieles WHERE noOperacion = :folio")
				.setParameter("folio", folio)
				.list();
				return preparacion;
	}

	@Override
	public int guardarPreparacionPieles(PreparacionPieles preparacionPieles) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(preparacionPieles);
            transaction.commit();
            String info = "Se ha registrado una nueva preparación de piel";

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
        return preparacionPieles.getIdPreparacion();

	}

	@Override
	public void actualizarPreparcionPieles(PreparacionPieles preparacionPieles) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(preparacionPieles);
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
	public List<PreparacionPieles> listaPreparacion() {
		@SuppressWarnings("unchecked")
		List<PreparacionPieles> operacion = (List<PreparacionPieles>) HibernateUtil.getSessionFactory().openSession()
				.createQuery("FROM PreparacionPieles WHERE estado != 'Finalizado'")
				.list();
				return operacion;
	}

}
