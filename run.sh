#!/bin/bash

rm -rf bin 

mkdir bin 

javac -d bin -sourcepath src -cp lib/*:src $(find src -name *.java) 

java -cp lib/*:bin  gym $@

#read var 
