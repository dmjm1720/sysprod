package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICuentasContablesDao;
import com.dmjm.model.CuentasContables;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class CuentasContablesDaoImpl extends Conexion implements ICuentasContablesDao {

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
		 List<String> resultCuenta = new ArrayList<>();
	        List<String> listarTodo = new ArrayList<>();

	        ConectarSysProd();

	        PreparedStatement st = getCnSysProd().prepareStatement("SELECT DISTINCT (CUENTA) FROM CUENTAS_CONTABLES WHERE CUENTA LIKE '" + nombre + "%'");
	        ResultSet rs = st.executeQuery();
	        listarTodo = new ArrayList<>();
	        if (!rs.isBeforeFirst()) {
	            listarTodo.add("No hay resultados para tu búsqueda");
	        } else {
	            while (rs.next()) {
	                listarTodo.add(rs.getString("CUENTA"));
	            }
	        }
	        for (int i = 0; i < listarTodo.size(); i++) {
	        	resultCuenta.add(listarTodo.get(i));
	        }

	        CerrarSysProd();
	        return resultCuenta;
	}

	@Override
	public int buscarCuentaContable(String nombre) throws SQLException {
		ConectarSysProd();
        PreparedStatement st = getCnSysProd().prepareStatement("SELECT ID_CUENTA_CONTABLE FROM CUENTAS_CONTABLES WHERE CUENTA = '" + nombre + "'");
        ResultSet rs = st.executeQuery();
        int idCuenta = 0;
        if (!rs.isBeforeFirst()) {

        } else {
            while (rs.next()) {
            	idCuenta = rs.getInt("ID_CUENTA_CONTABLE");
            }
        }

        CerrarSysProd();
        return idCuenta;

	}

	

}
