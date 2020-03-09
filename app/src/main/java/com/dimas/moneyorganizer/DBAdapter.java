package com.dimas.moneyorganizer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_HARI = "hari";
    static final String KEY_TANGGAL = "tanggal";
    static final String KEY_PENGELUARAN = "pengeluaran";
    static final String KEY_PENDAPATAN = "pendapatan";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "moneyorganizer";
    static final int DATABASE_VERSION = 2;
    static final String DATABASE_CREATE =
            "create table moneyorganizer (_id integer primary key autoincrement, "
                    + "hari text not null, tanggal text not null,pengeluaran text not null, pendapatan text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertMoney(String hari, String tanggal,String pendapatan,String pengeluaran)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_HARI, hari);
        initialValues.put(KEY_TANGGAL, tanggal);
        initialValues.put(KEY_PENDAPATAN, pendapatan);
        initialValues.put(KEY_PENGELUARAN, pengeluaran);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    //---deletes a particular contact--
    public boolean deleteMoney(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor getAllMoney()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_HARI,
                KEY_TANGGAL, KEY_PENDAPATAN, KEY_PENGELUARAN}, null, null, null, null, null);
    }

    public Cursor getMoney(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_HARI, KEY_TANGGAL, KEY_PENGELUARAN, KEY_PENDAPATAN},     KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateMoney(long rowId, String hari, String tanggal, String pendapatan, String pengeluaran)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_HARI, hari);
        args.put(KEY_TANGGAL, tanggal);
        args.put(KEY_PENGELUARAN, pendapatan);
        args.put(KEY_PENDAPATAN, pengeluaran);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
