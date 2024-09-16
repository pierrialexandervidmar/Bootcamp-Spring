package com.devsuperior.dscatalog.services.exceptions;

/**
 * Exceção personalizada lançada quando um recurso solicitado não é encontrado.
 * 
 * Esta exceção estende RuntimeException, permitindo que seja usada em transações
 * sem a necessidade de ser explicitamente tratada. É tipicamente usada para indicar
 * que a entidade ou recurso não foi encontrado no banco de dados.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor que recebe uma mensagem detalhando a causa da exceção.
     *
     * @param msg A mensagem explicando o motivo da exceção, geralmente indicando qual recurso não foi encontrado.
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
