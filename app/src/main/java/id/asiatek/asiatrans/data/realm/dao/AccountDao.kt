package com.bpdsulteng.jbk.realm.dao

import android.util.Log
import id.asiatek.asiatrans.data.realm.RealmManager
import com.bpdsulteng.androidtvsliderimage.data.realm.AccountObject

class AccountDao {

    private val TAG = "AccountDao"

    fun addLogin(login: AccountObject) {
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            realm.insertOrUpdate(login)
            Log.d(TAG, "Insert Login Session")
        }
    }

    fun deleteLogin() {
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            realm.delete(AccountObject::class.java)
            Log.d(TAG, "Delete All Login Session")
        }
    }

    fun getLoginByUserId(id: Int): AccountObject? {
        val realmInstance = RealmManager.getRealm()
        var result: AccountObject? = null
        realmInstance.executeTransaction { realm ->
            result = realm.where(AccountObject::class.java).equalTo("userId", id).findFirst()

            Log.d(TAG, "Get Login By UserID")
        }
        return result
    }

    fun getLoginToken(): String? {
        val realmInstance = RealmManager.getRealm()
        var result: String? = ""
        realmInstance.executeTransaction { realm ->
            var account = realm.where(AccountObject::class.java).findFirst()
            result = account!!.token
            Log.d(TAG, "Get Login Token")
        }
        return result
    }

    fun getLogin(): AccountObject? {
        var result: AccountObject? = null
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            result = realm.where(AccountObject::class.java).findFirst()!!
            Log.d(TAG, "Get Login Data" + result.toString())
        }
        return result
    }
}