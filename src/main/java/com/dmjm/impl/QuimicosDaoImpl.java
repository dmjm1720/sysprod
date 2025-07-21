package com.dmjm.impl;

import java.util.List;

import com.dmjm.dao.IQuimicosDao;
import com.dmjm.model.Quimicos;
import com.dmjm.util.HibernateUtil;

public class QuimicosDaoImpl implements IQuimicosDao {

	@Override
	public List<Quimicos> listarQuimicos() {
		@SuppressWarnings("unchecked")
		List<Quimicos> operacion = (List<Quimicos>) HibernateUtil.getSessionFactory().openSession()
				.createQuery("FROM Quimicos")
				.list();
				return operacion;
	}

}
