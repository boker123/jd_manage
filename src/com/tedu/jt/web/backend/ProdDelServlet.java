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

@WebServlet(name = "ProdDelServlet", urlPatterns = "/ProdDelServlet")
public class ProdDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取商品的id
        int id = Integer.parseInt(request.getParameter("id"));

        // 2.根据id删除商品
        deleteProdById(id);

        // 3.提示用户,跳转到管理页面
        PrintWriter out = response.getWriter();
        out.write("<h1 style='color:red;margin:10px,15px'>");
        out.write("商品删除成功");
        out.write("</h1>");

        // 4.定时刷新
        response.setHeader("Refresh", "3;url="+request.getContextPath()+"/ProdListServlet");
    }

    /**
     * 根据商品id删除商品信息
     *
     * @param id 商品id
     */
    private void deleteProdById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtils.getConn();
            String sql = "delete from product where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("商品删除失败");
            throw new RuntimeException("商品删除失败");
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
