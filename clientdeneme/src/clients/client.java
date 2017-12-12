package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.io.*;
import java.net.*;



public class client {
    
    
     public static void main(String[] args) throws IOException {
          int id=0;
          
          
          id=sserver();
          islem(id);
     }
 
     public static void islem(int id) throws UnknownHostException, IOException {
          Socket socket = null;
          PrintWriter out = null;
          BufferedReader in = null;
          String operasyonKodu;
          int port=0;
          boolean bağlanti=false;
          port=id+5000;
          for(;bağlanti==false;++port)
          {
                try {
                    //* server 'a localhost ve 7755 portu Ã¼zerinden baÅŸlantÄ± saÄŸlanÄ±yor *//
                    socket = new Socket("localhost", port);
                    bağlanti=true;
                    break;
             
                } catch (Exception e) {
                    System.out.println("Port Hatası!Kullanılan port:"+port);
                }
          }
          
          
          out = new PrintWriter(socket.getOutputStream(), true);
          
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
          
          System.out.println("Server'a gönderilecek operasyon kodunu ve parametreleri giriniz(1 2 3 4):");
 
          
          BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
 
          if((operasyonKodu = data.readLine()) != null) {
               out.println(operasyonKodu);
               System.out.println("Server'dan gelen sonuç = " + in.readLine());
          }
          out.close();
          in.close();
          data.close();
          socket.close();
     }
     
     public static int sserver() throws IOException
     {
          int id=0;
          Socket socket = null;
          PrintWriter ssout = null;
          BufferedReader ssin = null;
          String operasyonKodu;
          int port=3000;
          try {
                    //* server 'a localhost ve 7755 portu Ã¼zerinden baÅŸlantÄ± saÄŸlanÄ±yor *//
                    socket = new Socket("localhost", port);
          } catch (Exception e) {
                    System.out.println("SSServer port Hatası!");
                    
          }
              
          System.out.println("SSSERVER BAĞLANTI İÇİN HAZIR..");
          
          
          ssout = new PrintWriter(socket.getOutputStream(), true);
 
          ssin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
          ssout = new PrintWriter(socket.getOutputStream(), true);
          
          ssin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
          
          System.out.println("Server'a gönderilecek id giriniz:");
 
          
          BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
 
          if((operasyonKodu = data.readLine()) != null) 
               ssout.println(operasyonKodu);
          id=Integer.parseInt(operasyonKodu);
          return id;
     }
}
    