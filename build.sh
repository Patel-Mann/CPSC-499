#!/bin/bash
set -e
GREEN="\033[32m"
RESET="\033[0m"
echo ""
echo "==== CPSC 449 Project ===="

# Create lib directory
mkdir -p bin

echo "1. Checking dependencies"

if [ ! -f "lib/antlr-4.9.3-complete.jar" ]; then
    echo "   Please Download ANTLR"
fi

if [ ! -f "lib/jgrapht-core-1.5.1.jar" ]; then
    echo "  Please Download JGraphT"
fi

if [ ! -f "lib/jgrapht-io-1.5.1.jar" ]; then
    echo "   Please Download JGraphT IO"
fi

echo "    [x] All dependencies acounted for"

CP="lib/antlr-4.9.3-complete.jar:lib/jgrapht-core-1.5.1.jar:lib/jgrapht-io-1.5.1.jar"

echo "2. Compiling CFG packages"
javac -d bin -cp "$CP" src/org/lsmr/cfg/*.java
echo "    [x] CFG compiled"

echo "3. Compiling PDG packages"
javac -d bin -cp "$CP:bin" src/pdg/*.java
echo "    [x] PDG compiled"

echo ""
echo -e "${GREEN}==== Compilation Complete! ====${RESET}"
echo -e "${GREEN}Compiled classes are in: bin/${RESET}"
echo ""
echo -e "${GREEN}>> To run this program:${RESET}"
echo -e "${GREEN}  java -cp bin:$CP YourMainClass${RESET}"
echo ""
