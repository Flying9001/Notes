## Sublime Text 3 使用指南    



### 1 下载  

官网:[https://www.sublimetext.com/3](https://www.sublimetext.com/3)

### 2 插件管理  

#### 2.1 插件安装控制包  

`Ctrl` + ` ` `  --- 将以下代码复制到输入框  

```javascript
import urllib.request,os; pf = 'Package Control.sublime-package'; ipp = sublime.installed_packages_path(); urllib.request.install_opener( urllib.request.build_opener( urllib.request.ProxyHandler()) ); open(os.path.join(ipp, pf), 'wb').write(urllib.request.urlopen( 'http://sublime.wbond.net/' + pf.replace(' ','%20')).read())
```

#### 2.2 查看已安装插件  

`preserences`  ---  `package setting`  

#### 2.3 添加插件    

`prferences` -- `package control` /  `Ctrl` + `Shift` + `P`  

#### 2.4 中文乱码    

插件: `ConvertToUTF8`  

#### 2.5 中文文件名乱码    

`perfences` -- `settings` : 在属性的最后添加: `"dpi_scale":1.0`  

#### 2.6 中文输入光标不跟随    

插件: `IMESupport`  

win 10 解决方案:  

下载针对 win10 的 `IMESupport` 插件  --- 将插件复制到 `Sublime_Text_3_Install_Path/Data/Packages` 目录下 

下载地址: [https://github.com/zcodes/IMESupport](https://github.com/zcodes/IMESupport)  

#### 2.7 设置全局标尺(Ruler)  

`preferences` -- `settings` : 在属性的最后添加: `"rulers":[100]`  

#### 2.8 关闭自动更新  

`perfences` -- `settings` : 在属性最后添加 : `"update_check": false`  

#### 2.9 激活  

下载注册机  

链接: [https://pan.baidu.com/s/1iLEJTJmsc8r28VBEkgW3ig](https://pan.baidu.com/s/1iLEJTJmsc8r28VBEkgW3ig)  密码: `gcqm`  

解压后，将 `Patch.exe` 复制到 `sublime text 3` 的根目录下，双击 `Patch.exe` 运行（或右击=>以管理员身份运行），重启 `sublime` 



