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
				.prepareStatement("SELECT ID_LAVADORA FROM LAVADORAS WHERE DESCRIPCION = '" + nombre + "'");
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

	@Override
	public void borrarLavadora(Lavadoras lavadoras) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.delete(lavadoras);
			transaction.commit();
			String info = "Se ha borrado la lavadora seleccionada";

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
	public List<Lavadoras> listaLavadorasChicas() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lavadoras WHERE nombre='LAVADORA CHICA' AND estado=0", Lavadoras.class)
					.list();
		}
	}

	@Override
	public void actualizarEstadoLavadora(int estado, String descripcion) {
		try {
			ConectarSysProd();
			String sql = "UPDATE LAVADORAS SET ESTADO = ?, ETAPA = 'Vacía', OPERACION=NULL WHERE DESCRIPCION = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setString(2, descripcion);
			ps.executeUpdate();
			LOGGER.info("LAVADORA ACTUALIZADA :  ESTADO: " + estado + " LAVADORA: " + descripcion);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA LAVADORA: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	@Override
	public void actualizarEstadoLavadoraChica(int estado, String etapa, String descripcion) {
		try {
			ConectarSysProd();
			String sql = "UPDATE LAVADORAS SET ESTADO = ?, ETAPA = ? WHERE DESCRIPCION = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setString(2, etapa);
			ps.setString(3, descripcion);
			ps.executeUpdate();
			LOGGER.info("LAVADORA ACTUALIZADA :  ESTADO: " + estado + " LAVADORA: " + descripcion);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA LAVADORA: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	@Override
	public List<Lavadoras> listaLavadorasChicasEncimar() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM Lavadoras WHERE nombre='LAVADORA CHICA'", Lavadoras.class).list();
		}
	}

	@Override
	public void actualizarOperacionLavadora(int operacion, String descripcion) {
		try {
			ConectarSysProd();
			String sql = "UPDATE LAVADORAS SET OPERACION = ? WHERE DESCRIPCION = ?";
			PreparedStatement ps = getCnSysProd().prepareStatement(sql);
			ps.setInt(1, operacion);
			ps.setString(2, descripcion);
			ps.executeUpdate();
			LOGGER.info("LAVADORA ACTUALIZADA :  OPERACION: " + operacion + " LAVADORA: " + descripcion);
			CerrarSysProd();
		} catch (SQLException ex) {
			LOGGER.error("ERROR AL ACTUALIZAR LA LAVADORA: " + ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡AVISO!", "ERROR AL ACTUALIZAR"));
		}

	}

	@Override
	public List<Lavadoras> listarLavodorasPorOperacion(int operacion) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Lavadoras l WHERE l.operacion IN (:operacion) AND l.nombre='LAVADORA CHICA'";
			return session.createQuery(hql, Lavadoras.class).setParameter("operacion", operacion).list();
		}
	}

	@Override
	public List<String> listaDeOperaciones(Integer operacion) throws SQLException {
		List<String> operaciones = new ArrayList<>();

		ConectarSysProd();

		String sql = "SELECT DISTINCT OPERACION FROM LAVADORAS WHERE OPERACION NOT IN (?)";

		PreparedStatement st = getCnSysProd().prepareStatement(sql);
		//st.setString(1, operacion.toString() + "%"); // LIKE con prefijo
		st.setString(1, operacion.toString()); // exclusión exacta

		ResultSet rs = st.executeQuery();
		if (!rs.isBeforeFirst()) {
			operaciones.add("No hay resultados para tu búsqueda");
		} else {
			while (rs.next()) {
				operaciones.add(rs.getString("OPERACION"));
			}
		}

		CerrarSysProd();
		return operaciones;

	}

}
