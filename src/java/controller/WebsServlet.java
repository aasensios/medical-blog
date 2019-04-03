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
import model.Web;
import model.persist.WebDAO;

@WebServlet("/webs")
public class WebsServlet extends HttpServlet {

    private String path;
    private WebDAO webDAO;
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
        webDAO = new WebDAO(path);

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
            case "list":
                list(request, response);
                break;
            case "add_form":
                showAddForm(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "modify_form":
                showModifyForm(request, response);
                break;
            case "prepare_web_to_modify":
                prepareWebToModify(request, response);
                break;
            case "modify":
                modify(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }

        // Refresh the webs JSP page after any action
        request.getRequestDispatcher("webs.jsp").forward(request, response);

    }

    /**
     * Lists all webs.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Web> webs = webDAO.list();
        request.setAttribute("webs", webs);

//        // Message telling the number of entries
//        if (webs.isEmpty()) {
//            messages.put("error", "Webs list is empty");
//        } else {
//            messages.put("success", String.format("Total webs: %d", webs.size()));
//        }
    }

    /**
     * Shows the form to add a new web.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("add", true);

    }

    /**
     * Adds a new web.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate web object
        Web web = new Web();

        // Postprocess request: gather and validate submitted data and display the result in the same JSP.
        // Get and validate code.
        String code = request.getParameter("code");
        if (code == null || code.trim().isEmpty()) {
            messages.put("code", "Please enter code");
        } else if (!code.matches("P-\\d{4}")) {
            messages.put("code", "Format must be like: P-0001");
        }

        // Get and validate publicationDate.
        String publicationDate = request.getParameter("publication_date");
        if (publicationDate == null || publicationDate.trim().isEmpty()) {
            messages.put("publicationDate", "Please enter publication date");
        } else if (!publicationDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            messages.put("publicationDate", "Format must be like: 2019-01-31");
        }

        // Get and validate title.
        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Please enter title");
        }

        // Get and validate url.
        String url = request.getParameter("url");
        if (url == null || url.trim().isEmpty()) {
            messages.put("url", "Please enter url");
        }

        // Set attribute to the view
        web.setCode(code);
        web.setPublicationDate(publicationDate);
        web.setTitle(title);
        web.setUrl(url);
        request.setAttribute("web", web);

        // No validation errors? Do the business job!
        if (messages.isEmpty()) {
            if (webDAO.insert(web) == 1) {
                messages.put("success", "Web added successfully");
                list(request, response);
            } else {
                messages.put("error", "Web not added due to a database error. Contact developer.");
                showAddForm(request, response);
            }
        } else {
            messages.put("error", "Please check the fields");
            showAddForm(request, response);
        }

    }

    /**
     * Gets all webs and sends them to the view.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showModifyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("modify", true);

    }

    /**
     * Modifies an existing web.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void prepareWebToModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Modify: Get web from form with fields separated by semicolon ';'
        String webToBeModified = request.getParameter("web_to_modify");
        String[] fields = webToBeModified.split(";");
        String code = fields[0];
        String publicationDate = fields[1];
        String title = fields[2];
        String url = fields[3];

        // Construct a web and set the attribute to the view
        Web web = new Web(code, publicationDate, title, url);
        request.setAttribute("web", web);
        showModifyForm(request, response);

    }

    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate web object
        Web web = new Web();

        // Postprocess request: gather and validate submitted data and display the result in the same JSP.
        // Get and do not validate code, because it is the primary key and cannot be modified.
        String code = request.getParameter("code");

        // Get and validate publicationDate.
        String publicationDate = request.getParameter("publication_date");
        if (publicationDate == null || publicationDate.trim().isEmpty()) {
            messages.put("publicationDate", "Please enter publication date");
        } else if (!publicationDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            messages.put("publicationDate", "Format must be like: 2019-01-31");
        }

        // Get and validate title.
        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            messages.put("title", "Please enter title");
        }

        // Get and validate url.
        String url = request.getParameter("url");
        if (url == null || url.trim().isEmpty()) {
            messages.put("url", "Please enter url");
        }

        // Set attribute to the view
        web.setCode(code);
        web.setPublicationDate(publicationDate);
        web.setTitle(title);
        web.setUrl(url);
        request.setAttribute("web", web);

        // No validation errors? Do the business job!
        if (messages.isEmpty()) {
            if (webDAO.update(web) == 1) {
                messages.put("success", "Web updated successfully");
                list(request, response);
            } else {
                messages.put("error", "Web not updated due to a database error. Contact developer.");
                showModifyForm(request, response);
            }
        } else {
            messages.put("error", "Please check the fields");
            showModifyForm(request, response);
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate web object
        Web web = new Web();

        // Get the code and set the attribute to the web object
        String code = request.getParameter("code");
        web.setCode(code);

        // Send to delete to DAO directly, without validation, we only need the web code (primary key)
        if (webDAO.delete(web) == 1) {
            messages.put("success", "Web deleted successfully");
        } else {
            messages.put("error", "Web not deleted due to a database error. Contact developer.");
        }

        // Keep showing the list, no matter what the result is
        list(request, response);

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
            Logger.getLogger(WebsServlet.class
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
            Logger.getLogger(WebsServlet.class
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
