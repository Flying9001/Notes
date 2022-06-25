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



