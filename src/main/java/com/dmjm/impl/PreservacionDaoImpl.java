package com.dmjm.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dmjm.dao.IPreservacionDao;
import com.dmjm.model.Preservacion;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;


public class PreservacionDaoImpl extends Conexion implements IPreservacionDao{

    @Override
    public List<Preservacion> listarPreservacion() {
       @SuppressWarnings("JPQLValidation")
        List<Preservacion> preservacion = (List<Preservacion>) HibernateUtil.getSessionFactory().openSession().createQuery("From Preservacion").list();
        return preservacion;
    }
    
    
    @Override
    public void guardarPreservacion(Preservacion presevacion) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(presevacion);
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
    public void actualizarPreservacion(Preservacion preservacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> completePreservacion(String nombre) throws SQLException {
       List<String> resultRFC = new ArrayList<>();
        List<String> listarTodo = new ArrayList<>();

        ConectarSysProd();

        PreparedStatement st = getCnSysProd().prepareStatement("SELECT DISTINCT (NOMBRE) FROM PRESERVACION WHERE NOMBRE LIKE '" + nombre + "%'");
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
            resultRFC.add(listarTodo.get(i));
        }

        CerrarSysProd();
        return resultRFC;
    }

    @Override
    public int buscarPreservacion(String nombre) throws SQLException {
        ConectarSysProd();
        PreparedStatement st = getCnSysProd().prepareStatement("SELECT ID_PRESERVACION FROM PRESERVACION WHERE NOMBRE = '" + nombre + "'");
        ResultSet rs = st.executeQuery();
        int preservacion = 0;
        if (!rs.isBeforeFirst()) {

        } else {
            while (rs.next()) {
                preservacion = rs.getInt("ID_PRESERVACION");
            }
        }

        CerrarSysProd();
        return preservacion;
    }
    
}
