import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;

import java.net.ServerSocket;
import java.net.Socket;

public class tcp1ser {
    
    public Integer porto;
    public long acumulador;
    public static DataOutputStream saida;
    public static DataInputStream entrada;
    public static String ipServidor = "127.0.0.1";
    public static Socket socket = null;
    public ServerSocket serverSocket = null;   
    
    public static int mensaxe [] = {0,0,0,0};

    public tcp1ser (String[] args){

        comprobaArgs(args);

        this.porto = Integer.parseInt(args[0]);

    }

    public static void main (String[] args) {

        tcp1ser server = new tcp1ser(args);

        try {
            server.serverSocket = new ServerSocket(server.porto);

            Integer conexions = 0; 
            server.acumulador = 0;
            while(true) {


                socket = server.serverSocket.accept();

                entrada = new DataInputStream(socket.getInputStream()) ;
                saida = new DataOutputStream(socket.getOutputStream());
                
                try{
                    while(true) {
                        byte []envio = new byte[10];

                        mensaxe[0] = entrada.readByte();
                        mensaxe[1] = entrada.readByte();
                        mensaxe[2] = entrada.readByte();
                        if(mensaxe[0] != 6){
                            mensaxe[3] = entrada.readByte();
                        }

                        server.acumulador = calculadoraAcumulador(server.acumulador);
                        
                        envio = numerosTLV(server.acumulador);

                        saida.write(envio);
                                        
                    }
                }catch (EOFException e) {

                }

                socket.close();
                conexions += 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void comprobaArgs (String [] args){

        if(args.length != 1){
            System.out.println("Erro nos argumentos pasados ó servidor.\nA sintaxe válida é a seguinte:");
            System.out.println("\ttcp1ser porto");
            System.exit(-1);
        }

    }

    public static long calculadoraAcumulador (long acumulador){
        switch (mensaxe[0]) {
            case 1:
                int suma = mensaxe[2] + mensaxe[3];
                System.out.println(mensaxe[2]+" + "+mensaxe[3] +" = "+ suma);
                acumulador += suma;
                break;
            case 2:
                int resta = mensaxe[2] - mensaxe[3];
                System.out.println(mensaxe[2]+" - "+mensaxe[3] +" = "+ resta);
                acumulador += resta;
                break;
            case 3:
            
                int producto = mensaxe[2] * mensaxe[3];
                System.out.println(mensaxe[2]+" * "+mensaxe[3] +" = "+ producto);
                acumulador += producto;
                break;
            case 4:
                int cociente = mensaxe[2] / mensaxe[3];
                System.out.println(mensaxe[2]+" / "+mensaxe[3] +" = "+ cociente);
                acumulador += cociente;
                break;
            case 5:
                int resto = mensaxe[2] % mensaxe[3];
                System.out.println(mensaxe[2]+" % "+mensaxe[3] +" = "+ resto);
                acumulador += resto;
                break;
            case 6:
                System.out.println(mensaxe[2]+"!"+" = "+ factorial(mensaxe[2]));
                acumulador += factorial(mensaxe[2]);
                break;
        }

        return acumulador;
        
    }

    public static long factorial(int N1){
        long factorial = 1;

        for (long i = 1; i <= N1; i++){
            factorial *= i;
        }
        return factorial;
    }

    public static byte[] numerosTLV (long acumulador){

            Integer tipo = 16;
            Integer lonx = 8;
            //System.out.println(String.format("%64s", Long.toBinaryString(acumulador)).replace(' ', '0'));

            byte bytes[] = new byte[]{
                tipo.byteValue(),
                lonx.byteValue(),
                (byte) ((acumulador >> 56) & 0xff),
                (byte) ((acumulador >> 48) & 0xff),
                (byte) ((acumulador >> 40) & 0xff),
                (byte) ((acumulador >> 32) & 0xff),
                (byte) ((acumulador >> 24) & 0xff),
                (byte) ((acumulador >> 16) & 0xff),
                (byte) ((acumulador >> 8) & 0xff),
                (byte) ((acumulador >> 0) & 0xff),
            };

            return bytes;        
    }

}
