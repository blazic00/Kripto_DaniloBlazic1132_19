#!/bin/bash

userCert="$1"
CApassword="$2"

serial=$(openssl x509 -in ./CA/certs/$userCert -serial -noout | cut -d '=' -f2)
while read line
do
	if [[ $line = R* ]] 
	then
		serialPom=$(echo $line | cut -d ' ' -f4)
		if [[ $serialPom = $serial ]]
		then
			pom=$(echo "$line" | sed 's/R/V/; s/\w*,\w*//')
			sed -i "s.$line.$pom." ./CA/index.txt
		fi
	fi
done < ./CA/index.txt
openssl ca -gencrl -out ./CA/crl/crlLista.pem -config ./CA/openssl.cnf -passin pass:$CApassword
