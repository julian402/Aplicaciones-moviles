package ue.edu.co.basedatossqlite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ue.edu.co.basedatossqlite.entity.User;
import ue.edu.co.basedatossqlite.manager.ManagerDataBase;
import ue.edu.co.basedatossqlite.manager.UserContract;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private static final int STATUS_ACTIVE = 1;
    private static final int STATUS_INACTIVE = 0;
    private final ManagerDataBase managerDataBase;

    public UserRepository(Context context){
        managerDataBase = new ManagerDataBase(context.getApplicationContext());
    }

    //metodo crud para insertar usuarios

    public long insertUser (User user){
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_DOCUMENT, user.getDocument());
        values.put(UserContract.COLUMN_NAMES, user.getNames());
        values.put(UserContract.COLUMN_LAST_NAMES, user.getLastNames());
        values.put(UserContract.COLUMN_USERNAME, user.getUser());
        values.put(UserContract.COLUMN_PASSWORD, user.getPassword());
        values.put(UserContract.COLUMN_STATUS, STATUS_ACTIVE);
        try {
            SQLiteDatabase database = managerDataBase.getWritableDatabase();
            return database.insert(UserContract.TABlE_NAME,null,values);
        } catch (Exception e) {
            Log.e(TAG, "ERROR AL INTENATR REGISTRAR EL USUARIO", e);
        }
        return -1;
    }

    public ArrayList<User> getActiveUsers(){
        ArrayList<User> listUsers = new ArrayList<>();
        String []columns = {
                UserContract.COLUMN_DOCUMENT, UserContract.COLUMN_NAMES,
                UserContract.COLUMN_LAST_NAMES, UserContract.COLUMN_USERNAME
        };
        String selection = UserContract.COLUMN_STATUS + " =?";
        String [] selectionArgs = {
                String.valueOf(STATUS_ACTIVE)
        };
        try {
            SQLiteDatabase database = managerDataBase.getReadableDatabase();
            try(Cursor cursor = database.query(UserContract.TABlE_NAME,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    UserContract.COLUMN_NAMES + " ASC")){
                while (cursor.moveToNext()){
                    User user = new User();
                    user.setDocument(cursor.getLong(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_DOCUMENT)));
                    user.setNames(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_NAMES)));
                    user.setLastNames(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_LAST_NAMES)));
                    user.setUser(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_USERNAME)));
                    listUsers.add(user);
                }

            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR AL CONSULTAR LOS USUARIO", e);
        }
        return listUsers;
    }

    public User searchUserByDocument(String document) {
        User user = new User();
        try {
            SQLiteDatabase database = managerDataBase.getReadableDatabase();
            try (Cursor cursor = database.rawQuery(UserContract.SEARCH_USER, new String[]{document})) {
                if (cursor.moveToFirst()) {
                    user = new User();
                    user.setDocument(cursor.getLong(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_DOCUMENT)));
                    user.setNames(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_NAMES)));
                    user.setLastNames(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_LAST_NAMES)));
                    user.setUser(cursor.getString(cursor.getColumnIndexOrThrow(
                            UserContract.COLUMN_USERNAME)));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR AL BUSCAR EL USUARIO", e);
        }
        return user;
    }

    public int deleteUser(String document) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_STATUS, STATUS_INACTIVE);

        String whereClause = UserContract.COLUMN_DOCUMENT + " =?";
        String[] whereArgs = {document};

        try {
            SQLiteDatabase database = managerDataBase.getWritableDatabase();
            return database.update(UserContract.TABlE_NAME, values, whereClause, whereArgs);
        } catch (Exception e) {
            Log.e(TAG, "ERROR AL ELIMINAR EL USUARIO", e);
        }
        return -1;
    }

    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_NAMES, user.getNames());
        values.put(UserContract.COLUMN_LAST_NAMES, user.getLastNames());
        values.put(UserContract.COLUMN_USERNAME, user.getUser());
        values.put(UserContract.COLUMN_PASSWORD, user.getPassword());

        String whereClause = UserContract.COLUMN_DOCUMENT + " =?";
        String[] whereArgs = {String.valueOf(user.getDocument())};

        try {
            SQLiteDatabase database = managerDataBase.getWritableDatabase();
            return database.update(UserContract.TABlE_NAME, values, whereClause, whereArgs);
        } catch (Exception e) {
            Log.e(TAG, "ERROR AL ACTUALIZAR EL USUARIO", e);
        }
        return -1;
    }
}
