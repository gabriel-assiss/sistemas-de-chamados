package br.com.projeto.chamados.dto;

import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.enums.Role;

public class RequestBuscarChamadosUser {
    long  id;
    Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
