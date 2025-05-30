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

import com.dmjm.dao.IOperadorDao;
import com.dmjm.model.Operador;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class OperadorDaoImpl extends Conexion implements IOperadorDao {

	@Override
	public List<String> completeOperador(String nombre) throws SQLException {
		List<String> resultOperador = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NOMBRE) FROM OPERADOR WHERE NOMBRE LIKE '" + nombre + "%'");
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
			resultOperador.add(listarTodo.get(i));
		}

		CerrarSysProd();
		return resultOperador;
	}

	@Override
	public int buscarOperador(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_OPERADOR FROM OPERADOR WHERE NOMBRE = '" + nombre + "'");
		ResultSet rs = st.executeQuery();
		int operador = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				operador = rs.getInt("ID_OPERADOR");
			}
		}

		CerrarSysProd();
		return operador;
	}

	@Override
	public void guardarOperador(Operador operador) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(operador);
			transaction.commit();
			String info = "Se ha registrado un nuevo Operador";

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
	public void actualizarOperador(Operador operador) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(operador);
			transaction.commit();
			String info = "Se ha actualizado el Operador";

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
	public List<Operador> listaOperadorCocedores() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery(
					"FROM Operador WHERE Proceso='Cocedores' AND estado='Activo'",
					Operador.class).list();
		}
	}

}
