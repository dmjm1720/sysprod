package com.dmjm.impl;

import com.dmjm.dao.IUsuarioDao;
import com.dmjm.model.Usuarios;
import com.dmjm.util.HibernateUtil;
import com.dmjm.util.Password;
import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuarios obtenerDatosUsuario(Usuarios usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Usuarios WHERE nombre=:nombre AND password=:password";
        Query q = session.createQuery(hql).setMaxResults(1);
        q.setParameter("nombre", usuario.getNombre());
        q.setParameter("password", Password.sha512(usuario.getPassword()));
        return (Usuarios) q.uniqueResult();
    }

    @Override
    public Usuarios login(Usuarios usuarios) {
        Usuarios user = this.obtenerDatosUsuario(usuarios);
        if (user != null) {
            if (!user.getPassword().equals(Password.sha512(usuarios.getPassword()))) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public void guardarUsuario(Usuarios usuarios) {
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();
            session.save(usuarios);
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
    public void actualizarUsuario(Usuarios usuarios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarUsuario(Usuarios usuarios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        List<Usuarios> usuarios = (List<Usuarios>) HibernateUtil.getSessionFactory().openSession().createQuery("From Usuarios").list();
        return usuarios;
    }

}
