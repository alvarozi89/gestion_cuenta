package com.gestion.cuentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.cuentas.entity.Cuenta;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findByClienteId(Long clienteId); 

}
