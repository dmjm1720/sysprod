package com.dmjm.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IPreciosDao;
import com.dmjm.model.Precios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;

public class PreciosDaoImpl extends Conexion implements IPreciosDao {
	private static final Logger LOGGER = LogManager.getLogger(PreciosDaoImpl.class.getName());
    @Override
    public List<Precios> listarPrecios() {        
        Transaction transaction = null;
        List<Precios> precios = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            precios = session.createQuery("FROM Precios ORDER BY idPrecios DESC", Precios.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return precios;
    }

    @Override
    public void guardarPrecios(Precios precios) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(precios);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void actualizarPrecios(Precios precios) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.merge(precios);
            transaction.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public double buscarPrecios(int idProveedor, int idMateria) {
        double precio = 0.0;
        try {
            ConectarSysProd();
            PreparedStatement st = getCnSysProd().prepareStatement("SELECT TOP(1) PRECIO_ACTUAL "
                    + "FROM PRECIOS "
                    + "WHERE ID_MATERIA='" + idMateria + "' AND ID_PROVEEDOR='" + idProveedor + "' "
                    + "ORDER BY ID_PRECIOS DESC;");
            ResultSet rs = st.executeQuery();

            if (!rs.isBeforeFirst()) {
                precio = 0.0;
                LOGGER.info("NO EXISTE PRECIO PARA ID PROVEEDOR:" + idProveedor + " MATERIA PRIMA ID: " + idMateria);
            } else {
                while (rs.next()) {
                    precio = rs.getDouble("PRECIO_ACTUAL");
                }
            }

            CerrarSysProd();
            return precio;
        } catch (SQLException ex) {
            LOGGER.error("ERROR AL OBTENER EL PRECIO PARA ID PROVEEDOR:" + idProveedor + " MATERIA PRIMA ID: " + idMateria + "     ERROR: " + ex);
        }
        return precio;
    }

}
