package com.dmjm.impl;

import com.dmjm.dao.IProveedoresDao;
import com.dmjm.model.Proveedores;
import com.dmjm.util.Conexion;
import com.dmjm.util.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProveedoresDaoImpl extends Conexion implements IProveedoresDao {

    @Override
    public List<Proveedores> listarProveedores() {
        @SuppressWarnings("JPQLValidation")
        List<Proveedores> proveedores = (List<Proveedores>) HibernateUtil.getSessionFactory().openSession().createQuery("From Proveedores").list();
        return proveedores;
    }

    @Override
    public void guardarProveedores(Proveedores proveedores) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(proveedores);
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
    public void actualizarProveedores(Proveedores proveedores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> completeProveedor(String nombre) throws SQLException {
        List<String> resultRFC = new ArrayList<>();
        List<String> listarTodo = new ArrayList<>();

        ConectarSysProd();

        PreparedStatement st = getCnSysProd().prepareStatement("SELECT DISTINCT (NOMBRE) FROM PROVEEDORES WHERE NOMBRE LIKE '" + nombre + "%'");
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
    public int buscarProveedor(String nombre) throws SQLException {
        ConectarSysProd();
        PreparedStatement st = getCnSysProd().prepareStatement("SELECT ID_PROVEEDOR FROM PROVEEDORES WHERE NOMBRE = '" + nombre + "'");
        ResultSet rs = st.executeQuery();
        int proveedor = 0;
        if (!rs.isBeforeFirst()) {

        } else {
            while (rs.next()) {
                proveedor = rs.getInt("ID_PROVEEDOR");
            }
        }

        CerrarSysProd();
        return proveedor;
    }

   

    

}
