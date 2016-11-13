/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author caiol
 */
public interface DAO<T> {
    public boolean inserir(T obj);
    public boolean alterar(T obj);
    public boolean excluir(T obj);
    public T pesquisar(T obj);
    //public List<T> listar(String filtro);
    public int proxCodigo();
}
