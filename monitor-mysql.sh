#!/bin/sh

########################
## Get mysql status

HOME=`/bin/echo $HOME`
DIR=$HOME/data
HOST=`/bin/hostname`
DATE=`/bin/date "+%Y/%m/%d %H:%M:%S"`

SOCKET=/var/run/mysqld/mysqld.sock
COMMAND="/usr/bin/mysqladmin -u root -p3641ec2 extended-status"

if [ -S $SOCKET ];then
   $COMMAND | /usr/bin/awk '{print $2,$4}' | /bin/egrep -v '(^ |Variable_name)' | \
      while read LABEL DATA
   do
       /bin/echo -e $DATE "\t" $DATA >> $DIR/$HOST.$LABEL
     done
  else
    /bin/echo "MySQL is not running."
fi
