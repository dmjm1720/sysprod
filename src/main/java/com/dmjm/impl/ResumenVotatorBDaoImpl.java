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

import com.dmjm.dao.IResumenVotatorBDao;
import com.dmjm.model.ResumenVotatorB;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class ResumenVotatorBDaoImpl extends Conexion implements IResumenVotatorBDao {
	private static final Logger LOGGER = LogManager.getLogger(ResumenVotatorBDaoImpl.class.getName());

	@Override
	public void guardarResumen(ResumenVotatorB resumen) {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			session.save(resumen);
			transaction.commit();

			String info = "Se ha actualizado el resumen de tiempos en Votato A";

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
	public void borrarResumen(int folio) {

		String sql = "DELETE FROM RESUMEN_VOTATOR_B WHERE ID_FOLIO_PREP = ?";

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
	public List<ResumenVotatorB> listaResumen(int folio) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    String hql = "FROM ResumenVotatorB r JOIN FETCH r.folioPreparacionVotatorB WHERE r.folioPreparacionVotatorB.idFolioPrep = :folio";
		    Query<ResumenVotatorB> query = session.createQuery(hql, ResumenVotatorB.class);
		    query.setParameter("folio", folio);

		    return query.list();
		}
	}
}
