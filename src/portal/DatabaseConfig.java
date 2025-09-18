package portal;

public class DatabaseConfig {
    // Database configuration that works with both local and Docker
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DB = "insurance_db";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";
    
    public static String getConnectionUrl() {
        // Check if running in Docker container
        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        
        if (host == null) host = DEFAULT_HOST;
        if (port == null) port = DEFAULT_PORT;
        if (dbName == null) dbName = DEFAULT_DB;
        
        return String.format("jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", 
                            host, port, dbName);
    }
    
    public static String getUsername() {
        String user = System.getenv("DB_USER");
        return user != null ? user : DEFAULT_USER;
    }
    
    public static String getPassword() {
        String password = System.getenv("DB_PASSWORD");
        return password != null ? password : DEFAULT_PASSWORD;
    }
}
