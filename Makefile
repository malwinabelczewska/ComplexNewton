JAVAC=javac
JAVA=java
CLASSPATH=./lib/commons-numbers-complex-1.1.jar
SOURCE_FILES=$(shell find . -name "*.java")
OUTPUT_DIR=bin
MAIN_CLASS=pl.edu.ug.prog.complexnewton.Main

all: compile

compile:
	$(JAVAC) -cp $(CLASSPATH) $(SOURCE_FILES) -d $(OUTPUT_DIR)

run:
	$(JAVA) -cp $(OUTPUT_DIR):$(CLASSPATH) $(MAIN_CLASS)

clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all compile run clean
