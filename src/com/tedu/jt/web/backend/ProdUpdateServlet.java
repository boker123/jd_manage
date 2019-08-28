package com.tedu.jt.web.backend;

import com.tedu.jt.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ProdUpdateServlet", urlPatterns = "/ProdUpdateServlet")
public class ProdUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name =request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        String description = request.getParameter("description");
        updateProdById(id, name, category, price, pnum, description);
        PrintWriter out = response.getWriter();
        response.getWriter().write("<h1 style='color:green;margin5px 15px'>");
        response.getWriter().write("商品修改成功，3秒之后将跳转到商品列表页面");
        response.getWriter().write("</h1>");
        response.setHeader("Refresh", "3,url="+request.getContextPath()+"/ProdListServlet");
    }

    /**
     * According to id update productions information
     * @param id            production's id
     * @param name          production's name
     * @param category      production's category
     * @param price         production's price
     * @param pnum          production's amounts
     * @param description   production's description
     */
    private void updateProdById(int id, String name, String category, double price, int pnum, String description) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConn();
            String sql = "update product set name=?,category=?,price=?,pnum=?,description=?" +
                    "where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, pnum);
            ps.setString(5,description);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("商品修改错误");
            throw new RuntimeException("商品修改失败");
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
