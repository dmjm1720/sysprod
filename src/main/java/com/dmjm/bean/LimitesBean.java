package com.dmjm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.dmjm.dao.ILimitesDao;
import com.dmjm.impl.LimitesDaoImpl;
import com.dmjm.model.LimitesEspecificosA;
import com.dmjm.model.LimitesEspecificosB;
import com.dmjm.model.LimitesReferenciaA;
import com.dmjm.model.LimitesReferenciaB;

@Named(value = "limitesBean")
@ViewScoped
public class LimitesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LimitesEspecificosA> listalimitesEspA;
	private LimitesEspecificosA limitesEspA;
	private LimitesEspecificosA limitesEspAEditar;

	private List<LimitesEspecificosB> listalimitesEspB;
	private LimitesEspecificosB limitesEspB;
	private LimitesEspecificosB limitesEspBEditar;

	private List<LimitesReferenciaA> listalimitesRefA;
	private LimitesReferenciaA limitesRefA;
	private LimitesReferenciaA limitesRefAEditar;

	private List<LimitesReferenciaB> listalimitesRefB;
	private LimitesReferenciaB limitesRefB;
	private LimitesReferenciaB limitesRefBEditar;

	private int folioSigLimA;
	private int folioSigRefA;

	private int folioSigLimB;
	private int folioSigRefB;

	public LimitesBean() {

	}

	@PostConstruct
	public void init() {
		listalimitesEspA = new ArrayList<>();
		limitesEspA = new LimitesEspecificosA();
		limitesEspAEditar = new LimitesEspecificosA();

		listalimitesEspB = new ArrayList<>();
		limitesEspB = new LimitesEspecificosB();
		limitesEspBEditar = new LimitesEspecificosB();

		listalimitesRefA = new ArrayList<>();
		limitesRefA = new LimitesReferenciaA();
		limitesRefAEditar = new LimitesReferenciaA();

		listalimitesRefB = new ArrayList<>();
		limitesRefB = new LimitesReferenciaB();
		limitesRefBEditar = new LimitesReferenciaB();

		folioSigLimA = valorfolioMaxLimA();
		folioSigRefA = valorFolioMaxRefA();

		folioSigLimB = valorfolioMaxLimB();
		folioSigRefB = valorFolioMaxRefB();
	}

	public int getFolioSigLimB() {
		return folioSigLimB;
	}

	public void setFolioSigLimB(int folioSigLimB) {
		this.folioSigLimB = folioSigLimB;
	}

	public int getFolioSigRefB() {
		return folioSigRefB;
	}

	public void setFolioSigRefB(int folioSigRefB) {
		this.folioSigRefB = folioSigRefB;
	}

	public int getFolioSigLimA() {
		return folioSigLimA;
	}

	public void setFolioSigLimA(int folioSigLimA) {
		this.folioSigLimA = folioSigLimA;
	}

	public int getFolioSigRefA() {
		return folioSigRefA;
	}

	public void setFolioSigRefA(int folioSigRefA) {
		this.folioSigRefA = folioSigRefA;
	}

	public LimitesEspecificosA getLimitesEspA() {
		return limitesEspA;
	}

	public void setLimitesEspA(LimitesEspecificosA limitesEspA) {
		this.limitesEspA = limitesEspA;
	}

	public LimitesEspecificosA getLimitesEspAEditar() {
		return limitesEspAEditar;
	}

	public void setLimitesEspAEditar(LimitesEspecificosA limitesEspAEditar) {
		this.limitesEspAEditar = limitesEspAEditar;
	}

	public LimitesEspecificosB getLimitesEspB() {
		return limitesEspB;
	}

	public void setLimitesEspB(LimitesEspecificosB limitesEspB) {
		this.limitesEspB = limitesEspB;
	}

	public LimitesEspecificosB getLimitesEspBEditar() {
		return limitesEspBEditar;
	}

	public void setLimitesEspBEditar(LimitesEspecificosB limitesEspBEditar) {
		this.limitesEspBEditar = limitesEspBEditar;
	}

	public LimitesReferenciaA getLimitesRefA() {
		return limitesRefA;
	}

	public void setLimitesRefA(LimitesReferenciaA limitesRefA) {
		this.limitesRefA = limitesRefA;
	}

	public LimitesReferenciaA getLimitesRefAEditar() {
		return limitesRefAEditar;
	}

	public void setLimitesRefAEditar(LimitesReferenciaA limitesRefAEditar) {
		this.limitesRefAEditar = limitesRefAEditar;
	}

	public LimitesReferenciaB getLimitesRefB() {
		return limitesRefB;
	}

	public void setLimitesRefB(LimitesReferenciaB limitesRefB) {
		this.limitesRefB = limitesRefB;
	}

	public LimitesReferenciaB getLimitesRefBEditar() {
		return limitesRefBEditar;
	}

	public void setLimitesRefBEditar(LimitesReferenciaB limitesRefBEditar) {
		this.limitesRefBEditar = limitesRefBEditar;
	}

	public List<LimitesEspecificosA> getListalimitesEspA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		listalimitesEspA = lDao.limitesEspecificosA();
		return listalimitesEspA;
	}

	public List<LimitesEspecificosB> getListalimitesEspB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		listalimitesEspB = lDao.limitesEspecificosB();
		return listalimitesEspB;
	}

	public List<LimitesReferenciaA> getListalimitesRefA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		listalimitesRefA = lDao.limitesReferenciaA();
		return listalimitesRefA;
	}

	public List<LimitesReferenciaB> getListalimitesRefB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		listalimitesRefB = lDao.limitesReferenciaB();
		return listalimitesRefB;
	}

	public void guardarLimitesEspA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		limitesEspA.setFolioLm(valorfolioMaxLimA());
		lDao.guardarLimitesEspA(limitesEspA);
		limitesEspA = new LimitesEspecificosA();

		String script = "setTimeout(function() { window.location.href='LimEspYRef.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);
	}

	public void actualizarLimitesEspA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		lDao.actualizarLimitesEspA(limitesEspAEditar);
		limitesEspAEditar = new LimitesEspecificosA();
		
	}

	public void guardarLimitesEspB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		limitesEspB.setFolioLm(valorfolioMaxLimB());
		lDao.guardarLimitesEspB(limitesEspB);
		limitesEspB = new LimitesEspecificosB();
		String script = "setTimeout(function() { window.location.href='LimEspYRef.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);
	}

	public void actualizarLimitesEspB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		lDao.actualizarLimitesEspB(limitesEspBEditar);
		limitesEspBEditar = new LimitesEspecificosB();
	}

	public void guardarLimitesRefA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		limitesRefA.setFolioLr(valorFolioMaxRefA());
		lDao.guardarLimitesRefA(limitesRefA);
		limitesRefA = new LimitesReferenciaA();
		String script = "setTimeout(function() { window.location.href='LimEspYRef.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);
	}

	public void actualizarLimitesRefA() {
		ILimitesDao lDao = new LimitesDaoImpl();
		lDao.actualizarLimitesRefA(limitesRefAEditar);
		limitesRefAEditar = new LimitesReferenciaA();
	}

	public void guardarLimitesRefB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		limitesRefB.setFolioLr(valorFolioMaxRefB());
		lDao.guardarLimitesRefB(limitesRefB);
		limitesRefB = new LimitesReferenciaB();
		String script = "setTimeout(function() { window.location.href='LimEspYRef.html'; }, 3000);";
		PrimeFaces.current().executeScript(script);
	}

	public void actualizarLimitesRefB() {
		ILimitesDao lDao = new LimitesDaoImpl();
		lDao.actualizarLimitesRefB(limitesRefBEditar);
		limitesRefBEditar = new LimitesReferenciaB();
	}

	public int valorfolioMaxLimA() {
		ILimitesDao fDao = new LimitesDaoImpl();
		return fDao.folioLim("LimitesEspecificosA");
	}

	public int valorFolioMaxRefA() {
		ILimitesDao fDao = new LimitesDaoImpl();
		return fDao.folioRef("LimitesReferenciaA");
	}

	public int valorfolioMaxLimB() {
		ILimitesDao fDao = new LimitesDaoImpl();
		return fDao.folioLim("LimitesEspecificosB");
	}

	public int valorFolioMaxRefB() {
		ILimitesDao fDao = new LimitesDaoImpl();
		return fDao.folioRef("LimitesReferenciaB");
	}
}
