package springboot_crud_parttwo.springboot_crud_parttwo.directions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class paginaWC {
    
    @GetMapping("/direcciones")
    public String PageAddresses() {
        return "paginas/direcciones";
    }
    @GetMapping("/bienvenida")
    public String PageWelcome() {
        return "paginas/bienvenida";
    }
    
    @GetMapping("/agregarDireccion")
    public String AddDireccion() {
        return "formularios/agregarDireccion";
    }
   
    @GetMapping("/editarDireccion")
    public String EditDireccion() {
        return "formularios/editarDireccion";
    }
    
    
    
}
