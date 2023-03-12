From openjdk:8-alpine3.9
#作者信息
MAINTAINER lasiji Docker springboot "laosiji@lagou,com"
RUN apk update
RUN apk --update add curl bash tzdata && rm -rf /var/cache/apk/*
# 修改源
RUN echo "https://mirrors.aliyun.com/alpine/latest-stable/main/">/etc/apk/repositories && \
   echo "https://mirrors.aliyun.com/alpine/latest-stable/community/">>/etc/apk/repositories
# 安装需要的软件，解决时区问题

# 修改镜像为东八区时间
ENV TZ Asia/Shanghai
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
#docker build --rm -t lagou/jenkinsdemo:v1 --build-arg JAR_FILE=spring-boot-1.0.jar