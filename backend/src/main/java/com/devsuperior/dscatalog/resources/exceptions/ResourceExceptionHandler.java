package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Manipulador global de exceções para a aplicação.
 * Captura e processa exceções lançadas pelos controladores e retorna respostas adequadas.
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Manipulador para exceções de recurso não encontrado.
     *
     * @param e A exceção de recurso não encontrado.
     * @param request A solicitação HTTP que causou a exceção.
     * @return Resposta HTTP com detalhes do erro e status 404 (Não Encontrado).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Recurso não encontrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Manipulador para exceções de banco de dados.
     *
     * @param e A exceção de banco de dados.
     * @param request A solicitação HTTP que causou a exceção.
     * @return Resposta HTTP com detalhes do erro e status 400 (Solicitação Inválida).
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Exceção de Banco de Dados");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(err);
    }
}
