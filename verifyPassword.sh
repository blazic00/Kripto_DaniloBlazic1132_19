#!/bin/bash

username="$1"
password="$2"

for file in ./korisnici/*
do
	if [ "./korisnici/$username" = "$file" ] 
	then
		salt=$(cut -d '$' -f3 ./korisnici/$username/password.txt)
		hashOriginal=$(cat ./korisnici/$username/password.txt)
		hashComputed=$(openssl passwd -5 -salt $salt $password)
		if [ "$hashComputed" = "$hashOriginal" ]
		then
			exit 1
		fi
	fi	
done
