/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.capchaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
//import nl.captcha.Captcha;

/**
 *
 * @author PC
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Random random = new Random();
        int r = random.nextInt(256); // giá trị R từ 0 đến 255
        int g = random.nextInt(256); // giá trị G từ 0 đến 255
        int b = random.nextInt(256); // giá trị B từ 0 đến 255

        String rgb = String.format("#%02x%02x%02x", r, g, b);

        String letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(letter.length());
            char randomChar = letter.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
// Chuyển hướng đến trang JSP và gửi captchaText qua request
        session.setAttribute("captchaText", randomString);
        session.setAttribute("rgb", rgb);
        response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check what button is clicked
        if (request.getParameter("login") != null) {
            String acc = request.getParameter("account");
            String pass = request.getParameter("pass");
            String captchaInput = request.getParameter("captcha");
            HttpSession sess = request.getSession();
            String captcha = (String) sess.getAttribute("captcha");
            if(captcha.equals(captchaInput)){
                request.getRequestDispatcher("rePass.jsp").forward(request, response);
            }else{
                response.sendRedirect(request.getContextPath() + "/LoginController");
                return;
            }
            //check account exsit
        }

        if (request.getParameter("signUp") != null) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        if (request.getParameter("rePass") != null) {
            request.getRequestDispatcher("rePass.jsp").forward(request, response);
        }

        //
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}