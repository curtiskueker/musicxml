#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

USERNAME=''
PASSWORD=''
DATABASE=''
SERVER=''
DATABASE_TYPE=''
LILYPOND=''
PDF_READER=''

while getopts 'd:l:p:r:s:t:u:' flag; do
  case "${flag}" in
    d) DATABASE="${OPTARG}" ;;
    l) LILYPOND="${OPTARG}" ;;
    p) PASSWORD="${OPTARG}" ;;
    r) PDF_READER="${OPTARG}" ;;
    s) SERVER="${OPTARG}" ;;
    t) DATABASE_TYPE="${OPTARG}" ;;
    u) USERNAME="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.SetProperties USERNAME=$USERNAME PASSWORD=$PASSWORD DATABASE=$DATABASE SERVER=$SERVER DATABASE_TYPE=$DATABASE_TYPE LILYPOND=$LILYPOND PDF_READER=$PDF_READER
