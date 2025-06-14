package springboot_crud_parttwo.springboot_crud_parttwo.directions.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import springboot_crud_parttwo.springboot_crud_parttwo.directions.model.directions;
import  springboot_crud_parttwo.springboot_crud_parttwo.directions.repository.directionRepository;

@Service
public class direccionService {
    // Repositorio de direcciones que se inyectara en el constructor
    
    private final directionRepository directionRepository;
    
    // haz una llamada HTTP al microservicio de clientes
    private final RestTemplate restTemplate = new RestTemplate();

    //constructor de la clase que recibe un repositorio de direcciones 
    public direccionService(directionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }
    // metodo para obtener todas las direcciones este sera llamado desde el controlador
    @Transactional(readOnly = true)
    public List<directions> getAllDirections() {
        return directionRepository.obtenerTodasDirecciones();
    }
    @Transactional(readOnly = true)
     public directions getDirectionById(Long id) {
        return directionRepository.obtenerDireccionPorId(id);
    }
    @Transactional(readOnly = true)
    public void saveDirection(directions direction) {
        Long clienteId =direction.getClienteId();
        System.out.println("Cliente ID: " + clienteId);
        String url = "http://localhost:8082/api/directions/get/dirCli/" + clienteId;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Cliente con ID " + clienteId + " no encontrado.");
            }
        } catch (RestClientException e) {
            throw new RuntimeException("Cliente con ID " + clienteId + " no encontrado.");
        }
        directionRepository.insertarDireccion(
            direction.getClienteId(),
            direction.getDireccion(),
            direction.getCiudad(),
            direction.getDepartamento(),
            direction.getCodigoPostal(),
            direction.getTipo()
        );
    }
    @Transactional(readOnly = true)
    public void updateDirection(Long id, directions directionDa){
        try {
            directions existing = directionRepository.obtenerDireccionPorId(id);
            if (existing != null) {
                directionRepository.actualizarDireccion(
                    id,
                    directionDa.getClienteId(),
                    directionDa.getDireccion(),
                    directionDa.getCiudad(),
                    directionDa.getDepartamento(),
                    directionDa.getCodigoPostal(),
                    directionDa.getTipo()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating direction with id: " + id, e);
        }
    }
    @Transactional(readOnly = true)
     public directions deleteDirection(Long id){
        try {
            directions direccion = directionRepository.obtenerDireccionPorId(id);
            if (direccion != null) {
                directionRepository.eliminarDireccion(id);
                return direccion;
            } 
        } catch (Exception e) {
            throw new RuntimeException("Error deleting direction with id: " + id, e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<directions> getDirectionByClientId(Long id) {
        return directionRepository.obtenerDireccionPorClienteId(id);
    }

}