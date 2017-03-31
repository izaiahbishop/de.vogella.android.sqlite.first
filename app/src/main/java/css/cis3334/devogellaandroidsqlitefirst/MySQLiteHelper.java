package css.cis3334.devogellaandroidsqlitefirst;

/**
 * Created by ibishop on 3/31/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class serves as a helper class to ope and create a database. Constant variables are created for table columns,
 * a constructor is defined to create a table, an execute database SQL is executed in onCreate, and code is provided
 * to assist with upgrading the database.
 *
 * @author Modified ibishop on 3/31/2017.
 */
    public class MySQLiteHelper extends SQLiteOpenHelper {

        /*
        * This variable serves as a constant for the TABLE_COMMENTS column.
        * We keep it as a constant in case the variable (table) name "comments" ever changes, or is moved around in the table.
        * We would only have to change this one line of code instead of all code where this variable is referenced.
        */
        public static final String TABLE_COMMENTS = "comments";

        /*
        * This variable serves as a constant for the COLUMN_ID column.
        * We keep it as a constant in case the variable (table) name "comments" ever changes, or is moved around in the table.
        * We would only have to change this one line of code instead of all code where this variable is referenced.
        */
        public static final String COLUMN_ID = "_id";

        /*
        * This variable serves as a constant for the COLUMN_COMMENT column.
        * We keep it as a constant in case the variable (table) name "comment" ever changes, or is moved around in the table.
        * We would only have to change this one line of code instead of all code where this variable is referenced.
        */
        public static final String COLUMN_COMMENT = "comment";

        /*
        * This variable serves as a constant for the DATABASE_NAME column.
        * We keep it as a constant in case the variable (table) name "commments.db" ever changes, or is moved around in the table.
        * We would only have to change this one line of code instead of all code where this variable is referenced.
        */
        private static final String DATABASE_NAME = "commments.db";

        /*
        * This variable serves as a constant for the DATABASE version.
        * We keep it as a constant in case the variable (table) integer 1 ever changes.
        * We would only have to change this one line of code instead of all code where this variable is referenced.
        */
        private static final int DATABASE_VERSION = 1;

        /*
        * This statement defines a database creation sql statement.
        */
        private static final String DATABASE_CREATE = "create table "
                + TABLE_COMMENTS + "( " + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_COMMENT
                + " text not null);";

        /*
        * This method makes a method call to the SQLiteOpenHelper (super). Passes in the context
        * (parameter), database name (set as a constant), null (no value needed), and database version
        * (also defined as a constant). This is used to help open and reference the database.
        *
        * @param context This parameter is a handle to the system. Helps obtain access to databases,
        * preferences, and helps resolve resources.
        */
        public MySQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*
        * This method contains a method call that will execute an SQL Statement defined as a String
        * parameter. Upon creating the application, we run our SQL command.
        *
        * @param database This is the database we are referencing to get the SQL statements.
         */
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        /*
        * This method provides code to assist with a database upgrade. A log file is written stating
        * what the old and newer version is, and drops the table defined in the constant TABLE_COMMENTS.
        * Then, the database is recreated with the updated version.
        *
        * @param SQLiteDatabase db This is the database being referenced that is to be upgraded
        * @param int oldVersion This is the old version of the database
        * @param int newVersion This is the new version of the database
        *
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(MySQLiteHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
            onCreate(db);
        }

    /* Here is the generic code for creating a table:
    CREATE TABLE table_name (
    column1 datatype,
    column2 datatype,
    column3 datatype,
    ...
    );
    */

    }

