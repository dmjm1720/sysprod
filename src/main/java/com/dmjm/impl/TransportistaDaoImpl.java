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

import com.dmjm.dao.ITransportistaDao;
import com.dmjm.model.Transportista;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;


public class TransportistaDaoImpl extends Conexion implements ITransportistaDao {

	@Override
	public List<Transportista> listarTransportista() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Transportista WHERE ESTADO=1", Transportista.class).list();
		}
	}

	@Override
	public void guardarTransportista(Transportista transportista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(transportista);
			transaction.commit();
			String info = "Se ha registrado un nuevo Transportista";

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
	public void actualizarTransportista(Transportista transportista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(transportista);
			transaction.commit();
			String info = "Se ha actualizado el Transportista";

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
	public List<String> completeTransportista(String nombre) throws SQLException {
		List<String> resultRFC = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NOMBRE) FROM TRANSPORTISTA WHERE NOMBRE LIKE '" + nombre + "%' AND ESTADO=1");
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
	public int buscarTransportista(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_TRANSPORTISTA FROM TRANSPORTISTA WHERE NOMBRE = '" + nombre + "'");
		ResultSet rs = st.executeQuery();
		int transportista = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				transportista = rs.getInt("ID_TRANSPORTISTA");
			}
		}

		CerrarSysProd();
		return transportista;
	}

	@Override
	public void borrarTransportista(Transportista transportista) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(transportista);
			transaction.commit();
			String info = "El transportista se ha dado de baja correctamente";

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
