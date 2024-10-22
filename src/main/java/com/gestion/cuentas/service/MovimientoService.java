package com.gestion.cuentas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.cuentas.entity.Cuenta;
import com.gestion.cuentas.entity.Movimiento;
import com.gestion.cuentas.exception.CuentaNoEncontradaException;
import com.gestion.cuentas.exception.SaldoInsuficienteException;
import com.gestion.cuentas.repository.CuentaRepository;
import com.gestion.cuentas.repository.MovimientoRepository;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

   
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + movimiento.getCuenta().getId() + " no encontrada"));

        clienteService.obtenerClientePorId(cuenta.getClienteId());

        if (movimiento.getValor() == 0) {
            throw new IllegalArgumentException("El valor del movimiento no puede ser cero.");
        }

        if (movimiento.getValor() < 0 && cuenta.getSaldoInicial() < Math.abs(movimiento.getValor())) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
        cuentaRepository.save(cuenta);

        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setFecha(new Date());
        return movimientoRepository.save(movimiento);
    }

    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta con ID " + id + " no encontrada"));
    }

    public List<Movimiento> obtenerMovimientosPorRango(Long clienteId, Date fechaInicio, Date fechaFin) {
        clienteService.obtenerClientePorId(clienteId);
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        return movimientoRepository.findByCuentaInAndFechaBetween(cuentas, fechaInicio, fechaFin);
    }
}
