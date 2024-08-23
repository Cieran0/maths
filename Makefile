build:
	javac -d src src/*.java
	cd src; \
    jar cmvf ../MANIFEST.MF ../Maths.jar *.class

run: build
	java -jar Maths.jar
