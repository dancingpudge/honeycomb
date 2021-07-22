# 启动

* 1、通过maven打包命令一键打包
* 2、运行通过shell脚本运行(projectName需要替换成你项目打包后的名称)通过该脚本配合JK可以实现一键发布，为后期DOCKER部署做铺垫。

###### 功能点：

    A、根据应用包名杀掉现有应用（kill 30次，还是杀不掉则kill -9） 
    B、备份老包 
    C、执行新包

注意：jvm配置需要根据虚拟机实际情况来调整，现有的只提供非压测开发、测试环境使用

````Shell
   #!/bin/sh
     cd `dirname $0`
     source  /etc/profile
     source  /etc/bashrc
     projectName=honeycomb-raven-svc-0.0.1-SNAPSHOT.jar
     echo "stopping old project $projectName application...."
     n=0
     while true;do
         pid=`ps -ef | grep $projectName | grep -v grep | awk '{print $2}'`
         if [ pid"$pid" == pid ];then
             break
         fi
         if [ $n -ge 30 ];then
             echo "kill -9 的pid:"+$pid
             kill -9 $pid
       sleep 1
       break
         fi
         echo "kill  的pid:"+$pid
         kill  $pid
         sleep 1
         n=$((n+1))
     done
  
     echo "backup old $projectName by time"
     dateTime=$(date +%Y%m%d-%H%M%S)
     mkdir -p backup/$dateTime && cp *.jar backup/$dateTime
     echo "remove old $projectName ..."
     rm *.jar
     cp  jar/*.jar ./
     sleep 2s
     echo "running $projectName...."
     java -Xmx128m -Xms128m -Xss256k -jar $projectName> /dev/null 2>&1 &
     echo "running $projectName finish"
````
