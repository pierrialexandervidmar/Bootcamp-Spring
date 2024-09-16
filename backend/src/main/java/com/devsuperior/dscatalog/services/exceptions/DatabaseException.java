package com.devsuperior.dscatalog.services.exceptions;

/**
 * Exceção personalizada lançada quando ocorre uma violação de integridade referencial no banco de dados.
 * 
 * Esta exceção estende RuntimeException, permitindo que seja usada em transações
 * sem a necessidade de ser explicitamente tratada.
 */
public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construtor que recebe uma mensagem detalhando a causa da exceção.
     *
     * @param msg A mensagem explicando o motivo da exceção.
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
