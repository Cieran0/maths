build:
	javac -d src src/*.java
	cd src; \
    jar cmvf ../MANIFEST.MF ../Gamerr.jar *.class

run: build
	java -jar Gamerr.jar
