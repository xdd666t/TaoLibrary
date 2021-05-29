# File篇
### 使用封装的file方法需要先实例化对象
```
FileLibrary mFileLibrary = new FileLibrary(this);
或
IFile mFileLibrary = new FileLibrary(this);
```
### 说明:
- 该篇中用到下列依赖库，如果开发者项目不存在下列依赖库，请添加：
```
implementation 'com.squareup.okhttp3:okhttp:3.6.0'
```
- 下载文件需要在AndroidManifest.xml文件中配置下列权限：
```
<uses-permission android:name="android.permission.INTERNET" /> <!-- 联网权限 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
- 如果打开文件是apk，在AndroidManifest.xml文件中需要配置下列权限：
```
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />  <!--打开安装包必须的权限-->
```

***
</br>

## 下载文件(downloadFile)
### 使用方式:
- #### 使用详细：使用下载方法，需要先设置下载弹窗（setDownloadDialog），然后再设置下载方法（downloadFile）。</br>
- #### 下载弹窗：setDownloadDialog(String title, String content)
  俩个参数：title（设置弹窗的标题），content（设置弹窗显示的内容）
- #### 下载方法：downloadFile(String downloadUrl, String saveFolder, final OnDownloadFileListener onDownloadFileListener);
  三个参数：downloadUrl（需要下载文件的链接），saveFolder（需要将下载的文件，保存在某个指定的文件夹），OnDownloadFileListener（需要实现该接口，监听下载成功，失败和取消这些操作）
- #### 下载方法（提供手动设置下载文件命名）：downloadFile(String downloadUrl, String fileName, String saveFolder, final OnDownloadFileListener onDownloadFileListener);
   四个参数：downloadUrl（需要下载文件的链接），fileName（手动命名下载文件命名），saveFolder（需要将下载的文件，保存在某个指定的文件夹），OnDownloadFileListener（需要实现该接口，监听下载成功，失败和取消这些操作）
- #### 说明：俩个下载方法区别是，是否传入命名下载文件名。第一个下载方法，是通过访问到真实下载链接，从中截取链接中的文件名，然后将截取到的文件名，自动命名到我们的下载文件上。该种方法在测试中均成功，如果开发者在使用过程报异常，请使用第二种下载方法，手动定义文件名。


```
String downloadUrl = "http://speedtest.tokyo.linode.com/100MB-tokyo.bin";
String defaultFolder = Environment.getExternalStorageDirectory() + "/1/1";
mFileLibrary.setDownloadDialog("下载文件","正在下载文件，请稍等。。。")
        .downloadFile(downloadUrl, defaultFolder,
                new IFile.OnDownloadFileListener() {
                    @Override
                    public void onSuccess(File file) {
                        showToast("下载成功");  //使用BaseFunction封装方法
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
```
- 效果图
<img src="/README/picture/downloadFile.png" width = "337" height = "600"/>

***
</br>


## 获取下载链接中的文件名(getFileName)
### 使用方式:
- #### 获取文件名：getFileName(final String downloadUrl);
  一个参数：downloadUrl（下载链接）：直接截取下载链接中的文件名，如果下载链接是真实链接映射出来，直接获取的的文件名可能不携带后缀或某些信息缺失，此种情况请使用第二种方法。

```
showToast(mFileLibrary.getFileName("http://speedtest.tokyo.linode.com/100MB-tokyo.bin"));
```

- #### 获取文件名（解析访问到的真实下载链接）：getFileName(final String downloadUrl, final OnFileNameListener onFileNameListener);
  俩个参数：downloadUrl（下载链接），OnFileNameListener（需要实现该接口，该接口监听访问的真实链接，并截取出其中文件名）


```
mFileLibrary.getFileName("http://speedtest.tokyo.linode.com/100MB-tokyo.bin",
                new IFile.OnFileNameListener() {
                    @Override
                    public void getFileName(String fileName) {
                        showToast(fileName);
                    }
                });
```
- 效果图
  无

***
</br>

## 打开文件(openFile)
### 使用方式:
- #### 使用详细：由于Android7.0的访问文件权限设置，以前直接访问URL会闪退报错。谷歌官方解决方案：使用FileProvider来生成一个content://格式的URI。具体打开方法已封装，但是在使用之前需要配置一些文件和设置</br>
- #### AndroidManifest.xml中添加配置
  authorities的值需要自己设置，一般设置为包名即可
```
<manifest ...>
<application ...>

......

<!--此处一定要注意，authorities可以可以修改格式为  包名.fileProvider   
        在代码中一定要使用 FileLibrary中的 setOpenFileAuthorities("包名.fileProvider") 设置-->
        <!--setOpenFileAuthorities  代码中默认设置的是  "com.taolibrary.fileProvider" -->

<provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="包名.fileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths" />
</provider>

......

</application>
</manifest>

```
- #### res文件夹下增加“xml”文件夹，xml文件夹下增加“file_paths”文件
  file_paths文件填写以下代码
```
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path path="Android/data/com.ytaolibrary/" name="files_root" />
    <external-path path="." name="external_storage_root" />
    <root-path name="root_path" path="."/>
</paths>
```
- 效果图
<img src="/README/picture/openFileOne.png" width = "950" height = "257"/>

- #### 设置authorities（可设置为包名）：setOpenFileAuthorities(String authorities);
   一个参数：authorities（自己定义，可设置为包名）。说明：此处设置的值，必须和AndroidManifest.xml中设置的authorities值一致。
- #### 打开文件（openFile）:openFile(File file);
  一个参数：file（传入需要打开的file）
```
mFileLibrary.setOpenFileAuthorities("com.ytaolibrary.fileProvider");
File file = new File(Environment.getExternalStorageDirectory() + "/1/1", "100MB-tokyo.bin");
mFileLibrary.openFile(file);
```
- 效果图
<img src="/README/picture/openFileTwo.png" width = "337" height = "600"/>
