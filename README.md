# TaoLibrary库

该Android库封装了一些Android常用的控件和功能，长期更新...

# 引入

- 在gradle(Module:app)里添加配置：
```java
implementation 'com.ytman:TaoLibrary:1.2.11'
```

# 说明

- 引入本库，安装包体积大概增加：415kb左右，使用前请知晓
- 点击图片可查看高清图

# 多分辨率屏幕适配

- 讲真的，在项目开发中，屏幕适配是非常重要的，在Android上面有俩个很火的屏幕适配库：AndroidAutoLayout和AndroidAutoSize，AndroidAutoLayou用起来感觉还是略有点麻烦；AndroidAutoSize我之前，在刚开始一个项目里面引入了，可是这个库的侵性太强，按照头条的适配原理去实现的，改变了：density 的值，这个值是个很重要的值：px = dp * density，连toast大小都强行改变了，当然在issues里找到解决办法，然后我之前封装的dialog也改变了等等，还遇到其他一些问题，我佛了，弃用了。
- 在我想法里，我只需要Application里注册下，然后在Activity一行代码适配下view就行了，不适配的view，样式都不会改变，最好还能把适配ViewGroup中某个ViewGroup/View还原回去，界面适配又是很重要的部分，还是自己搞懂下吧，如果需求调整，自己也可动态调整代码。
- 适配功能实现只有俩个类：AutoUi.java和ScreenParam.java，代码只有五百行左右哟。在方法命名和对外提供的api，做了较多思虑，而且注释写的灰常灰常详细，有兴趣可以去看看，保证你一看就懂，这套适配在公司几个项目用起来还不错。
- 使用了适配后，在XML里面，直接填设计图上的尺寸标注（单位：px）就行了，这边在XML布局直接写的单位为：px

#### 使用

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
View rootView = findViewById(android.R.id.content);  //直接获取根View
AutoUi.with().asWidth(rootView);
*** mBinding = ***.inflate(getLayoutInflater()); //通过ViewBinding获取根View
AutoUi.with().asWidth(mBinding.getRoot());
/****************在Fragment或者RecyclerView里面***********************/
LayoutInflater inflater = LayoutInflater.from(mContext);
View view = inflater.inflate(R.layout.xxxxx, parent, false); //拿到View就行，然后对这个View进行适配
AutoUi.with().asWidth(view);
```

#### 对外提供方法（AutoUi）

| 方法名                                                       | 含义                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| getInstance()                                                | 实例化：获取AutoUi适配类单例                                 |
| with()                                                       | 实例化：获取AutoUi适配类单例（整个短点的方法名）             |
| init(Context context, int width, int height, boolean isFontScale) | 请在Application里初始化设计稿的屏幕参数 <br/>@param context 上下文<br/>@param width   屏幕宽度参数（如果参数小于等于0，则自动设置默认参数）<br/>@param height  屏幕长度参数（如果参数小于等于0，则自动设置默认参数）<br/>@param isFontScale  字体大小根据系统的“字体大小”设置选项来进行缩放  false：不开启  true：开启<br/>默认参数：默认按照: 长/宽：1334/750 ----> iPhone 6的尺寸稿设计 |
| asWidth(View view)                                           | 水平方向按照宽度适配，垂直方向按照高度适配 <br/>推荐：小程序界面适配就是按照屏幕宽度适配，一般情况可以使用该种适配<br/>@param view   ViewGroup/View 均可 |
| asHeight(View view)                                          | 按照屏幕高度适配<br/>不支持下滑的界面,可采用高度适配: 可保持布局不会超出屏幕<br/>@param view   ViewGroup/View 均可 |
| asWidthAndHeight(View view)                                  | 水平方向按照宽度适配，垂直方向按照高度适配<br/>eg：保留该种适配，某些情况，该种适配视觉效果较好<br/>@param view  ViewGroup/View 均可 |
| asCancel(View view)                                          | 取消已经适配过的view<br/>说明：这是一个较为重要的方法，如果已经将所有布局适配了，其中某个模块并不想适配了，可以调用这个方法，将某个适配的ViewGroup或View还原。<br/>场景：该方法使用场景，一般是还原某个适配过的第三方控件，项目中遇到过这种问题，所以特地写个取消适配方法<br/>@param view  ViewGroup/View 均可 |

#### 实时预览适配布局

- 使用了上面适配，在XML里面，直接填设计图上的尺寸标注就行了
- 这个步骤截图较多，放到详情

#### 适配详情

- 查看详情：[界面适配使用详情](https://github.com/CNAD666/TaoLibrary/blob/master/README/AutoUi.md)

# 自定义view

- 写到按钮的时候，经常遇到长方形按钮，角度需要不同的弧度；需要边框；需要点击的按压效果；渐变色，渐变色按压效果啥的；圆形头像，弧度图片什么的。
- 每每这时候，就去写个一堆xml，放在drawerable里面，然后再去调用，写的真是方的一批，有时xml写多了，写的都搞不清那些xml实现了啥场景，整的是头皮发麻。
- 痛定思痛，自定义个view，起码能够解决写xml大部分痛点，反正我肝出了这个RoundView后，几乎就很少用xml去实现效果了，用在多个公司项目里，测试并没有在这个控件上给我提出什么bug，稳，放心食用。

### RoundView

- 举例（RoundView是继承RelativeLayout，可以使用RelativeLayout的所有参数）

  ```java
  <com.taolibrary.widget.view.round.RoundView
      android:id="@+id/rdv_certify_submit"
      android:layout_width="match_parent"
      android:layout_height="50dp"
      android:layout_margin="50dp"
      app:roundAll="5dp"
      app:isClickEffect="true"
      app:backgroudColor="#34a7f2"
      app:colorPress="#008FED">
      	<!--被包裹的布局,可以随便用什么，用ImageView就能被切割成弧形或者圆形头像 -->
  </com.taolibrary.widget.view.round.RoundView>
  ```

- **所有属性**

  | 参数             | 含义                                                         |
  | ---------------- | ------------------------------------------------------------ |
  | backgroudColor   | 控件的背景色                                                 |
  | colorPress       | 按压颜色（需要设置isClickEffect属性）                        |
  | angle            | 设置渐变方向（left：从左到右渐变，top：从上到下渐变，right：从右到左渐变，bottom：从下到上渐变） |
  | colorStart       | 渐变开始颜色                                                 |
  | colorEnd         | 渐变结束颜色                                                 |
  | colorPressStart  | 按压渐变开始颜色                                             |
  | colorPressEnd    | 按压渐变结束颜色                                             |
  | isClickEffect    | 是否开启按压效果，true:开启  false:不开启   默认false （这个属性比较重要，如果需要按钮效果，记得设置为true） |
  | isCircle         | 是否设置为圆, true:设置为圆形  false:不设置   默认false      |
  | isClipBg         | 是否切割背景（前景色也会被切割，如果非要使用涟漪效果，想涟漪效果只在边界内，可以开启该属性。这个地方试了很多方式，自定义Drawable什么的，发现在Drawable里还是没有设置单边弧度的方法，目前只发现切割的方法）, true:切割  false:不切割   默认false  切割背景会存在锯齿的情况 |
  | roundAll         | 设置四个角度，设置此值后，单设其它各边无效（这个属性也比较重要，举个例子：如果是长方形，设置的值超过宽，宽的俩边就是半圆；如果是正方形，设置的值超过边长，则就是圆了） |
  | roundTopLeft     | 设置顶部左边弧度                                             |
  | roundTopRight    | 设置顶部右边弧度                                             |
  | roundBottomLeft  | 设置底部左边弧度                                             |
  | roundBottomRight | 设置底部右边弧度                                             |
  | stroke_width     | 添加边框宽度                                                 |
  | stroke_color     | 添加边框颜色                                                 |
  | text             | 添加文字内容（这个是绘制个文字，如果写按钮的时候，有文字，懒得写TextView，可以用这个属性，写写简单的文字） |
  | textSize         | 添加文字大小                                                 |
  | textColor        | 添加文字颜色                                                 |
  | isShadow         | 是否开启阴影（开启阴影内部关闭此类功能：边框，渐变，内部显示图片）<br/>true:开启    false:关闭（默认false，关闭） |
  | shadowRadius     | 阴影四边角度弧度  向外扩充的半径                             |
  | shadowDx         | 阴影X轴偏转                                                  |
  | shadowDy         | 阴影Y轴偏转                                                  |
  | shadow_color     | 阴影颜色                                                     |

- 效果图

  - 俩倍速演示，可能是转gif原因，未开启切个好像还有点毛边；但是明确的说，不开启切割，是没有毛边的。
  - 点击的涟漪效果，使用的前景色自带的涟漪效果，因为用的是前景色，绘制的view没办法去改变其形状，导致涟漪效果越界；eg：这个在豆瓣app，刚安装的时候，弹出的授权，同意拒绝按钮，点击效果也越过了按钮的圆角。试了很多办法，都没有成功，最后只能用切割视图，保持涟漪效果不越界，但是切割存在毛边；没辙，然后我就在该view中还内置了一种设置点击时颜色的属性，曲线救国，日常使用内置的点击颜色，完全没问题，小米的通知栏，点击也只是整个item微微变灰，思路相似。
  - 增加了设置阴影操作，设置阴影的方法和PorterDuff冲突，开启阴影的时候会把：边框，渐变，内部显示图片这些功能阉割掉。

  </br>

  <img src="https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200621183204.gif"  width = "279" height = "600" >

- 参考部分逻辑：[https://github.com/GcsSloop/rclayout](https://github.com/GcsSloop/rclayout)

  - 不得不说，这位老兄的PorterDuff.Mode的几种模式运用的非常吊，还有对自定义view的细节掌握，例如边框的切割，关于绘制圆角的核心逻辑，参考了上述开源项目。我之前尝试自定义Drawable等等，对包裹的子view显示就不那么友好，如果大家有去看过自定义Drawable，会发现它只能使用RoundRect统一绘制所有角度，没法使用path绘制单个角度，爬坑爬了许久。。。这让我想到flutter，设置圆角和按压效果圆角不过界，仅仅设置下borderRadius参数就行了（   (╯‵□′)╯︵┻━┻   ）。。。
  - 使用了PorterDuff.Mode模式，基本实现目前需求，但是对前景色，背景色的限制还是没辙，我目前只能采取切割。

### CalendarList

- 这个view是一个垂直滑动的日历view，日历的定制型太多变了，大家想要什么，只能去代码里面去改了

- View路径

  <img src="https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200322222843.png" width = "500" height = "450"/>

- 效果图

  <img src="https://raw.githubusercontent.com/CNAD666/MyData/master/pic/study/20200322223156.jpg" width = "279" height = "600" />



### SwitchView

- 参考：[https://github.com/iielse/switchbutton](https://github.com/iielse/switchbutton )

- 这个开关按钮太常用了，搬了一个过来，微调了下接口调用方式，上面那俩个view，都是一行行代码苟出来的。

- 基本参数

  | 参数             | description           |
  | ---------------- | --------------------- |
  | hasShadow        | 是否显示按钮阴影      |
  | primaryColor     | 开启状态背景色        |
  | primaryColorDark | 开启状态按钮描边色    |
  | offColor         | 关闭状态描边色        |
  | offColorDark     | 关闭状态按钮描边色    |
  | shadowColor      | 按钮阴影色            |
  | ratioAspect      | 按钮宽高形状比率(0,1] |
  | isOpened         | 初始化默认状态        |
  | barColor         | 按钮颜色              |
  | bgColor          | 背景色                |

- 使用

  ```java
  <com.taolibrary.widget.view.switchview.SwitchView
      android:id="@+id/sw_circle"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:ratioAspect="0.6"
      app:primaryColor="#34a7f2"
      app:primaryColorDark="#E0E0E0"/>
  ```



# 基本功能篇

- 进入详细讲解：[BaseFunction详细讲解](https://github.com/CNAD666/YTAOLibrary/blob/master/README/BaseFunctionDetails.md)
- 效果图

<img src="/README/picture/showToast.png" width = "279" height = "496" />



# Dialog篇

- 进入详细讲解：[dialog详细讲解](https://github.com/CNAD666/YTAOLibrary/blob/master/README/DialogDetails.md)
- 效果图

<img src="/README/picture/promptDialogOne.png" width = "279" height = "496" div align=left />
<img src="/README/picture/commonDialogOne.png" width = "279" height = "496" div align=left />
<img src="/README/picture/waitDialog.gif" width = "279" height = "496"  />

<img src="/README/picture/bottomDialog.jpg" width = "279" height = "496" div align=left />
<img src="/README/picture/baseDialog.jpg" width = "279" height = "496"  />



# File篇

- 进入详细讲解：[file详细讲解](https://github.com/CNAD666/YTAOLibrary/blob/master/README/FileDetails.md)
- 效果图

<img src="/README/picture/downloadFile.png" width = "279" height = "496" div align=left />
<img src="/README/picture/openFileTwo.png" width = "279" height = "496"  />



# 网络篇

- 开发时，一般都会封装一套网络库进去请求等操作，本module也简单整了一套，是针对retrofit封装的，因为为了不增大依赖库的体积且避免产生冲突问题，依赖库中依赖okhttp和retrofit都是采用compileOnly，只参与编译，不参入打包，此处做个笔记，

  - 使用该封装网络库，需要的依赖有

  ```java
  // Okhttp库
  implementation  'com.squareup.okhttp3:okhttp:4.4.0'
  implementation 'com.squareup.okhttp3:logging-interceptor:3.8.1'
  // Retrofit库
  implementation 'com.squareup.retrofit2:retrofit:2.8.1'
  implementation 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'
  implementation 'com.google.code.gson:gson:2.8.2'
  //RxJava
  implementation 'io.reactivex.rxjava2:rxjava:2.2.19'
  implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
  ```

- 使用，做个备忘

  ```java
  Map<String, String> map = new HashMap<>();
  map.put("id", "1");   //设置参数
  HttpClient.getInstance().getApiService() //获取设置ApiService实例,Api的管理类
      .getCheckInRecord(map)   //填入设置的参数
      .compose(HttpClient.rxIo2Main(this)) //IO线程切换主线程
      .subscribe(new BaseObserver<BaseResponseBean<String>>() { //回调
          @Override
          protected void onSuccess(BaseResponseBean<String> stringBaseResponseBean) {

          }

          @Override
          protected void onFinish(String errorMessage) {

          }
      });
  ```
