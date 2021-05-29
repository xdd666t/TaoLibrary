# Dialog篇
### 使用封装的dialog控件需要先实例化对象
```
DialogLibrary dialogLibrary = new DialogLibrary(this);
或
IDialog dialogLibrary = new DialogLibrary(this);
```

***
</br>

### 重要说明：
#### 如果因为项目api太低，项目默认style原因，使 提示弹窗（promptDialog）和通用弹窗（commonDialog）背景变成黑色，请在实例化DialogLibrary对象后，使用你本身项目中的自定义style。</br>
- 使用如下：
```
DialogLibrary dialogLibrary = new DialogLibrary(context);
dialogLibrary.setDialogStyle(R.style.myDialog);  //设置style
```
- 关于style中自定义myDialog代码：
```
<style name="MyDialog" parent="@android:style/Theme.Dialog">
        <!--边框-->
        <item name="android:windowFrame">@null</item>
        <!--是否浮现在activity之上-->
        <item name="android:windowIsFloating">true</item>
        <!--半透明-->
        <item name="android:windowIsTranslucent">false</item>
        <!--无标题-->
        <item name="android:windowNoTitle">true</item>
        <!--设置dialog的背景-->
        <item name="android:background">@android:color/transparent</item>
        <!--背景透明-->
        <item name="android:windowBackground">@android:color/transparent</item>
        <!--模糊-->
        <item name="android:backgroundDimEnabled">true</item>
    </style>
```

***
</br>

## 提示弹窗（promptDialog）
### 使用方式
- #### 极简使用：一个变量。</br>
内容（content）String类型。
```
dialogLibrary.promptDialog("测试");
```
- 效果图
<img src="/README/picture/promptDialogOne.png" width = "337" height = "600"/>

- #### 高级使用(增加头部标题)：俩个变量。</br>
标题（title）String类型，内容（content）String类型。
```
dialogLibrary.promptDialog("简单提示","测试");
```
- 效果图
<img src="/README/picture/promptDialogTwo.png" width = "337" height = "600"/>

- #### 超详细使用：六个变量，实现一个接口。</br>
- #### 该方法基本提供了，对该类型弹窗字体颜色样式多种设置，适合开发再度定制封装，请详细阅读下述内容。</br>
第一个，标题（title）String类型；第二个，标题颜色（titleColor）String类型；</br>
第三个，内容（content）String类型；第四个，内容颜色（contentColor）String类型；</br>
第五个，按钮名（buttonName）String类型；第六个，按钮颜色（buttonColor）String类型；</br>
第七个，实现OnPromptDialogClick()接口
#### 注；如果颜色字符赋“0”，则取默认值
```
String content ="测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试试内内试内"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容"
                + "内容测试内容测试内容测试内内容测试内容测试内容测试内容测试容测试内容测试内容测试内容";
```

```
        dialogLibrary.promptDialog("简单提示", "#D81B60", content, "#008577", "明白了", "#555555",
                new IDialog.OnPromptDialogClick() {
                    @Override
                    public void onClick() {
                        //写入你的逻辑
                    }
                });
```
 #### 提供去掉头部 title布局设置：dialogLibrary.setHideTitle().promptDialog(...)  </br>
 #### 提供去掉内容content布局设置：dialogLibrary.setHideContent().promptDialog(...)，用法同上  </br>
因为promptDialog(...)中封装了show()方法，所以setHideTitle()设置必须写在调用promptDialog(...)前才能起作用
```
dialogLibrary.setHideTitle().promptDialog("简单提示", "#D81B60", content, "#008577", "明白了", "#555555",
                new IDialog.OnPromptDialogClick() {
                    @Override
                    public void onClick() {
                        //写入你的逻辑
                    }
                });
```
- 效果图

<img src="/README/picture/promptDialogThree.png" width = "337" height = "600" div align=left />
<img src="/README/picture/promptDialogFour.png" width = "337" height = "600" div  />


***
</br>


## 通用弹窗(waitDialog)
### 使用方式:
- #### 极简使用：一个变量，实现一个接口。</br>
内容（content）String类型。</br>
实现OnCommonDialogClick()接口
```
dialogLibrary.commonDialog("我来展示一些信息,大家看看就行", new IDialog.OnCommonDialogClick() {
            @Override
            public void affirmOnClick() {
                //写入逻辑
            }
        });
```
- 效果图
<img src="/README/picture/commonDialogOne.png" width = "337" height = "600"/>

- #### 高级设置：传俩个变量，实现一个接口（去掉标题布局）.
  第一个，内容（content）String类型；第二个，确认按钮名（affirmButtonName）String类型；</br>
  第三个，实现OnCommonDialogClick()接口
```
dialogLibrary.commonDialog("我来展示一些信息,大家看看就行","点我点我", new IDialog.OnCommonDialogClick() {
            @Override
            public void affirmOnClick() {
                //写入逻辑
            }
        });
```
- 效果图
<img src="/README/picture/commonDialogTwo.png" width = "337" height = "600"/>

- #### 高级设置：传三个变量，实现一个接口.
  第一个，标题（title）String类型；第二个，内容（content）String类型；</br>
  第三个，确认按钮名（affirmButtonName）String类型；</br>
  第四个，实现OnCommonDialogClick()接口
```
dialogLibrary.commonDialog("需要下载文件","我来展示一些信息,大家看看就行","点我点我", new IDialog.OnCommonDialogClick() {
            @Override
            public void affirmOnClick() {
                //写入逻辑
            }
        });
```
- 效果图
<img src="/README/picture/commonDialogThree.png" width = "337" height = "600"/>

- #### 超详细设置：传八个变量，实现一个接口.
- #### 该方法基本提供了，对该类型弹窗字体颜色样式多种设置，适合开发再度定制封装，请详细阅读下述内容。</br>
  第一个，标题（title）String类型；第二个，标题颜色（titleColor）String类型；</br>
  第三个，内容（content）String类型；第四个，内容（contentColor）String类型；</br>
  第五个，确认按钮名（affirmButtonName）String类型；第六个，确认按钮颜色（affirmColor）String类型；</br>
  第七个，取消按钮名（cancelButtonName）String类型；第八个，取消按钮颜色（affirmColor）String类型；</br>
  第九个，实现OnCommonDialogHighClick()接口
  #### 注；如果颜色字符赋“0”，则取默认值
```
dialogLibrary.setHideTitle().commonDialog("需要下载文件", "#D81B60", "我来展示一些信息,大家看看就行", "#008FED",
                "点我点我", "#6C6C6C", "取不取消", "#008577",
                new IDialog.OnCommonDialogHighClick() {
                    @Override
                    public void affirmOnClick() {
                        //写入你的逻辑
                    }

                    @Override
                    public void cancelOnClick() {
                        //写入你的逻辑
                    }
                });
```
#### 提供去掉头部 title设置：dialogLibrary.setHideTitle().commonDialog(...)  </br>
#### 提供去掉内容content布局设置：dialogLibrary.setHideContent().commonDialog(...)，用法同上  </br>
因为commonDialog(...)中封装了show()方法，所以setHideTitle()设置必须写在调用commonDialog(...)前才能起作用
```
dialogLibrary.setHideTitle().commonDialog("需要下载文件", "#D81B60", "我来展示一些信息,大家看看就行",
                "#008FED", "点我点我", "#6C6C6C", "取不取消", "#008577",
                new IDialog.OnCommonDialogHighClick() {
                    @Override
                    public void affirmOnClick() {
                        showToast("确定");
                    }

                    @Override
                    public void cancelOnClick() {
                        showToast("取消");
                    }
                });
```

- 效果图

<img src="/README/picture/commonDialogFour.png" width = "337" height = "600" div align=left />
<img src="/README/picture/commonDialogFive.png" width = "337" height = "600" div  />

***
</br>


## 等待弹窗(waitDialog)
### 说明：该控件是对另一依赖库的进一步封装，详细请看 [自定义Loading View库](https://github.com/zyao89/ZLoading)
### 使用方式:
- 等待加载弹窗关闭，统一使用
```
dialogLibrary.closeWaitDialog();
```

- #### 极简使用:只需要传一个变量.
  等待信息（message）String类型
```
dialogLibrary.waitDialog("测试");
```
- 效果图
<img src="/README/picture/waitDialog.gif" width = "337" height = "600"/>

- #### 高级设置：传俩个变量.
  第一个，等待信息（message）String类型；第二个动画类型(cartoonStyle)int类型（只限0-17，18种动画类型）。
```
dialogLibrary.waitDialog("测试", 12);
```
- 效果图
<img src="/README/picture/waitDialogOne.gif" width = "337" height = "600"/>

- #### 详细设置：传三个变量.
  第一个，等待信息（message）String类型；第二个动画类型（cartoonStyle）int类型（只限0-17，18种动画类型）；</br>
  第三个，动画背景（background）String类型，传入颜色代码，参照下方范例。</br>
```
dialogLibrary.waitDialog("测试",13, "#6C6C6C");
```
- 效果图
<img src="/README/picture/waitDialogTwo.gif" width = "337" height = "600"/>

***
</br>



## 底部弹窗(bottomDialog)
### 使用方式:
- #### 极简使用：传俩个变量，实现一个接口。</br>
第一个，顶部按钮名（topButtonName）String类型；第二个，中间按钮名（midButtonName）String类型；</br>
第三个，实现 OnBottomDialogClick 接口。</br>
实现接口全称：new IDialog.OnBottomDialogClick(){...}。</br>
```
dialogLibrary.bottomDialog("顶部（替换）", "中间（替换）", new IDialog.OnBottomDialogClick() {
            @Override
            public void onTopButtonClick() {
                showToast("自定义你的逻辑"); //该方法取自基本功能封装类
            }

            @Override
            public void onMidButonClick() {
                showToast("自定义你的逻辑");
            }
        });
```
- 效果图
<img src="/README/picture/bottomDialog.jpg" width = "337" height = "600"/>

- #### 超详细使用：传六个变量，实现一个接口。</br>
- #### 该种使用，可以按照各自的需求简单封装。</br>
第一个，顶部按钮名（topButtonName）String类型；第二个，顶部按钮颜色（topColor）String类型；</br>
第三个，中间按钮名（midButtonName）String类型；第四个，中间按钮颜色（midColor）String类型；</br>
第五个，取消按钮名（cancelButtonName）String类型；第六个，取消按钮颜色（cancelColor）String类型。</br>
实现 OnBottomDialogDetailClick 接口。具体使用见下方代码范例。
```
dialogLibrary.bottomDialog("顶部按钮", "#008577", "中间按钮", "#00574B", "取消按钮", "#D81B60",
                new IDialog.OnBottomDialogHighClick() {
                    @Override
                    public void onTopButtonClick() {
                        //添加你的逻辑,或者接口监听该项
                    }

                    @Override
                    public void onMidButonClick() {
                        //添加你的逻辑,或者接口监听该项
                    }

                    @Override
                    public void onCancelButtonClick() {
                        //添加你的逻辑,或者接口监听该项
                    }
                });
    }
```
- 效果图
<img src="/README/picture/bottomDialogOne.png" width = "337" height = "600"/>

***
</br>

## 基础弹窗（baseDialog）
### 使用方式：
- #### 极简使用：俩个变量，实现一个接口。</br>
第一个，标题（title）String类型；第二个，消息内容（message）String类型；实现OnBaseDialogClick接口。
```
dialogLibrary.baseDialog("测试", "测试内容展示", new IDialog.OnBaseDialogClick() {
            @Override
            public void positiveOnClick() {
                showToast("测试"); //该方法来自基本功能库
            }
        });
```
- 效果图
<img src="/README/picture/baseDialogOne.png" width = "337" height = "600"/>

- #### 高级使用：四个变量，实现一个接口。</br>
第一个，标题（title）String类型；第二个，消息内容（message）String类型；</br>
第三个，positive按钮（positiveButtonName）String类型；第四个，negative按钮（negativeButtonName）String类型。</br>
实现 OnBaseDialogHighClick 接口。具体使用见下方代码范例。
```
dialogLibrary.baseDialog("测试", "测试内容显示", "积极按钮", "消极按钮",
        new IDialog.OnBaseDialogHighClick() {
            @Override
            public void positiveOnClick() {
                //写入你的逻辑
            }

            @Override
            public void negativeOnClick() {
                //写入你的逻辑
            }
        });
```
- 效果图
<img src="/README/picture/baseDialogTwo.png" width = "337" height = "600"/>

***
</br>
