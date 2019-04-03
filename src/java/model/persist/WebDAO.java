package model.persist;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Web;

public class WebDAO {

    private final Properties queries;
    private static DBConnect dataSource;
    private static String PROPS_FILE;

    public WebDAO(String path) throws IOException, ClassNotFoundException {

        // instanciamos la conexion a traves del metodo getInstance, patron Singleton
        dataSource = DBConnect.getInstance();

        // estructura ordenada en ficheros de clase Property para guardar las sentencias SQL
        queries = new Properties();
        PROPS_FILE = path + "/resources/queries_on_webs.properties";
        queries.load(new FileInputStream(PROPS_FILE));

    }

    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }

    /**
     * List all webs.
     *
     * @return an array of Web objects
     */
    public List<Web> list() {

        List<Web> webs = new ArrayList<>();

        // Parenthesis try block: 'finally' is not necessary to close the database connection,
        // because the parenthesis close the connection automatically.
        try (
                Connection conn = dataSource.getConnection();
                Statement st = conn.createStatement();
                ResultSet resultSet = st.executeQuery(getQuery("SELECT_ALL"));) {
            while (resultSet.next()) {
                Web web = new Web();
                web.setCode(resultSet.getString("code"));
                web.setPublicationDate(resultSet.getString("publication_date"));
                web.setTitle(resultSet.getString("title"));
                web.setUrl(resultSet.getString("url"));
                webs.add(web);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return webs;

    }

    /**
     * Inserts a new web.
     *
     * @param web to be inserted
     * @return 1 if success; 0 otherwise
     */
    public int insert(Web web) {
        int rowsAffected;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("INSERT")
                );) {

            pst.setString(1, web.getCode());

            // https://stackoverflow.com/questions/5468540/string-to-date-in-a-preparedstatement
            // d = java.sql.Date.valueOf ("2008-07-31")
            pst.setDate(2, java.sql.Date.valueOf(web.getPublicationDate()));

            pst.setString(3, web.getTitle());

            pst.setString(4, web.getUrl());

            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            rowsAffected = 0;
        }

        return rowsAffected;
    }

    /**
     * Updates an existing user.
     *
     * @param web
     * @return 1 if success, 0 otherwise
     */
    public int update(Web web) {
        int rowsAffected;

        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("UPDATE"));) {

            // https://stackoverflow.com/questions/5468540/string-to-date-in-a-preparedstatement
            // d = java.sql.Date.valueOf ("2008-07-31")
            pst.setDate(1, java.sql.Date.valueOf(web.getPublicationDate()));

            pst.setString(2, web.getTitle());

            pst.setString(3, web.getUrl());

            pst.setString(4, web.getCode());

            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            rowsAffected = 0;
        }

        return rowsAffected;
    }

    /**
     * Deletes an existing user.
     *
     * @param web
     * @return 1 if success, -1 if constraint fail, -2 if SQL exception occurs
     */
    public int delete(Web web) {

        int rowsAffected = 0;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("DELETE"));) {

            pst.setString(1, web.getCode());

            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            rowsAffected = -2;
        }

        return rowsAffected;

    }

}
