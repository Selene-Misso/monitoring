# モニタリングスクリプト
1秒間隔でAmazon EC2の状態を監視するプログラム 要：ubuntu

sar,netstat,mysqladminコマンドを用いて，
CPU使用率，メモリ使用量，ネットワーク入出力，TCPコネクション数，
データベースのステータスを1秒間隔で監視するプログラム．

## 使い方

クローンしてから実行まで:

```
$ git clone https://github.com/Selene-Misso/monitoring.git
$ cd monitoring
$ ./run.sh
```


モニタリングスクリプトを実行すると，
バックグランドで各コマンドを実行させます．

モニタリングスクリプトを停止させるには，
以下を実行します．

```
$ ./stop.sh
```

sarコマンドはモニタリングしたデータをバイナリで保存しているため，
テキスト(CSV)形式に変換する．

```
$ ./convert-sar.sh
```


