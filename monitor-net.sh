#!/bin/bash
#set -eux

: 値を書き込むファイル
file=net.dat

: 配列開始文字を追加
echo "[" > $file

: 終了時に最後のピリオドを削除して
: 配列終了文字を書き込む
trap "sed -i '\$d' $file; echo ] >> $file;" EXIT

# netstatを1秒間隔で実行
while :; do
  # 各コネクションの状態別にカウントし，結果をJSONで書き出す
  netstat -tn|awk 'NR>2 {print $6}'|sort|uniq -c \
  |awk 'BEGIN {print "{\"Timestamp.UTC\":",strftime("\"%Y-%m-%dT%H:%M:%SZ\"",systime(),1),","} \
  {printf ("\"%s\": %d,", $2, $1);}
  END {printf ("\b}\n,")} {system("")}'|col -b >> $file;

  sleep 1;
done
