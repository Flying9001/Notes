# /bin/sh

# 批量给文件添加后缀
# 适配多种 Linux 发行版(centOS/Ubuntu)


# 文件路径(必选参数)
targetPath=$1
# 文件后缀(可选参数)
sufix=$2

# 判断路径是否存在
if [ ! -x "${targetPath}" ]; then
    echo "「${targetPath}」文件夹不存在"
    exit 0
fi

# 判断后缀名是否为空,如果为空,则默认 .tar
if [ "${sufix}" = "" ]; then 
    sufix=".tar"
fi

for i in `ls ${targetPath}`
do 
mv ${targetPath}${i} ${targetPath}${i}${sufix}
done

echo "批量添加后缀名完成"


