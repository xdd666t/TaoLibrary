package com.taolibrary.util.data.forresult;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;

import androidx.annotation.NonNull;

/**
 * 功能描述: activity for result
 */
public class ForResultUtil {
    private static final String TAG = "ResultBack";
    private ForResultFrag mFragment;

    public static ForResultUtil with(@NonNull Activity activity) {
        return new ForResultUtil(activity);
    }

    private ForResultUtil(@NonNull Activity activity) {
        mFragment = getResultBackFragment(activity);
    }

    private ForResultFrag getResultBackFragment(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        ForResultFrag fragment = findResultBackFragment(activity);
        if (fragment == null) {
            fragment = ForResultFrag.newInstance();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(fragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return fragment;
    }

    private ForResultFrag findResultBackFragment(Activity activity) {
        return (ForResultFrag) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public void startForResult(Intent intent, Callback callback) {
        if (mFragment == null && mFragment.getActivity() == null) {
            return;
        }
        mFragment.startForResult(intent, callback);
    }

    public interface Callback {
        void onActivityResult(Result result);
    }

    public static class Result {

        private int resultCode;
        private Intent data;

        Result(int resultCode, Intent data) {
            this.resultCode = resultCode;
            this.data = data;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public Intent getData() {
            return data;
        }

        public void setData(Intent data) {
            this.data = data;
        }
    }
}
