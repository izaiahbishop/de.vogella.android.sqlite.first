package css.cis3334.devogellaandroidsqlitefirst;

/**
 * Created by ibishop on 3/31/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,           //Create an array of all columns stored as a string
            MySQLiteHelper.COLUMN_COMMENT };

    /*
    * This method creates a new database helper that is a new MySQLHelper object with the parameter context.
    *
    * @param context This parameter is a handle to the system. Helps obtain access to databases,
    * preferences, and helps resolve resources.
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Comment createComment(String comment, String rating) {           //Added String rating as a parameter
        ContentValues values = new ContentValues();                         // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);                 // Insert a comment into the COLUMN_COMMENT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_RATING, rating);                   // Insert rating into the COLUMN_RATING field using MYSQLiteHelper
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,       // Set the cursor
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();                                               //  Cursor will move to the
        Comment newComment = cursorToComment(cursor);
        cursor.close();                                                     // Close the cursor
        return newComment;
    }

    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,       //Modified to return all database fields
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        comment.setRating(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_RATING)));      // initialize rating attribute
        return comment;
    }
}

