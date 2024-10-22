package com.gestion.cuentas.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.cuentas.entity.Cuenta;
import com.gestion.cuentas.exception.CuentaExistenteException;
import com.gestion.cuentas.exception.CuentaNoEncontradaException;
import com.gestion.cuentas.repository.CuentaRepository;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    
    @Autowired
    private ClienteService clienteService;

    public Cuenta guardar(Cuenta cuenta) {
        boolean existe = cuentaRepository.existsByNumeroCuenta(cuenta.getNumeroCuenta());
        if (existe) {
            throw new CuentaExistenteException("Ya existe una cuenta con el número " + cuenta.getNumeroCuenta());
        }
        clienteService.obtenerClientePorId(cuenta.getClienteId());

        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> obtenerTodas() {
        return cuentaRepository.findAll();
    }


    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));
    }


    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuentaExistente = obtenerPorId(id); 

        cuentaExistente.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuentaExistente.setEstado(cuentaActualizada.isEstado());
        cuentaExistente.setClienteId(cuentaActualizada.getClienteId());

        return cuentaRepository.save(cuentaExistente);
    }

    public void eliminar(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));
        cuentaRepository.deleteById(id);
    }
}
