function validarCaptura() {
	document.getElementById('frmPrinicpal').addEventListener('submit', function(event) {
		event.preventDefault(); // Evita el envío del formulario

		let suscursal = document.getElementById('suscursal').value;
		let email = document.getElementById('email').value;
		let mensajeError = document.getElementById('mensajeError');
		mensajeError.innerHTML = '';

		if (suscursal === '') {
			mensajeError.innerHTML += 'El nombre es obligatorio.<br>';
		}

		if (email === '') {
			mensajeError.innerHTML += 'El email es obligatorio.<br>';
		} else if (!/\S+@\S+\.\S+/.test(email)) {
			mensajeError.innerHTML += 'El email no es válido.<br>';
		}

		if (mensajeError.innerHTML === '') {
			// Si no hay errores, se puede enviar el formulario
			alert('Formulario enviado correctamente');
			// Aquí puedes enviar el formulario usando AJAX o similar
		}
	});
}

validarCaptura();