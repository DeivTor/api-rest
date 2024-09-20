package com.message.helloworld;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController //Etiqueta que indica que esto es un controlador REST

@RequestMapping("/prueba/v1") //Definimos una ruta base por la que podremos accerder a los distintos metodos que crearemos.

public class Helloworldcontroller {



    //Creacion de una lista tipo String para almecanar ciertos paises.
    private List<String> items = new ArrayList<String>();

    // Al instanciar la clase de 'Helloworldcontroller' se creara y agregara estos paises a la lista 'items'.
    public Helloworldcontroller(){
        items.add("Colombia");
        items.add("Brasil");
        items.add("Argentina");
        items.add("Peru");
        items.add("Chile");
        items.add("Venezuela");
        items.add("Bolivia");
    }



    //GET: Para realizar la consulta de elementos
    //1. Cuando un usuario consulta mediante GET la URL '/api/v1', Retornara todos los items de la lista (items).

    @GetMapping
    public List<String> getAllItems() {
        return items;
    }


    //2. Cuando el usuario consulta la la URL '/api/v1' y le agrega un index '/{index}', el metodo retornara el pais correspondiente al index establecido
    //2.1 Tambien si se coloca un index incorrecto le arrojara "Item no encontrado".

    @GetMapping("/{index}")
    public String getItem(@PathVariable int index){
        if(index >= 0 && index < items.size()){
            return items.get(index);
        }else{
            return "Item no encontrado!";
        }
    }

    //POST: Creacion de elementos.
    //3. Este metodo se ejecuta cuando el pasamos la solicitud POST.
    // Este agrega un nuevo pais a la lista 'Items' que creamos antes por medio de la funcion @RequestBody
    // Al ser agregado correctamente, se mostrara un mensaje de que se 'Inserto con exito'

    @PostMapping
    public String addItem(@RequestBody String newItem){
        items.add(newItem);
        return "Item Insertado con Exito!!";
    }



    //PUT: Actualizacion de elementos.
    // 4. En este, se debe de especificar un index del pais a modificar en la URL y el valor que quiere que quede se a de colocar en el cuerpo de la solicitud.
    // Luego utilizamos la misma logica utilizada antes para verificar si es existente el indice ingresado.

    @PutMapping("/{index}") // Se pide un index para poder actualizar
    public String modifyItem(@PathVariable int index, @RequestBody String newItem){ // Se toma el index y el nombre a actualizar.
        if(index >= 0 && index < items.size()){
            items.set(index, newItem);
            return "Item Modificado con Exito!!";
        }else{
            return "Item no encontrado!";
        }
    }


    //DELETE: Eliminacion de elementos.
    // 5. Se especifica el valor a eliminar por medio de la URL, por medio de la funcion @PathVariable.
    // Si el indice es valido, se realizara dicha eliminacion del pais correspondiente y retornara un texto que dice que fue hecho exitosamente.


    @DeleteMapping("/{index}")
    public String deleteItem(@PathVariable int index){
        if(index >= 0 && index < items.size()){
            items.remove(index);
            return "Item eliminado con Exito!!";
        }else{
            return "Item no encontrado!";
        }
    }

}
