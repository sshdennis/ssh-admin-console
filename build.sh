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
clear

echo "[build] Start Building Project..."
startTime=`date +%s`

# Check failures
function checkFailure(){
	if [ "$?" != "0" ]; then
	    echo
        echo "[build] Build finished with error(s)";
        echo
        exit -1;
	fi;
}

#################################################################################
# Clean up
#################################################################################

echo "[build] Start Cleaning Output Folder..."
mvn clean
checkFailure
cd $APP_HOME
rm -Rf output
echo "[build] Finished Cleaning Output Folder"


#################################################################################
# Build Core
#################################################################################

cd $APP_HOME
echo "[build] Start Building Packages..."
mvn package
checkFailure
echo
echo "[build] Finished Building Packages"

endTime=`date +%s`
elapsedTime=`expr $endTime - $startTime`

echo "[build] Finished Building. Time Elapsed: $elapsedTime seconds"
echo
