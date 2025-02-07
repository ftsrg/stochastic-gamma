/**
 */
package hu.bme.mit.gamma.environment.stochastic.stochastic;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Influx DB</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getDbname <em>Dbname</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getIp <em>Ip</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getPort <em>Port</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getInfluxDB()
 * @model
 * @generated
 */
public interface InfluxDB extends DataSource {
	/**
	 * Returns the value of the '<em><b>Dbname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dbname</em>' attribute.
	 * @see #setDbname(String)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getInfluxDB_Dbname()
	 * @model required="true"
	 * @generated
	 */
	String getDbname();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getDbname <em>Dbname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dbname</em>' attribute.
	 * @see #getDbname()
	 * @generated
	 */
	void setDbname(String value);

	/**
	 * Returns the value of the '<em><b>Ip</b></em>' attribute.
	 * The default value is <code>"\"localhost\""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ip</em>' attribute.
	 * @see #setIp(String)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getInfluxDB_Ip()
	 * @model default="\"localhost\"" required="true"
	 * @generated
	 */
	String getIp();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getIp <em>Ip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ip</em>' attribute.
	 * @see #getIp()
	 * @generated
	 */
	void setIp(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(String)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getInfluxDB_Port()
	 * @model required="true"
	 * @generated
	 */
	String getPort();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(String value);

	/**
	 * Returns the value of the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query</em>' attribute.
	 * @see #setQuery(String)
	 * @see hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticPackage#getInfluxDB_Query()
	 * @model required="true"
	 * @generated
	 */
	String getQuery();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.environment.stochastic.stochastic.InfluxDB#getQuery <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' attribute.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(String value);

} // InfluxDB
