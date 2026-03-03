package com.dmjm.dao;

import java.util.List;

import com.dmjm.model.LimitesEspecificosA;
import com.dmjm.model.LimitesEspecificosB;
import com.dmjm.model.LimitesReferenciaA;
import com.dmjm.model.LimitesReferenciaB;

public interface ILimitesDao {

	List<LimitesEspecificosA> limitesEspecificosA();

	void guardarLimitesEspA(LimitesEspecificosA limites);

	void actualizarLimitesEspA(LimitesEspecificosA limites);

	List<LimitesEspecificosB> limitesEspecificosB();

	void guardarLimitesEspB(LimitesEspecificosB limites);

	void actualizarLimitesEspB(LimitesEspecificosB limites);

	List<LimitesReferenciaA> limitesReferenciaA();

	void guardarLimitesRefA(LimitesReferenciaA limites);

	void actualizarLimitesRefA(LimitesReferenciaA limites);

	List<LimitesReferenciaB> limitesReferenciaB();

	void guardarLimitesRefB(LimitesReferenciaB limites);

	void actualizarLimitesRefB(LimitesReferenciaB limites);

	int folioLim(String tabla);

	int folioRef(String tabla);

	LimitesEspecificosA limEspA(int folioLm);

	LimitesEspecificosB limEspB(int folioLm);

	LimitesReferenciaA limRefA(int folioLr);

	LimitesReferenciaB limRefB(int folioLr);

	LimitesEspecificosA limEspA();

	LimitesEspecificosB limEspB();

	LimitesReferenciaA limRefA();

	LimitesReferenciaB limRefB();
}
