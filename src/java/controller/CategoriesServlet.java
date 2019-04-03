package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Model
import model.Category;
import model.persist.CategoryDAO;

@WebServlet("/categories")
public class CategoriesServlet extends HttpServlet {

    private String path;
    private CategoryDAO categoryDAO;
    private Map<String, String> messages;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        path = getServletContext().getRealPath("/WEB-INF/");
        categoryDAO = new CategoryDAO(path);

        // Prepare messages.
        messages = new HashMap<>();
        request.setAttribute("messages", messages);

        String action = request.getParameter("action");

        // Switch block does NOT accept null values.
        if (action == null) {
            action = "";
        }

        // Decide which method has to be called
        switch (action) {
            case "":
                response.sendRedirect("index.jsp");
                break;
            case "list":
                list(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }

        // Refresh the categories JSP page after any action
        request.getRequestDispatcher("categories.jsp").forward(request, response);

    }

    /**
     * Lists all categories.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categories = categoryDAO.list();
        request.setAttribute("categories", categories);

        if (categories.isEmpty()) {
            messages.put("error", "Categories list is empty");
        } else {
            messages.put("success", String.format("Total categories: %d", categories.size()));
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriesServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriesServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
