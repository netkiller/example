#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>

struct string {
  char *ptr;
  size_t len;
};

void init_string(struct string *s) {
  s->len = 0;
  s->ptr = malloc(s->len+1);
  if (s->ptr == NULL) {
    fprintf(stderr, "malloc() failed\n");
    exit(EXIT_FAILURE);
  }
  s->ptr[0] = '\0';
}

size_t writefunc(void *ptr, size_t size, size_t nmemb, struct string *s)
{
  size_t new_len = s->len + size*nmemb;
  s->ptr = realloc(s->ptr, new_len+1);
  if (s->ptr == NULL) {
    fprintf(stderr, "realloc() failed\n");
    exit(EXIT_FAILURE);
  }
  memcpy(s->ptr+s->len, ptr, size*nmemb);
  s->ptr[new_len] = '\0';
  s->len = new_len;

  return size*nmemb;
}
char * safenet(char *mode, char *key, char *in )
{ 
  CURL *curl;
  CURLcode res;
  char *fields;
  char *data;
  /* In windows, this will init the winsock stuff */ 
//  curl_global_init(CURL_GLOBAL_ALL);
 
  /* get a curl handle */ 
  curl = curl_easy_init();
  if(curl) {
    struct string s;
    init_string(&s); 
    
    asprintf(&fields, "mode=%s&keyname=%s&input=%s", mode, key, in);    

    curl_easy_setopt(curl, CURLOPT_URL, "http://175.145.40.155/safe/interface");
    curl_easy_setopt(curl, CURLOPT_USERAGENT, "safenet/1.0 by netkiller <netkiller@msn.com>");
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writefunc);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &s);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, fields);
 
    /* Perform the request, res will get the return code */ 
    res = curl_easy_perform(curl);
    /* Check for errors */ 
    if(res != CURLE_OK)
      fprintf(stderr, "curl_easy_perform() failed: %s\n",
              curl_easy_strerror(res));
 
    asprintf(&data, "%s", s.ptr);
    //printf("Encrypt: %s\n", data);

    free(s.ptr);
    /* always cleanup */ 
    curl_easy_cleanup(curl);
  }

    //printf("POST: %s\r\n", fields);
    return data;
  //curl_global_cleanup();
}
int main(void)
{
//  printf("Encrypt[[[%s]]]\r\n",safenet("encrypt","Myid01-key","Test"));
  printf("Decrypt[[[%s]]]\r\n",safenet("decrypt","Myid01-key", safenet("encrypt","Myid01-key","Test")));
//  printf("Decrypt[[[%s]]]\r\n",safenet("decrypt","Myid01-key","B133BA5960D762B98D2333F3654A3D0B534780AC4405FE048F9B6487B56A5405D2A24E81923A56CC5F0D57673B133DA5BD7417302624D4B3FF1B29FF6BFCE2B3144763C5E9129A1F50F0902C5D226B0D96443C2874B62DE6F867B55174FA1DE273AF1EF4F4F85223F7362773CC59D6EB2D30D4930A730990B004326AE48606D60FF323F970C8C767B932B986140C3A5F6B8A7E54D4D5CA642C04A733814F7115E2861F1E67066216A207EC681AE34EC1BD68DC359233EC669D13E23C3F983492F8AFDEF4B887D111EC750EFCFB1173ED9B30B6A516BBD110B28A004D554F170D8B71BDD32759AA235798A71632083AF2E829B02873037D771B5604DCEA24204E"));
  return 0;

}

