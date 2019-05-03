package net.unibave.mytwitter.core.model;

import net.unibave.mytwitter.core.controller.PessoaController;
import net.unibave.mytwitter.core.dao.ContextFactoryImpl;
import net.unibave.mytwitter.core.validator.PessoaValidator;
import net.unibave.npa.core.persistence.exceptions.ValidatorException;
import net.unibave.npa.core.persistence.metainf.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by wesley on 28/05/16.
 */
@Entity(name = "cadpessoa", comment = "Cadastro de Pessoas", dataBase = "test")
@ContextFactory(ContextFactoryImpl.class)
@Controller(implementClass = PessoaController.class)
@Validator(implementClass = PessoaValidator.class, message = "Pessoa inválida")
public class PessoaEntity implements Serializable {

    @EntityKey
    @Attribute(name = "_id", comment = "Identificação da Pessoa")
    @NotNull
    private Long id;

    @Attribute(name = "nome", comment = "Nome da Pessoa")
    @NotNull
    @MinLength(1)
    @MaxLength(3)
    private String nome;

    @Attribute(name = "dataNascimento", comment = "Data de nascimento da Pessoa")
    @NotNull
    private Date dataNascimento;

    @Converter(implementClass = TipoPessoaConverter.class)
    @Attribute(name = "tipoPessoa", comment = "Tipo da Pessoa")
    @NotNull
    private TipoPessoaEnum tipoPessoa;

    @OneToOne
    @Attribute(name = "endereco", comment = "Endereço da Pessoa")
    @NotNull
    private EnderecoEntity endereco;

    @OneToMany
    @Attribute(name = "enderecosAdicionais", comment = "Endereço adicionais da Pessoa")
    private List<EnderecoEntity> enderecosAdicionais;

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public List<EnderecoEntity> getEnderecosAdicionais() {
        return enderecosAdicionais;
    }

    public void setEnderecosAdicionais(List<EnderecoEntity> enderecosAdicionais) {
        this.enderecosAdicionais = enderecosAdicionais;
    }
}
