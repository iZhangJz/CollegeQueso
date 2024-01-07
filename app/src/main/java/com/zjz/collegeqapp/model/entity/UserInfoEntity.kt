package com.zjz.collegeqapp.model.entity

class UserInfoEntity(private val userName:String,private val password:String) {
    override fun toString(): String {
        return "UserInfoEntity(userName='$userName', password='$password')"
    }
}