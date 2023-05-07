package br.com.felippe.dao;

import br.com.felippe.modelos.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void adicionar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public void limparProdutos() {
        List<Produto> produtos = this.entityManager.createQuery("select p from Produto p", Produto.class).getResultList();
        for (Produto produto : produtos) {
            this.entityManager.remove(produto);
        }
    }
    public void atualizar(Produto produto) {
        this.entityManager.merge(produto);
    }

    public void deletar(Produto produto) {
        produto = entityManager.merge(produto);
        this.entityManager.remove(produto);
    }

    public List<Produto> buscarTodosOsProdutos() {
        return this.entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public List<Produto> buscarProdutoPorDescricao(String nome) {
        return this.entityManager.createQuery("SELECT p FROM Produto p WHERE p.descricao = :nome", Produto.class).setParameter("nome", nome).getResultList();
    }
}
