#!/bin/bash

username="$1"

for file in ./korisnici/*
do
	if [ "./korisnici/$username" = "$file" ] 
	then
		exit 1
	fi	
done
	
