
JavaFxDemo

-----------------------------------------------------------
Descriptions:

A demo maven project about JavaFx.

-----------------------------------------------------------
How to use it:

This is a maven project. To use it, you will need to install
maven. Once you have maven installed, navigate to the 
"..\JavaFxDemo\my-app" directory. Set the class that you want 
to execute in the javafx plugin in pom.xml:
"
    <plugin>
		<groupId>org.openjfx</groupId>
		<artifactId>javafx-maven-plugin</artifactId>
		<version>0.0.3</version>
		<configuration>
			<mainClass>[---modify here---]</mainClass>
		</configuration>
	</plugin>
"
and then execute the following commands in your cmd, and it 
should be up and running.

"mvn clean compile"
"mvn javafx:run". 

For classes that contains main method, modify the properties in pom.xml:

"<exec.mainClass>
	com.curtisnewbie.javafxdemo.[---modify here---]
</exec.mainClass>"

and then execute the following command in your cmd.

"mvn clean compile exec:java". 
-----------------------------------------------------------
Author:

Yongjie Zhuang
Github: https://github.com/CurtisNewbie

___________________________________________________________
