#!/bin/bash
#set -eux

: 値を書き込むファイル
file=io.dat
: モニタするデバイス
DeviceName=sda

# cpu.datが存在するかチェック
if [ -e $file ]; then
    : 何もしない
else
	: 行名を追加
    echo "TimeStamp(UTC) kB_read/s kB_wrtn/s kB_read kB_wrtn" > $file
fi


: iostatを1秒間隔で実行
iostat -d 1 | awk '/'$DeviceName'/ \
{print strftime("%Y-%m-%dT%H:%M:%SZ",systime(),1), $3, $4, $5, $6}{system("")}' >> $file

