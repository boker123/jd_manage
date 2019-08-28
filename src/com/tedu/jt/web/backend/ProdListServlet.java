package com.tedu.jt.web.backend;

import com.tedu.jt.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责查询所有商品信息
 *
 * @author Boker
 * @time 2019年8月9日18点24分
 */
@WebServlet(name = "ProdListServlet", urlPatterns = "/ProdListServlet")
public class ProdListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.查询出所有商品信息
        List<Product> list = findProdList();

        // 2.将商品信息存入request域中
        request.setAttribute("list", list);

        // 3.通过转发将所有商品的集合带到JSP，取出并进行显示
        request.getRequestDispatcher("/backend/prod_list.jsp").forward(request, response);
    }

    private List<Product> findProdList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.从连接池中获取一个连接对象
            conn = JDBCUtils.getConn();

            // 2.声明sql语句
            String sql = "select * from product";

            // 3.获取传输器对象
            ps = conn.prepareStatement(sql);

            // 4.执行sql，返回结果
            rs = ps.executeQuery();

            // 5.处理结果
            List<Product> list  = new ArrayList<>();
            Product prod = null;
            while(rs.next()){
                prod = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("pnum"),
                        rs.getString("description"));
                list.add(prod);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("商品查询失败");
            throw new RuntimeException("商品查询失败");
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
