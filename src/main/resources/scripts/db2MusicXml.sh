#!/bin/bash

JAR_DIR=/home/curtis/.m2/repository/org/curtis/musicxml/1.0

CLASSPATH=${JAR_DIR}/musicxml-1.0-jar-with-dependencies.jar

SCORE_ID=''

while getopts 'i:' flag; do
  case "${flag}" in
    i) SCORE_ID="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.Db2MusicXml SCORE_ID=$SCORE_ID
