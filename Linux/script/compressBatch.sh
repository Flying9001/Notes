#!/bin/sh
## 用途: 批量压缩当前目录所有文件(夹)
## 使用: 将脚本置于待压缩文件的目录下,设置压缩密码,执行脚本即可

## 当前目录
currentDir=`pwd`
## 生成压缩包的目录
subDir=archives
## 压缩密码
passcode=12345qwert
## 文件名去空格
rename 's/ /_/g' *

list=`ls -1`
for var in ${list}
do
    7z a -r -t7z -p${passcode} -mhe -mx3 ${currentDir}/${subDir}/${var}.7z ${var} 
done 

