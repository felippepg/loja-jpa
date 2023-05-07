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
}
