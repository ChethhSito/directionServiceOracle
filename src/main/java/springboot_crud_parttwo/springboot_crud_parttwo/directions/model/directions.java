package springboot_crud_parttwo.springboot_crud_parttwo.directions.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@NamedStoredProcedureQuery(
    name="direccion.insertar",
    procedureName="DIRECCIONES_PKG.insertar_direccion",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_cliente_id",type= Long.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_direccion",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_ciudad",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_departamento",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_codigo_postal",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_tipo",type= String.class)
    }
)

@NamedStoredProcedureQuery(
    name="direccion.actualizar",
    procedureName="DIRECCIONES_PKG.actualizar_direccion",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_direccion_id",type= Long.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_cliente_id",type= Long.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_direccion",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_ciudad",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_departamento",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_codigo_postal",type= String.class),
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_tipo",type= String.class)
    }
)

@NamedStoredProcedureQuery(
    name="direccion.eliminar",
    procedureName="DIRECCIONES_PKG.eliminar_direccion",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_direccion_id",type= Long.class)
    }
)

@NamedStoredProcedureQuery(
    name="direccion.obtener_todas_direcciones",
    procedureName="DIRECCIONES_PKG.obtener_todas_direcciones",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.REF_CURSOR, name="p_resultado",type= Void.class)
    },
    resultClasses = directions.class
)

@NamedStoredProcedureQuery(
    name="direccion.obtener_direccion_por_id",
    procedureName="DIRECCIONES_PKG.obtener_direccion_por_id",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_direccion_id",type= Long.class),
        @StoredProcedureParameter(mode= ParameterMode.REF_CURSOR, name="p_resultado",type= Void.class)
    },
    resultClasses = directions.class
)
@NamedStoredProcedureQuery(
    name="direccion.obtener_direcciones_por_cliente",
    procedureName="DIRECCIONES_PKG.obtener_direcciones_por_cliente",
    parameters={ 
        @StoredProcedureParameter(mode= ParameterMode.IN, name="p_cliente_id",type= Long.class),
        @StoredProcedureParameter(mode= ParameterMode.REF_CURSOR, name="p_resultado",type= Void.class)
    },
    resultClasses = directions.class
)

@Entity
@Table(name = "DIRECCIONES")
public class directions {
    
    @Id
    @Column(name = "direccion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;
    @Column(name="direccion")
    private String direccion;
    @Column(name="ciudad")
    private String ciudad;
    @Column(name="departamento")
    private String departamento;
    @Column(name="codigo_postal")
    private String codigoPostal;
    @Column(name="tipo")
    private String tipo;


    public Long getId(){ return id;}
    public void setId(Long id){this.id = id; }

    public Long getClienteId(){ return clienteId; }
    public void setClienteId(Long clienteId){ this.clienteId = clienteId; }

    public String getDireccion(){ return direccion; }
    public void setDireccion(String direccion){ this.direccion = direccion; }

    public String getCiudad(){ return ciudad; }
    public void setCiudad(String ciudad){ this.ciudad = ciudad; }

    public String getDepartamento(){ return departamento; }
    public void setDepartamento(String departamento){ this.departamento = departamento; }

    public String getCodigoPostal(){ return codigoPostal; }
    public void setCodigoPostal(String codigoPostal){ this.codigoPostal = codigoPostal; }

    public String getTipo(){ return tipo; }
    public void setTipo(String tipo){ this.tipo = tipo;}


}
