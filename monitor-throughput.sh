#!/bin/bash
#set -eux

: log file name
file=throughput.dat
: monitoring network device name
DeviceName=eth0

# exist file?
if [ -e $file ]; then
    : doanything
else
	: add colmn name
    echo "Timestamp.UTC rxkB/s txkB/s" > $file
fi


: run sar commnad per 1 second
while :
do
  sudo sar -n DEV 1 1| \
  awk '/^Average/ {
    if($2=="'$DeviceName'"){
      print strftime("%Y-%m-%dT%H:%M:%SZ",systime(),1), $5, $6
    }
  }' >> $file
done
