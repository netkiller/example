

curl -I -X POST --user 'admin:chen' http://localhost:8085/oauth/authorize?response_type=code&client_id=sso&redirect_uri=http://localhost:8082/callback&scope=read


-H "Accept: text/html"

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" --user 'api:secret' -d 'grant_type=password&username=admin&password=chen' http://localhost:8085/oauth/token

curl -X POST --user 'api:secret' -d 'grant_type=password&username=admin&password=chen' http://localhost:8085/oauth/token

curl -H "Accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTU1Nzc3NjI0LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6ImUzZTc4ZDdjLTc0YWMtNGY0Mi05ODI2LTIzYjk3Mzg3NTViZCIsImNsaWVudF9pZCI6ImFwaSJ9.yqZXlSLkGuz6ULLj0l0srlyCK1WDI5IbMkbXeH53eSI" -X GET http://localhost:8085/user