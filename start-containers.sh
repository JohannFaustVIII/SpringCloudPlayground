#!/bin/bash

dicts=("kafka" "keycloak" "jaeger")

for d in ${dicts[*]};
do
  (cd $d && bash start.sh)
done