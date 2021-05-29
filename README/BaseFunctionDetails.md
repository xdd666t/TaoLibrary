[僵小鱼图片]:https://raw.githubusercontent.com/CNAD666/YTAOLibrary/master/README/picture/one.jpg
<!--
 ![僵小鱼][僵小鱼图片] &nbsp; </br>
 <img src="/README/picture/showToast.jpg" width = "239" height = "426" div align=left />
-->


### 使用基础功能前需要初始化：
```
new BaseFunction(this);
```

### 简单封装弹出Toast：
```
showToast("测试");  //默认弹出时间短

//设置弹出时间
showToast("测试",Toast.LENGTH_SHORT); //弹出时间短
showToast("测试",Toast.LENGTH_LONG);  //弹出时间长
```

<img src="/README/picture/showToast.png" width = "337" height = "600"  />

### 封装SharedPreferences

- #### 保存数据：saveSpData(String key, Object object)
  俩个参数：key（输入key值，String类型），object（相当于value值，你需要保存的数据，object类型，随意输入类型，方法类能识别你输入的类型，再加以保存）
- #### 获取数据：(Object) getSpData(String key, Object defaultObject)
  俩个参数：key（输入key值，String类型），defaultObject（这个值的输入，需要和你保存的key一样的类型，值的内容可以随便输。通过key查找你是否保存了对应的value，如果没有，则返回你输入的defaultObject内容）

- #### 设置保存SharedPreferences文件的文件名（可选操作）：setSpFileName(String fileName)
  一个参数：fileName（输入文件名，String类型）
- #### 使用
  ```
  saveSpData("test", "测试一下");
  showToast((String) getSpData("test", ""));
  ```
