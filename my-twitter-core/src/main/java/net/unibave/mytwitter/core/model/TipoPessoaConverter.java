package net.unibave.mytwitter.core.model;

import net.unibave.npa.core.persistence.abstracts.IConverter;

import java.util.Objects;

/**
 * Created by wesley on 10/10/16.
 */
public class TipoPessoaConverter implements IConverter {

    @Override
    public Object setAs(Object o) {
        if (Objects.nonNull(o)) {
            if (o instanceof String) {
                final String value = (String) o;
                return TipoPessoaEnum.toEnum(value);
            }
            throw new IllegalArgumentException("TipoPessoa must be String");
        } else {
            return null;
        }
    }

    @Override
    public Object getAs(Object o) {
        if (Objects.nonNull(o)) {
            if (o instanceof TipoPessoaEnum) {
                final TipoPessoaEnum tipoPessoaEnum = (TipoPessoaEnum) o;
                return tipoPessoaEnum.getTipoPessoa();
            }
            throw new IllegalArgumentException("TipoPessoa must be a "+TipoPessoaEnum.class.getName());
        } else {
            return null;
        }
    }
}
