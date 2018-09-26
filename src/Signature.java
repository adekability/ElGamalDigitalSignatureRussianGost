import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.Color;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Files.*;
import java.nio.file.Paths;
import java.nio.file.Paths.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Signature extends javax.swing.JFrame{
    public static void add(ZipOutputStream zos, String fileName)throws Exception
    {
        File file= new File(fileName);
        FileInputStream fis= new FileInputStream(file);
        ZipEntry zipEntry=new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[]bytes=new byte[1024];
        int length;
        while((length=fis.read(bytes))>=0)
        {
            zos.write(bytes,0,length);
        }
        zos.closeEntry();
        fis.close();
    }
    //this method needed for compressing the text files




    public static BigInteger InverseElement(BigInteger k, BigInteger p)
    {
        if(k.gcd(p).compareTo(BigInteger.ONE)==0)
        {
            BigInteger ret=BigInteger.ZERO;
            BigInteger a,b,a1,b1,q;
            a=k;//a=7
            b=p;//b=11
            a1=BigInteger.ZERO;//a1=0
            b1=BigInteger.ONE;//b1=1
            if(b.compareTo(a)>0)//if b>a
            {
                BigInteger d=a;// d=a
                a=b;// a=b -> a=11
                b=d;//b=d  -> b=7
            }
            BigInteger main=a;
            while(a.compareTo(BigInteger.ZERO)!=0||b.compareTo(BigInteger.ZERO)!=0)// while a and b !=0
            {
                q=a.divide(b);//q=11 divide 7 = 1 -> q=1// q=4 divide 3=1 -> q=1
                //  System.out.println("q="+q);
                a=a.mod(b);//a=11 mod 7 =4 ->        a=4// a=4 mod 3=1 -> a=1
                // System.out.println("a="+a);
                if(a.compareTo(BigInteger.ZERO)==0)
                {
                    if(b1.compareTo(BigInteger.ZERO)<0)
                    {
                        b1=b1.mod(main);
                        ret=b1;
                    }
                    ret=b1;
                    //System.out.println(b1);
                    break;
                }
                a1=a1.subtract(q.multiply(b1));//a1=0-1*1=-1 -> a1=-1//a1=-1-1*2=-3
                // System.out.println("a1="+a1);
                q=b.divide(a);//q=7 divide 4 = 1//q=3 divide 1=3
                //  System.out.println("q="+q);
                b=b.mod(a);//b=7 mod 4= 3//b=3 mod 1=0
                // System.out.println("b="+b);
                b1=b1.subtract(q.multiply(a1));//b1=1-1*(-1)=2 -> b1=2//
                //System.out.println("b1="+b1);
                if(b.compareTo(BigInteger.ZERO)==0)
                {
                    if(a1.compareTo(BigInteger.ZERO)<0)
                    {
                        a1=a1.mod(main);
                        ret=a1;
                    }
                    ret=a1;
                    //System.out.println(a1);
                    break;
                }
            }
            return ret;
        }
        else
        {
            return null;
        }

    }//I need this method to find Inverse Element in several verification cases
    public static BigInteger random()
    {
        Random b = new SecureRandom();
        BigInteger a = BigInteger.probablePrime(2048,b);
        return a;
    }//this method is method that calls random BigIntegers
    public static String SHA256(String a)throws NoSuchAlgorithmException //METHOD OF
    {
        byte[]input=a.getBytes();
        MessageDigest SHA256=MessageDigest.getInstance("SHA-256");
        SHA256.update(input);
        byte[] digest=SHA256.digest() ;
        StringBuffer hexDigest=new StringBuffer();
        for(int i=0;i<digest.length;i++)
        {
            hexDigest.append(Integer.toString((digest[i]&0xff)+0x100,16).substring(1));
        }
        String b=hexDigest.toString();
        return b;
    }//this method is a method that calls hash-function of the message
    JFileChooser openFileChooser;// this parameter is for opening file
    JFileChooser saveFileChooser;// this parameter is for saving the file


    public Signature() {
        initComponents();

        openFileChooser=new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("DOC files","doc"));
        //this method calling opens a text file
        saveFileChooser=new JFileChooser();
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("DOC files","doc"));
        //this method calling saves changed text file
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        readButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        writeButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        readButton.setLabel("Signing");
        readButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    readButtonActionPerformed(evt);
                }catch(NoSuchAlgorithmException e)
                {

                }
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        writeButton.setText("Write to File...");
        writeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeButtonActionPerformed(evt);
            }
        });

        messageLabel.setBackground(new java.awt.Color(153, 153, 153));
        messageLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(readButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(22, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(writeButton)))
                                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(readButton)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(writeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold> // this source is an amount of rows of code that makes
    //some graphical works to establish the working process

    private void readButtonActionPerformed(java.awt.event.ActionEvent evt)throws NoSuchAlgorithmException {
        //this is a function for handled "read" button, it means that after nadling "read" button I begin to select a text file
        // TODO add your handling code here:
        int returnValue=openFileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                //open the file
                XWPFDocument document = new XWPFDocument(new FileInputStream(openFileChooser.getSelectedFile()));
                XWPFWordExtractor extract=new XWPFWordExtractor(document);
                String m=extract.getText();
                System.out.println(m);
                /*
                File inputFile=openFileChooser.getSelectedFile();
                FileReader inputReader = new FileReader(inputFile);
                BufferedReader inputBR=new BufferedReader(inputReader);
                //Now the file is selected and opened
                //read the contents
                String line=inputBR.readLine();//null when at the end of a file
                textArea.setText("");//initializing empty textArea to give it our future values
                while(line!=null)
                {
                    textArea.append(line+"\n");
                    line= inputBR.readLine();
                    //to read all the symbols from text file and give this value to textArea
                }*/


                BigInteger p=new BigInteger("22447072865393472692006793475380077783246646951332628480076162978739967760505240514595048494451676165364978771086841379170260388092142401398066292497601681128577979645169477600035433565630062761348711783768490243675310093632776676289031153847415278625675014283691810501797109754612231745160664099519400956174035003690866818522308530532264070536025268940898250124626110833624472362461317256136065198411737714337761464991350936230064116741471236874192557746857986375626495393516141328485752429316369597637976437142115869464179895410975582222084215407560557041370151766183367150358148675463458057954581677198982032338367");
                BigInteger q=new BigInteger("11223536432696736346003396737690038891623323475666314240038081489369983880252620257297524247225838082682489385543420689585130194046071200699033146248800840564288989822584738800017716782815031380674355891884245121837655046816388338144515576923707639312837507141845905250898554877306115872580332049759700478087017501845433409261154265266132035268012634470449125062313055416812236181230658628068032599205868857168880732495675468115032058370735618437096278873428993187813247696758070664242876214658184798818988218571057934732089947705487791111042107703780278520685075883091683575179074337731729028977290838599491016169183");
                BigInteger y=new BigInteger("20029416035480938902981684884434922553174623943449257184850285692886388839500202149673250454142553444850414319628826455952811469225473801307920096741957849232330484172230464252898653683974484888282614852046467750264473467967744880414152989103445386044898655038554422951619479849740197018085882242375932263680000724457320601629962248525396490402722296187235775443024113042801660486158188947547726167457291178947857073960113910662581537695593413883205547455085739644550369626406327502716559103923722908876655108565362970693895999847075904557971016700967611965149252312809188226729828654244948657552957517709060253596102");
                BigInteger x=new BigInteger("9977200918824036479438929379720205094149964884188445619462078735156270479063260564729426780294239293996894614297819");
                BigInteger k,r,s;
                BigInteger a=new BigInteger("81856755457110211584763405475314283238642221604096931560051613034484120657539");
                //initializing all the parameters that I found before
                //String m;//initializing m for the message
                //m=textArea.getText();//giving to string m value of textArea
                //m=m.substring(0,m.length()-1);//text file gives to the textArea empty symbol in the end of the las line
                //that's why I'm cutting last place in m's length


                //checking parameters:
                //char[]ch=m.toCharArray();
                //int a=ch.length;
                //for(int i=0;i<ch.length;i++)
                //ch[a]=ch[a-1];
                //int c=ch.length;
                String hm=SHA256(m);//initializing hm as hash-fucntion of the message
                BigInteger hmp=new BigInteger(hm,16);//initializing hmp as
                // decimal value of hexadecimal value of hash-function
                Random aa=new SecureRandom();//initializing aa for secure random generating
                Random rand=new Random();//initializing random for integer random generating
                int random=rand.nextInt((512-16)+1)+16;//initializing random for random bit length
                //as I did before, with secret key x
                do {//beginning a cycle for finding s
                    do {//beginning a cycle for finding r
                        k=BigInteger.probablePrime(random,aa);//giving to k it's own expression
                        r=(a.modPow(k,p)).mod(q);// giving to r it's own expression
                    }while(r.compareTo(BigInteger.ZERO)==0);//cycle must go on while r is 0
                    s=((k.multiply(hmp)).add(x.multiply(r))).mod(q);//giving to s it's own expression
                }while(s.compareTo(BigInteger.ZERO)==0);//cycle must go on while s is 0

                if(r.compareTo(BigInteger.ZERO)<=0||r.compareTo(q)>=0||s.compareTo(BigInteger.ZERO)<=0||s.compareTo(q)>=0)
                {
                    System.out.println("Error");//checking r and s for future verification
                }
                //System.out.println(m);
                //System.out.println(c);
                //System.out.println(hm);
                //System.out.println(r);
                //System.out.println(s);
                String R=r.toString();//initializing BigInteger r to String R
                String S=s.toString();//initializing BigInteger s to String S
                //System.out.println(hm);
                final Formatter defined;
                final Formatter define;
                final Formatter definede;
                //final method is for creating a new text document
                try
                {
                    defined=new Formatter("r.txt");//creating text document with a name r
                    defined.flush();//flush is for not to continue while all the operations above aren't finished
                    /*System.out.println(R);
                    FileWriter filew=new FileWriter("r.txt");
                    BufferedWriter buffw=new BufferedWriter(filew);
                    buffw.write("sda");*/
                    define=new Formatter("s.txt");//creating text document with a name r
                    define.flush();//flush is for not to continue while all the operations above aren't finished
                    define.close();//closing formatter, means textfile
                    defined.close();//closing formatter, means textfile
                    /*System.out.println(S);
                    define.format("%s%s%s"+R);
                    defined.format("%s%s%s"+S);*/
                    File  file=new File("r.txt");//selecting r file
                    PrintWriter pw=new PrintWriter(file);//initializing needed pw parameter
                    pw.print(R);//writing to this file r's value
                    pw.close();//closing file
                    File  filenew=new File("s.txt");//selecting s file
                    PrintWriter pwnew=new PrintWriter(filenew);//initializing needed pwnew parameter
                    pwnew.print(S);//writing to this file s's value
                    pwnew.close();//closing file
                    definede=new Formatter("m.doc");//creating text document with a name m
                    definede.flush();//flush is for not to continue while all the operations above aren't finished
                    definede.close();//closing formatter, means textfile
                    File  filer=new File("m.doc");//selecting m file
                    PrintWriter pr=new PrintWriter(filer);//initializing needed pr parameter
                    pr.print(m);//writing to this file m's value
                    pr.close();//closing file
                    FileOutputStream fos=new FileOutputStream("mrs.zip");//creating zip.file mrs
                    ZipOutputStream zos=new ZipOutputStream(fos);//initializing needed parameter aoz
                    String file1="r.txt";
                    String file2="s.txt";
                    String file3="m.doc";
                    //initializing three string for all the text files
                    add(zos,file1);
                    add(zos,file2);
                    add(zos,file3);
                    // these strings are compressed inro mrs.zip file
                    zos.close();
                    fos.close();
                    //zip's parameters close
/*FileWriter filewr=new FileWriter("s.txt");
                    BufferedWriter buffwr=new BufferedWriter(filew);
                    buffwr.write("asd");*/
                    /*Path sourcePath=Paths.get("C:\\Users\\w\\IdeaProjects\\School\\Java techniques\\Writing and Reading Text\\mrs.zip"),destinationPath=Paths.get("C:\\Users\\w\\IdeaProjects\\School\\Java techniques\\Verification");
                    Files.move(sourcePath,destinationPath);*/
                    File f1 = new File("C:\\Users\\w\\IdeaProjects\\School\\Cryptography\\Signatures\\GOST for El-Gamal Signature\\Signature\\mrs.zip");
                    File f2 = new File("C:\\Users\\w\\IdeaProjects\\School\\Cryptography\\Signatures\\GOST for El-Gamal Signature\\Verificating\\mrs.zip");
                    f1.renameTo(f2);
                    //sending mrs.zip to Verification folder
                }
                catch(Exception e)
                {
                    //catching all Exception after try
                }
                //close the file readers
                //inputBR.close();
                //inputReader.close();
                message("File successfully read!",false);//closing selectArea




            }catch(IOException ioe){
                message("Error reading file",true);//if file isn't readable, error message comes
            }
        }
        else
        {
            message("No file chosen!",true);//if file isn't chosen, message comes
        }
    }

    private void writeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int returnValue=saveFileChooser.showSaveDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File outputFile=saveFileChooser.getSelectedFile();
                PrintWriter outputPW=new PrintWriter(outputFile);
                outputPW.write(textArea.getText());
                outputPW.flush();
                outputPW.close();
                message("File successfully written!",false);
            }
            catch(IOException ioe){
                message("Error writing file!",true);
            }
        }else
        {
            message("No file chosen!",true);
        }
    }
    private void message(String msg,boolean isError)
    {
        if(isError)
        {
            messageLabel.setText(msg);
            messageLabel.setForeground(Color.red);
        }
        else
        {
            messageLabel.setText(msg);
            messageLabel.setForeground(Color.BLACK);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {//main class, that begins a program
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Signature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signature().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton readButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton writeButton;
    //Graphical instruments programming
    // End of variables declaration
}
