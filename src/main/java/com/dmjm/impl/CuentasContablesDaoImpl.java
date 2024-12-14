package com.dmjm.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICuentasContablesDao;
import com.dmjm.model.CuentasContables;
import com.dmjm.model.DescuentoCalciosTablaA;
import com.dmjm.util.HibernateUtil;

public class CuentasContablesDaoImpl implements ICuentasContablesDao {

	@Override
	public List<CuentasContables> listaCuentasContables() {
		@SuppressWarnings("JPQLValidation")
        List<CuentasContables> cuentas = (List<CuentasContables>) HibernateUtil.getSessionFactory().openSession().createQuery("From CuentasContables").list();
        return cuentas;
	}

	@Override
	public void guardarCuentasContables(CuentasContables cuentasContables) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(cuentasContables);
            transaction.commit();

			String info = "Se ha registrado un nueva cuenta contable";

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
	public void actualizarCuentasContables(CuentasContables cuentasContables) {
		Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.update(cuentasContables);
            transaction.commit();

			String info = "Se ha actualizado la cuenta contable";

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
	public List<String> completeCuentasContablesImp(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int buscarCuentaContable(String nombre) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
