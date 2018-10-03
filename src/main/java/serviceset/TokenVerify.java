/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceset;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
/**
 *
 * @author hp
 */
public class TokenVerify extends HttpServlet {

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param servletrequest
     * @param servletresponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest servletrequest, HttpServletResponse servletresponse) throws ServletException, IOException {
        Cookie[] cookie = servletrequest.getCookies();
        String tokens = DataSave.getData().retrieve(cookie[0].getValue());
        servletresponse.setContentType("application/json");
        JSONObject json = new JSONObject();
        json.put("tokens",tokens);
        PrintWriter writer =servletresponse.getWriter();
        writer.print(json.toString());
        writer.flush();
        writer.close();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param servletrequest
     * @param servletresponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest servletrequest, HttpServletResponse servletresponse) throws ServletException, IOException {
        requestResponse(servletrequest, servletresponse);
    }

    protected void requestResponse(HttpServletRequest servletrequest, HttpServletResponse servletresponse) throws ServletException, IOException {
        servletresponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletresponse.getWriter();
        try {
            String identity = servletrequest.getParameter("identity");
            String values = servletrequest.getParameter("values");
            String receiver = servletrequest.getParameter("receiver");
            writer.println("Number :" + identity);
            writer.println("Password :" + values);
            writer.println("Token :" + receiver);
            Cookie[] cookie = servletrequest.getCookies();
            String tokens = DataSave.getData().retrieve(cookie[0].getValue());
            if (receiver.equals(tokens)) {
                writer.println("Token has been verfied");
            } else {
                writer.println("Token verification has Failed");
            }
        }finally {
            writer.close();
        }
    }

}
