package br.com.felippe.persistencia;

import br.com.felippe.dao.ProdutoDAO;
import br.com.felippe.modelos.Categoria;
import br.com.felippe.modelos.Produto;
import br.com.felippe.utils.JPAUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProdutoTest {
    private EntityManager entityManager = JPAUtil.getEntityManager();
    private ProdutoDAO dao = new ProdutoDAO(entityManager);

    @Before
    public void setUp() {
        entityManager.getTransaction().begin();
        dao.limparProdutos();
        entityManager.getTransaction().commit();

    }

    @Test
    public void DeveriaCadastrarProdutoNoBancoDeDados() {

        Categoria categoria = new Categoria("Video Games");
        Produto ps4 = new Produto("Video Game", "Playstation4", new BigDecimal(4000), categoria);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.flush();

        dao.adicionar(ps4);
        entityManager.getTransaction().commit();


        Produto produtoDoBancoDeDados = entityManager.find(Produto.class, ps4.getId());
        entityManager.close();
        Assert.assertEquals(ps4.getDescricao(), produtoDoBancoDeDados.getDescricao());

    }

    @Test
    public void DeveriaAtualzarProdutoNoBancoDeDados() {

        Categoria categoria = new Categoria("Video Games");
        Produto ps4 = new Produto("Video Game", "Playstation4", new BigDecimal(4000), categoria);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.flush();

        dao.adicionar(ps4);

        ps4.setNome("Console");
        dao.atualizar(ps4);

        entityManager.getTransaction().commit();

        Produto produtoDoBancoDeDados = entityManager.find(Produto.class, ps4.getId());
        entityManager.close();
        Assert.assertEquals(ps4.getNome(), produtoDoBancoDeDados.getNome());

    }

    @Test
    public void DeveriaRemoverProdutoNoBancoDeDados() {

        Categoria categoria = new Categoria("Video Games");
        Produto ps4 = new Produto("Video Game", "Playstation4", new BigDecimal(4000), categoria);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.flush();

        dao.adicionar(ps4);
        dao.deletar(ps4);
        entityManager.getTransaction().commit();

        Produto produtoDoBancoDeDados = entityManager.find(Produto.class, ps4.getId());
        Assert.assertNull(produtoDoBancoDeDados);

    }

}

