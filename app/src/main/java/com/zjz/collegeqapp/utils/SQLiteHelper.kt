package com.zjz.collegeqapp.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    // 用户表
    private val USER_TABLE_NAME = "user_info"
    private val COLUMN_USERNAME = "username"
    private val COLUMN_PASSWORD = "password"
    private val COLUMN_POINT_TODAY = "point_today"
    private val COLUMN_POINT_MONTH = "point_month"



    companion object{
        private const val DATABASE_NAME = "collegeQueso.db"
        private const val DATABASE_VERSION = 1
    }

    // 数据库被第一次创建时调用
    override fun onCreate(db: SQLiteDatabase) {
        // 创建用户信息表
        val createUserTableQuery =
                "CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT NOT NULL, " +
                "$COLUMN_PASSWORD TEXT NOT NULL," +
                "$COLUMN_POINT_TODAY INTEGER," +
                "$COLUMN_POINT_MONTH INTEGER);"
        db.execSQL(createUserTableQuery)
    }

    // 数据库升级时调用
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // 查询用户是否存在
    fun checkUserExist(username:String,db: SQLiteDatabase): Boolean{
        val query = "SELECT username FROM $USER_TABLE_NAME WHERE $COLUMN_USERNAME = ?"

        val cursor: Cursor = db.rawQuery(query, arrayOf(username))
        val userExists = cursor.count > 0
        cursor.close()

        return userExists
    }

    // 查询用户密码是否匹配
    @SuppressLint("Range")
    fun verifyPassword(username: String, password: String,db: SQLiteDatabase):Boolean{
        var result = false
        val query = "SELECT $COLUMN_PASSWORD FROM $USER_TABLE_NAME WHERE $COLUMN_PASSWORD = ?"

        val cursor:Cursor = db.rawQuery(query, arrayOf(username))
        if (cursor.moveToFirst()){
            val storedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            result = storedPassword == password
        }
        cursor.close()
        return result
    }

    // 插入用户信息
    fun insertUserInfo(username: String, password: String,db: SQLiteDatabase):Boolean{
        val values = ContentValues().apply {
            put(COLUMN_USERNAME,username)
            put(COLUMN_PASSWORD,password)
            put(COLUMN_POINT_TODAY,0)
            put(COLUMN_POINT_MONTH,0)
        }

        val row = db.insert(USER_TABLE_NAME,null,values)
        return row != -1L
    }
}