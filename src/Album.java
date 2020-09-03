import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Album {
    Connection con;

    Album(Connection con) {
        this.con = con;
    }

    public void getData() throws SQLException {

        String query = "SELECT a.a_id as 'Album Id', a.title as 'Album title', a.erscheinungsjahr, "
                + "i.i_id as 'Interpreter Id', i.vorname as 'Album Interpreter Vorname', "
                + "i.nachname as 'Album Interpreter Nachname', t.t_id as 'Track Id', "
                + "t.title as 'Track title', t.spieldauer FROM Album a LEFT JOIN "
                + "Album_Interpreter ai ON a.a_id = ai.a_id LEFT JOIN Interpreter i ON ai.i_id = i.i_id "
                + "LEFT JOIN Track t ON t.a_id = a.a_id " + "ORDER BY a.title;";

        Statement st = this.con.createStatement();
        ResultSet rs = st.executeQuery(query);
        printData(rs);
        st.close();
    }

    public void searchQuery() throws IOException, SQLException {
        System.out.println("---------------------------------------------------------");
        System.out.println("Suchbegriff eingeben:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String searchString = reader.readLine();

        String query = "SELECT a.a_id as 'Album Id', a.title as 'Album title', a.erscheinungsjahr, "
                + "i.i_id as 'Interpreter Id', i.vorname as 'Album Interpreter Vorname', "
                + "i.nachname as 'Album Interpreter Nachname', t.t_id as 'Track Id', "
                + "t.title as 'Track title', t.spieldauer FROM Album a LEFT JOIN "
                + "Album_Interpreter ai ON a.a_id = ai.a_id LEFT JOIN Interpreter i ON ai.i_id = i.i_id "
                + "LEFT JOIN Track t ON t.a_id = a.a_id WHERE a.title LIKE '%" + searchString + "%' "
                + "OR t.title LIKE '%" + searchString + "%' " + "OR i.vorname LIKE '%" + searchString + "%' "
                + "OR i.nachname LIKE '%" + searchString + "%' " + "ORDER BY a.title;";

        Statement st = this.con.createStatement();
        ResultSet rs = st.executeQuery(query);
        printData(rs);
        st.close();

    }

    private void printData(ResultSet result) throws SQLException {
        while (result.next()) {
            int albumId = result.getInt("Album Id");
            String albumtitle = result.getString("Album title");
            int erscheinungsjahr = result.getInt("erscheinungsjahr");
            int interpreterId = result.getInt("Interpreter Id");
            String albumInterpreterVorname = result.getString("Album Interpreter Vorname");
            String albumInterpreterNachname = result.getString("Album Interpreter Nachname");
            int trackId = result.getInt("Track Id");
            String tracktitle = result.getString("Track title");
            int spieldauer = result.getInt("spieldauer");

            System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", albumId, albumtitle, erscheinungsjahr,
                    interpreterId, albumInterpreterVorname, albumInterpreterNachname, trackId, tracktitle, spieldauer);
        }
    }

}
