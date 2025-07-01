package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILimpiezaDao;
import com.dmjm.dao.ILimpiezaEsterilizadorPlantaADao;
import com.dmjm.dao.ILimpiezaEsterilizadorPlantaBDao;
import com.dmjm.dao.ILimpiezaUltraDosDao;
import com.dmjm.dao.ILimpiezaUltraUnoDao;
import com.dmjm.impl.LimpiezaDaoImpl;
import com.dmjm.impl.LimpiezaEsterilizadorPlantaADaoImpl;
import com.dmjm.impl.LimpiezaEsterilizadorPlantaBDaoImpl;
import com.dmjm.impl.LimpiezaUltraDosDaoImpl;
import com.dmjm.impl.LimpiezaUltraUnoDaoImpl;
import com.dmjm.model.Limpieza;
import com.dmjm.model.LimpiezaEstA;
import com.dmjm.model.LimpiezaEstB;
import com.dmjm.model.LimpiezaUltraDos;
import com.dmjm.model.LimpiezaUltraUno;

@Named("voboBean")
@ViewScoped
public class VistoBuenoLimpieza implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LimpiezaUltraUno> listarUltraUno;
	private List<LimpiezaUltraDos> listarUltraDos;

	private List<LimpiezaEstA> listarEstA;
	private List<LimpiezaEstB> listarEstB;

	private List<Limpieza> limpiezaCocedores;

	public VistoBuenoLimpieza() {
		
	}

	@PostConstruct
	public void init() {
		// **LISTAR ULTRA UNO**//
		limpiezaUltraUno();

		// **LISTAR ULTRA DOS**//
		limpiezaUltraDos();

		// **LISTAR ESTELIRILIZADORES A **//
		limpiezaEsterilizadoresA();

		// **LISTAR ESTELIRILIZADORES B **//
		limpiezaEsterilizadoresB();

		// **LISTAR COCEDORES **//
		limpiezaCocedores();

	}

	public List<LimpiezaUltraUno> getListarUltraUno() {
		return listarUltraUno;
	}

	public List<LimpiezaUltraDos> getListarUltraDos() {
		return listarUltraDos;
	}

	public List<LimpiezaEstA> getListarEstA() {
		return listarEstA;
	}

	public List<LimpiezaEstB> getListarEstB() {
		return listarEstB;
	}

	public List<Limpieza> getLimpiezaCocedores() {
		return limpiezaCocedores;
	}

	public void limpiezaEsterilizadoresA() {
		listarEstA = new ArrayList<>();
		ILimpiezaEsterilizadorPlantaADao estADao = new LimpiezaEsterilizadorPlantaADaoImpl();
		listarEstA = estADao.listarTodo();
	}

	public void limpiezaEsterilizadoresB() {
		ILimpiezaEsterilizadorPlantaBDao estBDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		listarEstB = new ArrayList<>();
		listarEstB = estBDao.listarTodo();
	}

	public void limpiezaUltraUno() {
		listarUltraUno = new ArrayList<>();
		ILimpiezaUltraUnoDao unoDao = new LimpiezaUltraUnoDaoImpl();
		listarUltraUno = unoDao.listarTodo();
	}

	public void limpiezaUltraDos() {
		listarUltraDos = new ArrayList<>();
		ILimpiezaUltraDosDao dosDao = new LimpiezaUltraDosDaoImpl();
		listarUltraDos = dosDao.listarTodo();
	}

	public void limpiezaCocedores() {
		limpiezaCocedores = new ArrayList<>();
		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		limpiezaCocedores = lDao.listarTodo();
	}

	public void actualizarLimpiezaCocedores() {
		ILimpiezaDao lDao = new LimpiezaDaoImpl();
		lDao.actualizarTodoLimpieza();
		String info = "Se ha dado visto bueno a las lipiezas de Cocedores";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");
		limpiezaCocedores();
	}

	public void actualizarLimpiezaEstA() {
		ILimpiezaEsterilizadorPlantaADao lDao = new LimpiezaEsterilizadorPlantaADaoImpl();
		lDao.actualizarTodoLimpieza();
		String info = "Se ha dado visto bueno a las lipiezas de Esterilizadores Planta A";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");
		limpiezaEsterilizadoresA();
	}

	public void actualizarLimpiezaEstB() {
		ILimpiezaEsterilizadorPlantaBDao lDao = new LimpiezaEsterilizadorPlantaBDaoImpl();
		lDao.actualizarTodoLimpieza();
		String info = "Se ha dado visto bueno a las lipiezas de Esterilizadores Planta B";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");
		limpiezaEsterilizadoresB();
	}

	public void actualizarLimpiezaUltraUno() {
		ILimpiezaUltraUnoDao lDao = new LimpiezaUltraUnoDaoImpl();
		lDao.actualizarTodoLimpieza();
		String info = "Se ha dado visto bueno a las limpiezas de Ultra Filtración I";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");
		limpiezaUltraUno();
	}

	public void actualizarLimpiezaUltraDos() {
		ILimpiezaUltraDosDao lDao = new LimpiezaUltraDosDaoImpl();
		lDao.actualizarTodoLimpieza();
		String info = "Se ha dado visto bueno a las limpiezas de Ultra Filtración II";

		PrimeFaces.current()
				.executeScript("Swal.fire({\n" + "  position: 'top-center',\n" + "  icon: 'info',\n"
						+ "  title: '¡Aviso!',\n" + "  text: '" + info + "',\n" + "  showConfirmButton: true,\n"
						+ "  timer: 8000\n" + "})");
		limpiezaUltraDos();
	}

}
