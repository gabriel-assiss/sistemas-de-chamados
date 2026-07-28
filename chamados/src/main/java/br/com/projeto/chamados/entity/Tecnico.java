package br.com.projeto.chamados.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
