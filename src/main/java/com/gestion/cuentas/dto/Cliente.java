package com.gestion.cuentas.dto;

import lombok.Data;

@Data
public class Cliente {

    private Long id;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String clienteId;
    private boolean estado;


}
