#!/bin/bash
#set -eux

./monitor-net.sh &
sar -A -o sar.out 1 >/dev/null &

# MySQL が存在するか?
mysql=/usr/bin/mysqladmin
if [ -e $mysql ]; then
  echo "$mysql found!"
  ./monitor-mysql.sh &
fi
