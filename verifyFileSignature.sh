#!/bin/bash

username="$1"
file="$2"
fileName="$3"


openssl dgst -sha256 -verify ./korisnici/$username/pub_kljuc.pem -signature ./signedFiles/$fileName.$username.signed $file

