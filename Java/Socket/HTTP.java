/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author neo
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.print("helloworld");
        try {
            Socket socket = new Socket(InetAddress.getByName("inf.example.com") , 80); //
            
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write("GET /mix/json2app/2/20/0.html HTTP/1.1\r\n");
            wr.write("Host: inf.example.com\r\n");
            wr.write("\r\n");
            wr.flush();
            
/*
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("GET /mix/json2app/2/20/0.html HTTP/1.1");
            pw.println("Host: inf.example.com");
            pw.println();
            pw.flush();
*/            

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
            //pw.close();
            wr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
