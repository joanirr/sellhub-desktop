package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.repositorio.CrudRepositorio;
import com.jotadev.gestao.vendas.modelo.util.SQLFormato;
import com.jotadev.gestao.vendas.modelo.util.SQLFormatoAtualizar;
import com.jotadev.gestao.vendas.modelo.util.SQLFormatoInserir;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.reflections.ReflectionUtils;

public abstract class CrudRepositorioImpl<T> implements CrudRepositorio<T> {
    
    private Class<T> t;
    
    public CrudRepositorioImpl(Class<T> t) {
        this.t = t;
    }
    
    @Override
    public boolean salvar(T t) {
        Object id = null;
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> 
            !e.getName().equalsIgnoreCase("categoria") && 
            !e.getName().equalsIgnoreCase("serialVersionUID")
        );

        try {
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().equalsIgnoreCase("id")) {
                    id = campo.get(t);
                }
            }

            if (id == null || id.toString().equals("0")) { 
                SQLFormato sql = new SQLFormatoInserir(this.t);
                return persistencia(sql.formato(), t, false);
            }
            SQLFormato sql = new SQLFormatoAtualizar(this.t);
            return persistencia(sql.formato(), t, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int preencherPreparedStatement(Object t, PreparedStatement ps, boolean atualizar) {
        int contador = 1; 
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> 
            !e.getName().equalsIgnoreCase("categoria") && 
            !e.getName().equalsIgnoreCase("serialVersionUID")
        );

        try {
            Field campoId = null;
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().equalsIgnoreCase("id")) {
                    campoId = campo;
                    if (atualizar) continue; 
                }
                ps.setObject(contador, campo.get(t));
                contador++;
            }

            if (atualizar && campoId != null) {
                ps.setObject(contador, campoId.get(t));
            } else {
                contador--;
            }

            return atualizar ? contador : contador; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private boolean persistencia(String sql, Object t, boolean atualizar){
        try {
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);

            int totalPreenchido = preencherPreparedStatement(t, ps, atualizar);

            int resultado = ps.executeUpdate();
            return resultado == 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public List<T> buscarTodos() {
        List<T> lista = new ArrayList<>();
        try {
            String SQL = String.format("SELECT * FROM %s", t.getSimpleName());
            ResultSet resultSet = ConexaoMySQL.obterConexao().prepareStatement(SQL).executeQuery();
            
            while (resultSet.next()) {
                lista.add(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return lista;
    }
    
    public T getT(ResultSet resultSet) {
        try {
            T tNovo = t.newInstance();
            Method metodo = null;
            Set<Field> campos = ReflectionUtils.getAllFields(t, e -> true);
            
            for (Field campo : campos){
                if (campo.getName().equalsIgnoreCase("categoria")) continue;
                Object valor = null;
                String nome = campo.getName();
                
                switch(campo.getType().getSimpleName().toUpperCase()) {
                    case "STRING" -> { 
                        valor = resultSet.getString(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), String.class);
                    }
                    
                    case "LONG" -> { 
                        valor = resultSet.getLong(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Long.class);
                    }
                    
                    case "INTEGER" -> { 
                        valor = resultSet.getInt(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Integer.class);
                    }
                    
                    case "BOOLEAN" -> { 
                        valor = resultSet.getBoolean(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Boolean.class);
                    }
                    
                    case "OBJECT" -> { 
                        valor = resultSet.getObject(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), Object.class);
                    }
                    
                    case "LOCALDATETIME" -> { 
                        valor = resultSet.getObject(nome, LocalDateTime.class);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), LocalDateTime.class);
                    }
                    
                    case "BIGDECIMAL" -> { 
                        valor = resultSet.getBigDecimal(nome);
                        metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), BigDecimal.class);
                    }
                }
                
                metodo.invoke(tNovo, valor);
            }
            return tNovo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private String primeiraLetraMaiuscula(String texto) {
        if (!texto.isBlank()) {
            return texto.substring(0, 1).toUpperCase().concat(texto.substring(1));
        }
        
        return "";
    }

    @Override
    public Optional<T> buscarPeloId(Long id) {
        try {
            String SQL = String.format("SELECT * FROM %s WHERE id = ?", t.getSimpleName());
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            
            ps.setLong(1, id);
            
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return Optional.empty();
    }

    @Override
    public boolean removerPeloId(Long id) {
        try {
            String SQL = String.format("DELETE FROM %s WHERE id = ?", t.getSimpleName());
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            
            ps.setLong(1, id);
            
            int resultado = ps.executeUpdate();
            
            return resultado == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
