package com.gestion.cuentas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gestion.cuentas.dto.Cliente;

@Service
public class ClienteService {

    @Autowired
    private RestTemplate restTemplate;

    public Cliente obtenerClientePorId(Long clienteId) {
        return restTemplate.getForObject("gestioncliente-production.up.railway.app/api/clientes/" + clienteId, Cliente.class);
    }
}
