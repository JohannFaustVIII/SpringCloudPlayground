#!/bin/bash

dicts=("kafka" "keycloak")

for d in ${dicts[*]};
do
  (cd $d && bash start.sh)
done