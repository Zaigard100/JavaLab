import java.sql.*;
import java.util.*;

public class LibraryApp {
    private static final String DB_URL = "jdbc:derby://localhost:1527/LibraryDB";
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL);
            //createTables();
            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            do {
                System.out.println("\nLibrary Management System");
                System.out.println("1. View Records");
                System.out.println("2. Add Record");
                System.out.println("3. Edit Record");
                System.out.println("4. Delete Record");
                System.out.println("5. List Publishers by Floor");
                System.out.println("6. List Publications by Author");
                System.out.println("7. Reset to Defaults");
                System.out.println("8. Exit");
                System.out.print("Enter choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }catch (Exception e) {
                    e.printStackTrace();
                    scanner.nextLine();
                    continue;
                }
                try {
                    switch (choice) {
                        case 1:
                            viewRecords();
                            break;
                        case 2:
                            addRecord(scanner);
                            break;
                        case 3:
                            editRecord(scanner);
                            break;
                        case 4:
                            deleteRecord(scanner);
                            break;
                        case 5:
                            listPublishersByFloor(scanner);
                            break;
                        case 6:
                            listPublicationsByAuthor(scanner);
                            break;
                        case 7:
                            resetToDefaults();
                            break;
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    scanner.nextLine();
                }
            } while (choice != 8);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    private static void createTables() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE Shelves (" +
                    "shelf_id INT PRIMARY KEY, " +
                    "floor INT, " +
                    "cabinet VARCHAR(50), " +
                    "shelf_number VARCHAR(10))");

            stmt.execute("CREATE TABLE Publications (" +
                    "pub_id INT PRIMARY KEY, " +
                    "author VARCHAR(100), " +
                    "title VARCHAR(200), " +
                    "publisher VARCHAR(100), " +
                    "year_published INT, " +
                    "pages INT, " +
                    "year_written INT, " +
                    "weight INT, " +
                    "shelf_id INT, " +
                    "FOREIGN KEY (shelf_id) REFERENCES Shelves(shelf_id))");
        }
    }

    private static void resetToDefaults() {
        try {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DELETE FROM Publications");
                stmt.execute("DELETE FROM Shelves");

                // Add default shelves
                stmt.execute("INSERT INTO Shelves VALUES (1, 1, 'A', '01')");
                stmt.execute("INSERT INTO Shelves VALUES (2, 2, 'B', '02')");

                // Add default publications
                stmt.execute("INSERT INTO Publications VALUES (1, 'Ansel Adams', 'Ah, Wilderness!', 'Schumm and Sons', 2020, 300, 2018, 500, 1)");
                stmt.execute("INSERT INTO Publications VALUES (2, 'Joan Miro', 'The Road Less Traveled', 'Daniel-Koepp', 2019, 250, 2017, 450, 2)");
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void viewRecords() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Publications")) {
            System.out.println("Publications:\n");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("pub_id") +
                        ", Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author")+
                        ", Publisher: " + rs.getString("publisher")+
                        ", Year Published: " + rs.getInt("year_published") +
                        ", Pages: " + rs.getInt("pages") +
                        ", Year Written: " + rs.getInt("year_written")+
                        ", Shelf ID: " + rs.getInt("shelf_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nRecords:\n");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Shelves")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("shelf_id") +
                        ", Floor : " + rs.getInt("floor") +
                        ", Cabinet : " + rs.getString("cabinet")+
                        ", Shelf Number : " + rs.getString("shelf_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void listPublishersByFloor(Scanner scanner) throws NoSuchElementException {
        System.out.print("Enter floor number: ");
        int floor = scanner.nextInt();
        scanner.nextLine();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT p.publisher FROM Publications p " +
                        "JOIN Shelves s ON p.shelf_id = s.shelf_id " +
                        "WHERE s.floor = ? ORDER BY p.publisher")) {

            pstmt.setInt(1, floor);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Publishers on floor " + floor + ":");
            while (rs.next()) {
                System.out.println(rs.getString("publisher"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listPublicationsByAuthor(Scanner scanner) throws NoSuchElementException  {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM Publications " +
                        "WHERE author = ? ORDER BY year_written")) {

            pstmt.setString(1, author);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Publications by " + author + ":");
            while (rs.next()) {
                System.out.println(rs.getString("title") +
                        " (" + rs.getInt("year_written") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addRecord(Scanner scanner) throws NoSuchElementException {
        System.out.println("Add new record:");
        System.out.println("1. Add Shelf");
        System.out.println("2. Add Publication");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            addShelf(scanner);
        } else if (choice == 2) {
            addPublication(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void addShelf(Scanner scanner) throws NoSuchElementException {
        System.out.println("Enter shelf details:");
        System.out.print("Floor: ");
        int floor = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cabinet: ");
        String cabinet = scanner.nextLine();
        System.out.print("Shelf Number: ");
        String shelfNumber = scanner.nextLine();

        int newId = 1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(shelf_id) FROM Shelves")) {
            if (rs.next()) {
                newId = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO Shelves VALUES (?, ?, ?, ?)")) {
            pstmt.setInt(1, newId);
            pstmt.setInt(2, floor);
            pstmt.setString(3, cabinet);
            pstmt.setString(4, shelfNumber);
            pstmt.executeUpdate();
            System.out.println("Shelf added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addPublication(Scanner scanner) throws NoSuchElementException {
        System.out.println("Available shelves:");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Shelves")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("shelf_id") +
                        ", Floor: " + rs.getInt("floor") +
                        ", Cabinet: " + rs.getString("cabinet") +
                        ", Shelf Number: " + rs.getString("shelf_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter shelf ID: ");
        int shelfId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter publication details:");
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Year Published: ");
        int yearPublished = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Year Written: ");
        int yearWritten = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Weight (grams): ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        int newId = 1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(pub_id) FROM Publications")) {
            if (rs.next()) {
                newId = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO Publications VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, newId);
            pstmt.setString(2, author);
            pstmt.setString(3, title);
            pstmt.setString(4, publisher);
            pstmt.setInt(5, yearPublished);
            pstmt.setInt(6, pages);
            pstmt.setInt(7, yearWritten);
            pstmt.setInt(8, weight);
            pstmt.setInt(9, shelfId);
            pstmt.executeUpdate();
            System.out.println("Publication added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void editRecord(Scanner scanner) throws NoSuchElementException {
        System.out.println("Edit record:");
        System.out.println("1. Edit Shelf");
        System.out.println("2. Edit Publication");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            editShelf(scanner);
        } else if (choice == 2) {
            editPublication(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void editShelf(Scanner scanner) throws NoSuchElementException {
        System.out.print("Enter shelf ID to edit: ");
        int shelfId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new details (leave blank to keep current):");
        System.out.print("Floor [" + getCurrentShelfValue(shelfId, "floor") + "]: ");
        String floorStr = scanner.nextLine();
        int floor = floorStr.isEmpty() ? getCurrentShelfValue(shelfId, "floor") : Integer.parseInt(floorStr);

        System.out.print("Cabinet [" + getCurrentShelfValueString(shelfId, "cabinet") + "]: ");
        String cabinet = scanner.nextLine();
        if (cabinet.isEmpty()) {
            cabinet = getCurrentShelfValueString(shelfId, "cabinet");
        }

        System.out.print("Shelf Number [" + getCurrentShelfValueString(shelfId, "shelf_number") + "]: ");
        String shelfNumber = scanner.nextLine();
        if (shelfNumber.isEmpty()) {
            shelfNumber = getCurrentShelfValueString(shelfId, "shelf_number");
        }

        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE Shelves SET floor = ?, cabinet = ?, shelf_number = ? WHERE shelf_id = ?")) {
            pstmt.setInt(1, floor);
            pstmt.setString(2, cabinet);
            pstmt.setString(3, shelfNumber);
            pstmt.setInt(4, shelfId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shelf updated successfully.");
            } else {
                System.out.println("Shelf not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getCurrentShelfValue(int shelfId, String column) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT " + column + " FROM Shelves WHERE shelf_id = ?")) {
            pstmt.setInt(1, shelfId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String getCurrentShelfValueString(int shelfId, String column) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT " + column + " FROM Shelves WHERE shelf_id = ?")) {
            pstmt.setInt(1, shelfId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void editPublication(Scanner scanner) throws NoSuchElementException {
        System.out.print("Enter publication ID to edit: ");
        int pubId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new details (leave blank to keep current):");
        System.out.print("Author [" + getCurrentPubValue(pubId, "author") + "]: ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            author = getCurrentPubValue(pubId, "author");
        }

        System.out.print("Title [" + getCurrentPubValue(pubId, "title") + "]: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = getCurrentPubValue(pubId, "title");
        }

        System.out.print("Publisher [" + getCurrentPubValue(pubId, "publisher") + "]: ");
        String publisher = scanner.nextLine();
        if (publisher.isEmpty()) {
            publisher = getCurrentPubValue(pubId, "publisher");
        }

        System.out.print("Year Published [" + getCurrentPubValueInt(pubId, "year_published") + "]: ");
        String yearPublishedStr = scanner.nextLine();
        int yearPublished = yearPublishedStr.isEmpty() ? getCurrentPubValueInt(pubId, "year_published") : Integer.parseInt(yearPublishedStr);

        System.out.print("Pages [" + getCurrentPubValueInt(pubId, "pages") + "]: ");
        String pagesStr = scanner.nextLine();
        int pages = pagesStr.isEmpty() ? getCurrentPubValueInt(pubId, "pages") : Integer.parseInt(pagesStr);

        System.out.print("Year Written [" + getCurrentPubValueInt(pubId, "year_written") + "]: ");
        String yearWrittenStr = scanner.nextLine();
        int yearWritten = yearWrittenStr.isEmpty() ? getCurrentPubValueInt(pubId, "year_written") : Integer.parseInt(yearWrittenStr);

        System.out.print("Weight [" + getCurrentPubValueInt(pubId, "weight") + "]: ");
        String weightStr = scanner.nextLine();
        int weight = weightStr.isEmpty() ? getCurrentPubValueInt(pubId, "weight") : Integer.parseInt(weightStr);

        System.out.print("Shelf ID [" + getCurrentPubValueInt(pubId, "shelf_id") + "]: ");
        String shelfIdStr = scanner.nextLine();
        int shelfId = shelfIdStr.isEmpty() ? getCurrentPubValueInt(pubId, "shelf_id") : Integer.parseInt(shelfIdStr);

        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE Publications SET author = ?, title = ?, publisher = ?, year_published = ?, pages = ?, year_written = ?, weight = ?, shelf_id = ? WHERE pub_id = ?")) {
            pstmt.setString(1, author);
            pstmt.setString(2, title);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, pages);
            pstmt.setInt(6, yearWritten);
            pstmt.setInt(7, weight);
            pstmt.setInt(8, shelfId);
            pstmt.setInt(9, pubId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Publication updated successfully.");
            } else {
                System.out.println("Publication not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentPubValue(int pubId, String column) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT " + column + " FROM Publications WHERE pub_id = ?")) {
            pstmt.setInt(1, pubId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static int getCurrentPubValueInt(int pubId, String column) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT " + column + " FROM Publications WHERE pub_id = ?")) {
            pstmt.setInt(1, pubId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void deleteRecord(Scanner scanner) throws NoSuchElementException {
        System.out.println("Delete record:");
        System.out.println("1. Delete Shelf");
        System.out.println("2. Delete Publication");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            deleteShelf(scanner);
        } else if (choice == 2) {
            deletePublication(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void deleteShelf(Scanner scanner) throws NoSuchElementException  {
        System.out.print("Enter shelf ID to delete: ");
        int shelfId = scanner.nextInt();
        scanner.nextLine();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM Publications WHERE shelf_id = ?")) {
                pstmt.setInt(1, shelfId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM Shelves WHERE shelf_id = ?")) {
                pstmt.setInt(1, shelfId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    System.out.println("Shelf and related publications deleted.");
                } else {
                    conn.rollback();
                    System.out.println("Shelf not found.");
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deletePublication(Scanner scanner) throws NoSuchElementException {
        System.out.print("Enter publication ID to delete: ");
        int pubId = scanner.nextInt();
        scanner.nextLine();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM Publications WHERE pub_id = ?")) {
            pstmt.setInt(1, pubId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Publication deleted successfully.");
            } else {
                System.out.println("Publication not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}