package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILimpiezaVotatorBDao;
import com.dmjm.model.LimpiezaVotatorB;
import com.dmjm.model.VotatorB;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class LimpiezaVotatorBDaoImpl extends Conexion implements ILimpiezaVotatorBDao {
	private static final Logger LOGGER = LogManager.getLogger(LimpiezaVotatorBDaoImpl.class.getName());

	@Override
	public List<LimpiezaVotatorB> listarLimpieza(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaVotatorB c JOIN FETCH c.folioPreparacionVotatorB WHERE  c.folioPreparacionVotatorB.idFolioPrep = :folio";
			Query<LimpiezaVotatorB> query = session.createQuery(hql, LimpiezaVotatorB.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarLimpieza(LimpiezaVotatorB limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(limpieza);
			transaction.commit();
			String info = "Se ha guardado el registro limpieza";

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
	public void actualizarLimpieza(LimpiezaVotatorB limpieza) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.update(limpieza);
			transaction.commit();
			String info = "Se ha actulizado el registro limpieza";

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
	public int validarNoLimpieza(int folioPrep) {
		int folio = 1; // Valor por defecto
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<?> query = session.createSQLQuery("SELECT COALESCE(MAX(NO_LIMPIEZA), 0) + 1 "
					+ "FROM LIMPIEZA_VOTATOR_B " + "WHERE ID_FOLIO_PREP = :idFolioPrep");
			query.setParameter("idFolioPrep", folioPrep);

			Object result = query.uniqueResult();
			if (result != null) {
				folio = ((Number) result).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace(); //
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return folio;
	}

	@Override
	public List<VotatorB> listarTodo() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM LimpiezaVotatorB WHERE voBo='PENDIENTE'";
			Query<VotatorB> query = session.createQuery(hql, VotatorB.class);
			return query.list();
		}
	}

	@Override
	public void actualizarTodoLimpieza() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query<?> query = session
					.createSQLQuery("UPDATE LIMPIEZA_VOTATOR_B SET VOBO = 'APROBADO' WHERE VOBO='PENDIENTE'");
			query.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); // logger
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<Integer> noLimpieza(int folio) throws SQLException {
		List<Integer> limpieza = new ArrayList<>();

		ConectarSysProd();

		PreparedStatement ps = getCnSysProd()
				.prepareStatement("SELECT DISTINCT (NO_LIMPIEZA) FROM LIMPIEZA_VOTATOR_B WHERE ID_FOLIO_PREP = ?");
		ps.setLong(1, folio);
		ResultSet rs = ps.executeQuery();
		limpieza = new ArrayList<>();
		if (!rs.isBeforeFirst()) {

		} else {
			while (rs.next()) {
				limpieza.add(rs.getInt("NO_LIMPIEZA"));
			}
		}
		CerrarSysProd();
		return limpieza;
	}

	@Override
	public void borrarLimpieza(int folio, int noLimpieza) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Query<?> query = session.createQuery(
					"DELETE FROM LimpiezaVotatorB WHERE ID_FOLIO_PREP = :folio AND NO_LIMPIEZA = :noLimpieza");
			query.setParameter("folio", folio);
			query.setParameter("noLimpieza", noLimpieza);

			int rowsAffected = query.executeUpdate();
			tx.commit();

			if (rowsAffected > 0) {
				LOGGER.info("Registro eliminado con éxito.");

				String info = "Se ha borrado la limpieza seleccionada: " + noLimpieza;

				PrimeFaces.current()
						.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
								+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n"
								+ "  showConfirmButton: false,\n" + "  timer: 6000\n" + "})");
			} else {
				LOGGER.info("No se encontró el registro con ese ID.");

			}
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void agregarVoBo(int folio, int noLimpieza, int idUsuario) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query<?> query = session.createSQLQuery(
					"UPDATE LIMPIEZA_VOTATOR_B SET VOBO = 'APROBADO', ID_USUARIO = :idUsuario WHERE ID_FOLIO_PREP = :folio AND NO_LIMPIEZA = :noLimpieza");
			query.setParameter("idUsuario", idUsuario);
			query.setParameter("folio", folio);
			query.setParameter("noLimpieza", noLimpieza);
			query.executeUpdate();

			tx.commit();
			String info = "Se ha agregado el Visto Bueno a la limpieza seleccionada";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 6000\n" + "})");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); // logger
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void borrarVoBo(int folio, int noLimpieza) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query<?> query = session.createSQLQuery(
					"UPDATE LIMPIEZA_VOTATOR_B SET VOBO = 'PENDIENTE', ID_USUARIO=1028 WHERE ID_FOLIO_PREP = :folio AND NO_LIMPIEZA = :noLimpieza");
			query.setParameter("folio", folio);
			query.setParameter("noLimpieza", noLimpieza);
			query.executeUpdate();

			tx.commit();

			String info = "Se ha borrado el Visto Bueno seleccionado";

			PrimeFaces.current()
					.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
							+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: false,\n"
							+ "  timer: 6000\n" + "})");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace(); // logger
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}
