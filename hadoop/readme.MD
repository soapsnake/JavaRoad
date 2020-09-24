# hadoop 任务提交流程
1. 在这个包里面编写一个java程序,例如wordCount
2. mvn clean -U package -Dmaven.test.skip=true
3. hadoop jar hadoop-1.0.0.jar com.soapsnake.hadoop.example.WordCount /user/soapsnake/input /user/soapsnake/output
4. 如果报output dir已存在的异常,在localhost:50070上面吧output dir删除即可
5. 如果在50070上删除文件夹失败,执行: hadoop fs -chmod -R 777 /user/soapsnake

# 没有解决干净的problem: