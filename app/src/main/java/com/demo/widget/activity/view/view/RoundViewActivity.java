package com.demo.widget.activity.view.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.databinding.ViewRoundBinding;
import com.google.android.flexbox.FlexboxLayout;
import com.orhanobut.logger.Logger;
import com.taolibrary.util.screen.DensityUtils;
import com.taolibrary.widget.view.switchview.SwitchView;

import org.w3c.dom.Entity;
import org.w3c.dom.Text;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2019/12/10 21:48
 */
public class RoundViewActivity extends BaseActivity {
    private ViewRoundBinding mBinding;

    @Override
    protected void initView() {
        mBinding = ViewRoundBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.rdvCertifySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBinding.rdvCertifySubmit.setBgColor(Color.RED);

    }

    @Override
    protected void initData() {
        initOpenView(); //初始化开启布局
        initSetView(); //初始化设置数据布局
    }


    private void initOpenView() {
        SwitchView shadowView = null;

        addOpenView("是否显示图片:", isOpen -> {
            if (isOpen) {
                mBinding.viewRoundIv.setVisibility(View.VISIBLE);
            } else {
                mBinding.viewRoundIv.setVisibility(View.GONE);
            }
        });
        addOpenView("是否开启圆形:", isOpen -> {
            mBinding.rdEg.setCircle( isOpen );
        });
        addOpenView("是否开启切割:", isOpen -> {
            mBinding.rdEg.setClipBg(isOpen);
        });
        shadowView = addOpenView("是否开启阴影:", isOpen -> {
            mBinding.rdEg.setShadow(isOpen);
        });

        //阴影的状态是开启的
        shadowView.setOpened(true);
    }


    private void initSetView() {
        addSetView("边框线宽:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setStrokeWidth(getProgressRadius(progress)/10);
            }
        });
        addSetView("全部边角:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setRoundAll(getProgressRadius(progress));
            }
        });
        addSetView("左上边角:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setRoundTopLeft(getProgressRadius(progress));
            }
        });
        addSetView("右上边角:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setRoundTopRight(getProgressRadius(progress));
            }
        });
        addSetView("右下边角:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setRoundBottomRight(getProgressRadius(progress));
            }
        });
        addSetView("左下边角:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setRoundBottomLeft( getProgressRadius(progress));
            }
        });
        addSetView("阴影半径:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setShadowRadius( getProgressRadius(progress));
            }
        });
        addSetView("阴影X轴:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setShadowDx( getProgressRadius(progress));
            }
        });
        addSetView("阴影Y轴:", new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.rdEg.setShadowDy( getProgressRadius(progress));
            }
        });
    }



    /**
     * 动态开启布局
     */
    private SwitchView addOpenView(String title, SwitchView.OnStateListener onStateListener) {
        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL); //水平布局
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        params.setMargins(dp2Px(20), dp2Px(6), dp2Px(20), 0);
        linearLayout.setLayoutParams(params);
        //设置text标题
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
        textView.setText(title);
        linearLayout.addView(textView);
        //添加开关
        LinearLayout.LayoutParams switchParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        SwitchView switchView = new SwitchView(this);
        switchParam.setMargins(dp2Px(10), 0, 0, 0);
        switchView.setLayoutParams(switchParam);
        switchView.setOnStateListener(onStateListener);
        linearLayout.addView(switchView);

        mBinding.viewRoundOpenFl.addView(linearLayout);
        return switchView;
    }

    /**
     * 动态设置数值
     */
    private void addSetView(String title, SimpleSeekBarChangeListener simpleSeekBarChangeListener) {
        //总布局
        LinearLayout linearLayout = new LinearLayout(this);
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL); //水平布局
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        params.setMargins(dp2Px(20), dp2Px(15), dp2Px(20), 0);
        linearLayout.setLayoutParams(params);
        //设置text标题
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
        textView.setText(title);
        linearLayout.addView(textView);
        //设置数值
        SeekBar seekBar = new SeekBar(this);
        LinearLayout.LayoutParams seekBarParam = new LinearLayout.LayoutParams(dp2Px(200), LinearLayout.LayoutParams.WRAP_CONTENT);
        seekBar.setOnSeekBarChangeListener(simpleSeekBarChangeListener);
        seekBarParam.setMargins(dp2Px(30), 0, 0, 0);
        seekBar.setLayoutParams(seekBarParam);
        linearLayout.addView(seekBar);

        mBinding.viewRoundOpenFl.addView(linearLayout);
    }


    private int getProgressRadius(int progress) {
        int size = getResources().getDimensionPixelOffset(R.dimen.size_example_image);
        return (int) ((float) progress / 100 * size) / 2;
    }


    public static abstract class SimpleSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }


    public int dp2Px(float dpValue) {
        return DensityUtils.dp2px(this, dpValue);
    }



}
