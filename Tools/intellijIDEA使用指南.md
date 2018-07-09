# intelliJ IDEA 常用设置使用指南  

### 1. 下载

[https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/ "https://www.jetbrains.com/idea/")  

### 2. 激活/破解  

2.1 下载破解文件(`JetbrainsCrack-x.x-release-str.jar`): [http://idea.lanyus.com/](http://idea.lanyus.com/ "http://idea.lanyus.com/")  

2.2 将破解文件(`JetbrainsCrack-2.7-release-str.jar`)复制到 intelliJ IDEA 安装目录下的 `bin` 目录中,  

eg: `D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.3\bin`  

2.3 修改 intelliJ 启动文件(`bin`目录下): `idea.exe.vmoptions` 和 `idea64.exe.vmoptions`  

在文件后边添加:  

`-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2018.1.3\bin\JetbrainsCrack-2.7-release-str.jar  

2.4 双击`bin` 目录下的 `idea64.exe` (32位系统为`idea.exe`) 打开 intelliJ(**必须从这里打开**),在进入出现有 `active code` 选择界面的时填写下面注册代码(2099 年到期):  

```json
ThisCrackLicenseId-{  
"licenseId":"ThisCrackLicenseId",  
"licenseeName":"idea",  
"assigneeName":"",  
"assigneeEmail":"idea@163.com",  
"licenseRestriction":"For This Crack, Only Test! Please support genuine!!!",  
"checkConcurrentUse":false,  
"products":[  
{"code":"II","paidUpTo":"2099-12-31"},  
{"code":"DM","paidUpTo":"2099-12-31"},  
{"code":"AC","paidUpTo":"2099-12-31"},  
{"code":"RS0","paidUpTo":"2099-12-31"},  
{"code":"WS","paidUpTo":"2099-12-31"},  
{"code":"DPN","paidUpTo":"2099-12-31"},  
{"code":"RC","paidUpTo":"2099-12-31"},  
{"code":"PS","paidUpTo":"2099-12-31"},  
{"code":"DC","paidUpTo":"2099-12-31"},  
{"code":"RM","paidUpTo":"2099-12-31"},  
{"code":"CL","paidUpTo":"2099-12-31"},  
{"code":"PC","paidUpTo":"2099-12-31"}  
],  
"hash":"2911276/0",  
"gracePeriodDays":7,  
"autoProlongated":false}  
```

激活完成  

### 3. 快捷键设置  

进入快捷键设置: file -- settings -- Keymap  

**在删除原来的快捷键之后,强制新增，如果有冲突,会移除冲突的快捷键**  

代码提示: 搜索"show intention actions" -- 删除原来的快捷键,添加 `Alt` + `/`    

撤销: 搜索 `Undo` --- 删除原来的快捷键,新增 `Ctrl` + `Z`  

恢复: 搜索 `Redo` --- 删除原来的快捷键，新增 `Ctrl` + `Y`  

删除一行: 搜索 `Delete Line` --- 删除原来的快捷键,新增 `Ctrl` + `D`  

关闭当前编辑窗口: 搜索 `Close` --- 删除原来的快捷键,新增 `Ctrl` + `W`  

**其他常用快捷键:**  

打开设置(`settings`): `Ctrl` + `Alt` + `S`  

上/下移动代码: 选中代码,然后按 `Atl` + `Shift` + `Up/Down`  

窗口切换: `Ctrl` + `Tab`  

`try-catch`: 选中代码,然后按 `Ctrl` + `Alt` + `T`  

`setter && getter` : `Alt` + `Ins(ert)`,该快捷键也可用于新建对象  

### 4. 其他设置  

- 设置主题  

  `settings`  ---  `Appearance & Behaiver`  --- `Appearance`  --- `Theme` 选项 (`Darcula`: 酷黑色,`Intellij`: 白色)  

- 设置创建 `class` 等文件的头部提示信息  

  `settings`  ---  `Editor`  --- `File and Code Templates` --- 选择 `class` / `interface` / `Enum` 等文件  

  效果: 

  ```java
  /**
   * @Description: ${DESCRIPTION}
   * @Author: junqiang.lu
   * @Date: ${DATE}
   */
  public class ${NAME} {
  }
  ```

- 关闭自动更新

  `settings`  ---  `Appearance & Behavior`  ---  `System Settings`  ---  `Updates`  --- 取消 `Automatically check updates for ...`  选项  ---  `Apply`  

- 设置本地 `maven`  

  `Settings`  ---  `Build,Execution,Development`  ---  `Build Tools`  ---  `Maven`  ---   
  `Maven home directory` : 选择本地 `Maven` 的地址  
  (eg: `D:/develop/software/apache-maven-3.5.3`)  
  `Use settings file`: 选择本地 `Maven` 的设置文件`settings.xml`,同时勾选后边的 `Override`  
  (eg: `D:\develop\software\apache-maven-3.5.3\conf\settings.xml`)  

- 代码提示不区分大小写  

  1. `Settings`  ---  `Editor`  ---  `Code Completion`  ---  `Case sensitive completion`  ---  选择 `NONE`  
  2. `Settings`  --- 搜索 `sensitive`  ---  `Case sensitive completion` 选择 `NONE`  

- 自动导包  

  `Settings`  ---  `Editor`  ---  `General`  ---  `Auto Import`  -- 选中 `Add unambiguous implorts to fly` 和  

  `Optimize imports on the fly(for current project)`  

- 鼠标放在方法上边显示具体参数  

  `Settings`  ---  `Editor`  ---  `General`  ---  `Other(Show quick documentation on mouse move )` 选中即可，时间可以设置为 `100ms`  

- Java 类实现 `Serializable` 接口，生成 `seriaVersionUID`  

  `Setting` --- 搜索 `Serializable`  ---  在展开的 `Serialization issues` 下拉列表下边选择 `Serializable class without 'seriaVersionUID'` ，勾选即可  

- 添加 `Jrebel`  插件  

  `Settings` ---  `Plugins`  --- 选择 `Browse repositories` ， 从应用市场中安装 --- 在弹出的窗口中搜索 `Jrebel`  

  `Jrebel` 个人免费激活码网站: [https://my.jrebel.com/](https://my.jrebel.com/ "https://my.jrebel.com/")  

  (用Facebook账号登录这个网站，就能获得一个免费的激活码,**需要饭蔷**)  

- 设置 `Tomcat` 热部署  

  关键点: 在 `Tomcat` 设置界面,`On 'Update' action` 和 `On frame deaction` 选项选择 `Update classes and resources` ,然后使用 `Debug 模式启动`(如果有`Jrebel` 插件,使用 `Jrebel`的 `Debug` 模式)  



### 5 插件安装  

#### 5.1 Jrebel  

jrebel 是一款 java 热部署插件,功能强大,开发必备,唯一不好，收费太贵  

- 安装:  

File --- Settings --- Plugins --- Browse respositories --- 搜索「Jrebel」 --- 选择「Jrebel for IntelliJ」  

点击「install」 进行安装(安装之后需要对 intellij 进行重启)  

- 破解:  

(1)在安装之后,设置中会有 「jrebel」选项, File --- Settings --- Jrebel --- 点击「change license」 --- 出现激活面板  

(2)下载激活工具(反代理工具),根据不同的系统，下载对应的版本,windows 系统下载 `ReverseProxy_windows_386.exe`  

或者 `ReverseProxy_windows_amd64.exe`  

下载地址: [https://github.com/ilanyu/ReverseProxy/releases](https://github.com/ilanyu/ReverseProxy/releases)  

(3)打开反代理工具,直接双击下载的 `.exe` 文件,打开之后**窗口不要关闭**，否则无法进行激活  

(4)生成一个 `GUID`  

在线生成 `GUID` 地址:   

[https://www.guidgen.com/](https://www.guidgen.com/)  

[https://1024tools.com/uuid](https://1024tools.com/uuid)  

(5)激活,在 Jrebel 激活面板下边选中 「Co在nnect to License Server」  

第一行填入本地反代理地址: [http://127.0.0.1:8888/24d0606a-606b-48c2-b39d-83ed43b3463f](http://127.0.0.1:8888/24d0606a-606b-48c2-b39d-83ed43b3463f)  

第二行填入邮箱(符合邮箱规则即可,可以随意写)  

关于地址和端口,都是可以修改的,在反代理工具的 Github 中都有说明，以上为在本机进行反代理设置,也可以在搭建本地服务器用于激活    

填好之后点击「chenge license」(这个时候,反代理工具窗口会有一些激活信息输出)  

此时, Jrebel 已经被激活,但是 **关闭反代理窗口,则激活失效**  

因此，**在激活之后，在 Jrebel 的选项面板选择 `Work offline` ，开启离线模式之后,可以有 180天(半年)的有效时间，半年之后可以用同样的方法再进行激活**  

在开启离线模式之后,可以关闭反代理工具的窗口了，关闭之后 Jrebel 仍然可以使用(如果是通过本地内网服务器进行激活，则不需要开启离线模式，只要服务器开着就可以了)  

至此,破解工作结束  

破解教程(原创作者):  

[撸了个反代工具, 可用于激活JRebel](http://blog.lanyus.com/archives/317.html)  

[JRebel 2018.1使用反代失败解决](http://blog.lanyus.com/archives/337.html)  

