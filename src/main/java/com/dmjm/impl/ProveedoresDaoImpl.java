package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IProveedoresDao;
import com.dmjm.model.Proveedores;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class ProveedoresDaoImpl extends Conexion implements IProveedoresDao {

	@Override
	public List<Proveedores> listarProveedores() {

		List<Proveedores> proveedores = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Proveedores> query = session.createQuery("FROM Proveedores p WHERE p.estado = 1 ORDER BY p.nombre",
					Proveedores.class);
			proveedores = query.list();
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de error
		}
		return proveedores;
	}

	@Override
	public void guardarProveedores(Proveedores proveedores) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(proveedores);
			transaction.commit();

			String info = "Se ha registrado un nuevo Proveedor";

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
	public void actualizarProveedores(Proveedores proveedores) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(proveedores);
			transaction.commit();
			String info = "Proveedor actualizado";

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

	@Override
	public List<String> completeProveedor(String nombre) throws SQLException {
		List<String> resultRFC = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd().prepareStatement(
				"SELECT DISTINCT (NOMBRE) FROM PROVEEDORES WHERE NOMBRE LIKE '" + nombre + "%' AND ESTADO=1");
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
	public int buscarProveedor(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_PROVEEDOR FROM PROVEEDORES WHERE NOMBRE = '" + nombre + "'");
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
	public void borrarProveedores(Proveedores proveedores) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(proveedores);
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

	@Override
	public String buscarTipoMonedaProveedor(String nombre) throws SQLException {
		String mx_usd = "";
		String tipo_modeda = "";
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT NACIONAL_IMPORTACION FROM PROVEEDORES WHERE NOMBRE = '" + nombre + "'");
		ResultSet rs = st.executeQuery();

		if (!rs.isBeforeFirst()) {
			mx_usd = "NA";
		} else {
			while (rs.next()) {
				mx_usd = rs.getString("NACIONAL_IMPORTACION");
				if (mx_usd.equals("IMPORTACIÓN")) {
					tipo_modeda = "USD";
				} else {
					tipo_modeda = "MXN";
				}
			}
		}

		CerrarSysProd();
		return tipo_modeda;
	}

	@Override
	public Proveedores buscarMerma(int id) {
		Proveedores proveedores = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    Query<Proveedores> query = session.createQuery(
		        "FROM Proveedores p WHERE p.idProveedor = :idProveedor", 
		        Proveedores.class
		    );
		    query.setParameter("idProveedor", id); // Parámetro seguro
		    proveedores = query.uniqueResult();
		} catch (Exception e) {
		    e.printStackTrace(); // Manejo de error
		}
		return proveedores;
	}

}
