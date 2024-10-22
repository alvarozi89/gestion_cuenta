package com.gestion.cuentas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.cuentas.entity.Movimiento;
import com.gestion.cuentas.service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/registrar")
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
        return ResponseEntity.ok(nuevoMovimiento);
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<Movimiento>> obtenerReporte(@RequestParam("clienteId") Long clienteId, 
                                           @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, 
                                           @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorRango(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }
}
