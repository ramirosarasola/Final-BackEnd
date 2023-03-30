window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará el nuevo turno
    const formulario = document.querySelector("#add_new_turno");

    const renderDataOdon = () => {
        fetch(url='/odontologos/todos')
            .then(response => response.json())
            .then(data => {
                console.log(data.length);
                console.log(data);
                if(data.length > 0){
                    data.forEach((item) => {

                        const selectElementOdontologo = document.getElementById("odontologo")
                        const optionElementOdontologo = document.createElement("option")
                        optionElementOdontologo.innerHTML = item.nombre;
                        optionElementOdontologo.setAttribute("tag", item.id)
                        selectElementOdontologo.appendChild(optionElementOdontologo);

                    })
                }
            });
        }

    const renderDataPac = () => {
        fetch(url='/pacientes/todos')
            .then(response => response.json())
            .then(data => {
                console.log(data.length);
                console.log(data);
                if(data.length > 0){
                    data.forEach((item) => {
                        const selectElementPaciente = document.getElementById("paciente")
                        const optionElementPaciente = document.createElement("option")
                        optionElementPaciente.innerHTML = item.nombre;
                        optionElementPaciente.setAttribute("tag", item.id);
                        selectElementPaciente.appendChild(optionElementPaciente);


                    })
                }
            });
        }

    renderDataOdon();
    renderDataPac();

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener("submit", function (event) {
    event.preventDefault();

       //creamos un JSON que tendrá los datos del nuevo turno
        const formData = {
            paciente_id: document.getElementById("paciente")[document.getElementById("paciente").selectedIndex].getAttribute("tag"),
            odontologo_id: document.getElementById("odontologo")[document.getElementById("odontologo").selectedIndex].getAttribute("tag"),
            fechaTurno: document.querySelector('#fechaTurno').value,

        };

        console.log(formData);
        //invocamos utilizando la función fetch con el método POST que guardará
        //el turno que enviaremos en formato JSON
        const url ='/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                 //Si no hay ningun error se muestra un mensaje diciendo que el turno
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong>Registro Exitoso</div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                console.error(error);
                    //Si hay algun error se muestra un mensaje diciendo que el odontologo
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong>Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otra registro
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#paciente').value = "";
        document.querySelector('#odontologo').value = "";
         document.querySelector('#fechaTurno').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/buscarTurno.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});