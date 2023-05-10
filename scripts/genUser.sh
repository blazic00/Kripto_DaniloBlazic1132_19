#!/bin/bash

username="$1"
password="$2"
name="$3"
mail="$4"
CApassword="$5"

mkdir ./korisnici/$username

#smjestanje otiska lozinke
openssl passwd -5 $password > ./korisnici/$username/password.txt

#generisanje kljuca
openssl dsaparam -out ./korisnici/$username/dsaparam.pem 2048
openssl gendsa -out ./korisnici/$username/kljuc.pem ./korisnici/$username/dsaparam.pem
openssl dsa -in ./korisnici/$username/kljuc.pem -inform PEM -pubout -out ./korisnici/$username/pub_kljuc.pem

#generisanje sertifikata
openssl req -new -key ./korisnici/$username/kljuc.pem -out ./CA/requests/$username.csr -config ./CA/openssl.cnf -subj "/C=BA/ST=RS/L=Banja Luka/O=Kripto/OU=Kri/CN=$name/emailAddress=$mail"
openssl ca -in ./CA/requests/$username.csr -out ./CA/certs/$username.crt -config ./CA/openssl.cnf -keyfile ./CA/private/private4096.key -passin pass:$CApassword -batch
 


