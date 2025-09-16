package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import com.devsuperior.dscatalog.services.validation.UserUpdateValid;

/**
 * Classe de transferência de dados (DTO) usada para inserir novos usuários.
 * Herda os campos de {@link UserDTO} e adiciona o campo de senha.
 * É utilizada nas operações de criação de novos usuários, onde a senha também precisa ser enviada.
 */

@UserUpdateValid
public class UserUpdateDTO extends UserDTO {
    private static final long serialVersionUID = 1L;

}
