package com.zjz.collegeqapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zjz.collegeqapp.model.entity.UserInfoEntity
import com.zjz.collegeqapp.utils.SQLiteHelper

class UserViewModel(private val dbHelper: SQLiteHelper): ViewModel(){

    private var user : UserInfoEntity? = null

    //  用户登录状态
    val isLogin : Boolean
        get(){
            return user != null
        }

    fun getUser():UserInfoEntity?{
        return user
    }

    fun login(userName: String,password: String): Boolean{
        if (!dbHelper.checkUserExist(userName,dbHelper.readableDatabase)){
            // 数据库不存在该用户，登录失败
            return false
        }
        // 检查密码是否正确
        if (!dbHelper.verifyPassword(userName,password,dbHelper.readableDatabase)){
            return false
        }
        // 设置用户的登录状态为成功登录
        user = UserInfoEntity(userName, password)
        return true
    }

    fun register(userName: String,password: String,pwdConfirmation: String):Pair<Boolean,String>{
        // 判断是否为空
        if (userName == "" || password == "" || pwdConfirmation == ""){
            return Pair(false,"用户名或密码不能为空")
        }
        // 检查该用户名是否存在
        if (dbHelper.checkUserExist(userName,dbHelper.readableDatabase)){
            // 注册失败
            return Pair(false,"用户名已存在")
        }
        // 检查两次输入的密码是否相同
        if ( password != pwdConfirmation){
            return Pair(false,"两次输入的密码不匹配")
        }
        // 向数据库插入新用户
        if (!dbHelper.insertUserInfo(userName,password,dbHelper.writableDatabase)){
            // 插入失败 注册失败
            return Pair(false,"服务器异常")
        }
        user = UserInfoEntity(userName, password)
        return Pair(true,"注册成功")
    }
}