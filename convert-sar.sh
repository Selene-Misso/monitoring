#!/bin/bash
#set -eux

# sarコマンドで記録したリソース使用情報をテキストにする

: sarコマンドで生成したバイナリファイル
file=sar.out

# CPU
echo "\"hostname\",\"interval\",\"timestamp\",\"CPU\",\"type\",\"value\"" > cpu.csv
sadf -T -- -u $file |tr "\t" , >> cpu.csv

# Memory
echo "\"hostname\",\"interval\",\"timestamp\",\"Memory\",\"type\",\"value\"" > memory.csv
sadf -T -- -r $file |tr "\t" , >> memory.csv

# Network
echo "\"hostname\",\"interval\",\"timestamp\",\"Network\",\"type\",\"value\"" > net.csv
sadf -T -- -n DEV sar.out|grep eth0 |tr "\t" , >> net.csv

# IO
echo "\"hostname\",\"interval\",\"timestamp\",\"IO\",\"type\",\"value\"" > io.csv
sadf -T -- -b sar.out |tr "\t" , >> io.csv
