package br.com.vitor.minishop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerCPFInvalidoException extends RuntimeException {
    public CustomerCPFInvalidoException(String s){
        super("Não é possivel digitar um CPF já existente.");
    }
}
