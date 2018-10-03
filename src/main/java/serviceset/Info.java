package serviceset;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.util.UUID;
import java.util.Base64;
import javax.servlet.http.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Info extends HttpServlet{

    /**
     *
     * @param servletrequest
     * @param servletresponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest servletrequest, HttpServletResponse servletresponse) throws ServletException, IOException {
        String identitiy = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("ID of Session", identitiy);
        cookie.setMaxAge(3600);
        cookie.setSecure(false);
        servletresponse.addCookie(cookie);
        service(servletrequest, servletresponse);
    }
    
    private static String tokens(){
        String tokenone = null;
        byte[] bytes = new byte[16];
        try {
            //SecureRandom and Base64 will only work when have Java 8 
            SecureRandom random = SecureRandom.getInstanceStrong();
            random.nextBytes(bytes);
            tokenone = new String(Base64.getEncoder().encode(bytes));
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return tokenone;
    }

    /**
     *
     * @param servletrequest
     * @param servletresponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest servletrequest, HttpServletResponse servletresponse) throws ServletException, IOException {
        servletresponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletresponse.getWriter();

        try {
            String username = servletrequest.getParameter("username");
            String password = servletrequest.getParameter("password");

            Cookie[] cookies = servletrequest.getCookies();
            cookies[0].setPath("/");

            if (username.equals("admin") && password.equals("admin")) {
                HttpSession session = servletrequest.getSession();
                session.setAttribute("username", username);
                DataSave.getData().insert(cookies[0].getValue(), tokens());
                System.out.println("Inside logindetails ="+DataSave.getData().retrieve(cookies[0].getValue()));
                DataSave.getData().view();
                servletresponse.sendRedirect("main.jsp");
            } else {
                writer.println("Invalid credentials, username or password. Therefore, use admin for username and password");
            } 
        }finally {
            writer.close();
        }

    }
}