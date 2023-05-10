#!/bin/bash

AESpassword="$1"
i=1

for file in ./tempFileSegments/*
do
	filename=$(echo "$file" | sed 's#./tempFileSegments/##')
	openssl aes-256-cbc -in $file -out ./upload/$i/$filename.enc -e -a -pass pass:$AESpassword
	((i++))
done

