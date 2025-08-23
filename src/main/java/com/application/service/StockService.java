package com.application.service;

import com.application.config.DatabaseConfig;
import com.application.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class StockService {

    public static void getInventory(){
        List<String> products = new ArrayList<>();
        String SQL ="SELECT p.name, p.price, p.quantity, c.name AS category " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id";;
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL)
        ){
            ResultSet result = pstmt.executeQuery();
            while( result.next()){
                String name = result.getString("name");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");
                String category = result.getString("category");

                String product = "Produto : "+ name + " - R$" + price + " - Qtd: " + quantity + " Categoria: " + category;
                products.add(product);
            }
            System.out.println("Produtos em estoque:");
            for (String p : products) {
                System.out.println(p);
            }
        }catch (SQLException err){
            System.out.println("[ERROR] - Falha ao acessar estoque - " + err);
        }catch (NullPointerException err){
            System.out.println("[ERROR] - Pointer null - " + err);
        }
    }

    public static void addProduct(Product product){
        System.out.println(product.getName() + " " + product.getPrice() + " " + product.getQuantity() + " " + product.getCategory());
        String SQL = "INSERT INTO products( name, price, quantity, category_id) VALUES (?, ?, ?, ?)";
        if(product.getName() != null && !product.getName().isEmpty() && product.getPrice() != 0 && product.getCategory() != null && product.getQuantity()  >= 1){
            try(Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL)
            ){
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                pstmt.setInt(3, product.getQuantity());
                pstmt.setInt(4, product.getCategory().getId());

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("[ INFO ] Produto cadastrado com sucesso!");
                } else {
                    System.out.println("[ WARN ] Nenhum produto foi inserido.");
                }
            }catch (SQLException err){
                System.out.println("[ERROR] - Falha ao dar entrada no estoque - " + err);
            }catch (NullPointerException err){
                System.out.println("[ERROR] - Pointer null - " + err);
            }
        } else{
            System.out.println("A quantidade minima para entrar em estoque deve ser apartir de 1");
        }
    }

    public static void entryStock(int id, int quantity){
        String SQL = "UPDATE products SET quantity = quantity + ? WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL)
        ){
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        }catch (SQLException err){
            System.out.println("[ERROR] - Falha ao dar entrada no estoque - " + err);
        }catch (NullPointerException err){
            System.out.println("[ERROR] - Pointer null - " + err);
        }
    }

    public static void removeStock(int id, int quantity){
        String SQL = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL)
        ){
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        }catch (SQLException err){
            System.out.println("[ERROR] - Falha na saida do produto em estoque - " + err);
        }catch (NullPointerException err){
            System.out.println("[ERROR] - Pointer null - " + err);
        }
    }

    public void deleteProduct(int id){
        String SQL = "";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL)
        ){
            ResultSet result = pstmt.executeQuery();

        }catch (SQLException err){
            System.out.println("[ERROR] - Não foi possivel deletar o Produto do estoque - " + err);
        }catch (NullPointerException err){
            System.out.println("[ERROR] - Pointer null - " + err);
        }
    }
}
