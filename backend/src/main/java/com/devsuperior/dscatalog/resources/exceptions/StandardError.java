package com.devsuperior.dscatalog.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

/**
 * Classe para representar a estrutura de um erro padrão em resposta a exceções.
 * Utilizada para fornecer informações detalhadas sobre erros ocorridos na aplicação.
 */
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    /**
     * Construtor padrão.
     */
    public StandardError() {
    }

    /**
     * Construtor com parâmetros para inicialização dos atributos.
     *
     * @param timestamp O instante em que o erro ocorreu.
     * @param status O código de status HTTP do erro.
     * @param error A descrição curta do erro.
     * @param message A mensagem detalhada sobre o erro.
     * @param path O caminho da solicitação que causou o erro.
     */
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Obtém o instante em que o erro ocorreu.
     *
     * @return O instante do erro.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Define o instante em que o erro ocorreu.
     *
     * @param timestamp O instante do erro.
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtém o código de status HTTP do erro.
     *
     * @return O código de status HTTP.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Define o código de status HTTP do erro.
     *
     * @param status O código de status HTTP.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Obtém a descrição curta do erro.
     *
     * @return A descrição do erro.
     */
    public String getError() {
        return error;
    }

    /**
     * Define a descrição curta do erro.
     *
     * @param error A descrição do erro.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Obtém a mensagem detalhada sobre o erro.
     *
     * @return A mensagem de erro.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem detalhada sobre o erro.
     *
     * @param message A mensagem de erro.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtém o caminho da solicitação que causou o erro.
     *
     * @return O caminho da solicitação.
     */
    public String getPath() {
        return path;
    }

    /**
     * Define o caminho da solicitação que causou o erro.
     *
     * @param path O caminho da solicitação.
     */
    public void setPath(String path) {
        this.path = path;
    }
}
