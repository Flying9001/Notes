## git 本地仓库关联远程仓库以及分支冲突处理  

- 本地仓库初始化  
 `git init`  
    
- 本地仓库关联远程仓库  
  `git remote add origin remote_git_url` 
  eg: `git remote add origin git@github.com:Flying9001/GitTest.git`  
    
- 将本地分支提交到远程分支  
  `git push -u origin remote_branch_name`  
  eg: `git push -u origin master` 将本地的 `master` 分支同步到远程仓库的 `master` 
  分支(前提是在当前本地的 `master` 分支上边操作)
    
- 本地有提交历史，在同步到远程仓库时会抛出以下异常  
<pre><code>
To git.XXXXX.git
 ! [rejected]        HEAD -> develop (non-fast-forward)
error: failed to push some refs to 'git@git.XXX/wyczFileSave.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
</code></pre>
    
  解决办法: 
<pre><code>
  git pull --rebase origin master  
  git push -u origin master  
</code></pre>  
    
- 在本地分支与远程分支同步后出现分支冲突  
  eg: 当执行 `git pull --rebase master` 命令后出现 分支冲突（是因为远程分支和本地分支对  
    于同一份文件修改不一致导致）此时，需要手动解决分支冲突，在命令中会有提示什么文件  
    不能自动合并  
  抛出异常:  
<pre><code>
(error: Failed to merge in the changes.
Using index info to reconstruct a base tree...
Falling back to patching base and 3-way merge...
Auto-merging README.md
CONFLICT (add/add): Merge conflict in README.md
) 
</code></pre>  
    
  手动处理冲突文件之后，需要执行命令: `git add .`   
  然后执行命令: `git rebase --continue`  
  
  ok，问题解决  

- git 本地仓库删除与远程仓库的关联  
  `git remote remove origin `  
    
  
  
  
         





