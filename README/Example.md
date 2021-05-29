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
