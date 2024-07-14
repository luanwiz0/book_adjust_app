package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookDBHelper extends SQLiteOpenHelper {
    final static String TAG = "BookDBHelper";

    final static String DB_NAME = "books.db";
    public final static String TABLE_NAME = "book_table";

    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_AUTHOR = "author";
    public final static String COL_PUBLISHER = "publisher";
    public final static String COL_PRICE = "price";
    public final static String COL_CATEGORY = "category";
    public final static String COL_DESCRIPTION = "description";

    public BookDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " + COL_AUTHOR + " TEXT, " + COL_PUBLISHER + " TEXT, " +
                COL_PRICE + " TEXT, " + COL_CATEGORY + " TEXT, " + COL_DESCRIPTION + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        insertSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME +
                " values (null, '어린왕자', '생택쥐페리', '비룡소', '12000', '동화', '어린왕자 책');");
        db.execSQL("insert into " + TABLE_NAME +
                " values (null, '나의 라임 오렌지나무', '바스콘셀로스', '동녘', '13000', '동화', '나의 라임 오렌지나무 책');");
        db.execSQL("insert into " + TABLE_NAME +
                " values (null, '데이터통신', '이재광', 'McGrawHill', '23000', 'IT서적', '데이터통신 책');");
        db.execSQL("insert into " + TABLE_NAME +
                " values (null, '코스모스', '칼 세이건', '사이언스북스', '25000', '과학', '코스모스 책');");
        db.execSQL("insert into " + TABLE_NAME +
                " values (null, '생각의 지도', '리처드 니스벳', '김영사', '13000', '인문학', '생각의 지도 책');");

    }

}
