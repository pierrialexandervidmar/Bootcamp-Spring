package com.devsuperior.dscatalog.resources.exceptions;

import java.io.Serializable;

/**
 * Classe auxiliar que representa uma mensagem de erro relacionada a um campo específico.
 *
 * <p>Utilizada para encapsular informações sobre erros de validação de dados,
 * como o nome do campo que apresentou erro e a mensagem descritiva associada.</p>
 *
 * <p>Implementa {@link Serializable} para permitir que instâncias sejam serializadas.</p>
 *
 * @author
 */
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    /**
     * Construtor padrão sem argumentos.
     */
    public FieldMessage() {
    }

    /**
     * Construtor que inicializa o objeto com o nome do campo e a mensagem de erro.
     *
     * @param fieldName o nome do campo que causou o erro
     * @param message a mensagem descrevendo o erro
     */
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    /**
     * Retorna o nome do campo que causou o erro.
     *
     * @return o nome do campo
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Define o nome do campo que causou o erro.
     *
     * @param fieldName o nome do campo
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Retorna a mensagem de erro associada ao campo.
     *
     * @return a mensagem de erro
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem de erro associada ao campo.
     *
     * @param message a mensagem de erro
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
