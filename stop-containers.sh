#!/bin/bash

dicts=("kafka")

for d in $dicts
do
  (cd $d && bash stop.sh)
done