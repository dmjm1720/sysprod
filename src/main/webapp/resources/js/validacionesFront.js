function validarMinimo(input) {
	if (parseInt(input.value) < 100) {
		input.setCustomValidity("El valor debe ser mayor o igual a 100");
	} else {
		input.setCustomValidity("");
	}
}