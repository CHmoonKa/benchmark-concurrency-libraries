#! /bin/bash

BMJar=$1
Type=$2
Scenario=$3
Round=$4

# args count
AC=$#

if [ $AC -lt 4 ];then
    echo "----------------------------"
    echo "Usage:" 
    echo "      ./bm.sh bm.jar type scenario round core"
    echo ""
    echo "bm.jar:"
    echo "     the path of benchmark jar file"
    echo "type:" 
    echo "    1: Disruptor"
    echo "    2: Akka"
    echo "    3: Fork/Join"
    echo "    4: Executors"
    echo ""
    echo "scenario:"
    echo "    1: bm_1k_iterator_100k_Job"
    echo "    2: bm_100_iterator_1Million_Job"
    echo ""
    echo "round:"
    echo "    n: The separated test count"
    echo ""
    echo "core:"
    echo "    n: The number of thread,default to system cpu count"
    echo ""
    echo "Example:"
    echo "        ./bm.sh 1 2 100"
    echo "        --Means benchmark disruptor with scenario 2, and separated run 100 times."
    echo "----------------------------"
    exit
fi

echo ""

case $Type in
    1)
        echo "Disruptor"
    ;;
    2)
	echo "Akka"
    ;;
    3)
	echo "JDK-Fork/Join"
    ;;
    4)
	echo "JDK-Executors"
    ;;
esac
echo "----------------------------"

case $Scenario in
    1)
	echo "Test Scenario: bm_1k_iterator_100k_Job"
    ;;
    2)
	echo "Test Scenario: bm_100_iterator_1Million_Job"
    ;;
esac
echo "----------------------------"

for i in `seq 1 $Round`
do
    if [ $AC -eq 5 ]; then
	java -jar $BMJar $Type $Scenario $5
    else
        java -jar $BMJar $Type $Scenario
    fi    
done
