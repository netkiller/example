
# Menmonic

## Create menmonic

	http://localhost:8080/wallet/mnemonic
	
	{
	"status": true,
	"reason": null,
	"code": 0,
	"data": {
		"address": "0x57b49fb3eca9476e748c27f3dc435922ef4246b1",
		"privateKey": "V1eKH+dRsPTZ58vtxNb6QGQN+X7O1L1kfn7nRBLq8xj97n6o7vlxDk1yU5lPxihMsaGBS8/mnVaiVXdEyog5sEJeswDOTN63OZaq3KMD45w=",
		"mnemonic": "brisk adapt element calm share argue enroll employ print vessel medal sail",
		"keystore": null
		}
	}
	

## IBAN

### Iban encode

	curl -XPOST -d'address=0xea2299ab6e6d65fe895cf35c443087f8954b47e0&symbol=ETH&amount=0.001' http://localhost:8080/iban/encode
	{"status":true,"reason":null,"code":0,"data":"iban:XE78RCL2UMG7ZTXE4WSTZPKEBC7FYFBTBS0?token=ETH&amount=0.001"}
	
### Iban decode

	% curl -XPOST -d'uri=iban:XE78RCL2UMG7ZTXE4WSTZPKEBC7FYFBTBS0?token=ETH&amount=0.001' http://localhost:8080/iban/decode
	{"status":true,"reason":null,"code":0,"data":{"address":"0xea2299ab6e6d65fe895cf35c443087f8954b47e0","token":"ETH","amount":null}}  