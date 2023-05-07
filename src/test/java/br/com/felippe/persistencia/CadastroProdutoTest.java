package br.com.felippe.persistencia;

import br.com.felippe.modelos.Produto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CadastroProdutoTest {
    @Before
    public void setUp() {
        limparBancoDeDados();
    }

    @Test
    public void DeveriaCadastrarProdutoNoBancoDeDados() {
        Produto celular = new Produto();
        celular.setNome("Celular");
        celular.setDescricao("Galaxy S20 Fe");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("loja");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(celular);

        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        Produto produtoDoBancoDeDados = entityManager.find(Produto.class, celular.getId());

        entityManager.close();
        entityManagerFactory.close();

        Assert.assertEquals(celular.getNome(), produtoDoBancoDeDados.getNome());
        Assert.assertEquals(celular.getDescricao(), produtoDoBancoDeDados.getDescricao());
    }

    private void limparBancoDeDados() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("loja");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Produto> produtos = entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
        for (Produto produto : produtos) {
            entityManager.remove(produto);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
