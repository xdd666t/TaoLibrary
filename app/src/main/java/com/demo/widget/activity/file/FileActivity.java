package com.demo.widget.activity.file;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.databinding.ActivityFileBinding;
import com.taolibrary.base.single.SingleRvAdapter;
import com.taolibrary.cache.file.FileLibrary;
import com.taolibrary.cache.file.interfaces.IFile;
import com.taolibrary.widget.dialog.DialogLibrary;

import java.io.File;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import static com.taolibrary.BaseFunction.showToast;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/1/26 13:51
 */
public class FileActivity extends BaseActivity {
    private ActivityFileBinding mBinding;

    private DialogLibrary mDialogLibrary;
    private FileLibrary mFileLibrary;

    private SingleRvAdapter<String> mSingleRvAdapter;
    private FileVM mFileVM;

    @Override
    protected void initInstance(Context context) {
        super.initInstance(this);
    }


    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this,  R.layout.activity_file);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void initData() {
        mDialogLibrary = new DialogLibrary(this);
        mFileLibrary = new FileLibrary(this);
        mFileVM = new ViewModelProvider(this).get(FileVM.class);

        skipFunction();
    }

    private void skipFunction() {
        mFileVM.getList().observe(this, new Observer<List<String>>() {
            @Override public void onChanged(List<String> strings) {
                mSingleRvAdapter.updateData(strings);
            }
        });

        //列表实现
        mBinding.rvFile.setLayoutManager(new GridLayoutManager(this, 2));
        mSingleRvAdapter = new SingleRvAdapter<String>(R.layout.item_normal) {
            @Override
            protected void onBindDataToView(SingleRvAdapter.BaseViewHolder holder, String s, int position) {
                TextView textView = (TextView) holder.getView(R.id.tv_content);
                textView.setText(mList.get(position));
            }
        };
        mSingleRvAdapter.setOnItemClickListener(new SingleRvAdapter.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                switch (position){
                    case 0:
                        downloadFile();
                        break;
                    case 1:
                        getUrlFileName();
                        break;
                    case 2:
                        openFile();
                        break;
                }
            }
        });
        mBinding.rvFile.setAdapter(mSingleRvAdapter);
    }



    /**
     * 下载文件
     */
    private void downloadFile() {
//        String downloadUrl = "http://finedo.cn/finedosite/download/planmarketNO4A.apk";
        String downloadUrl = "https://www.pgyer.com/app/installUpdate/e5709c14c8c54fbbfa8cc29e1fed69ce?sig=ImSk7wWMr%2BvvmjX4Dv22lbsZ5Evu6qNNBFzC7XNT1lnDxFbKyPyGJuMQnY%2BcNIgP";
        String defaultFolder = Environment.getExternalStorageDirectory() + "/1/1";
        mFileLibrary.setDownloadDialog("下载文件","正在下载文件，请稍等。。。")
                .downloadFile(downloadUrl,"test.apk", defaultFolder,
                        new IFile.OnDownloadFileListener() {
                            @Override
                            public void onSuccess(File file) {
                                showToast("下载成功");
//                                mFileLibrary.openFile(file);
                            }

                            @Override
                            public void onFail(Exception e) {
                                showToast("下载出错，出错原因：" + e, Toast.LENGTH_LONG);
                            }

                            @Override
                            public void cancel() {
                                showToast("取消下载");
                            }
                        });
    }

    /**
     * 获取下载链接中的文件名
     */
    private void getUrlFileName() {
        mFileLibrary.getFileName("http://speedtest.tokyo.linode.com/100MB-tokyo.bin",
                new IFile.OnFileNameListener() {
                    @Override
                    public void getFileName(String fileName) {
                        Message msg = new Message();
                        msg.obj = fileName;
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }
                });
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mDialogLibrary.promptDialog((String) msg.obj);
                    break;
                case 1:
                    break;
            }
            return false;
        }
    });


    private void openFile() {
        mFileLibrary.setOpenFileAuthorities("com.taolibrary.fileProvider");
        File file = new File(Environment.getExternalStorageDirectory() + "/1/1", "100MB-tokyo.bin");
        mFileLibrary.openFile(file);
    }


}
