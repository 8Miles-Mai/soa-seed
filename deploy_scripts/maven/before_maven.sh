#!/bin/sh
# vars definition
project_name="soa"
tomcat_path="/app/deploy/tomcat_sb_soa"
replace_string="{soa.sys.build.time}"
replace_with=$(date +"%Y-%m-%d %H:%M:%S")

# 打上war包的打包时间，可以用于判断各个服务器的版本是否一致
sed -i "s/${replace_string}/${replace_with}/g" `grep "${replace_string}" -rl ./src/main/resources/*`