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

  `Settings`  ---  `Editor`  ---  `Auto Import`  -- 选中 `Add unambiguous implorts to fly` 和  

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

