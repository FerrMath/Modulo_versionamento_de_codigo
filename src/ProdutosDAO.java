/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.execute();
            
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto:\n" + e.getMessage());
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM produtos";
        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while(resultset.next()){
                int id = resultset.getInt("id");
                int valor = resultset.getInt("valor");
                String nome = resultset.getString("nome");
                String status = resultset.getString("status");
                listagem.add(new ProdutosDTO(id, nome, valor, status));
            }
        } catch (SQLException e) {     
        }
        return listagem;
    }
    
    
    
        
}

