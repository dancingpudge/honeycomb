# honeycomb-hoenycomb-dependencies
最上层springboot ，springcloud，spring-cloud-alibaba,hoenycomb（公司内部各组件）, mybatis，msyql 等等第三方的jar包的版本管理pom，
给公司提供统一的Springboot体系，防止由于spring的版本不一致而导致项目各种冲突

# 版本管理
1、SNAPSHOT与RELEASE

    新版本开发时确定定版本号，x.x.x.SNAPSHOT，移交时，版本号不变从SNAPSHOT更换为RELEASE

2、各服务版本号（有修改才变更）

    各服务在新版本开发时，更换项目本身的版本号为所依赖的hoenycomb-parent的版本号。

3、同步修改honeycomb-hoenycomb-dependencies中项目的版本号