package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookDBManager {
    BookDBHelper bookDBHelper = null;
    Cursor cursor = null;

    public BookDBManager(Context context){
        bookDBHelper = new BookDBHelper(context);
    }

    public ArrayList<Book> getAllBook(){
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + BookDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()){
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(BookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PUBLISHER));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PRICE));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_CATEGORY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_DESCRIPTION));
            bookList.add(new Book(id, title, author, publisher, price, category, description));
        }
        close();

        return bookList;
    }

    public boolean addNewBook(Book newBook){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(BookDBHelper.COL_TITLE, newBook.getTitle());
        value.put(BookDBHelper.COL_AUTHOR, newBook.getAuthor());
        value.put(BookDBHelper.COL_PUBLISHER, newBook.getPublisher());
        value.put(BookDBHelper.COL_PRICE, newBook.getPrice());
        value.put(BookDBHelper.COL_CATEGORY, newBook.getCategory());
        value.put(BookDBHelper.COL_DESCRIPTION, newBook.getDescription());

        long count = db.insert(BookDBHelper.TABLE_NAME, null, value);
        close();

        if(count > 0) return true;
        return false;
    }

    public boolean updateBook(Book book){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(BookDBHelper.COL_TITLE, book.getTitle());
        row.put(BookDBHelper.COL_AUTHOR, book.getAuthor());
        row.put(BookDBHelper.COL_PUBLISHER, book.getPublisher());
        row.put(BookDBHelper.COL_PRICE, book.getPrice());
        row.put(BookDBHelper.COL_CATEGORY, book.getCategory());
        row.put(BookDBHelper.COL_DESCRIPTION, book.getDescription());

        String whereClause = BookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(book.get_id())};
        int result = db.update(BookDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        close();

        if(result > 0) return true;
        return false;
    }

    public boolean removeBook(long id){
        SQLiteDatabase db = bookDBHelper.getWritableDatabase();

        String whereClause = BookDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        int result = db.delete(BookDBHelper.TABLE_NAME, whereClause, whereArgs);
        close();

        if(result > 0) return true;
        return false;
    }

    public ArrayList<Book> getBookByName(String name){
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();
        String whereClause = BookDBHelper.COL_TITLE + "=?";
        String[] whereArgs = new String[]{name};
        cursor = db.query(BookDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);

        while(cursor.moveToNext()){
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(BookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PUBLISHER));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PRICE));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_CATEGORY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_DESCRIPTION));
            bookList.add(new Book(id, title, author, publisher, price, category, description));
        }
        close();

        return bookList;
    }

    public ArrayList<Book> getBookByAuthor(String bookAuthor){
        ArrayList bookList = new ArrayList();
        SQLiteDatabase db = bookDBHelper.getReadableDatabase();
        String whereClause = BookDBHelper.COL_AUTHOR + "=?";
        String[] whereArgs = new String[]{bookAuthor};
        cursor = db.query(BookDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);

        while(cursor.moveToNext()){
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(BookDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_TITLE));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PUBLISHER));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_PRICE));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_CATEGORY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(BookDBHelper.COL_DESCRIPTION));
            bookList.add(new Book(id, title, author, publisher, price, category, description));
        }
        close();

        return bookList;
    }

    public void close(){
        if (bookDBHelper != null)
            bookDBHelper.close();
        if (cursor != null)
            cursor.close();
    }

}
