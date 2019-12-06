#!/bin/bash

REQUIRED_JDK_VERSION="1.8"
mvn -version | grep " $REQUIRED_JDK_VERSION"

JDK_COMPATIBLE="$?"

if [ "$JDK_COMPATIBLE" != "0" ]; then
    echo "Sorry, JDK $REQUIRED_JDK_VERSION is required to build this project"
    exit -1;
fi;

cd `dirname "$0"`;
export APP_HOME=`pwd`;

#################################################################################
# Run
#################################################################################

java -jar $APP_HOME/output/ssh-admin-console-1.0.0-jar-with-dependencies.jar
