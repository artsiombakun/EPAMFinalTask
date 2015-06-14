package util;
/**
 * @author Artyom
 * This class stores all constants that used in the project
 * */
public class Config {
	
	//application name
	//private static final String BASE_APPLICATION_URL = "";//"/Task6FinalWebProject";
	
	//packages
	private static final String ADMIN_PACKAGE_PREFIX = "";//BASE_APPLICATION_URL + "/admin";
	private static final String CLIENT_PACKAGE_PREFIX = "";//BASE_APPLICATION_URL + "/client";
	private static final String SERVICE_PACKAGE_PREFIX = "";//BASE_APPLICATION_URL + "/service";
	
	//jsp-pages
	public static final String UNLOCK_ACCOUNT_PAGE = ADMIN_PACKAGE_PREFIX + "/admin-unlock-account.jsp";
	public static final String CREATE_ACCOUNT_PAGE = ADMIN_PACKAGE_PREFIX + "/admin-create-account.jsp";
	public static final String GET_INFO_ABOUT_CLIENT_PAGE = ADMIN_PACKAGE_PREFIX + "/admin-get-info.jsp";
	public static final String LOG_IN_PAGE = "/log.jsp";
	public static final String WELCOME_PAGE = "/welcome.jsp";
	public static final String SIGN_UP_PAGE = "/sign-up.jsp";
	public static final String CLOSE_ACCOUNT_CLIENT_PAGE = CLIENT_PACKAGE_PREFIX + "/client-close-account.jsp";
	public static final String BLOCK_ACCOUNT_CLIENT_PAGE = CLIENT_PACKAGE_PREFIX + "/client-block-account.jsp";
	public static final String CLIENT_TRANSFER_PAGE = CLIENT_PACKAGE_PREFIX + "/client-payment.jsp";
	public static final String FILL_BALANCE_PAGE = CLIENT_PACKAGE_PREFIX + "/client-fill-account.jsp";
	public static final String ERROR_PAGE = SERVICE_PACKAGE_PREFIX + "/error.jsp";
	
	//commands
	public static final String UNLOCK_ACCOUNT_COMMAND = "unlock-account-command";
	public static final String CREATE_ACCOUNT_COMMAND = "create-account-command";
	public static final String GET_INFO_ABOUT_CLIENT_COMMAND = "get-info-command";
	public static final String LOG_IN_COMMAND = "log-in-command";
	public static final String CHANGE_LANGUAGE_COMMAND = "change-lang-command";
	public static final String SIGN_UP_COMMAND = "sign-up-command";
	public static final String LOG_OUT_COMMAND = "log-out-command";
	public static final String CLOSE_ACCOUNT_CLIENT_COMMAND = "close-account-command";
	public static final String BLOCK_ACCOUNT_CLIENT_COMMAND = "block-account-command";
	public static final String CLIENT_TRANSFER_COMMAND = "transfer-command";
	public static final String FILL_BALANCE_COMMAND = "fill-account-command";
	public static final String PAGINATION_COMMAND = "pagination-command";
	
	//attributes at session and request scope
	public static final String CLIENT_LIST_ATTR = "clientlist";
	public static final String ACCOUNT_LIST_ATTR = "acclist";
	public static final String BLOCKED_ACCOUNTS_LIST_ATTR = "blockedAccs";
	public static final String ERROR_ATTR = "errmsg";
	public static final String SUCCESS_ATTR = "success";
	public static final String ERROR_MSG_FOR_LOG = "error";
	public static final String SUM_ATTR = "sum";
	public static final String USER_ATTR = "user";
	public static final String DICTIONARY_ATTR = "dictionary";
	public static final String CURRENT_PAGE_ATTR = "current-page";
	public static final String RECORDS_PER_PAGE_TAG = "recordsPerPage";
	public static final String CLIENT_LIST_PAGE_ATTR = "clientListPageNo";
	public static final String CLIENT_LIST_TOTAL_PAGE_ATTR = "clientListTotalPage";
	public static final String ACC_LIST_PAGE_ATTR = "accListPageNo";
	public static final String ACC_LIST_TOTAL_PAGE_ATTR = "accListTotalPage";
	public static final String BLOCKED_ACCS_LIST_PAGE_ATTR = "blockedAccsPageNo";
	public static final String BLOCKED_ACCS_LIST_TOTAL_PAGE_ATTR = "blockedAccsTotalPage";
	
	//parameters of requests
	public static final String ID_PARAM = "clID";
	public static final String COMMAND_TYPE_PARAM = "command";
	public static final String FIRSTNAME_PARAM = "fname";
	public static final String LASTNAME_PARAM = "lname";
	public static final String LOGIN_PARAM = "login";
	public static final String PASSWORD_PARAM = "pswd";
	public static final String CONFIRM_PASSWORD_PARAM = "confirmpswd";
	public static final String BLOCK_ID_PARAM = "block";
	public static final String UNLOCK_ID_PARAM = "unlock";
	public static final String SUM_PARAM = "sum";
	public static final String FROM_ACC_PARAM = "from";
	public static final String TO_ACC_PARAM = "to";
	public static final String LIST_NAME_PARAM = "list-name";
	public static final String PAGE_DIRECTION_PARAM = "page-direction";
	
	//separate constants
	public static final String LANGUAGE = "LANGUAGE";
	public static final String LOCALE_ADDRESS = "resources.MyLocale";
	public static final String DB_CONFIG_FILE_ADDRESS = "resources\\database.properties";
	public static final String LOG4G_CONFIG_FILE_ADDRESS = "resources/mylog4j.properties";
	public static final String DRIVER_TAG = "driver";
	public static final String URL_TAG = "url";
	public static final String LOGIN_TAG = "login";
	public static final String PASSWORD_TAG = "password";
	public static final int ITEMS_PER_PAGE = 5;
	public static final int POOL_SIZE = 10;
	public static final String NEXT_PAGE_TAG = "next";
	public static final String PREVIOUS_PAGE_TAG = "prev";
	public static final String LOCALE_LANGUAGE = "language";
	public static final String LOCALE_COUNTRY = "country";
	
	//error messages
	public static final String CAN_NOT_CONNECT = "Can't connect to database.";
	public static final String CONN_UNAVAILABLE = "Connection to DB is unavailable.";
	public static final String CAN_NOT_CLOSE = "Can't close connection.";
	public static final String CAN_NOT_LOAD_DRIVER = "Can't load database driver.";
	public static final String INTERRUPTED_ERROR = "Error while waiting connection.";
	public static final String POOL_IS_EMPTY = "Pool is empty.";
	public static final String WRONG_DRIVER_TYPE = "Driver type is not correct in URL ";
	public static final String CAN_NOT_LOAD_DB_CONFIG_FILE = "Can't load .properties file.";
	
}
