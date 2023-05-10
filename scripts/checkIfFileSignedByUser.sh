#!/bin/bash

username="$1"
file="$2"
filename="$3"

for file in ./signedFiles/*
do
  echo $file
done
