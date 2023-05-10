#!/bin/bash

userCert="$1"

openssl verify -crl_check -CAfile ./CA/rootca.pem -CRLfile ./CA/crl/crlLista.pem ./CA/certs/$userCert


