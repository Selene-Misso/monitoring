#!/bin/sh

########################
## Get mysql status

HOME=`/bin/echo $HOME`
DIR=$HOME/data
HOST=`/bin/hostname`


SOCKET=/var/run/mysqld/mysqld.sock
COMMAND="/usr/bin/mysqladmin -u root -p3641ec2 extended-status"

if [ -S $SOCKET ];then
  while true; do 
       $COMMAND | /usr/bin/awk '{print $2,$4}' | /bin/egrep -v '(^ |Variable_name)' | \
          while read LABEL DATA
       do
           DATE=`/bin/date "+%Y-%m-%dT%H:%M:%SZ"`
           /bin/echo -e $DATE "\t" $DATA >> $DIR/$HOST.$LABEL
       done
       sleep 1s
  done
  else
    /bin/echo "MySQL is not running."
fi
