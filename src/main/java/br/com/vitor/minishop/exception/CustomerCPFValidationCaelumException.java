package br.com.vitor.minishop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerCPFValidationCaelumException extends RuntimeException {
    public CustomerCPFValidationCaelumException(String s){
        super("Não é possivel digitar um CPF invalido.");
    }
}
