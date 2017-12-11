使用步骤：<br>
①注释掉mybatis-generator-maven-plugin（mgb子项目pom.xml）部分 <br>
②mgb项目执行install命令（因插件需要依赖mgb项目）   <br>
③更改generatorConfig.xml中的表配置，配置需要生成文件的表  <br>
④打开①步的注释部分，执行mybatis-generator:generate，即可生成相应文件