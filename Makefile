all: jeu.jar cls

compile-class:
	javac -classpath jars/json-20220924.jar:src -d classes src/pandemic/*/*.java src/pandemic/*.java

compile-test:
	javac -classpath classes:jars/junit-4.13.2.jar -d classes_test tests/pandemic/*.java tests/pandemic/*/*.java

cls: compile-class compile-test

main: compile-class
	java -cp jars/json-20220924.jar:classes pandemic.Main

test: cls
	java -jar jars/junit-platform-console-standalone-1.9.2.jar -cp classes:classes_test:jars/json-20220924.jar --scan-classpath

doc:
	javadoc -classpath jars/json-20220924.jar:src -d docs -subpackages pandemic

clean:
	rm -rf docs classes classes_test

jeu.jar: cls
	jar cvfm jars/jeu.jar manifest.txt -C classes .


.PHONY: all clean