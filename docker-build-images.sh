#!/bin/bash

services=("first" "second" "third" "fourth" "fifth" "sixth" "seventh" "eighth" "ninth" "tenth" "eleventh" "twelfth" "thirteenth" "fourteenth")
others=("configuration-server" "discovery" "gateway")

for d in ${services[*]};
do
  (cd "$d"-service && docker build -t "$d"-local .)
done

for d in ${others[*]};
do
  (cd $d && docker build -t "$d"-local .)
done