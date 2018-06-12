## 与 Windows 的斗智斗勇   

### 1. [电脑缺少msvcr120.dll文件 怎么弄](https://blog.csdn.net/zhunianguo/article/details/52294377 "https://blog.csdn.net/zhunianguo/article/details/52294377")  

### 2. 系统设置  

#### 2.1 关闭系统更新服务

2.1.1 Win + R --- 输入 `services.msc`进入「服务」设置 --- 在服务列表中找到 `Windows update` 服务,右键「属性」,进入属性设置,「停止」服务，并设置为「手动」  
    
2.1.2 鼠标右键任务栏->任务管理器->服务(在上边)->服务(右下角)->名称->Windows Update->停止->属性->禁用  
    
2.1.3 命令行关闭：cmd->sc config "wuauserv" start=disable   //将windows update状态设置为禁用  
   sc stop wuauserv    //关闭windows update服务     

#### 2.2 卸载无关应用

  6.2.1 Win + R --- 输入 `control`进入「控制面板」  --- 选择「卸载程序」  --- 卸载无关紧要的程序  
    
  6.2.2 关于 win10: 点击「开始」菜单 --- 进入「设置」 --- 选择「应用」 --- 卸载无关紧要的应用程序  

#### 2.3 设置休眠文件大小

(可以节省C盘空间，位置C:\Hiberfil.sys,默认隐藏)  
`cmd-> powercfg -h -size 70` (70为休眠文件所占电脑内存的百分比)    

#### 2.4 关闭「开始」菜单广告(win 10)  

点击「开始」菜单 ----  设置 ---- 个性化  ---  开始  --- 关闭「偶尔在"开始"屏幕中显示建议」  

