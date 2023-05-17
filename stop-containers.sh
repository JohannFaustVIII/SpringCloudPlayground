#!/bin/bash

dicts=("kafka" "keycloak")

for d in ${dicts[*]};
do
  (cd $d && bash stop.sh)
done