    //UDP Client Program 
    import java.net.*;  
    import java.util.*;
    public class client
    {  
      public static void main(String[] args) throws Exception 
      { 
        String se,re; 
        DatagramSocket ds = new DatagramSocket(3000); 
        DatagramPacket dp; 
        while(true)
        { 
          Scanner in=new Scanner(System.in);
          se=in.nextLine();
          if(se.equals("bye"))
          {
             break;  
          }
          InetAddress ip = InetAddress.getByName("127.0.0.1");       
          dp = new DatagramPacket(se.getBytes(),se.length(),ip,3001);  
          ds.send(dp);
          byte[] buf = new byte[1024];  
          dp = new DatagramPacket(buf, 1024);
          ds.receive(dp);  
          re = new String(dp.getData(), 0, dp.getLength());  
          if(re.equals("bye"))
          {
            break;  
          }
          System.out.println(re);
        }  
        ds.close();  
      }  
    }  
