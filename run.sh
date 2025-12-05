#!/bin/bash

if [ "$#" -lt 1 ]; then
    echo "Usage: ./run.sh <file> [line]"
    echo "Example: ./run.sh examples/Example1.java 3"
    exit 1
fi

# Check for antlr jar
if [ ! -f "lib/antlr-4.13.2-complete.jar" ]; then
    echo "ERROR: Put antlr-4.13.2-complete.jar in lib/"
    exit 1
fi

# Build if needed be
if [ ! -d "bin/pdg" ]; then
    echo "Building..."
    mkdir -p bin
    CP="lib/antlr-4.13.2-complete.jar"
    javac -d bin -cp "$CP" src/org/lsmr/cfg/*.java || exit 1
    javac -d bin -cp "$CP:bin" src/pdg/PDG.java || exit 1
    javac -d bin -cp "$CP:bin" src/CFGBuilder.java src/PDGTool.java || exit 1
    echo "Build complete!"
fi

# Run
java -cp "bin:lib/antlr-4.13.2-complete.jar" PDGTool "$@"
