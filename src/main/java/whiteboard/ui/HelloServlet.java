package whiteboard.ui;

import java.io.*;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
        name = "helloServlet",
        urlPatterns = {"/hello"}
)
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println("Hello, world!");
        out.close();
    }
}