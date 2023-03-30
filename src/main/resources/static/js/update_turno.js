window.addEventListener('load', function () {
    // Inicia el Loading Page

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la turno
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;
        console.log(turnoId)

        //creamos un JSON que tendrá los datos de los turnos
        //a diferencia de un turno nuevo en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: document.querySelector('paciente').value,
            odontologo: document.querySelector('#odontologo').value,
            fechaTurno: document.querySelector('#fechaTurno').value,

        };

        //invocamos utilizando la función fetch la API los turnos con el método PUT que modificará
        //el turno que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })

    })
    // Fin del Loading Page

    //Es la funcion que se invoca cuando se hace click sobre el id de una turno del listado
    //se encarga de llenar el formulario con los datos de la turno
    //que se desea modificar
    function findBy(id) {
          const url = '/turnos'+"/"+id;

          fetch(url)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              console.log(turno)
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#paciente').value = turno.nombre_paciente;
              document.querySelector('#odontologo').value = turno.nombre_odontologo;
              document.querySelector('#fechaTurno').value = turno.fechaTurno;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }