// window.addEventListener('load', function () {
    // Inicia el Loading Page

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la odontologo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#odontologo_id').value;
        console.log(odontologoId)

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una odontologo nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';
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

    // })
    // Fin del Loading Page

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/odontologos'+"/"+id;
          
          fetch(url)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              console.log(odontologo)
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#apellido').value = odontologo.apellido;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#matricula').value = odontologo.matricula;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }