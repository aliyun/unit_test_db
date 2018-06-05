package com.aliyun.kongming.unittest.fit.dbfit.environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.aliyun.kongming.unittest.fit.dbfit.util.DbParameterAccessor;

public interface DBEnvironment {
    /**
     * Meta-data retrieval method that provides a list of parameters for a given
     * stored procedure or function name. The name may contain a schema
     * qualifier. While implementing, use {@link NameNormaliser} to make sure
     * parameters are mapped properly. Parameters that map to return values
     * should have an empty string for the name.
     */
    Map<String, DbParameterAccessor> getAllProcedureParameters(String procName) throws SQLException;

    /**
     * Meta-data retrieval method that provides a list of columns a given stored
     * table or view. The name may contain a schema qualifier.
     */
    Map<String, DbParameterAccessor> getAllColumns(String tableOrViewName) throws SQLException;

    /**
     * This method creates an insert command that will be used to populate new
     * rows in a table.
     */
    String buildInsertCommand(String tableName, DbParameterAccessor[] accessors) throws SQLException;

    /**
     * 构造delete语句
     * 
     * @param tableName
     * @param accessors
     * @return
     */
    String buildDeleteCommand(String tableName, DbParameterAccessor[] accessors) throws SQLException;

    /**
     * A flag to signal whether the database supports output parameters on
     * insert commands. If this method returns true, then output parameters will
     * be used to retrieve possible outputs after an insert. if not, then we can
     * only try to fetch new IDs using the JDBC support for autogenerated ID
     * retrieval.
     */
    boolean supportsOuputOnInsert();

    /*
     * CreateCommand(String statement) and BindFixtureSymbols are implemented
     * differently then in the .Net version due to JDBC API; they are combined
     * into createStatementWithBoundFixtureSymbols
     */

    /**
     * Create a {@link PreparedStatement} object and binds fixture symbols to
     * SQL statement parameters with matching names.
     */
    PreparedStatement createStatementWithBoundFixtureSymbols(String commandText) throws SQLException;

    /**
     * Closes the current connection and rolls back any active transactions. The
     * transactions are automatically rolled back to make tests repeatable.
     */
    void closeConnection() throws SQLException;

    /**
     * 使用jtester中的缺省数据库配置
     * 
     * @throws SQLException
     */
    void connect() throws SQLException;

    /**
     * Connects to the database using a default database for the user.
     * 
     * @param dataSource Host (optionally port), machine name or any other data
     *            source identifier
     */
    void connect(String url, String username, String password) throws SQLException;

    /**
     * Connects to the database using a default database for the user.
     * 
     * @param url
     * @param username
     * @param password
     * @param driver
     * @throws SQLException
     */
    void connect(String url, String username, String password, String driver) throws SQLException;

    /**
     * Connects using a database-specific connection string. This allows users
     * to specify parameters that would not be used otherwise (i.e. windows
     * integrated security or a different network protocol).
     * 
     * @param connectionString full JDBC connection string
     */
    void connect(String connectionString) throws SQLException;

    /**
     * Connects using a database-specific connection string. This allows users
     * to specify parameters that would not be used otherwise (i.e. windows
     * integrated security or a different network protocol).
     * 
     * @param connectionString
     * @param driver
     * @throws SQLException
     */
    void connect(String connectionString, String driver) throws SQLException;

    /**
     * Commit current transaction.
     */
    void commit() throws SQLException;

    /**
     * Rollback current transaction.
     */
    void rollback() throws SQLException;

    /**
     * Retrieve an exception code from a database exception. This method should
     * perform any required conversion between a JDBC exception and the real
     * database error code.
     */
    int getExceptionCode(SQLException ex);

    /**
     * Retrieve current connection. Could be used by 3rd party classes to
     * execute database commands in the same session.
     */
    Connection getConnection();

    /**
     * Get the Java class that should be used to store objects of a DB specific
     * data type.
     * 
     * @param dataType DB data type name
     */
    Class<?> getJavaClass(String dataType);
}
