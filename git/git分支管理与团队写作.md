## git 分支管理与团队写作  
    
通常,一个开发团队使用git方式为:在远程创建主分支(master),然后每个人在本地创建功能分支  
具体工作流程为:  
    
- 在自己的分支工作  
    `$ git checkout work`  
- 工作  
    `... ...`  
- 提交工作分支修改  
    `$ git commit ...`  
- 回到主分支  
    `$ git checkout master`  
- 获取远程分支最新更新(此时一般不会有冲突)  
    `$ git pull`  
- 回到工作分支  
    `$ git checkout work`  
- 使用 `rebase` 方式合并主分支代码,并手动解决冲突  
    `$ git rebase master`  
- 返回主分支  
    `$ git checkout master`  
- 合并工作分支(此时不会有冲突,因为即使有冲突,刚才也已经手动处理了)  
    `$ git merge work`  
- 提交到远程主分支(master)  
    `$ git push`  
    
    
**采用这种方式的优点: 远程的主干历史永远都是线性的,清楚明了。团队的每一个成员都在本地解决分支冲突,不会  在远程主干上边产生冲突**  


