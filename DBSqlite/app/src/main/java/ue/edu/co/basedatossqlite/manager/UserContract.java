package ue.edu.co.basedatossqlite.manager;

public final class UserContract {
    private UserContract(){
        //impide crear objetos de esta clase
    }
    public static final String TABlE_NAME = "users";
    public static final String COLUMN_DOCUMENT = "use_document";
    public static final String COLUMN_NAMES = "use_names";
    public static final String COLUMN_LAST_NAMES = "use_last_names";
    public static final String COLUMN_USERNAME = "use_username";
    public static final String COLUMN_PASSWORD = "use_password";
    public static final String COLUMN_STATUS = "use_status";

    public static final String CREATE_TABLE_USER = " CREATE TABLE " + TABlE_NAME +
            " (" + COLUMN_DOCUMENT + " INTEGER PRIMARY KEY, " + COLUMN_NAMES + " TEXT NOT NULL, " +
            COLUMN_LAST_NAMES + " TEXT NOT NULL, " + COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
            COLUMN_PASSWORD + " TEXT NOT NULL, " + COLUMN_STATUS + " INTEGER NOT NULL DEFAULT 1 " +
            "CHECK (" + COLUMN_STATUS + " IN (0,1)))";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABlE_NAME;

    public static final String SEARCH_USER = " SELECT * FROM " + TABlE_NAME + " WHERE " + COLUMN_DOCUMENT + " =?";

}
