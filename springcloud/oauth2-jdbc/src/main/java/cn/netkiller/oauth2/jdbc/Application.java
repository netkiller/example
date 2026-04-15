package cn.netkiller.oauth2.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application 
{
    public static void main( String[] args )
    {
        System.out.println( "Oauth2 Jdbc!" );
		SpringApplication.run(Application.class, args);
    }
}
