#!/bin/sh
## 用途: 批量解压缩当前目录所有压缩包
## 使用: 将脚本放在全是压缩包的目录下,执行脚本

## 当前目录
currentDir=`pwd`
## 解压目录
subDir=extracts
## 压缩密码
passcode=12345qwert

list=`ls -1`
for var in ${list}
do
    7z x -r -p${passcode} -o${currentDir}/${subDir} ${var} 
done 

