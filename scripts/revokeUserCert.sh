#!/bin/bash

userCert="$1"
CApassword="$2"

openssl ca -revoke ./CA/certs/$userCert -crl_reason unspecified -config ./CA/openssl.cnf -passin pass:$CApassword
openssl ca -gencrl -out ./CA/crl/crlLista.pem -config ./CA/openssl.cnf -passin pass:$CApassword