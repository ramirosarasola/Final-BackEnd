function deleteBy(id){
  const url = '/turnos/'+ id;
  const settings = {
    method: 'DELETE'
  };
  fetch(url, settings)
    .then(response => {
      if (response.status === 200 || response.status === 204) {
        // Eliminación exitosa, continuar con la eliminación de la fila
        let row_id = "#tr_" + id;
        let row = document.querySelector(row_id);
        if (row) {
          row.remove();
        }
      } else {
        // Eliminación fallida, manejar el error
        throw new Error(`Error eliminando el turno con ID ${id}: ${response.status} ${response.statusText}`);
      }
    })
    .catch(error => console.error(error));
}