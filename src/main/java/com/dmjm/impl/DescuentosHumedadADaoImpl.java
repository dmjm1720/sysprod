package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IDescuentoHumedadADao;
import com.dmjm.model.DescuentoHumedadTablaA;
import com.dmjm.util.HibernateUtil;

public class DescuentosHumedadADaoImpl implements IDescuentoHumedadADao {

	@Override
	public List<DescuentoHumedadTablaA> listarDescuentos() {
		@SuppressWarnings("JPQLValidation")
        List<DescuentoHumedadTablaA> descuentos = (List<DescuentoHumedadTablaA>) HibernateUtil.getSessionFactory().openSession().createQuery("From DescuentoHumedadTablaA").list();
        return descuentos;
	}

	@Override
	public void guardarHumedadA(DescuentoHumedadTablaA humedadTablaA) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(humedadTablaA);
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
	public void actualziarHumedadA(DescuentoHumedadTablaA humedadTablaA) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(humedadTablaA);
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

}
