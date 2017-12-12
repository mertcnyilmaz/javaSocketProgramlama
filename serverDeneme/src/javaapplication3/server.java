package javaapplication3;
 
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class server{
   
     public static void main(String[] args) throws IOException {
         ServerSocket listener;
         
         listener=new ServerSocket(3000);
        
         try{
            while(true)
            {
                System.out.println("Bağlantı bekleniyor..");
                
                new myThread(listener.accept()).start();
             
            }
         }
         catch (Exception e) {
                System.out.println("HATA!!!!");
     }
     }
    /**
     *
     */

    public static class myThread extends Thread{

        public static int id;
        Socket socket;
        private myThread(Socket socket) {
         
            this.socket=socket;
        }

         public void run()
            {
              String gelen;
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
 
          
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                if((gelen = in.readLine()) != null) 
                    System.out.println("Client'dan gelen id:" + gelen);
                  this.id=Integer.parseInt(gelen);
                islem();
            } catch (IOException ex) {
                //Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
     
        public static void islem() throws IOException
            {
                String clientGelen;
                ServerSocket serverSocket = null;
                Socket clientSocket = null;
                String hata;  
                int operasyon_kodu;
                int a,b,c,d;
                int port_islem=0;
                double[] sonuc=new double[2];
                String clientGiden;
                
                
                port_islem=5000+id;
                try {
                    //*Server 5000 portundan Client'Ä± dinliyor *//
                    serverSocket = new ServerSocket(port_islem);
                    System.out.println("Math server kuruldu.");
                    
                } catch (Exception e) {
                System.out.println("Math server port hatası!");
                }
                clientSocket = serverSocket.accept();
          
                System.out.println("SERVER BAĞLANTI İÇİN HAZIR...");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
 
          
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
                if((clientGelen = in.readLine()) != null) {
                    System.out.println("Client'dan gelen operasyon kodu ve parametreler = " + clientGelen);
                    String[] parametreler=clientGelen.split(" ");
                    operasyon_kodu = Integer.valueOf(parametreler[0]);
               
                if(operasyon_kodu==1)
                {
                        a=Integer.valueOf(parametreler[1]);
                        b=Integer.valueOf(parametreler[2]);
                        c=Integer.valueOf(parametreler[3]);
                        System.out.println("Client id:"+id);
                        System.out.println("Client'a giden sonuç:"+operation1(a,b,c));
                        out.println(operation1(a,b,c));
                   
                }
                else if(operasyon_kodu==2)
                {
                        a=Integer.valueOf(parametreler[1]);
                        b=Integer.valueOf(parametreler[2]);
                        System.out.println("Client id:"+id);
                        System.out.println("Client'a giden sonuç:"+operation2(a,b));
                        out.println(operation2(a,b));
                }
                else if(operasyon_kodu==3)
                {
                        a=Integer.valueOf(parametreler[1]);
                        b=Integer.valueOf(parametreler[2]);
                        c=Integer.valueOf(parametreler[3]);
                       
                        sonuc=operation3(a,b,c);
                        clientGiden=sonuc[0]+" ve "+sonuc[1];
                        System.out.println("Client id:"+id);
                        System.out.println("Client'a giden sonuç:"+clientGiden);
                        out.println(clientGiden);
                    
                }
                else if(operasyon_kodu==4)
                {
                        a=Integer.valueOf(parametreler[1]);
                        b=Integer.valueOf(parametreler[2]);
                        c=Integer.valueOf(parametreler[3]);
                        d=Integer.valueOf(parametreler[4]);
                        System.out.println("Client id:"+id);
                        System.out.println("Client'a giden sonuç:"+operation4(a,b,c,d));
                        out.println(operation4(a,b,c,d));
                }
                else
                {
                   hata="Yanlış operasyon kodu girdiniz.";
                   out.println(hata);
                }
                    
            }
           out.close();
           in.close();
           clientSocket.close();
           serverSocket.close();
     }
     
     public static double operation1(double a,double b,double c)
     {
         
         double sonuc;
         if(c==0)
         {
             String sonuc1;
             sonuc1="division by zero";
             System.out.println(sonuc1);
             return 0;
         }
         else
         {
             sonuc=Math.sqrt(a*a+b*b)/Math.abs(c);
             return sonuc;
         }
         
     }
     
     public static double operation2(double a,double b)
     {
         double sonuc;
         if(a+b<0)
         {
             String sonuc1;
             sonuc1="sum of parameters is negative";
             System.out.println(sonuc1);
             return 0;
         }
         else
         {
             sonuc=Math.sqrt(a+b);
             return sonuc;
         }
     }
     
     public static double[] operation3(double a,double b,double c)
     {
         double[] sonuc = new double[2];
         double delta;
         if(b*b-4*a*c<0)
         {
             String sonuc1;
             sonuc1="delta is negative";
             System.out.println(sonuc1);
             //return 0;
         }
         else
         {
             delta=b*b-4*a*c;
             sonuc[0]=(-b+Math.sqrt(delta))/2*a;
             sonuc[1]=(-b-Math.sqrt(delta))/2*a;
             
         }
         return sonuc;
     }
     
     public static double operation4(double a,double b,double c,double d)
     {
         double sonuc;
         if(a-c==0)
         {
             String sonuc1;
             sonuc1="inverse of the function is undefined";
             System.out.println(sonuc1);
             return 0;
         }
         else
         {
             sonuc=(b-d)/(a-c);
             return sonuc;
         }
     }         
     }
}
