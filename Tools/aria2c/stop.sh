#!/bin/sh

# 停止 aria2c 程序
# 流程: 从进程 id 文件中读取 aria2c 进程 id,停止对应的进程


# aria2c 配置文件路径(进程 id 文件保存路径)
aria2cCfgPath="/root/.aria2c/aria2c/"
# 保存 aria2c 进程 id 的文件名
aria2cPidFile="aria2c_pid_file.txt"

# 判断进程 id 保存文件是否存在
if [ -f "${aria2cCfgPath}${aria2cPidFile}" ]; then
    # 杀死 aria2c 进程
    kill -9 `cat ${aria2cCfgPath}${aria2cPidFile}`
    # 删除 aria2c 进程 id 文件
    rm -rf "${aria2cCfgPath}${aria2cPidFile}"
    # 输出 aria2c 关闭提示
    echo "stopping aria2c ......"
else 
    # 输出文件不存在提示
    echo "aria2c 进程文件不存在"
fi

