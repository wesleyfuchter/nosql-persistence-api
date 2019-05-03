package net.unibave.mytwitter.core;

import net.unibave.mytwitter.core.dao.PessoaDAO;
import net.unibave.mytwitter.core.model.PessoaEntity;
import net.unibave.mytwitter.core.model.TipoPessoaEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by wesley on 06/10/16.
 */
public class Example {

    public static void main(String[] args) throws Exception {

        final PessoaDAO pessoaDAO = new PessoaDAO();
        final PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome("Pessoa Teste");
        pessoaEntity.setDataNascimento(new Date());
        pessoaDAO.create(pessoaEntity);
        pessoaEntity.setNome("Pessoa atualizada");
        pessoaDAO.update(pessoaEntity);
        final PessoaEntity pessoaRead = pessoaDAO.read(1l);
        final List<PessoaEntity> readEntityList = pessoaDAO.read();
        pessoaDAO.delete(pessoaEntity);

    }

}
