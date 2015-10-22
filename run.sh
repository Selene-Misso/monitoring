#!/bin/bash
#set -eux

./monitor-io.sh &
./monitor-cpu.sh &
./monitor-net.sh &
