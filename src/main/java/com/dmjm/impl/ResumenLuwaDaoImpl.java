package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primefaces.PrimeFaces;

import com.dmjm.dao.IResumenLuwaDao;
import com.dmjm.model.ResumenLuwaCinco;
import com.dmjm.model.ResumenLuwaCuatro;
import com.dmjm.model.ResumenLuwaDos;
import com.dmjm.model.ResumenLuwaTres;
import com.dmjm.model.ResumenLuwaUno;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class ResumenLuwaDaoImpl extends Conexion implements IResumenLuwaDao {
	private static final Logger LOGGER = LogManager.getLogger(ResumenLuwaDaoImpl.class.getName());

	@Override
	public void guardarResumenUno(ResumenLuwaUno resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Luwa Uno";

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
	public void borrarResumenUno(int folio) {

		String sql = "DELETE FROM RESUMEN_LUWA_UNO WHERE ID_FOLIO_PREP = ?";

		try {
			ConectarSysProd();

			try (PreparedStatement ps = getCnSysProd().prepareStatement(sql)) {
				ps.setLong(1, folio);

				int filasBorradas = ps.executeUpdate();
				LOGGER.info("SE HA BORRADO EL FOLIO: " + folio + " FILAS BORRADAS: " + filasBorradas);
			}

		} catch (SQLException ex) {
			LOGGER.info("NO SE HA BORRADO EL FOLIO: " + folio);
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ResumenLuwaUno> listaResumenUno(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ResumenLuwaUno r JOIN FETCH r.folioPreparacionLuwaUno WHERE r.folioPreparacionLuwaUno.idFolioPrep = :folio";
			Query<ResumenLuwaUno> query = session.createQuery(hql, ResumenLuwaUno.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarResumenDos(ResumenLuwaDos resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Luwa Dos";

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
	public void borrarResumenDos(int folio) {

		String sql = "DELETE FROM RESUMEN_LUWA_DOS WHERE ID_FOLIO_PREP = ?";

		try {
			ConectarSysProd();

			try (PreparedStatement ps = getCnSysProd().prepareStatement(sql)) {
				ps.setLong(1, folio);

				int filasBorradas = ps.executeUpdate();
				LOGGER.info("SE HA BORRADO EL FOLIO: " + folio + " FILAS BORRADAS: " + filasBorradas);
			}

		} catch (SQLException ex) {
			LOGGER.info("NO SE HA BORRADO EL FOLIO: " + folio);
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ResumenLuwaDos> listaResumenDos(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ResumenLuwaDos r JOIN FETCH r.folioPreparacionLuwaDos WHERE r.folioPreparacionLuwaDos.idFolioPrep = :folio";
			Query<ResumenLuwaDos> query = session.createQuery(hql, ResumenLuwaDos.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarResumenTres(ResumenLuwaTres resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Luwa Tres";

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
	public void borrarResumenTres(int folio) {

		String sql = "DELETE FROM RESUMEN_LUWA_TRES WHERE ID_FOLIO_PREP = ?";

		try {
			ConectarSysProd();

			try (PreparedStatement ps = getCnSysProd().prepareStatement(sql)) {
				ps.setLong(1, folio);

				int filasBorradas = ps.executeUpdate();
				LOGGER.info("SE HA BORRADO EL FOLIO: " + folio + " FILAS BORRADAS: " + filasBorradas);
			}

		} catch (SQLException ex) {
			LOGGER.info("NO SE HA BORRADO EL FOLIO: " + folio);
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ResumenLuwaTres> listaResumenTres(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ResumenLuwaTres r JOIN FETCH r.folioPreparacionLuwaTres WHERE r.folioPreparacionLuwaTres.idFolioPrep = :folio";
			Query<ResumenLuwaTres> query = session.createQuery(hql, ResumenLuwaTres.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarResumenCuatro(ResumenLuwaCuatro resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Luwa Cuatro";

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
	public void borrarResumenCuatro(int folio) {

		String sql = "DELETE FROM RESUMEN_LUWA_CUATRO WHERE ID_FOLIO_PREP = ?";

		try {
			ConectarSysProd();

			try (PreparedStatement ps = getCnSysProd().prepareStatement(sql)) {
				ps.setLong(1, folio);

				int filasBorradas = ps.executeUpdate();
				LOGGER.info("SE HA BORRADO EL FOLIO: " + folio + " FILAS BORRADAS: " + filasBorradas);
			}

		} catch (SQLException ex) {
			LOGGER.info("NO SE HA BORRADO EL FOLIO: " + folio);
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ResumenLuwaCuatro> listaResumenCuatro(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ResumenLuwaCuatro r JOIN FETCH r.folioPreparacionLuwaCuatro WHERE r.folioPreparacionLuwaCuatro.idFolioPrep = :folio";
			Query<ResumenLuwaCuatro> query = session.createQuery(hql, ResumenLuwaCuatro.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

	@Override
	public void guardarResumenCinco(ResumenLuwaCinco resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Luwa Cinco";

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
	public void borrarResumenCinco(int folio) {

		String sql = "DELETE FROM RESUMEN_LUWA_CINCO WHERE ID_FOLIO_PREP = ?";

		try {
			ConectarSysProd();

			try (PreparedStatement ps = getCnSysProd().prepareStatement(sql)) {
				ps.setLong(1, folio);

				int filasBorradas = ps.executeUpdate();
				LOGGER.info("SE HA BORRADO EL FOLIO: " + folio + " FILAS BORRADAS: " + filasBorradas);
			}

		} catch (SQLException ex) {
			LOGGER.info("NO SE HA BORRADO EL FOLIO: " + folio);
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<ResumenLuwaCinco> listaResumenCinco(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM ResumenLuwaCinco r JOIN FETCH r.folioPreparacionLuwaCinco WHERE r.folioPreparacionLuwaCinco.idFolioPrep = :folio";
			Query<ResumenLuwaCinco> query = session.createQuery(hql, ResumenLuwaCinco.class);
			query.setParameter("folio", folio);

			return query.list();
		}
	}

}
