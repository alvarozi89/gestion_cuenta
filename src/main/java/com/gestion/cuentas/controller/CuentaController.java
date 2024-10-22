package com.gestion.cuentas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.cuentas.entity.Cuenta;
import com.gestion.cuentas.service.CuentaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;


    @GetMapping("/all")
    public List<Cuenta> obtenerCuentas() {
        return cuentaService.obtenerTodas();
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerPorId(id);
        return ResponseEntity.ok(cuenta);
    }

  
    @PostMapping("/create")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody Cuenta cuenta) {
        try {
            Cuenta nuevaCuenta = cuentaService.guardar(cuenta);
            return ResponseEntity.ok(nuevaCuenta);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

  
    @PutMapping("/update/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @Valid @RequestBody Cuenta cuentaActualizada) {
        Cuenta cuenta = cuentaService.actualizarCuenta(id, cuentaActualizada);
        return ResponseEntity.ok(cuenta);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable Long id) {
        try {
            cuentaService.eliminar(id);
            return ResponseEntity.ok("Cuenta con ID " + id + " eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());  // Devuelve un código 404 si la cuenta no existe
        }
    }
}
