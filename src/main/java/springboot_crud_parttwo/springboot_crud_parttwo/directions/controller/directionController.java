package springboot_crud_parttwo.springboot_crud_parttwo.directions.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot_crud_parttwo.springboot_crud_parttwo.directions.model.directions;
import springboot_crud_parttwo.springboot_crud_parttwo.directions.service.direccionService;


@RestController
@RequestMapping("/api/directions")
public class directionController {
    
    private final direccionService direccionService;

    // constructor de la clase que recibe un servicio de direcciones  
    public directionController(direccionService directionService) {
        this.direccionService = directionService;
    }

    @GetMapping
    public List<directions> getAllDirections() {
        System.out.println("Fetching all directions");
        //hace la conversion de la lista de direcciones a JSON 
        return direccionService.getAllDirections();
    }
     @GetMapping("/get/{id}")
    public directions getDireccionId(@PathVariable Long id) {
        return direccionService.getDirectionById(id);
    }
    @PostMapping("/add")
    public void nuevaDireccion(@RequestBody directions direction) {
        direccionService.saveDirection(direction);
    }
    @PutMapping("/update/{id}")
    public void actualizarDirection(@PathVariable Long id,@RequestBody directions datos) {
        direccionService.updateDirection(id, datos);
    }
    @DeleteMapping("/delete/{id}")
    public directions eliminarDirection(@PathVariable Long id) {
        return direccionService.deleteDirection(id);
    }
    @GetMapping("/get/dirCli/{id}")
    public List<directions> getDireccionClienteId(@PathVariable Long id) {
        return direccionService.getDirectionByClientId(id);
    }
    
    

}   