package com.dmjm.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IDescuentoCalciosADao;
import com.dmjm.model.DescuentoCalciosTablaA;
import com.dmjm.util.HibernateUtil;

public class DescuentosCalciosADaoImpl implements IDescuentoCalciosADao {

	@Override
	public List<DescuentoCalciosTablaA> listarDescuentos() {
		@SuppressWarnings({ "JPQLValidation", "unchecked" })
		List<DescuentoCalciosTablaA> descuentos = (List<DescuentoCalciosTablaA>) HibernateUtil.getSessionFactory()
				.openSession().createQuery("From DescuentoCalciosTablaA").list();
		return descuentos;
	}

	@Override
	public void guardarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(calciosTablaA);
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
	public void actualizarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(calciosTablaA);
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
	public void eliminarDescuentosCalciosA(DescuentoCalciosTablaA calciosTablaA) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(calciosTablaA);
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
