#!/bin/bash
#set -eux

# sarコマンドで記録したリソース使用情報をテキストにする

: sarコマンドで生成したバイナリファイル
file=sar.out

# CPU
echo "\"timestamp\",\"CPU\",\"type\",\"value\"" > cpu.csv
sadf -T -- -u $file |awk '{print $3"T"$4"Z,"$5","$6","$7}' >> cpu.csv

# Memory
echo "\"timestamp\",\"Memory\",\"type\",\"value\"" > memory.csv
sadf -T -- -r $file |awk '{print $3"T"$4"Z,"$5","$6","$7}' >> memory.csv

# Network
echo "\"timestamp\",\"Network\",\"type\",\"value\"" > net.csv
sadf -T -- -n DEV sar.out|grep eth0 |awk '{print $3"T"$4"Z,"$5","$6","$7}' >> net.csv

# IO
echo "\"timestamp\",\"IO\",\"type\",\"value\"" > io.csv
sadf -T -- -b sar.out |awk '{print $3"T"$4"Z,"$5","$6","$7}' >> io.csv
