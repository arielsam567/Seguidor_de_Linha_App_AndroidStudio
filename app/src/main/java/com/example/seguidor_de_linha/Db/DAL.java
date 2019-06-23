package com.example.seguidor_de_linha.Db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DAL {
    private static final String TAG = "DAL";
    private SQLiteDatabase db;
    private CreateDatabase database;

    public DAL(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insert(String info, int thre, int vmax ,int vmin, int kp, int kd, int timer, int t1, int t2, int t3, int t4, int t5, int t6, int t7, int t8, int t9, int t10, int d1, int d2, int d3, int d4) {
        ContentValues values;
        long result;
        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.INFORMACAO, info);
        values.put(CreateDatabase.THRESHOLD, thre);
        values.put(CreateDatabase.VMAX, vmax);
        values.put(CreateDatabase.VMIN, vmin);
        values.put(CreateDatabase.KP, kp);
        values.put(CreateDatabase.KD, kd);
        values.put(CreateDatabase.TIMER, timer);
        values.put(CreateDatabase.T1, t1);
        values.put(CreateDatabase.T2, t2);
        values.put(CreateDatabase.T3, t3);
        values.put(CreateDatabase.T4, t4);
        values.put(CreateDatabase.T5, t5);
        values.put(CreateDatabase.T6, t6);
        values.put(CreateDatabase.T7, t7);
        values.put(CreateDatabase.T8, t8);
        values.put(CreateDatabase.T9, t9);
        values.put(CreateDatabase.T10, t10);
        values.put(CreateDatabase.D1, d1);
        values.put(CreateDatabase.D2, d2);
        values.put(CreateDatabase.D3, d3);
        values.put(CreateDatabase.D4, d4);
        result = db.insert(CreateDatabase.TABLE, null, values);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
       // ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();
        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "delete: Erro deletando registro");
            return false;
        }
        return true;
    }

    public boolean drop(int id) {
        ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();

        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizando registro");
            return false;
        }
        return true;
    }

    public Cursor findById(int id) {
        Cursor cursor;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE, null,
                where, args, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID,CreateDatabase.INFORMACAO, CreateDatabase.THRESHOLD ,CreateDatabase.VMAX, CreateDatabase.VMIN,CreateDatabase.KP, CreateDatabase.KD ,CreateDatabase.TIMER, CreateDatabase.T1,CreateDatabase.T2, CreateDatabase.T3 ,CreateDatabase.T4, CreateDatabase.T5,
                CreateDatabase.T6,CreateDatabase.T7, CreateDatabase.T8 ,CreateDatabase.T9, CreateDatabase.T10,CreateDatabase.D1, CreateDatabase.D2, CreateDatabase.D3, CreateDatabase.D4};
        db = database.getReadableDatabase();
        // SELECT _id, title FROM book
        // String sql = "SELECT _id, title FROM book";
        //cursor = db.rawQuery(sql, null);
        cursor = db.query(CreateDatabase.TABLE, fields, null,
                null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }










    /*public boolean update(int id, String nome, Double idade,Double leococitos,Double glicemia,Double ast,Double ldh, Double mortalidade) {
        ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();
       values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.IDADE, idade);
        values.put(CreateDatabase.LEOCOCITOS, leococitos);
        values.put(CreateDatabase.GLICEMIA, glicemia);
        values.put(CreateDatabase.AST, ast);
        values.put(CreateDatabase.LDH, ldh);
        values.put(CreateDatabase.MORTALIDADE, mortalidade);
        result = db.update(CreateDatabase.TABLE, values, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizando registro");
            return false;
        }
        return true;
    }*/
}
