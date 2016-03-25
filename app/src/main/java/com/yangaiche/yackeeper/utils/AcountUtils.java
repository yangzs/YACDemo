package com.yangaiche.yackeeper.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.yangaiche.yackeeper.bean.UserACcount;
import com.yangaiche.yackeeper.login.view.UserLoginActivity;

/**
 * Created by mr_yang on 16-3-21.
 */
public class AcountUtils {

    /**
     * 判断用户是否有可使用账户,不能使用，则打开登录界面
     * @param activity
     */
    public static boolean ensureAccountAvailability(Activity activity) {
        boolean accountAvailable = true;
        if (!hasAccount(activity)) {
            accountAvailable = false;
            addAccount(activity, activity.getIntent());
        }
        if (!accountAvailable) {
            activity.finish();

        }
        return accountAvailable;
    }

    public static boolean hasAccount(Context context) {
        UserACcount userACcount = (UserACcount) MySharedPreferences.getInstance().getString4Class(context, MySharedPreferences.USER_ACCOUNT_SP, UserACcount.class);
        if(userACcount != null && userACcount.token != null){
            return true;
        }
        return false;
    }

    public static void addAccount(Activity activity, Intent onAddedIntent) {
        Intent intent = new Intent(activity, UserLoginActivity.class);
        intent.putExtra(UserLoginActivity.EXTRA_ON_ADDED_INTENT, onAddedIntent);
        activity.startActivity(intent);
    }

}