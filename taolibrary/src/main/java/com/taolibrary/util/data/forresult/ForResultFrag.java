package com.taolibrary.util.data.forresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 桥接器
 */
public class ForResultFrag extends Fragment {
    private Map<Integer, ForResultUtil.Callback> mCallbacks = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ForResultUtil.Callback callback = mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onActivityResult(new ForResultUtil.Result(resultCode, data));
        }
    }

    public void startForResult(Intent intent, ForResultUtil.Callback callback) {
        mCallbacks.put(callback.hashCode(), callback);
        startActivityForResult(intent, callback.hashCode());
    }

    public static ForResultFrag newInstance() {
        return new ForResultFrag();
    }
}
