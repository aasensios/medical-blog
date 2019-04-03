package model.persist;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Category;

public class CategoryDAO {

    private final Properties queries;
    private static DBConnect dataSource;
    private static String PROPS_FILE;

    public CategoryDAO(String path) throws IOException, ClassNotFoundException {

        // instanciamos la conexion a traves del metodo getInstance, patron Singleton
        dataSource = DBConnect.getInstance();

        // estructura ordenada en ficheros de clase Property para guardar las sentencias SQL
        queries = new Properties();
        PROPS_FILE = path + "/resources/queries_on_categories.properties";
        queries.load(new FileInputStream(PROPS_FILE));

    }

    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }

    /**
     * List all categories.
     *
     * @return an array of Category objects
     */
    public List<Category> list() {

        List<Category> categories = new ArrayList<>();

        // Parenthesis try block: 'finally' is not necessary to close the database connection,
        // because the parenthesis close the connection automatically.
        try (
                Connection conn = dataSource.getConnection();
                Statement st = conn.createStatement();
                ResultSet resultSet = st.executeQuery(getQuery("SELECT_ALL"));) {
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categories;

    }

}
