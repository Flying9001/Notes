## 自制 SSL 域名证书  



#### 1 脚本  

```
./script/create_cert.sh
```

```sh
# !/bin/sh
# 创建自制ssl证书

# 证书过期时间
expireDate=366
# 国家代码
country=CN
# 省份/州
province=Guangdong
# 城市
city=Shenzhen
# 组织/公司
organizetion=DemaxiyaTechnology
# 部门
organizetionUnit=Develop
# 域名 
domain=xxooxxx.com


# 生成证书私钥 
openssl genrsa -out root.key 2048

# 生成证书(使用指定配置文件)  
openssl req -new -x509 -days ${expireDate} -subj /C=${country}/ST=${province}/L=${city}/O=${organizetion}/OU=${organizetionUnit}/CN=${domain} -key root.key -out root.crt

# 将 CRT 转换为 PEM 格式
openssl x509 -in root.crt -out root.pem

# 删掉 CRT 格式证书
rm -rf root.crt

# 输出证书信息
echo "证书内容:"
content=`openssl x509 -in root.pem -inform pem -text -noout`
echo "$content"
```

可根据需要修改脚本内容  

#### 2 使用方法  

将脚本复制到 Linux 系统中  

执行脚本  

```sh
sh ./create_cert.sh
```

脚本执行完成后会在同目录下生成 `root.key` 和 `root.pem` 文件，其中 `root.key` 为私钥，`root.pem` 为证书  

