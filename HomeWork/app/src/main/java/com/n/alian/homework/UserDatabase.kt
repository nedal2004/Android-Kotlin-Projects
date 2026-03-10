package com.n.alian.homework

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

lateinit private var db: SQLiteDatabase

class UserDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "UserDatabase"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "User"
        const val COL_ID = "id"
        const val COL_USERNAME = "username"
        const val COL_EMAIL = "email"
        const val COL_PHONE = "phone"
        const val COL_PASSWORD = "password"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val TABLE_CREATE =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_USERNAME TEXT ," +
                    " $COL_EMAIL TEXT ," +
                    " $COL_PHONE INTEGER ," +
                    "$COL_PASSWORD TEXT)"
        p0?.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(p0)

    }

    fun insertUser(user: Users): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_USERNAME, user.userName)
        cv.put(COL_EMAIL, user.email)
        cv.put(COL_PHONE, user.phone)
        cv.put(COL_PASSWORD, user.password)
        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return result != -1L
    }

    fun isLogIn(userName: String, password: String): Boolean {
        val db = this.readableDatabase
        val query =
            "SELECT * FROM ${TABLE_NAME} WHERE ${COL_USERNAME} = ? AND ${COL_PASSWORD} = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(userName, password))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists

    }

}
