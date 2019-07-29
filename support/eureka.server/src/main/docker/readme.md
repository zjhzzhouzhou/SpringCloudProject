# 构建
## 开发环境
```
docker login -u admin -p *IK\<0okm 192.168.85.9:8010
mvn clean install -P dev  -DskipTests=true dockerfile:build dockerfile:push
# 运行测试
docker run -d -p 17001:17001 -it \
 -v /home/yyzlpt/logs:/home/yyzlpt/logs \
 -e JAVA_OPTS="-server -Xms1024M -Xmx1024M -Xss512K -Dfile.encoding=UTF-8 -XX:NewRatio=1 -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1096M -Djava.awt.headless=true -d64 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=6 -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true" \
 192.168.85.9:8010/docker-dev/eureka-server:1.0.0

```

## 测试环境
```
docker login -u admin -p *IK\<0okm 192.168.85.9:8011
mvn clean install -P test  -DskipTests=true dockerfile:build dockerfile:push

# 运行测试
docker run -d -p 17001:17001 -it \
 -v /home/yyzlpt/logs:/home/yyzlpt/logs \
 -e JAVA_OPTS="-server -Xms1024M -Xmx1024M -Xss512K -Dfile.encoding=UTF-8 -XX:NewRatio=1 -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1096M -Djava.awt.headless=true -d64 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=6 -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true" \
 -e EUREKA_SERVICE_URL="http://admin:1qaz_QAZ@192.168.85.7:17001/eureka-server/eureka/" \
 192.168.85.9:8011/docker-test/eureka-server:1.0.0
```

## 灰度环境
```
docker login -u admin -p *IK\<0okm 192.168.85.9:8011
mvn clean install -P test  -DskipTests=true dockerfile:build dockerfile:push

# 运行测试
docker run -d -p 17001:17001 -it \
 -v /home/yyzlpt/logs:/home/yyzlpt/logs \
 -e JAVA_OPTS="-server -Xms1024M -Xmx1024M -Xss512K -Dfile.encoding=UTF-8 -XX:NewRatio=1 -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1096M -Djava.awt.headless=true -d64 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=6 -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true" \
 -e EUREKA_SERVICE_URL="http://admin:1qaz_QAZ@localhost:17001/eureka-server/eureka/" \
 192.168.85.9:8011/docker-test/eureka-server:1.0.0
```

## 生产环境
```
docker login -u admin -p *IK\<0okm 192.168.85.9:8011
mvn clean install -P test  -DskipTests=true dockerfile:build dockerfile:push

# 运行测试
docker run -d -p 17001:17001 -it \
 -v /home/yyzlpt/logs:/home/yyzlpt/logs \
 -e JAVA_OPTS="-server -Xms1024M -Xmx1024M -Xss512K -Dfile.encoding=UTF-8 -XX:NewRatio=1 -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1096M -Djava.awt.headless=true -d64 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=6 -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true" \
 -e EUREKA_SERVICE_URL="http://admin:1qaz_QAZ@localhost:17001/eureka-server/eureka/" \
 192.168.85.9:8011/docker-test/eureka-server:1.0.0
```
