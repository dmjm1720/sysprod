package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ICategoriasDao;
import com.dmjm.model.Categorias;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class CategoriasDaoImpl extends Conexion implements ICategoriasDao {

	@Override
	public List<Categorias> listaCategorias() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Categorias", Categorias.class).list();
		}
	}

	@Override
	public void guardarCategoria(Categorias categorias) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(categorias);
			transaction.commit();
			String info = "Se ha registrado una nueva categoría";

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
	public void actualizarCategoria(Categorias categorias) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(categorias);
			transaction.commit();
			String info = "Se ha actualizado la categoría";

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
	public void borrarCategoria(Categorias categorias) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(categorias);
			transaction.commit();
			String info = "Se ha eliminado el registro de la categoría";

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
	public int validarCategoriaExistente(int categoria_sistema) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_MATERIA FROM CATEGORIAS WHERE ID_MATERIA = '" + categoria_sistema + "'");
		ResultSet rs = st.executeQuery();
		int categoria = 0;
		if (!rs.isBeforeFirst()) {
			categoria = 0;
		} else {
			while (rs.next()) {
				categoria = rs.getInt("ID_MATERIA");
			}
		}

		CerrarSysProd();
		return categoria;
	}

}
