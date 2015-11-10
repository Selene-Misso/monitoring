#!/bin/bash
#set -eux

: 値を書き込むファイル
file=cpu.dat

# cpu.datが存在するかチェック
if [ -e $file ]; then
    : 何もしない
else
	: 行名を追加
    echo "Timestamp.UTC Cpu_us.percent Cpu_sys.percent" > $file
fi

: topを1秒間隔で実行
## mpstat版
#cpu=`mpstat |awk '/all/ {print strftime("%Y-%m-%dT%H:%M:%SZ",systime(),1),$3, $5}'|tee cpu.dat`
## top版
top -b -d 1| awk -F'[: ,]*' '/Cpu/ {print strftime("%Y-%m-%dT%H:%M:%SZ",systime(),1),$2, $4}{system("")}' >> $file


