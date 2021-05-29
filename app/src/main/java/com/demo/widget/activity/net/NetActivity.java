package com.demo.widget.activity.net;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.bean.TestBean;
import com.demo.databinding.ActivityNetBinding;
import com.taolibrary.BaseFunction;
import com.taolibrary.base.single.SingleRvAdapter;
import com.taolibrary.widget.dialog.DialogLibrary;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/1/31 14:40
 */
public class NetActivity extends BaseActivity{
    private ActivityNetBinding mBinding;
    private DialogLibrary mDialogLibrary;

    private SingleRvAdapter<String> mSingleRvAdapter;
    private NetVM mNetVM;

    @Override
    protected void initInstance(Context context) {
        super.initInstance(this);

    }


    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_net);

        mBinding.showContent.setMovementMethod(ScrollingMovementMethod.getInstance()); //设置内容可滚动
    }

    @Override
    protected void initData() {
        new BaseFunction(this);
        mDialogLibrary = new DialogLibrary(this);
        mNetVM = new ViewModelProvider(this).get(NetVM.class);

        skipFunction();
    }

    private void skipFunction() {
        mNetVM.getList().observe(this, new androidx.lifecycle.Observer<List<String>>() {
            @Override public void onChanged(List<String> strings) {
                mSingleRvAdapter.updateData(strings);
            }
        });

        //列表实现
        mBinding.rvNet.setLayoutManager(new GridLayoutManager(this, 2));
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
                        rxjavaAndRetrofit();
                        break;
                    case 1:
                        getJson();
                        break;
                }
            }
        });
        mBinding.rvNet.setAdapter(mSingleRvAdapter);
    }


    private void urlAnalysis() {
        mBinding.showContent.setText("");
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://xxx:9004/sellstd_webapp/")
                .build();

        Api mApi = retrofit.create(Api.class);

        final Call<TestBean> testBeanCall = mApi.getTest("36b7de49-9c92-4d02-8057-6394f36a4176");
        //发送网络请求(异步)
        testBeanCall.enqueue(new Callback<TestBean>() {
            @Override
            public void onResponse(Call<TestBean> call, retrofit2.Response<TestBean> response) {
                try {
//                    mBinding.showContent.setText("访问成功：" + new String(response.body()toString().getBytes()));
//                    Log.i("yutao", new String(response.body().getObject().toString().getBytes()));
                } catch (Exception e) {
                    mBinding.showContent.setText("访问成功,异常信息：" + e );
                }
            }

            @Override
            public void onFailure(Call<TestBean> call, Throwable t) {
                mBinding.showContent.setText( "访问失败：" + t );
            }
        });

    }

    public void getJson() {
        mBinding.showContent.setText("");
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://xxx:9004/sellstd_webapp/")
                .build();

        GetJson getJson = retrofit.create(GetJson.class);//这是get请求的接口

        final Call<ResponseBody> testBeanCall = getJson.jsonData("36b7de49-9c92-4d02-8057-6394f36a4176");
        testBeanCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    mBinding.showContent.setText("访问成功：" + new String(response.body().bytes()));
                    Log.i("yutao", new String(response.body().bytes()));
                } catch (Exception e) {
                    mBinding.showContent.setText("访问成功,异常信息：" + e );
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mBinding.showContent.setText( "访问失败：" + t );
            }
        });
    }


    public interface Api{
        //    finedo/loginold4a/getCommonInfo(不需要token)
        @GET("finedo/sellstd/queryQqhm")
        Call<TestBean> getTest(@Query("token") String token);
    }

    public interface GetJson{
        @GET("finedo/sellstd/queryQqhm")
        Call<ResponseBody> jsonData(@Query("token") String token);
    }


    public void rxjavaAndRetrofit() {
        mBinding.showContent.setText("");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        MixRetrofit mixRetrofit = retrofit.create(MixRetrofit.class);
        Observable<TestBean> observable = mixRetrofit.getSearchBook("西游记",0,1);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<TestBean>() {

                      @Override
                      public void onError(Throwable e) {
                          mBinding.showContent.setText(e + "");
                      }

                      @Override
                      public void onComplete() {

                      }

                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(TestBean testBean) {
                          mBinding.showContent.setText(String.valueOf(testBean.getBooks().size()));
                      }
                  });


    }


    private interface MixRetrofit{
        @GET("book/search")
        Observable<TestBean> getSearchBook(@Query("q") String name,
                                           @Query("start") int start,
                                           @Query("count") int count);
    }

    public void showMessage(Context context) {
        Toast.makeText(context, "多发送到" , Toast.LENGTH_SHORT).show();
    }

    public String gerMessage() {
        return "NetActivity信息，回调回调";
    }

}
