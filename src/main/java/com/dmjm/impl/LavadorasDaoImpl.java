package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILavadorasDao;
import com.dmjm.model.Lavadoras;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class LavadorasDaoImpl extends Conexion implements ILavadorasDao {

	private static final Logger LOGGER = LogManager.getLogger(LavadorasDaoImpl.class);

	@Override
	public List<Lavadoras> listaLavadoras() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lavadoras", Lavadoras.class).list();
		}
	}

	@Override
	public void guardarLavadoras(Lavadoras lavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(lavadoras);
			transaction.commit();
			String info = "Se ha registrado una nueva lavadora";

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
	public void actualizarLavadoras(Lavadoras lavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(lavadoras);
			transaction.commit();
			String info = "Se ha actulizado la lavadora";

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
	public List<String> completeLavadoras(String nombre) throws SQLException {
		List<String> listaLavadoras = new ArrayList<>();
		List<String> listarTodas = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement st = getCnSysProd().prepareStatement(
				"SELECT DISTINCT (NOMBRE) FROM LAVADORAS WHERE NOMBRE LIKE '" + nombre + "%' AND ESTADO=0");
		ResultSet rs = st.executeQuery();
		listarTodas = new ArrayList<>();
		if (!rs.isBeforeFirst()) {
			listarTodas.add("No hay resultados para tu búsqueda");
		} else {
			while (rs.next()) {
				listarTodas.add(rs.getString("NOMBRE"));
			}
		}
		for (int i = 0; i < listarTodas.size(); i++) {
			listaLavadoras.add(listarTodas.get(i));
		}

		CerrarSysProd();
		return listaLavadoras;
	}

	@Override
	public int buscarLavadora(String nombre) throws SQLException {
		ConectarSysProd();
		PreparedStatement st = getCnSysProd()
				.prepareStatement("SELECT ID_LAVADORA FROM LAVADORAS WHERE NOMBRE = '" + nombre + "'");
		ResultSet rs = st.executeQuery();
		int materia = 0;
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				materia = rs.getInt("ID_LAVADORA");
			}
		}

		CerrarSysProd();
		return materia;
	}

	@Override
	public void actualizarEstadoLavadora(int estado, String etapa, int idEntrada) {
		try {
			ConectarSysProd();
			String sql = "UPDATE LAVADORAS SET ESTADO = ?, ETAPA = ? WHERE ID_LAVADORA = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setString(2, etapa);
			ps.setInt(3, idEntrada);
			ps.executeUpdate();
			LOGGER.info("LAVADORA ACTUALIZADA : " + idEntrada + " ESTADO: " + estado + " ETAPA: " + etapa);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA LAVADORA: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	@Override
	public Lavadoras listarCapaciad(Lavadoras l) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lavadoras WHERE nombre = :nombre", Lavadoras.class)
					.setParameter("nombre", l.getNombre()).uniqueResult();
		}
	}

	@Override
	public List<Lavadoras> listarLavadorasDisponibles() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lavadoras WHERE estado=0", Lavadoras.class).list();
		}
	}

	@Override
	public void actualizarEtapaLavadora(String etapa, int id) {
		try {
			ConectarSysProd();
			String sql = "UPDATE LAVADORAS SET ETAPA = ? WHERE ID_LAVADORA = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setString(1, etapa);
			ps.setInt(2, id);
			ps.executeUpdate();
			LOGGER.info("LAVADORA ACTUALIZADA : " + id + " ETAPA: " + etapa);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA LAVADORA: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

}
