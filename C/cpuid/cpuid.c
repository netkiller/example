#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]) 
{ 
   unsigned long s1,s2,s3,s4;
 char string[128];
 char szCpuId[1024];
 char p1[128], p2[128];
 unsigned int eax = 0;
        unsigned int ebx,ecx,edx;

 asm volatile 
 ( "cpuid"
        : "=a"(eax), "=b"(ebx), "=c"(ecx), "=d"(edx)
        : "0"(0) 
 );
 snprintf(szCpuId, 5, "%s", (char *)&ebx);
        snprintf(szCpuId+4, 5, "%s", (char *)&edx);
        snprintf(szCpuId+8, 5, "%s", (char *)&ecx);

 asm volatile 
 ( "movl $0x01 , %%eax ; \n\t"
  "xorl %%edx , %%edx ;\n\t"
  "cpuid ;\n\t"
  "movl %%edx ,%0 ;\n\t"
  "movl %%eax ,%1 ; \n\t"
  :"=m"(s1),"=m"(s2)
 ); 

 sprintf((char *)p1, "-%08X\n%08X-", s1, s2);
 snprintf(szCpuId+12, 20, "%s", (char *)p1);

 asm volatile
 ( "movl $0x03,%%eax ;\n\t"
  "xorl %%ecx,%%ecx ;\n\t"
  "xorl %%edx,%%edx ;\n\t"
  "cpuid ;\n\t"
  "movl %%edx,%0 ;\n\t"
  "movl %%ecx,%1 ;\n\t"
  :"=m"(s3),"=m"(s4)
 );

 sprintf((char *)p2, "%08X-%08X\n", s3, s4);
 snprintf(szCpuId+31, 19, "%s", (char *)p2);

 printf((char*)szCpuId);

 return 0;
}
