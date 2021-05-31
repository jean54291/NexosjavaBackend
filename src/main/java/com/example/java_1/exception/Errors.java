package com.example.java_1.exception;


public enum Errors {
    FECHA_ERROR("Fecha de igreso debe ser menor o igual a al fecha actual"),
    NO_TIENE_FILTROS_LA_CONSULTA("La consulta debe tener 1 filtro"),
    USUARIOS_VACIO("No hay Usuarios Creados en base de datos"),
    CARGOS_VACIO("No hay Cargos Creados en base de datos"),
    USUARIO_NOT_EXIST("Usuario No existe en la base de datos"),
    ERROR_VALIDAR_DATOS_ENVIADOS("Error al momento de ejecutar el servicio por favor validar los datos"),
    ERROR_PERMISOS_USUARIOS("Error al momento eliminar valida el usuario de registro"),
    CANTIDAD_NUMERO("La cantidad debe ser igual o mayor a 0"),
    MERCANCIA_YA_EXISTE("El nombre que esta utilizando ya existe por favor validar");
    String descripcion;

    Errors(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
