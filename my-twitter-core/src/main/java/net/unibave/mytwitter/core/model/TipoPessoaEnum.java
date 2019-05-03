package net.unibave.mytwitter.core.model;

/**
 * Created by wesley on 19/09/16.
 */
public enum TipoPessoaEnum {

    FISICA("F"), JURIDICA("J");

    private String tipoPessoa;

    public static TipoPessoaEnum toEnum(String value) {
        for (TipoPessoaEnum tipoPessoaEnum : values()) {
            if (tipoPessoaEnum.getTipoPessoa().equals(value)) {
                return tipoPessoaEnum;
            }
        }
        return null;
    }

    TipoPessoaEnum(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
