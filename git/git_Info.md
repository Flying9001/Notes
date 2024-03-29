## Git 入门教程

### 1 配置Git
#### 1.1创建与删除用户

```
	创建全局用户：
	git config --global user.name "yourname"
	git config --global user.email "example@email.com"
	删除全局用户: 
	git config --unset --global user.name
	git config --unset --global user.email
	创建本地用户(一般用于多用户提交/不同的用户向不同的库提交):
	git config user.name "yourname"
	git config user.email "example@email.com"
```
#### 1.2创建版本库
```
	cd e: /*表明将要在e盘创建*/
	cd git	/*打开E盘下的文件夹git*/
	mkdir testgit	/*在git下边创建文件夹testgit*/
	pwd //显示当前目录
```
#### 1.3初始化git
```
(初始化之后这个目录将会变成git可以管理仓库)

git init //生成的.git(系统默认隐藏)是Git用来追踪还礼版本的，勿动
```
### 2 文件管理

#### 2.1添加文件
```
	前提：在版本库testgit目录下创建一个记事本test.txt文件,内容为11111
	git add test.txt	//将test.txt文件添加到缓存区
	git commit -m "添加test.txt文件"	//提交操作，-m:对操作进行注释说明
```
#### 2.2查看文件
```
	git status //检查文件是否提交
	cat test.txt	//查看test.txt文件中的内容
	git diff test.txt	//查看test.txt文件中修改的内容(没有保存到暂存区的操作)
```
#### 2.3查看日志
```
	git log 	//每一次操作以一个结果显示
	git log --pretty=oneline 	//所用操作在一个结果显示,每次操作占一行
	设置日志格式与别名: 
	git config --global alias.lg "log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%
d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative"
```
#### 2.4版本回退
```
	git reset --hard HEAD^		//会退到上一个版本
	git reset --hard HEAD^^		//回退到上上一个版本
	git reset --hard HEAD~100	//回退到前100个版本
```
#### 2.5撤销回退
```
	git reflog		//查询所有版本(包含已经回退的版本),每个版本前边都是版本的编号
	git reset --hard 1234asd 	//恢复到版本号为1234asd的版本(当前版本已经撤销)
```
#### 2.6文件的撤销
```
	git checkout -- test.txt	//撤销没有保存到暂存区的所有操作,已经保存到暂存区的操作将会被保留,即使还没有提交
```
#### 2.7删除文件
```
	rm test.txt 	//删除test.txt文件
	git checkout -- test.txt 	//恢复已经删除的test.txt文件(注意2.6/2.7区别)
```
### 3 远程仓库

#### 3.1管理GitHub仓库
```
	先注册一个GitHub账号
	ssh-keygen -t ed25519 -C "youremail@example.com" //创建SSH Key
	//id_ed25519 私钥，不要泄露(windows路径: C:\Users\username\.ssh)
	//id_ed25519.pub 公钥
	git remote add origin url	//将本地仓库与远程仓库关联
	git remote rm origin  // 删除远程仓库关联  
```
#### 3.2推送push
```
	git push -u origin master	//将本地仓库分支master推送到远程仓库(第一次推送使用)
	git push origin master 	//将本地仓库分支master推送到远程仓库(创建之后使用,如果远程仓库没有该分支,则创建新的)
```
#### 3.3克隆远程库
```
git clone url	//将远程库克隆到本地
git clone -b branch-name url  // 将远程库克隆到本地并指定分支  
```
#### 3.4创建与合并分支
```
	git checkout dev 	//查看dev分支
	git checkout -b dev	//创建并切换到dev分支
	git checkout -b origin/dev 	//在远程库上创建dev分支
	git checkout -b dev origin/dev //语法错误,不能同时在本地和远程库上同时创建分支
	git branch		//查看所有分支,当前的分支前边会有*
	git branch dev 	//创建一个dev分支(不会重复创建已经存在的分支)
	git merge dev 	//将dev合并到当前分支
	git branch -d dev 	//删除dev分支(如果当前分支就是dev则不能删除,分支没有合并，如果要删除的话将"-d"换成"-D")
	//error: you need to resolve your current index first意思是合并分支有问题
	//解决办法:直接会退到分支之前git reset --merge
	//在合并分支之后需要再一次将合并的代码添加到git，并提交
	//dev分支合并到master分支,则master分支上的代码为合并之后的代码，而dev分支上的代码不变
	git merge --no-ff dev 	//将dev合并到当前分支，并且分支dev删除之后，分支上边修改的信息
	//不变(这一种情况需要重新添加代码)
```
#### 3.5分支策略
```
	git stash //隐藏当前分支
	git stash list 	//查看所有隐藏分支
	git stash apply		//恢复隐藏
	git stash drop 	//恢复隐藏,并同时把隐藏的内容删除(一次只能删除一条)
	
	关于分支的切换: 当一个分支创建之后,假设为 dev 分支,那么在 dev 分支提交之前,从 dev 分支切换到其他分支(master),
	则 dev 分支上边没有提交的代码也会被带到新的分支 master, 不论原来的 dev 分支上边的修改代码是否添加到缓存区
	(git add file-changed),解决办法,在切换分支之前先执行 git stash 命令,切换回来之后再执行 git stash apply 命令
```
#### 3.6查看远程库
```
	git remote 	//查看远程库的信息
	git remote -v 	//查看远程库的详细信息
```
#### 3.7多人协作
```
	首先，可以试图用git push origin branch-name推送自己的修改.
	如果推送失败，则因为远程分支比你的本地更新早，需要先用git pull试图合并。
	如果合并有冲突，则需要解决冲突，并在本地提交。再用git push origin branch-name推送
```
#### 3.8 将本地分支与远程分支关联
```
    git pull origin branch-name // 将本地当前分支与远程分支匹配
```
#### 3.9 取消本地仓库与远程仓库的关联
```
    git remote remove origin 
```
### 4 Git常用指令
```
	mkdir test	//创建一个test目录
	pwd 	//显示当前的路径
	git init 	//把单签的目录变成一个可以管理的git仓库(会生成隐藏.git文件)
	git add file	//把file文件添加到缓存区
	git commit -m "这是注释"	//提交文件 , -m 后边的是操作注释
	git status 	//查看仓库状态(会显示修改二没有添加到暂存区的文件)
	git diff file 	//查看file文件修改了那些内容
	git log 	//查看操作记录(commit)
	git log --pretty=oneline 	//查看操作记录(每一个操作在一行显示)
	git reset -hard HEAD^或者git reset -hard HEAD~ 	//会退到上一个版本
	git reset -hard HEAD~100 	//会退到前100个版本
	cat file 	//查看file文件的内容
	git reflog 	//查看历史记录的版本号
	git checkout --file 	//撤销没有保存到暂存区的所有操作
	git rm file 	//删除file文件
	git remote  add origin url 	//关联一个远程库(url为远程库的地址)
	gti push -u origin master //把当前分支推送到远程master分支上(仅第一次推送使用需要加 -u)
	git clone url 	//从远程库中克隆(url为远程库的地址)
	git checkout -b dev 	//创建dev分支并切换到dev分支上
	git checkout dev 	//切换到dev分支
	git branch dev 	//创建dev分支(如果dev分支已经存在，则不能创建)
	git branch 	//查看所有分支(当前分支前边有*)
	git merge dev 	//把dev分支合并到当前分支
	git merge --no-ff dev //把dev分支合并到当前分支(并且分支dev删除之后，分支上边修改的信息)
	//不变(这一种情况需要重新添加代码)
	git branch -d dev 	//删除dev分支
	git stash //隐藏当前分支
	git stash list 	//查看所有隐藏文件的列表
	git stash drop	//删除隐藏文件
	git stash apply //恢复隐藏的文件,但是内容不删除
	git stash pop 	//恢复隐藏文件的同时，删除文件
	git remote 	//查看远程库的信息
	git remote -v 	//查看远程库的详细信息
	git pull 	//从远程库中将文件下载下来	
```
### 5 删除本地仓库
```
    find . -name ".git" | xargs rm -Rf
```
### 6 多设备使用同一个 ssh key
```
    备份旧设备上的 ssh key(Windows目录:c/user/.ssh;Linux/Unix目录 ~/.ssh)
    在新设备上生成新的 ssh key --- 将旧设备的key值覆盖新设备的 key值    
```
### 7 git 恢复被修改文件  

```
// 如果文件未被 git add
git checkout fileName

// 如果文件已经被 git add
git reset HEAD fileName
git checkout fileName
```



### 8 git 合并指定提交  

当需要将其他分支上边的某一次或多次提交合并到当前分支时，可以使用以下命令

```bash
git cherry-pick xxx1 xxx2
```

合并单个提交

```bash
git cherry-pick xxx
```

合并多次提交

```bash
git cherry-pick xxx1 xxx2
```

合并连续的多次提交

```bash
git cherry-pick xxx1^...xxx2
```

在团队协作中，会有众多的分支，然而有些提交是未上线的，而新开发的信息在新的分支上，这是可以不必将整个分支合并到主分支，而只合并新分支上的某一次提交即可。  

参考: [git cherry-pick 教程-阮一峰](https://www.ruanyifeng.com/blog/2020/04/git-cherry-pick.html)  

