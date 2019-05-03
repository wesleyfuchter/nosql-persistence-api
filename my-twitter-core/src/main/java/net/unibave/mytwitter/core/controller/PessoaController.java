package net.unibave.mytwitter.core.controller;

import net.unibave.mytwitter.core.model.PessoaEntity;
import net.unibave.npa.core.persistence.abstracts.AbstractControllerEntity;

public class PessoaController extends AbstractControllerEntity<PessoaEntity> {

    @Override
    public PessoaEntity beforeCreate(PessoaEntity entity) {
        System.out.println("beforeCreate executou "+entity.getNome());
        //entity.setNome("Wesley Fuchter teste");
        return super.beforeCreate(entity);
    }

    @Override
    public PessoaEntity afterCreate(PessoaEntity entity) {
        System.out.println("afterCreate executou "+entity.getNome());
        return super.afterCreate(entity);
    }

    @Override
    public PessoaEntity beforeUpdate(PessoaEntity entity) {
        System.out.println("beforeUpdate executou "+entity.getNome());
        return super.beforeUpdate(entity);
    }

    @Override
    public PessoaEntity afterUpdate(PessoaEntity entity) {
        System.out.println("afterUpdate executou "+entity.getNome());
        return super.afterUpdate(entity);
    }

    @Override
    public PessoaEntity beforeDelete(PessoaEntity entity) {
        System.out.println("beforeDelete executou "+entity.getNome());
        return super.beforeDelete(entity);
    }

    @Override
    public PessoaEntity afterDelete(PessoaEntity entity) {
        System.out.println("afterDelete executou "+entity.getNome());
        return super.afterDelete(entity);
    }

}
