# 多分辨率屏幕适配

- 在gradle(Module:app)里添加配置：

```java
implementation 'com.ytman:TaoLibrary:1.2.2'
```

### 前言

- 讲真的，在项目开发中，屏幕适配是非常重要的，在Android上面有俩个很火的屏幕适配库：AndroidAutoLayout和AndroidAutoSize，AndroidAutoLayou用起来感觉还是略有点麻烦；AndroidAutoSize我之前，在刚开始一个项目里面引入了，可是这个库的侵性太强，按照头条的适配原理去实现的，改变了：density 的值，这个值是个很重要的值：px = dp * density，连toast大小都强行改变了，当然在issues里找到解决办法，然后我之前封装的dialog也改变了等等，还遇到其他一些问题，我佛了，弃用了。
- 在我想法里，我只需要Application里注册下，然后在Activity一行代码适配下view就行了，不适配的view，样式都不会改变，最好还能把适配ViewGroup中某个ViewGroup/View还原回去，界面适配又是很重要的部分，还是自己搞懂下吧，如果需求调整，自己也可动态调整代码。
- 适配功能实现只有俩个类：AutoUi和ScreenParam，代码只有五百行左右哟。在方法命名和对外提供的api，做了较多思虑，而且注释写的灰常灰常详细，有兴趣可以去看看，保证你一看就懂，这套适配在公司几个项目用起来还不错。
- 使用了适配后，在XML里面，直接填设计图上的尺寸标注（单位：px）就行了，这边在XML布局直接写的单位为：px

### 使用

- 在Application里注册下设计稿的尺寸

```java
 /** 一行代码注册下设计稿
* 请在Application里初始化设计稿的屏幕参数 (init方法参数)
* @param context 上下文
* @param width   屏幕宽度参数 （如果参数小于等于0，则自动设置默认参数）
* @param height  屏幕长度参数 （如果参数小于等于0，则自动设置默认参数）
* @param isFontScale  字体大小根据系统的“字体大小”设置选项来进行缩放  false：不开启  true：开启
* 默认参数：默认按照: 长/宽：1334/750 ----> iPhone 6的尺寸稿设计
*/
AutoUi.getInstance().init(this, 750, 1334, false);

/****************Application完整代码***********************/
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AutoUi.getInstance().init(this, 750, 1334, false);
    }
}

/****************在其他需要适配的Activity里面***********************/
AutoUi.with().asWidth(getWindow().getDecorView());
//或者ViewBinding *** mBinding = ***.inflate(getLayoutInflater());
AutoUi.with().asWidth(mBinding.getRoot());
/****************在Fragment或者RecyclerView里面***********************/
LayoutInflater inflater = LayoutInflater.from(mContext);
View view = inflater.inflate(R.layout.xxxxx, parent, false); //拿到View就行，然后对这个View进行适配
AutoUi.with().asWidth(view);
```

### 对外提供方法（AutoUi）

| 方法名                                                       | 含义                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| getInstance()                                                | 实例化：获取AutoUi适配类单例                                 |
| with()                                                       | 实例化：获取AutoUi适配类单例（整个短点的方法名）             |
| init(Context context, int width, int height, boolean isFontScale) | 请在Application里初始化设计稿的屏幕参数 <br/>@param context 上下文<br/>@param width   屏幕宽度参数（如果参数小于等于0，则自动设置默认参数）<br/>@param height  屏幕长度参数（如果参数小于等于0，则自动设置默认参数）<br/>@param isFontScale  字体大小根据系统的“字体大小”设置选项来进行缩放  false：不开启  true：开启<br/>默认参数：默认按照: 长/宽：1334/750 ----> iPhone 6的尺寸稿设计 |
| asWidth(View view)                                           | 水平方向按照宽度适配，垂直方向按照高度适配 <br/>推荐：小程序界面适配就是按照屏幕宽度适配，一般情况可以使用该种适配<br/>@param view   ViewGroup/View 均可 |
| asHeight(View view)                                          | 按照屏幕高度适配<br/>不支持下滑的界面,可采用高度适配: 可保持布局不会超出屏幕<br/>@param view   ViewGroup/View 均可 |
| asWidthAndHeight(View view)                                  | 水平方向按照宽度适配，垂直方向按照高度适配<br/>eg：保留该种适配，某些情况，该种适配视觉效果较好<br/>@param view  ViewGroup/View 均可 |
| asCancel(View view)                                          | 取消已经适配过的view<br/>说明：这是一个较为重要的方法，如果已经将所有布局适配了，其中某个模块并不想适配了，可以调用这个方法，将某个适配的ViewGroup或View还原。<br/>场景：该方法使用场景，一般是还原某个适配过的第三方控件，项目中遇到过这种问题，所以特地写个取消适配方法<br/>@param view  ViewGroup/View 均可 |

### 实时预览适配布局

- 点击图标

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404211008.png)

- 选择 "New Hardware Profile"

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404212115.png)

- 这里填上你的设计稿的尺寸就行了。我在这里就设置为IPhone 6的尺寸的，主要是公司的UI，基本都是按照这个尺寸设计的：宽/长：750/1334，Device Name随你自己命名了

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404212838.png)

- 接下来，next就行了

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404213830.png)

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404213918.png)

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404213938.png)

Ok，这样就完成了！

- 重启下Android Studio，就能看见我们新建虚拟机
- ok，这样我们就是实时预览咱们的界面了

![](https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200404214638.png)







