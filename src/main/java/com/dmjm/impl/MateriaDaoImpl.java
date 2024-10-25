package com.dmjm.impl;

import com.dmjm.dao.IMateriaDao;
import com.dmjm.model.Materia;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MateriaDaoImpl extends Conexion implements IMateriaDao {

    @Override
    public List<Materia> listarMateria() {
        @SuppressWarnings("JPQLValidation")
        List<Materia> materia = (List<Materia>) HibernateUtil.getSessionFactory().openSession().createQuery("From Materia").list();
        return materia;
    }

    @Override
    public void guardarMateria(Materia materia) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(materia);
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
    public void actualizarMateria(Materia materia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> completeMateria(String nombre) throws SQLException {
        List<String> resultRFC = new ArrayList<>();
        List<String> listarTodo = new ArrayList<>();

        ConectarSysProd();

        PreparedStatement st = getCnSysProd().prepareStatement("SELECT DISTINCT (TIPO) FROM MATERIA WHERE TIPO LIKE '" + nombre + "%'");
        ResultSet rs = st.executeQuery();
        listarTodo = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            listarTodo.add("No hay resultados para tu búsqueda");
        } else {
            while (rs.next()) {
                listarTodo.add(rs.getString("TIPO"));
            }
        }
        for (int i = 0; i < listarTodo.size(); i++) {
            resultRFC.add(listarTodo.get(i));
        }

        CerrarSysProd();
        return resultRFC;
    }

    @Override
    public int buscarMateria(String nombre) throws SQLException {
        ConectarSysProd();
        PreparedStatement st = getCnSysProd().prepareStatement("SELECT ID_MATERIA FROM MATERIA WHERE TIPO = '" + nombre + "'");
        ResultSet rs = st.executeQuery();
        int materia = 0;
        if (!rs.isBeforeFirst()) {

        } else {
            while (rs.next()) {
                materia = rs.getInt("ID_MATERIA");
            }
        }

        CerrarSysProd();
        return materia;
    }

}
