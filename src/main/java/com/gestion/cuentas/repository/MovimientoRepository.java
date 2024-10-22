package com.gestion.cuentas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.cuentas.entity.Cuenta;
import com.gestion.cuentas.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaInAndFechaBetween(List<Cuenta> cuentas, Date fechaInicio, Date fechaFin);
}
