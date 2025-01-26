function redireccionamiento() {
	window.onload = function () {
	    // Empuja un estado vacío al historial
	    history.pushState(null, "", location.href);

	    window.onpopstate = function () {
	        // Cuando el usuario presiona "Regresar", empuja el estado nuevamente
	        history.pushState(null, "", location.href);
	        alert("El botón regresar está deshabilitado.");
	    };
	};
}

redireccionamiento();