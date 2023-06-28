all: run

clean:
	rm -f out/Bluck.jar out/ClosestPair.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java src/Node.java src/Point2D.java
	@javac -cp out/parcs.jar src/Bluck.java src/Node.java src/Point2D.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src Node.class -C src Point2D.class
	@rm -f src/Bluck.class src/Node.class src/Point2D.class

out/ClosestPair.jar: out/parcs.jar src/ClosestPair.java src/Node.java src/Point2D.java
	@javac -cp out/parcs.jar src/ClosestPair.java src/Node.java src/Point2D.java
	@jar cf out/ClosestPair.jar -C src ClosestPair.class -C src Node.class -C src Point2D.class
	@rm -f src/ClosestPair.class src/Node.class src/Point2D.class

build: out/Bluck.jar out/ClosestPair.jar

run: out/Bluck.jar out/ClosestPair.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck
