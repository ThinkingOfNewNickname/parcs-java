all: run

clean:
	rm -f out/Bluck.jar out/ClosestPair.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java
	@javac -cp out/parcs.jar src/Bluck.java
	@jar cf out/Bluck.jar -C src Bluck.class
	@rm -f src/Bluck.class

out/ClosestPair.jar: out/parcs.jar src/ClosestPair.java
	@javac -cp out/parcs.jar src/ClosestPair.java
	@jar cf out/ClosestPair.jar -C src ClosestPair.class
	@rm -f src/ClosestPair.class

build: out/Bluck.jar out/ClosestPair.jar

run: out/Bluck.jar out/ClosestPair.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck
