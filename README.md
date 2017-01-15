# sbweb
Java Spring-Boot Demo Framework

# step
0. init java 8, maven & mysql
0. run test.sql
0. mvn clean package
0. java -jar app/target/sbweb.jar

# 功能点
* mybatis generic dao
* mybatis sql endpoint
* mybatis pointcut catch exception

# TODO
* 去掉双花括号赋值, 匿名内部类赋值的方式对第三发jar的兼容性不高, 例如: gson, mtop
