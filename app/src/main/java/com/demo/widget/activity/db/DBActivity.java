package com.demo.widget.activity.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.bean.dao.DBHelper;
import com.demo.databinding.ActivityDbBinding;
import com.taolibrary.interfaces.dialog.IDialog;
import com.taolibrary.widget.dialog.DialogLibrary;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import static com.taolibrary.BaseFunction.showToast;

/**
 * Author 余涛
 * Description 数据库操作
 * Time 2019/1/26 13:54
 */
public class DBActivity extends BaseActivity implements View.OnClickListener {
    private ActivityDbBinding mBinding;

    private SQLiteDatabase db;
    private ContentValues mContentValues;
    private DialogLibrary mDialogLibrary;

    private DBVM mDBVM;

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_db);
        //和当前Activity生命周期绑定
        mDBVM = new ViewModelProvider(this).get(DBVM.class);
        mBinding.setDbVM(mDBVM);

        mBinding.insert.setOnClickListener(this);
        mBinding.insertCleardata.setOnClickListener(this);
        mBinding.update.setOnClickListener(this);
        mBinding.updateCleardata.setOnClickListener(this);
        mBinding.delete.setOnClickListener(this);
        mBinding.deleteCleardata.setOnClickListener(this);
        mBinding.query.setOnClickListener(this);
        mBinding.queryClear.setOnClickListener(this);

        mBinding.queryData.setMovementMethod(ScrollingMovementMethod.getInstance()); //设置内容可滚动
    }

    @Override
    protected void initData() {
        DBHelper dbHelper = new DBHelper(this, "test", null, 1);
        db = dbHelper.getWritableDatabase();
        mContentValues = new ContentValues();
        mDialogLibrary = new DialogLibrary(this);
    }


    @Override
    public void onClick(View v) {
        mContentValues.clear();
        //创建游标对象
        final Cursor cursor = db.query("user", new String[]{"name"},
                null,null,null,null,null);

        switch (v.getId()) {
            case R.id.insert:  //插入数据
//                try {
                    mContentValues.put("name", mBinding.insertEdittext.getText().toString());
                    db.insert("user", null, mContentValues);
                    if(!mBinding.insertEdittext.getText().toString().equals(""))
                        showToast("插入成功");
//                } catch (Exception e) {
//                    Logger.d(e.toString());
//                }

                break;
            case R.id.insert_cleardata: //清除输入插入的数据
                mBinding.insertEdittext.setText("");
                break;
            case R.id.update: //更新内容
                mContentValues.put("name", mBinding.updateAfterEdittext.getText().toString());
                db.update("user",mContentValues,"name=?",new String[]{mBinding.updateBeforeEdittext.getText().toString()});
                if(!mBinding.updateAfterEdittext.getText().toString().equals("") && !mBinding.updateBeforeEdittext.getText().toString().equals(""))
                    showToast("更新成功");
                break;
            case R.id.update_cleardata://清除输入更新的内容
                mBinding.updateBeforeEdittext.setText("");
                mBinding.updateAfterEdittext.setText("");
                break;
            case R.id.delete: //删除指定内容
                db.delete("user", "name=?", new String[]{mBinding.deleteEdittext.getText().toString()});
                if(!mBinding.deleteEdittext.getText().toString().equals("")) showToast("删除成功");
                break;
            case  R.id.delete_cleardata: //删除全部内容
                mDialogLibrary.commonDialog("是否删除数据库中的全部数据", new IDialog.OnCommonDialogClick() {
                    @Override
                    public void affirmOnClick() {
                        //利用游标遍历所有数据对象
                        while (cursor.moveToNext()) {
                            String mName = cursor.getString(cursor.getColumnIndex("name"));
                            db.delete("user", "name=?", new String[]{mName});
                        }
                        showToast("全部删除成功");
                    }
                });
                mBinding.deleteEdittext.setText("");
                break;
            case R.id.query:  //查询数据
               mDBVM.query(cursor);
                break;
            case R.id.query_clear:
                mBinding.queryData.setText("");
                mBinding.query.setHint("暂无查询内容");
                break;
        }
    }
}
