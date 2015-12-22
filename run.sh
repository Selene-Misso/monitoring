#!/bin/bash
#set -eux

#./monitor-io.sh &
#./monitor-cpu.sh &
./monitor-net.sh &
#./monitor-throughput.sh &
sar -A -o sar.out 1 >/dev/null &

# MySQL が存在するか?
mysql=/usr/bin/mysqladmin
if [ -e $mysql ]; then
  echo "$mysql found!"
  ./monitor-mysql.sh &
fi
