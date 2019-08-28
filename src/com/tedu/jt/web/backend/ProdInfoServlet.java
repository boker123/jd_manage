package com.tedu.jt.web.backend;

import com.tedu.jt.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ProdInfoServlet", urlPatterns = "/ProdInfoServlet")
public class ProdInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product prod = findProdById(id);
        request.setAttribute("prod", prod);
        request.getRequestDispatcher("/backend/prod_upd.jsp").forward(request, response);
    }

    private Product findProdById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConn();
            String sql = "select * from product where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Product prod = null;
            if(rs.next()){
                prod = new Product(id, rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("pnum"),
                        rs.getString("description"));
            }
            return prod;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("商品查询失败");
            throw new RuntimeException("商品查询失败");
        } finally {
            JDBCUtils.close(conn, ps ,rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
