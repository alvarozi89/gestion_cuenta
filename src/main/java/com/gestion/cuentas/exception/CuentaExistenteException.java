package com.gestion.cuentas.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CuentaExistenteException extends RuntimeException {
    public CuentaExistenteException(String message) {
        super(message);
    }
}
