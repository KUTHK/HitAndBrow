@echo off
set BIN_DIR=..\bin

:: コンパイル
if not exist %BIN_DIR% mkdir %BIN_DIR%
javac -d %BIN_DIR% -cp %BIN_DIR% *.java
echo コンパイル完了！