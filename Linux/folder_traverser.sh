#! /bin/bash 

## 目录遍历工具,同时过滤小于一定尺寸的文件
## 支持包含空格的文件目录  
## 可根据需要进行改装

## 目标文件目录
fileDir=/Users/ljq/Downloads/
## 文件最小尺寸(单位:kb)  
minSize=100
## (符合筛选条件的)文件总数
countFile=0
## 临时文件目录
tmpFilePath=

function readAndSelect() {
	for file in `ls "$*" | tr " " "\?"`       
    do
        file=`tr "\?" " " <<<${file}`
        tmpFilePath=$1/${file}
        if [ -d "${tmpFilePath}" ]; then
            readAndSelect "${tmpFilePath}"
        else
            fileSize=`wc -c "${tmpFilePath}" | awk '{print $1}'`
            fileSize=$[${fileSize}/1024]
            ## lt 小于,gt 大于
            if [ ${fileSize} -lt ${minSize} ]; then
                ## todo something
                
                ## eg: echo ${tmpFilePath}
                ## rm -rf "${tmpFilePath}"
            else
                ## todo something

                echo ${tmpFilePath}
                countFile=$[${countFile}+1]
            fi    
        fi
    done
}

readAndSelect ${fileDir}

echo ${countFile}
