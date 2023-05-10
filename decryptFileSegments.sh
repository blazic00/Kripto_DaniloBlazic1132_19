#!/bin/bash

username="$1"
filename="$2"
AESpassword="$3"

i=1
for dir in ./upload/*
do
	# Check if the directory is not empty
	if [ "$(ls -A $dir)" ]; then
		for file in "$dir"/*
		do
			pom=$(echo "$file" | sed "s#./upload/$i/##")
			if [ "$pom" = "$filename.$username.$i.enc" ]; then
				openssl aes-256-cbc -in $file -out ./tempFileSegments/$filename.$username.$i.dec -d -a -pass pass:$AESpassword
				echo $pom
			fi
		done
	fi
	((i++))
done