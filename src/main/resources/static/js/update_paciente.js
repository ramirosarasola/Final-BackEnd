 window.addEventListener('load', function () {
    // Inicia el Loading Page

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la odontologo
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;
        console.log(pacienteId)

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una odontologo nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#paciente_id').value,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
               calle: document.querySelector('#calle').value  ,
               numeroCalle: document.querySelector('#numeroCalle').value ,
               localidad: document.querySelector('#localidad').value ,
               provincia: document.querySelector('#provincia').value ,
               },
            email: document.querySelector('#email').value,

        };

        //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/pacientes';
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

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/pacientes'+"/"+id;
          
          fetch(url)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              console.log(paciente)
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#nombre').value = paciente.nombre;
              document.querySelector('#dni').value = paciente.dni;
              document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#domicilio').value = paciente.domicilio;
              document.querySelector('#email').value = paciente.email;

              //el formulario por default esta oculto y al editar se habilita

              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }