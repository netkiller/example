# Oauth2


curl http://client:secret@127.0.0.1:9000/oauth2/authorize?client_id=messaging-client&redirect_uri=http://example.com&response_type=code&scope=message.read




# Code

	curl -s  -X POST --user 'client:secret' http://127.0.0.1:9000/oauth2/authorize?client_id=messaging-client&redirect_uri=http://example.com&response_type=code&scope=message.read
	
	