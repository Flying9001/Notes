## 与 Windows 的斗智斗勇   

### 1. [电脑缺少msvcr120.dll文件 怎么弄](https://blog.csdn.net/zhunianguo/article/details/52294377 "https://blog.csdn.net/zhunianguo/article/details/52294377")  

### 2. 系统设置  

#### 2.1 关闭系统更新服务

2.1.1 Win + R --- 输入 `services.msc`进入「服务」设置 --- 在服务列表中找到 `Windows update` 服务,右键「属性」,在「常规」菜单选项下,选择「停止」服务，并设置为「手动」/「禁用」

不关闭窗口，选择「恢复」菜单项，在「第一次失败」选项选择「无操作」  

2.1.2 鼠标右键任务栏->任务管理器->服务(在上边)->服务(右下角)->名称->Windows Update->停止->属性->禁用  
    
2.1.3 命令行关闭：cmd->sc config "wuauserv" start=disable   //将windows update状态设置为禁用  
   sc stop wuauserv    //关闭windows update服务     

2.1.4 Win + R --- 输入 `gpedit.msc` 进入策略组，左侧菜单树选择「本地计算机策略」-> 「计算机配置」-> 「管理模板」 -> 「Windows 组件」 -> 右侧窗口选择并双击「Windows 更新」-> 双击「配置自动更新」-> 选择左上角「已禁用」选项，点击右下角「应用」

2.1.5 右键「我的电脑」，选择「管理」，在「计算机管理（本地）」菜单下的「系统工具」中找到「任务计划程序」，依次点击展开「任务计划程序库」>「Microsoft」> 「Windows」，下拉找到「WindowsUpdate」选项，点击，在右侧框中会出现的定时任务，选中后，右键禁用  

2.1.6 借助第三方软件「Windows Update Blocker」  

软件下载地址: [https://www.sordum.org/9470/windows-update-blocker-v1-8/](https://www.sordum.org/9470/windows-update-blocker-v1-8/)  

备用下载地址: [WindowsUpdateBlocker_v1.8.zip](https://mega.nz/file/uWxXHZLB#ZkJXoEeLSAJoIz8qTTBxkM79vfGigzPpidSHllUcKjM)  

#### 2.2 卸载无关应用

  6.2.1 Win + R --- 输入 `control`进入「控制面板」  --- 选择「卸载程序」  --- 卸载无关紧要的程序  
    
  6.2.2 关于 win10: 点击「开始」菜单 --- 进入「设置」 --- 选择「应用」 --- 卸载无关紧要的应用程序  

#### 2.3 设置休眠文件大小

(可以节省C盘空间，位置C:\Hiberfil.sys,默认隐藏)  
`cmd-> powercfg -h -size 70` (70为休眠文件所占电脑内存的百分比)    

#### 2.4 关闭「开始」菜单广告(win 10)  

点击「开始」菜单 ----  设置 ---- 个性化  ---  开始  --- 关闭「偶尔在"开始"屏幕中显示建议」  

### 3 win 10 自动重启  

#### 3.1 关闭 windows 更新  

参考 2.1  

#### 3.2 设置启动与系统故障  

右键「我的电脑」--- 点击「高级系统设置」--- 在「启动与系统故障」这一栏点击「设置」--- 

取消「自动重新启动」  

#### 3.3 关闭 Windows 自动更新组策略    

点击「开始」菜单 --- 输入搜索关键词 "gpedit.msc" --- 选择「编辑组策略」--- 依次选择「本地计算机 策略」--- 「管理模板」--- 「windows 组件」--- 「windows 更新」--- 「配置自动更新」 --- 右键「编辑」 --- 设置为 「禁用」  

