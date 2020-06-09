default: cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/IGameBoard.java
	javac cpsc2150/connectX/Connect4Game.java 
	javac cpsc2150/connectX/GameBoard.java 
	javac cpsc2150/connectX/GameBoardMem.java 
	javac cpsc2150/connectX/IGameBoard.java

run: cpsc2150/connectX/Connect4Game.class
	java cpsc2150.connectX.Connect4Game

test: cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/TestIGameBoard.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/TestIGameBoard.java


runtest: cpsc2150/connectX/Connect4Game.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/GameBoardMem.java cpsc2150/connectX/TestIGameBoard.java
	java  -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestIGameBoard 

clean: 
	rm cpsc2150/connectX/*.class



