package Main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.text.DecimalFormat;

class Saida {

   public void imprimirNumeroDouble(String msg,double num) {
        //formata e imprime um número double
        DecimalFormat df = new DecimalFormat("###,##0.00");
        System.out.println(msg + df.format(num));
    }

    public void imprimirAvisoString(String aviso) {
        System.out.println(aviso);
    }
    
    public void imprimirInt(String msg, int s) {
        System.out.println(msg + s);
    }
    
    public void imprimirString(String msg,String s) {
        System.out.println(msg + s);
    }
}
