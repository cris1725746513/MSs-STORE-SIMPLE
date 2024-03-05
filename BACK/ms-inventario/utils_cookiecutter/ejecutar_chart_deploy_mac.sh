#!/bin/bash
REPLACE_VALUE_1=`cat  ../../replace1.txt`
REPLACE_VALUE_2=`cat  ../../replace2.txt`
cat crear_chart_deploy.txt | while read line
do
  echo "sed -i .bak \"s/!!/$REPLACE_VALUE_1/g\" $line" | bash
  echo "sed -i .bak \"s/??/$REPLACE_VALUE_2/g\" $line" | bash
  rm -fr $line".bak"
done
