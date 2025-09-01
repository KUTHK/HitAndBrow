# Javaコンパイラとフラグ
JAVAC = javac
JAVA = java
SRC_DIR = ../src
BIN_DIR = ../bin

# ソースファイルとターゲット
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# デフォルトターゲット
all:
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -cp $(BIN_DIR) $(SOURCES)


# コンパイルルール
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $<

# 実行ターゲット
run: all
	$(JAVA) -cp $(BIN_DIR) Main

# クリーンアップ
clean:
	rm -rf $(BIN_DIR)
