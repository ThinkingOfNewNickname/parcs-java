all: run

clean:
	rm -f out/Bluck.jar out/ClosestPair.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java src/Node.java
	@javac -cp out/parcs.jar src/Bluck.java src/Node.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src Node.class
	@rm -f src/Bluck.class src/Node.class

out/ClosestPair.jar: out/parcs.jar src/ClosestPair.java src/Node.java
	@javac -cp out/parcs.jar src/ClosestPair.java src/Node.java
	@jar cf out/ClosestPair.jar -C src ClosestPair.class -C src Node.class
	@rm -f src/ClosestPair.class src/Node.class

build: out/Bluck.jar out/ClosestPair.jar

run: out/Bluck.jar out/ClosestPair.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck
