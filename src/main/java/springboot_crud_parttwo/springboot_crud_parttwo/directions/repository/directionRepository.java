package springboot_crud_parttwo.springboot_crud_parttwo.directions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import springboot_crud_parttwo.springboot_crud_parttwo.directions.model.directions;

public interface  directionRepository extends  JpaRepository<directions, Long>{
    @Procedure(name = "direccion.insertar")
    void insertarDireccion(Long p_cliente_id, String p_direccion, String p_ciudad, String p_departamento, 
    String p_codigo_postal, String p_tipo);

    @Procedure(name = "direccion.actualizar")
    void actualizarDireccion(Long p_direccion_id, Long p_cliente_id, String p_direccion, String p_ciudad,
    String p_departamento, String p_codigo_postal, String p_tipo);

    @Procedure(name = "direccion.eliminar")
    void eliminarDireccion(Long p_direccion_id);

    @Procedure(name = "direccion.obtener_todas_direcciones")
    List<directions> obtenerTodasDirecciones();

    @Procedure(name = "direccion.obtener_direccion_por_id")
    directions obtenerDireccionPorId(Long p_direccion_id);

    @Procedure(name = "direccion.obtener_direcciones_por_cliente")
    List<directions> obtenerDireccionPorClienteId(Long p_cliente_id);
}
