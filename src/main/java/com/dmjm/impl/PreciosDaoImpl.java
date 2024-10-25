package com.dmjm.impl;

import com.dmjm.dao.IPreciosDao;
import com.dmjm.model.Precios;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PreciosDaoImpl extends Conexion implements IPreciosDao {

    @Override
    public List<Precios> listarPrecios() {
        @SuppressWarnings("JPQLValidation")
        List<Precios> precios = (List<Precios>) HibernateUtil.getSessionFactory().openSession().createQuery("From Precios ORDER BY idPrecios DESC").list();
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
            session.update(precios);
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
            } else {
                while (rs.next()) {
                    precio = rs.getDouble("PRECIO_ACTUAL");
                }
            }

            CerrarSysProd();
            return precio;
        } catch (SQLException ex) {
            Logger.getLogger(PreciosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return precio;
    }

}
