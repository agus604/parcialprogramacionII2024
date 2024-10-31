// Call the dataTables jQuery plugin
let usuarios = [];

$(document).ready(function () {
  cargarUsuarios();
  $("#usuarios").DataTable();
});

async function cargarUsuarios() {
  const request = await fetch("api/usuarios", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });
  usuarios = await request.json();
  let listadoHTML = "";
  usuarios.forEach((usuario) => {
    let botonEliminar =
      '<a href="#" onclick="eliminarUsuario(' +
      usuario.id +
      ')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>';
    let usuarioHTML =
      "<tr><td>" +
      usuario.id +
      "</td><td>" +
      usuario.nombre +
      " " +
      usuario.apellido +
      "</td><td>" +
      usuario.email +
      "</td><td>" +
      usuario.telefono +
      "</td><td>" +
      botonEliminar +
      "</td></tr>";
    listadoHTML += usuarioHTML;
  });
  document.querySelector("#usuarios tbody").innerHTML = listadoHTML;
}

async function eliminarUsuario(usuarioId) {
  if (!confirm("¿Desea eliminar al usuario?")) {
    return;
  }
  try {
    const response = await fetch("api/usuarios/" + usuarioId, {
      method: "DELETE",
    });
    if (response.ok) {
      location.reload();
    } else {
      console.error("No se pudo eliminar el usuario");
    }
  } catch (error) {
    console.error("No se pudo eliminar el usuario", error);
  }
}
