#!/bin/bash
if [ -e /Users/aditya.nehra/apache-tomcat-8.5.31/webapps/restlearn.war ]
then
    rm /Users/aditya.nehra/apache-tomcat-8.5.31/webapps/restlearn.war
else
    echo "war doesn't exist"
fi

cp /Users/aditya.nehra/SampleProjects/restlearn/target/restlearn.war /Users/aditya.nehra/apache-tomcat-8.5.31/webapps/restlearn.war
