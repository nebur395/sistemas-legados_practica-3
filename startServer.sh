#!/bin/sh
# Start DOSBox, start gretty server

dosbox &
sleep 2
gradle appRun
