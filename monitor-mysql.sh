#!/bin/bash

########################
## Get mysql status

HOME=`/bin/echo $HOME`
DIR=$HOME/data
HOST=`/bin/hostname`

: 値を書き込むファイル
file=mysql.dat

: 配列開始文字を追加
echo "[" > $file

: 終了時に最後のピリオドを削除して
: 配列終了文字を書き込む
trap "sed -i '\$d' $file; echo ] >> $file;" EXIT

SOCKET=/var/run/mysqld/mysqld.sock
COMMAND="/usr/bin/mysqladmin -u root -p3641ec2 extended-status"

if [ -S $SOCKET ];then
  while true; do 
       $COMMAND | awk '{print $2,$4}'| egrep -v '(^ |Variale_name)' \
       | awk 'BEGIN  {print "{\"Timestamp.UTC\":",strftime("\"%Y-%m-%dT%H:%M:%SZ\"",systime(),1),","} \
       $2+0==$2 {printf ("\"%s\": %d,", $1, $2);} \
       $2+0!=$2 {printf ("\"%s\": \"%s\",", $1, $2);} \
       END{printf ("\b}\n,")} {system("")}' |col -b >> $file;
       sleep 1
  done
  else
    /bin/echo "MySQL is not running."
fi
