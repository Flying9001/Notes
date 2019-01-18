#!/bin/bash

# 更新 aria2c 配置中 bt 加速服务器列表
# 如果 aria2c 配置文件中不包含 bt 服务器配置,则向其中添加 bt 加速服务器配置 
# bt 加速服务器列表维护地址: https://github.com/ngosang/trackerslist

# 下载地址
trackerUrl="https://raw.githubusercontent.com/ngosang/trackerslist/master/trackers_all.txt"
# 下载文件保存路径
downPath="/var/www/html/down/"
# tracker 下载文件名
trackerFile="trackers_all.txt"
# aria2c 配置文件路径
aria2cCfgPath="/root/.aria2/"
# aria2c 配置文件名称
aria2cCfgFile="aria2.conf"

# 这里的-x 参数判断${downPath}是否存在并且是否具有可执行权限 
if [ ! -x "${downPath}" ]; then 
  mkdir -p "${downPath}" 
fi 
 
# 这里的-f参数判断${trackerFile}是否存在 
if [ -f "${downPath}${trackerFile}" ]; then 
  rm -rf  "${downPath}${trackerFile}" 
fi 

# 开始下载
/usr/bin/wget -O ${downPath}${trackerFile} ${trackerUrl}

output=`echo "${downPath}${trackerFile} downloaded !!!"`
echo ${output}

# bt 服务器列表文件处理

# 删除文本空白行
sed -i '/^\s*$/d' ${downPath}${trackerFile}
# 在每行结尾添加 ','
sed -i 's/$/&,/g' ${downPath}${trackerFile}
# 删除最后一行的 ','
sed -i '$s/,//g' ${downPath}${trackerFile}
# 在第一行开头添加 'bt-tracker=' 字符(添加之后添加的内容会成为新的第一行)
sed -i '1i bt-tracker=' ${downPath}${trackerFile}
#  删除换行,将所有文档整合到一行
sed -i ':a;N;$!ba;s/\n//g' ${downPath}${trackerFile}
# 读取 aria2c 配置文件中 bt 服务器列表属性所在行
btTrackerLine=`sed -n '/bt-tracker=/=' ${aria2cCfgPath}${aria2cCfgFile}`
# 更新 aria2c 配置文件中的 bt 服务器列表
# 如果存在 bt 服务器配置,则更新 bt 服务器配置
# 如果不存在 bt 服务器配置,则在最后一行添加 bt 服务器配置
if [ -n "${btTrackerLine}" ] && [ "${btTrackerLine}" -gt 0 ]; then
    # 删除 aria2c 配置文件中关于 bt 服务器属性的配置
    sed -i "${btTrackerLine} d" ${aria2cCfgPath}${aria2cCfgFile}
    # 更新 bt 服务器
    sed -i "$[btTrackerLine-1] r ${downPath}${trackerFile}" ${aria2cCfgPath}${aria2cCfgFile}
else
    # 获取文本最后一行行号
    lastLine=`awk '{print NR}' "${aria2cCfgPath}${aria2cCfgFile}"|tail -n1`
    # 在最后一行插入 bt 服务器配置
    sed -i "${lastLine} r ${downPath}${trackerFile}" ${aria2cCfgPath}${aria2cCfgFile}
fi











