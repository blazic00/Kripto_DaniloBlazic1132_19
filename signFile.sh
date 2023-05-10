#!/bin/bash

username="$1"
file="$2"
fileName="$3"

openssl dgst -sha256 -sign ./korisnici/$username/kljuc.pem $file > ./signedFiles/$fileName.$username.signed
