#!/bin/sh

# 启动 aria2c 服务
# 若 aria2c 服务未运行,则直接启动
# 若已经存在 aria2c 服务运行,则重启服务
# 服务启动后会将 aria2c 进程 id(pid) 保存至特定文件
# 以备停止 aria2c 服务时使用


# aria2c 配置文件路径
aria2cCfgPath="/root/.aria2c/aria2c/"
# aria2c 配置文件名称
aria2cCfgFile="aria2c.conf"
# aria2c 启动脚本名称
scriptName="aria2c_start.sh"
# 保存 aria2c 进程 id 的文件名
aria2cPidFile="aria2c_pid_file.txt"



# 判断 aria2c 进程 id 文件保存路径是否存在
if [ ! -x "${Pria2cCfgath}" ]; then
    mkdir -p "${aria2cCfgPath}"
fi

# 判断 aria2c 进程 id 文件是否存在
if [ ! -f "${aria2cCfgPath}${aria2cPidFile}" ]; then
    rm -rf "${aria2cCfgPath}${aria2cPidFile}"
fi

# 判断 aria2c 程序是否运行
count=$(ps -C aria2c --no-header | wc -l)
if [ ${count} -gt 0 ]; then
    echo "已经存在 ${count} 个 aria2c 程序在运行"
    # 获取正在运行的 aria2c 程序进程 id(排除 grep 本身、awk 命令以及脚本本身)
    aria2cPid=$(ps x | grep aria2c | grep -v grep | grep -v '${scriptName}' | awk '{print $1}')
    # 停止正在运行 aria2c 进程 
    kill -9 ${aria2cPid}
    output=`echo "正在关闭 aria2c 程序,进程id: ${aria2cPid}"`
    echo ${output}   
fi

# 后台启动 aria2c 服务,并将其进程 id 保存至文件
nohup aria2c --conf-path=${aria2cCfgPath}${aria2cCfgFile} >/dev/null 2>&1 & echo $! > ${aria2cCfgPath}${aria2cPidFile}
echo "aria2c starting ......"



