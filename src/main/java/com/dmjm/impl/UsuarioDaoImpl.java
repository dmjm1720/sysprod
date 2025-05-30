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

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.model.Usuarios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;
import com.dmjm.util.Password;

public class UsuarioDaoImpl extends Conexion implements IUsuarioDao {

	@Override
	public Usuarios obtenerDatosUsuario(Usuarios usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM Usuarios WHERE usuario=:usuario AND password=:password";
		@SuppressWarnings("rawtypes")
		Query q = session.createQuery(hql).setMaxResults(1);
		q.setParameter("usuario", usuario.getUsuario());
		q.setParameter("password", Password.sha512(usuario.getPassword()));
		return (Usuarios) q.uniqueResult();
	}

	@Override
	public Usuarios login(Usuarios usuarios) {
		Usuarios user = this.obtenerDatosUsuario(usuarios);
		if (user != null) {
			if (!user.getPassword().equals(Password.sha512(usuarios.getPassword()))) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public void guardarUsuario(Usuarios usuarios) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(usuarios);
			transaction.commit();
			String info = "Usuario agregado correctamente";

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
	public void actualizarUsuario(Usuarios usuarios) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(usuarios);
			transaction.commit();

			String info = "Usuario actualizado correctamente";

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
	public void borrarUsuario(Usuarios usuarios) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public List<Usuarios> listarUsuarios() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Usuarios", Usuarios.class).list();
		}
	}

	@Override
	public List<String> completeUsuario(String nombre) throws SQLException {
		List<String> resultUsuario = new ArrayList<>();
		List<String> listarTodo = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NOMBRE) FROM USUARIOS WHERE NOMBRE LIKE '" + nombre + "%' AND ID_PERFIL =2");
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
			resultUsuario.add(listarTodo.get(i));
		}

		CerrarSysProd();
		return resultUsuario;
	}

	@Override
	public int buscarUsuario(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_USUARIO FROM USUARIOS WHERE NOMBRE = '" + nombre + "' AND ID_PERFIL =2");
		ResultSet rs = st.executeQuery();
		int usuario = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				usuario = rs.getInt("ID_USUARIO");
			}
		}

		CerrarSysProd();
		return usuario;
	}

}
