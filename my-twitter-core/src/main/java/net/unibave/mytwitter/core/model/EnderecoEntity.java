package net.unibave.mytwitter.core.model;

import net.unibave.npa.core.persistence.metainf.Attribute;
import net.unibave.npa.core.persistence.metainf.EmbeddedEntity;

@EmbeddedEntity(principalEntityClass = PessoaEntity.class)
public class EnderecoEntity {

    @Attribute(name = "rua")
    private String rua;

    @Attribute(name = "bairro")
    private String bairro;

    @Attribute(name = "numero;")
    private Long numero;

    @Attribute(name = "cidade")
    private String cidade;

    public EnderecoEntity() {
    }

    public EnderecoEntity(String rua, String bairro, Long numero, String cidade) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

