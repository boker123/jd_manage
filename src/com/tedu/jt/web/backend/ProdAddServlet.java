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

/**
 * add product message
 *
 * @author Boker
 * @time 2019年7月25日14点29分
 */
@WebServlet(name = "ProdAddServlet", urlPatterns = "/ProdAddServlet")
public class ProdAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0.dealing with error code
        /*request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");*/

        // 1.get the product message which will be add.
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        String description = request.getParameter("description");

        // 2.save product message in database
        addProd(name, category, price, pnum, description);

        // 3.prompting users to add successfully and redirecting to product list page after 3 seconds
        response.getWriter().write("<h1 style='color:green;margin5px 15px'>");
        response.getWriter().write("商品添加成功，3秒之后将跳转到商品列表页面");
        response.getWriter().write("</h1>");

        // 4.Refresh after 3 seconds
        response.setHeader("Refresh","3;url="+request.getContextPath()+"/ProdListServlet");

    }

    /**
     * save the product massage in database
     *
     * @param name product name
     * @param category
     * @param price product price
     * @param pnum product numbers
     * @param description product description
     */
    private void addProd(String name, String category, double price, int pnum, String description) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.get connecting from connection pool
            conn = JDBCUtils.getConn();
            // 2.define sql frame
            String sql = "insert into product values(null,?,?,?,?,?)";
            // 3.get prepare statement object
            ps = conn.prepareStatement(sql);
            // 4.set sql parameter
            ps.setString(1,name);
            ps.setString(2,category);
            ps.setDouble(3,price);
            ps.setInt(4,pnum);
            ps.setString(5,description);
            // 5.run sql statement
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("商品添加失败！！！");
            throw new RuntimeException("商品添加失败");
        } finally {
            // release source
            JDBCUtils.close(conn,ps,null);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
