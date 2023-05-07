package br.com.felippe.persistencia;

import br.com.felippe.dao.ProdutoDAO;
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

        Produto ps4 = new Produto("Video Game", "Playstation4", new BigDecimal(4000) );

        entityManager.getTransaction().begin();
        dao.adicionar(ps4);

        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        Produto produtoDoBancoDeDados = entityManager.find(Produto.class, ps4.getId());
        entityManager.close();
        Assert.assertEquals(ps4.getDescricao(), produtoDoBancoDeDados.getDescricao());

    }

}
