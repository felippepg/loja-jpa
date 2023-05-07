package br.com.felippe;

import br.com.felippe.dao.ProdutoDAO;
import br.com.felippe.modelos.Categoria;
import br.com.felippe.modelos.Produto;
import br.com.felippe.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
//        CADASTRO DE PRODUTOS
//
//        Categoria celulares = new Categoria("Celulares");
//
//        Produto produto = new Produto("Celular", "Iphone 12", new BigDecimal(5000), celulares);
//        Produto produto2 = new Produto("Celular", "Galaxy s20 fe", new BigDecimal(2500), celulares);
//        Produto produto3 = new Produto("Celular", "Moto G", new BigDecimal(1200), celulares);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(celulares);
//        entityManager.flush();
//
//        produtoDAO.adicionar(produto);
//        produtoDAO.adicionar(produto2);
//        produtoDAO.adicionar(produto3);
//        entityManager.getTransaction().commit();
//
//
//
        List<Produto> produtos = produtoDAO.buscarProdutoPorDescricao("Iphone 12");
        produtos.forEach(System.out::println);
        entityManager.close();

    }
}