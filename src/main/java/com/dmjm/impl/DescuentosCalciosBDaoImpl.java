package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IDescuentoCalciosBDao;
import com.dmjm.model.DescuentoCalciosTablaB;
import com.dmjm.util.HibernateUtil;

public class DescuentosCalciosBDaoImpl implements IDescuentoCalciosBDao {

	@Override
	public List<DescuentoCalciosTablaB> listarDescuentos() {
		@SuppressWarnings({ "JPQLValidation", "unchecked" })
        List<DescuentoCalciosTablaB> descuentos = (List<DescuentoCalciosTablaB>) HibernateUtil.getSessionFactory().openSession().createQuery("From DescuentoCalciosTablaB").list();
        return descuentos;
	}

	@Override
	public void guardarDescuentosCalciosA(DescuentoCalciosTablaB calciosTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(calciosTablaB);
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
	public void actualizarDescuentosCalciosB(DescuentoCalciosTablaB calciosTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(calciosTablaB);
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
	public void eliminarDescuentosCalciosB(DescuentoCalciosTablaB calciosTablaB) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.delete(calciosTablaB);
            transaction.commit();

			String info = "Se ha eliminado descuento";

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
