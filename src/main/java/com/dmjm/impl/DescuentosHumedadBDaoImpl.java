package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IDescuentoHumedadBDao;
import com.dmjm.model.DescuentoHumedadTablaB;
import com.dmjm.util.HibernateUtil;

public class DescuentosHumedadBDaoImpl implements IDescuentoHumedadBDao {

	@Override
	public List<DescuentoHumedadTablaB> listarDescuentos() {
		@SuppressWarnings("JPQLValidation")
        List<DescuentoHumedadTablaB> descuentos = (List<DescuentoHumedadTablaB>) HibernateUtil.getSessionFactory().openSession().createQuery("From DescuentoHumedadTablaB").list();
        return descuentos;
	}

	@Override
	public void guardarHumedadB(DescuentoHumedadTablaB humedadTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(humedadTablaB);
            transaction.commit();

			String info = "Se ha registrado un nuevo descuento";

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
	public void actualizarHumedadB(DescuentoHumedadTablaB humedadTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(humedadTablaB);
            transaction.commit();

			String info = "Se ha actualizado el descuento";

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
	public void eliminarHumedadB(DescuentoHumedadTablaB humedadTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.delete(humedadTablaB);
            transaction.commit();

			String info = "Se ha borrado el descuento";

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

}
