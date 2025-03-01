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

import com.dmjm.dao.IProveedoresImportacionDao;
import com.dmjm.model.Proveedores;
import com.dmjm.model.ProveedoresImportacion;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class ProveedoresImportacionDaoImpl extends Conexion implements IProveedoresImportacionDao {

	@Override
	public List<ProveedoresImportacion> listaProvImp() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM ProveedoresImportacion WHERE estado = 1 ORDER BY nombre",
					ProveedoresImportacion.class).list();
		}

	}

	@Override
	public void guardarProvImp(ProveedoresImportacion proveedoresImportacion) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(proveedoresImportacion);
			transaction.commit();

			String info = "Se ha registrado un nuevo proveedor de importación";

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
	public void actualizarProvImp(ProveedoresImportacion proveedoresImportacion) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(proveedoresImportacion);
			transaction.commit();

			String info = "Se ha actualizado el proveedor de importación";

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
	public List<String> completeProveedorImp(String nombre) throws SQLException {
		List<String> resultRFC = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd().prepareStatement(
				"SELECT DISTINCT (NOMBRE) FROM PROVEEDORES_IMPORTACION WHERE NOMBRE LIKE '" + nombre + "%' AND ESTADO = 1");
		ResultSet rs = st.executeQuery();
		listarTodo = new ArrayList<>();
		if (!rs.isBeforeFirst()) {
			listarTodo.add("No hay resultados para tu búsqueda");
		} else {
			while (rs.next()) {
				listarTodo.add(rs.getString("NOMBRE"));
			}
		}
		for (int i = 0; i < listarTodo.size(); i++) {
			resultRFC.add(listarTodo.get(i));
		}

		CerrarSysProd();
		return resultRFC;

	}

	@Override
	public int buscarProvImp(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_PROVEEDOR FROM PROVEEDORES_IMPORTACION WHERE NOMBRE = '" + nombre + "' AND ESTADO = 1");
		ResultSet rs = st.executeQuery();
		int proveedor = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				proveedor = rs.getInt("ID_PROVEEDOR");
			}
		}

		CerrarSysProd();
		return proveedor;
	}

	@Override
	public void borrarProvImp(ProveedoresImportacion proveedoresImportacion) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(proveedoresImportacion);
			transaction.commit();
			String info = "El proveedor se ha dado de baja correctamente";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'success',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 8000\n" + "})");
		} catch (HibernateException e) {
			System.err.println(e.getMessage());
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
