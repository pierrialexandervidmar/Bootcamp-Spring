package com.devsuperior.dscatalog.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um erro de validação contendo múltiplos campos inválidos.
 *
 * <p>Extende a classe {@link StandardError} para incluir uma lista de erros de campo específicos,
 * cada um representado por um {@link FieldMessage}.</p>
 *
 * <p>Utilizada para retornar informações detalhadas sobre falhas de validação em requisições.</p>
 *
 */
public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    /**
     * Retorna a lista de erros de validação.
     *
     * @return Lista de objetos {@link FieldMessage} contendo informações dos campos inválidos.
     */
    public List<FieldMessage> getErrors() {
        return errors;
    }

    /**
     * Adiciona um novo erro de validação à lista de erros.
     *
     * @param fieldName o nome do campo que falhou na validação
     * @param message a mensagem descritiva do erro de validação
     */
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
