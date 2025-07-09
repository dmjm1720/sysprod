package com.dmjm.model;

import java.math.BigDecimal;
import java.util.Date;

public class Cocedores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idCocedor;
	private FolioPreparacionCocedores folioPreparacionCocedores;
	private String horaLimitesEspecificos;
	private String operacion;
	private BigDecimal grados;
	private Integer flujo;
	private BigDecimal ph;
	private Integer ntu;
	private Integer calcios;
	private BigDecimal viscocidad;
	private Integer condensacion;
	private BigDecimal concentrado;
	private Integer tempCoc01;
	private BigDecimal concCoc01;
	private Integer tempCoc02;
	private BigDecimal concCoc02;
	private Integer tempCoc03;
	private BigDecimal concCoc03;
	private Integer tempCoc04;
	private BigDecimal concCoc04;
	private Integer tempCoc05;
	private BigDecimal concCoc05;
	private Integer tempCoc06;
	private BigDecimal concCoc06;
	private Integer tempCoc07;
	private BigDecimal concCoc07;
	private Integer tempCoc08;
	private BigDecimal concCoc08;
	private Integer tempCoc09;
	private BigDecimal concCoc09;
	private Integer tempCoc10;
	private BigDecimal concCoc10;
	private String estadoA;
	private String estadoR;
	private Integer folioCocedor;
	private Date fecha;
	private Boolean estadoAR;
	private Boolean estadoManto;

	public Cocedores() {
	}

	public Cocedores(int idCocedor) {
		this.idCocedor = idCocedor;
	}

	public Cocedores(int idCocedor, FolioPreparacionCocedores folioPreparacionCocedores, String horaLimitesEspecificos,
			String operacion, BigDecimal grados, Integer flujo, BigDecimal ph, Integer ntu, Integer calcios,
			BigDecimal viscocidad, Integer condensacion, BigDecimal concentrado, Integer tempCoc01,
			BigDecimal concCoc01, Integer tempCoc02, BigDecimal concCoc02, Integer tempCoc03, BigDecimal concCoc03,
			Integer tempCoc04, BigDecimal concCoc04, Integer tempCoc05, BigDecimal concCoc05, Integer tempCoc06,
			BigDecimal concCoc06, Integer tempCoc07, BigDecimal concCoc07, Integer tempCoc08, BigDecimal concCoc08,
			Integer tempCoc09, BigDecimal concCoc09, Integer tempCoc10, BigDecimal concCoc10, String estadoA,
			String estadoR, Integer folioCocedor, Date fecha, Boolean estadoAR, Boolean estadoManto) {
		this.idCocedor = idCocedor;
		this.folioPreparacionCocedores = folioPreparacionCocedores;
		this.horaLimitesEspecificos = horaLimitesEspecificos;
		this.operacion = operacion;
		this.grados = grados;
		this.flujo = flujo;
		this.ph = ph;
		this.ntu = ntu;
		this.calcios = calcios;
		this.viscocidad = viscocidad;
		this.condensacion = condensacion;
		this.concentrado = concentrado;
		this.tempCoc01 = tempCoc01;
		this.concCoc01 = concCoc01;
		this.tempCoc02 = tempCoc02;
		this.concCoc02 = concCoc02;
		this.tempCoc03 = tempCoc03;
		this.concCoc03 = concCoc03;
		this.tempCoc04 = tempCoc04;
		this.concCoc04 = concCoc04;
		this.tempCoc05 = tempCoc05;
		this.concCoc05 = concCoc05;
		this.tempCoc06 = tempCoc06;
		this.concCoc06 = concCoc06;
		this.tempCoc07 = tempCoc07;
		this.concCoc07 = concCoc07;
		this.tempCoc08 = tempCoc08;
		this.concCoc08 = concCoc08;
		this.tempCoc09 = tempCoc09;
		this.concCoc09 = concCoc09;
		this.tempCoc10 = tempCoc10;
		this.concCoc10 = concCoc10;
		this.estadoA = estadoA;
		this.estadoR = estadoR;
		this.folioCocedor = folioCocedor;
		this.fecha = fecha;
		this.estadoAR = estadoAR;
		this.estadoManto = estadoManto;
	}

	public int getIdCocedor() {
		return this.idCocedor;
	}

	public void setIdCocedor(int idCocedor) {
		this.idCocedor = idCocedor;
	}

	public FolioPreparacionCocedores getFolioPreparacionCocedores() {
		return this.folioPreparacionCocedores;
	}

	public void setFolioPreparacionCocedores(FolioPreparacionCocedores folioPreparacionCocedores) {
		this.folioPreparacionCocedores = folioPreparacionCocedores;
	}

	public String getHoraLimitesEspecificos() {
		return this.horaLimitesEspecificos;
	}

	public void setHoraLimitesEspecificos(String horaLimitesEspecificos) {
		this.horaLimitesEspecificos = horaLimitesEspecificos;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public BigDecimal getGrados() {
		return this.grados;
	}

	public void setGrados(BigDecimal grados) {
		this.grados = grados;
	}

	public Integer getFlujo() {
		return this.flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public BigDecimal getPh() {
		return this.ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public Integer getNtu() {
		return this.ntu;
	}

	public void setNtu(Integer ntu) {
		this.ntu = ntu;
	}

	public Integer getCalcios() {
		return this.calcios;
	}

	public void setCalcios(Integer calcios) {
		this.calcios = calcios;
	}

	public BigDecimal getViscocidad() {
		return this.viscocidad;
	}

	public void setViscocidad(BigDecimal viscocidad) {
		this.viscocidad = viscocidad;
	}

	public Integer getCondensacion() {
		return this.condensacion;
	}

	public void setCondensacion(Integer condensacion) {
		this.condensacion = condensacion;
	}

	public BigDecimal getConcentrado() {
		return this.concentrado;
	}

	public void setConcentrado(BigDecimal concentrado) {
		this.concentrado = concentrado;
	}

	public Integer getTempCoc01() {
		return this.tempCoc01;
	}

	public void setTempCoc01(Integer tempCoc01) {
		this.tempCoc01 = tempCoc01;
	}

	public BigDecimal getConcCoc01() {
		return this.concCoc01;
	}

	public void setConcCoc01(BigDecimal concCoc01) {
		this.concCoc01 = concCoc01;
	}

	public Integer getTempCoc02() {
		return this.tempCoc02;
	}

	public void setTempCoc02(Integer tempCoc02) {
		this.tempCoc02 = tempCoc02;
	}

	public BigDecimal getConcCoc02() {
		return this.concCoc02;
	}

	public void setConcCoc02(BigDecimal concCoc02) {
		this.concCoc02 = concCoc02;
	}

	public Integer getTempCoc03() {
		return this.tempCoc03;
	}

	public void setTempCoc03(Integer tempCoc03) {
		this.tempCoc03 = tempCoc03;
	}

	public BigDecimal getConcCoc03() {
		return this.concCoc03;
	}

	public void setConcCoc03(BigDecimal concCoc03) {
		this.concCoc03 = concCoc03;
	}

	public Integer getTempCoc04() {
		return this.tempCoc04;
	}

	public void setTempCoc04(Integer tempCoc04) {
		this.tempCoc04 = tempCoc04;
	}

	public BigDecimal getConcCoc04() {
		return this.concCoc04;
	}

	public void setConcCoc04(BigDecimal concCoc04) {
		this.concCoc04 = concCoc04;
	}

	public Integer getTempCoc05() {
		return this.tempCoc05;
	}

	public void setTempCoc05(Integer tempCoc05) {
		this.tempCoc05 = tempCoc05;
	}

	public BigDecimal getConcCoc05() {
		return this.concCoc05;
	}

	public void setConcCoc05(BigDecimal concCoc05) {
		this.concCoc05 = concCoc05;
	}

	public Integer getTempCoc06() {
		return this.tempCoc06;
	}

	public void setTempCoc06(Integer tempCoc06) {
		this.tempCoc06 = tempCoc06;
	}

	public BigDecimal getConcCoc06() {
		return this.concCoc06;
	}

	public void setConcCoc06(BigDecimal concCoc06) {
		this.concCoc06 = concCoc06;
	}

	public Integer getTempCoc07() {
		return this.tempCoc07;
	}

	public void setTempCoc07(Integer tempCoc07) {
		this.tempCoc07 = tempCoc07;
	}

	public BigDecimal getConcCoc07() {
		return this.concCoc07;
	}

	public void setConcCoc07(BigDecimal concCoc07) {
		this.concCoc07 = concCoc07;
	}

	public Integer getTempCoc08() {
		return this.tempCoc08;
	}

	public void setTempCoc08(Integer tempCoc08) {
		this.tempCoc08 = tempCoc08;
	}

	public BigDecimal getConcCoc08() {
		return this.concCoc08;
	}

	public void setConcCoc08(BigDecimal concCoc08) {
		this.concCoc08 = concCoc08;
	}

	public Integer getTempCoc09() {
		return this.tempCoc09;
	}

	public void setTempCoc09(Integer tempCoc09) {
		this.tempCoc09 = tempCoc09;
	}

	public BigDecimal getConcCoc09() {
		return this.concCoc09;
	}

	public void setConcCoc09(BigDecimal concCoc09) {
		this.concCoc09 = concCoc09;
	}

	public Integer getTempCoc10() {
		return this.tempCoc10;
	}

	public void setTempCoc10(Integer tempCoc10) {
		this.tempCoc10 = tempCoc10;
	}

	public BigDecimal getConcCoc10() {
		return this.concCoc10;
	}

	public void setConcCoc10(BigDecimal concCoc10) {
		this.concCoc10 = concCoc10;
	}

	public String getEstadoA() {
		return this.estadoA;
	}

	public void setEstadoA(String estadoA) {
		this.estadoA = estadoA;
	}

	public String getEstadoR() {
		return this.estadoR;
	}

	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}

	public Integer getFolioCocedor() {
		return this.folioCocedor;
	}

	public void setFolioCocedor(Integer folioCocedor) {
		this.folioCocedor = folioCocedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstadoAR() {
		return estadoAR;
	}

	public void setEstadoAR(Boolean estadoAR) {
		this.estadoAR = estadoAR;
	}

	public Boolean getEstadoManto() {
		return estadoManto;
	}

	public void setEstadoManto(Boolean estadoManto) {
		this.estadoManto = estadoManto;
	}
	
	

}
