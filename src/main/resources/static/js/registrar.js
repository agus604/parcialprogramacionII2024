// Call the dataTables jQuery plugin
$(document).ready(function () {
  // Código que se ejecuta cuando el documento está listo
});

async function registrarUsuario() {
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.apellido = document.getElementById("txtApellido").value;
  datos.email = document.getElementById("txtEmail").value;
  datos.telefono = document.getElementById("txtTelefono").value;
  datos.password = document.getElementById("txtPassword").value;

  let repetirPassword = document.getElementById("txtRepetirPassword").value;

  if (repetirPassword !== datos.password) {
    alert("La contraseña no coincide");
    return;
  }

  const request = await fetch("api/usuarios", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });

  // Manejo de la respuesta
  if (request.ok) {
    alert("Usuario registrado con éxito");
    window.location.href = "login.html";
    //Limpiar los campos del formulario
    document.getElementById("txtNombre").value = "";
    document.getElementById("txtApellido").value = "";
    document.getElementById("txtEmail").value = "";
    document.getElementById("txtTelefono").value = "";
    document.getElementById("txtPassword").value = "";
    document.getElementById("txtRepetirPassword").value = "";
  } else {
    alert("Hubo un error al registrar el usuario");
  }
}
