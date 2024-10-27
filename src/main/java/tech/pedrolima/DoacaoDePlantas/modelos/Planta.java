package tech.pedrolima.DoacaoDePlantas.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "planta")
public class Planta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String caminhoImg;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Cadastro cadastro;
    private boolean adocaoSolicitada;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoImg() {
        return caminhoImg;
    }

    public void setCaminhoImg(String caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public boolean getAdocaoSolicitada() {
        return adocaoSolicitada;
    }

    public void setAdocaoSolicitada(boolean adocaoSolicitada) {
        this.adocaoSolicitada = adocaoSolicitada;
    }
}