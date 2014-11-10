package com.auditoria.sqlitecrud.models;

/**
 * Created by trejo on 11/10/14.
 */
public class ExperienciaLaboral {
    private long id;
    private String fecha_inicio;
    private String fecha_finalizacion;
    private String descripcion;

    public ExperienciaLaboral(long id, String fecha_inicio, String fecha_finalizacion, String descripcion){
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(String fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
