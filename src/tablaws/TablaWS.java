
package tablaws;

import java.util.Scanner;

public class TablaWS {

   
    public static void main(String[] args) {
        System.out.println("Introduzca la opcion deseada:" );
        System.out.println("getAtomicNumber: (1)" );
        System.out.println("getAtomicWeight: (2)");
        System.out.println("getAtoms: (3)"  );
        System.out.println("getElementSymbols: (4)" );
        boolean abrir = true;
       
     
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        
        selectMethod(num);
        
      
        
        
    }

   
    
    private static void selectMethod(int num){
        Scanner reader;
        String name;
        String aux;
        switch (num){
            case 1:
                System.out.println("Introduzca el nombre del elemento:" );
                reader = new Scanner(System.in);
                name = reader.nextLine();
                aux= getAtomicNumber(name);
                if (!aux.equals("<NewDataSet />")) {
                    String atomicNumber = parseResponse(aux, "</AtomicNumber>");
                    System.out.println(name + " atomic number is: " + atomicNumber);
                }
                break;
            case 2:
                System.out.println("Introduzca el nombre del elemento:" );
                reader = new Scanner(System.in);
                name = reader.nextLine();
                 
                aux=getAtomicWeight(name);
                if (!aux.equals("<NewDataSet />")) {
                    String atomicWeigth = parseResponse(aux, "</AtomicWeight>");
                    System.out.println(name +" element weigth is: " + atomicWeigth );
                }
                break;
            case 3:
               
                aux=getAtoms();
                if (!aux.equals("<NewDataSet />")) {
                    String atomicname = parseResponse(aux, "</ElementName>");
                    atomicname=atomicname.replaceAll("<Table>","");
                    atomicname=atomicname.replaceAll("</Table>","");
                    atomicname=atomicname.replaceAll("<ElementName>","");
                    atomicname=atomicname.replaceAll("</ElementName>","");
                    atomicname=atomicname.replaceAll("\n","");
                    atomicname=atomicname.replaceAll("    "," ");
                    atomicname=atomicname.replaceAll(" ","\n");
                    System.out.println("All elemnts: ");
                    System.out.println( atomicname );
                }
                break;
            case 4:
                System.out.println("Introduzca el nombre del elemento:" );
                reader = new Scanner(System.in);
                name = reader.nextLine();
                aux=getElementSymbol(name);
                if (!aux.equals("<NewDataSet />")) {
                    String elementSymbol = parseResponse(aux, "</Symbol>");
                    
                    System.out.println(name +" element symbol is: " + elementSymbol );
                };             
                break;
            default:    
        }
    }
    
     private static String getAtomicNumber(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtomicNumber(elementName);
    }

    private static String getAtomicWeight(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtomicWeight(elementName);
    }

    private static String getAtoms() {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getAtoms();
    }

    private static String getElementSymbol(java.lang.String elementName) {
        net.webservicex.Periodictable service = new net.webservicex.Periodictable();
        net.webservicex.PeriodictableSoap port = service.getPeriodictableSoap();
        return port.getElementSymbol(elementName);
    }
     private static String parseResponse(String response, String endTag) {
        String beginTag = endTag.replace("/", "");
        final int from = response.indexOf(beginTag);
        final int to = response.lastIndexOf(endTag);
        final String beginTagAndContent = response.substring(from, to);
        return beginTagAndContent.substring(beginTagAndContent.indexOf(">") + 1);
     }   
   
    
}
