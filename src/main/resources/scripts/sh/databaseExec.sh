#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

CREATE_DATABASE=''
SCHEMA_FILE=''
TEST_DATABASE=''

while getopts 'cf:t' flag; do
  case "${flag}" in
    c) CREATE_DATABASE="CREATE_DATABASE" ;;
    f) SCHEMA_FILE="${OPTARG}" ;;
    t) TEST_DATABASE="TEST_DATABASE" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.DatabaseExec SCHEMA_FILE=$SCHEMA_FILE $CREATE_DATABASE $TEST_DATABASE
