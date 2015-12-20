#!/bin/bash
#set -eux

./monitor-io.sh &
./monitor-cpu.sh &
./monitor-net.sh &
./monitor-throughput.sh &

# MySQL が存在するか?
mysql=/usr/bin/mysqladmin
if [ -e $mysql ]; then
  echo "$mysql found!"
  ./monitor-mysql.sh &
fi
