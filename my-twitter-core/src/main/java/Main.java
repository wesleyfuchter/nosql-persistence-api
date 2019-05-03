import net.unibave.mytwitter.core.dao.PessoaDAO;
import net.unibave.mytwitter.core.model.EnderecoEntity;
import net.unibave.mytwitter.core.model.PessoaEntity;
import net.unibave.mytwitter.core.model.TipoPessoaEnum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wesley on 28/05/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        final PessoaDAO pessoaDAO = new PessoaDAO();

        final PessoaEntity pessoaEntity = new PessoaEntity();

      //  pessoaEntity.setId(7l);
        pessoaEntity.setNome("");
        pessoaEntity.setDataNascimento(new Date());
        pessoaEntity.setTipoPessoa(TipoPessoaEnum.JURIDICA);

        pessoaEntity.setEndereco(new EnderecoEntity("a","b",123l,"sl"));
        pessoaEntity.setEnderecosAdicionais(Arrays.asList(new EnderecoEntity("a","b",123l,"sl"),new EnderecoEntity("c","b",123l,"tb")));

        pessoaDAO.create(pessoaEntity);

        pessoaEntity.setNome("");
        pessoaDAO.update(pessoaEntity);

       // pessoaDAO.delete(pessoaEntity);

        final PessoaEntity pessoaRead = pessoaDAO.read(pessoaEntity.getId());

        String nome = pessoaRead.getNome();

        System.out.println("Retorno consulta ID 2: "+pessoaRead.getNome());

        final List<PessoaEntity> readEntityList = pessoaDAO.read();

        readEntityList.forEach((pessoa) -> System.out.println("Retorno consulta total: "+pessoa.getTipoPessoa()));

//        pessoaDAO.delete(pessoaRead);

    }

}
