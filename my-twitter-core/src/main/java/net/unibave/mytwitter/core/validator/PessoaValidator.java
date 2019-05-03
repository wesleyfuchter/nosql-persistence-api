package net.unibave.mytwitter.core.validator;

import net.unibave.mytwitter.core.model.PessoaEntity;
import net.unibave.npa.core.persistence.abstracts.AbstractValidator;

import java.util.Objects;

/**
 * Created by wesley on 02/11/16.
 */
public class PessoaValidator extends AbstractValidator<Long, PessoaEntity> {

    @Override
    public Boolean validateEntity(PessoaEntity pessoaEntity) throws Exception {
        return true;
    }

    @Override
    public Boolean validateField(PessoaEntity pessoaEntity, Object o) throws Exception {
        return true;
    }
}
