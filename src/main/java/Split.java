import sun.nio.cs.UTF_32;

import java.io.*;
import java.nio.charset.Charset;

public class Split {


    public static void main(String[] args) {

        int data,count = 1 ;
        int size=0;
        long max_len = Long.parseLong(args[2]) * 1024 *1024;
        int line_size=0;
        String line = "";


        try {
            File dir = new File("Splited");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File filename = new File(args[0]);

            BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "windows-1251"));
                    //new BufferedInputStream(new FileInputStream(filename));
            data = infile.read();
            File new_filename = new File(dir, args[1] +"_" + count + ".log");
            BufferedWriter outfile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new_filename),"UTF-8"));
            while (data != -1 ) {

                size ++;
                line = line + (char)data;
                line_size++;
                if ((char)data =='\n')
                {

                    if ((size - line_size) <= (max_len - line_size)) {

                        byte [] b_strutf = line.getBytes("UTF-8");
                        String encoded_line = new String(b_strutf, "UTF8");
                        outfile.write(encoded_line);
                        //System.out.print(line);
                        //System.out.print(encoded_line);

                        line_size = 0;
                        line = "";


                    }else {

                        outfile.close();
                        count++;
                        new_filename = new File(dir,args[1] +"_" + count + ".log");
                        outfile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new_filename),"UTF-8"));
                        byte [] b_strutf = line.getBytes("ISO_8859_1");
                        String encoded_line = new String(b_strutf, "UTF8");
                        outfile.write(encoded_line);
                        //System.out.print(encoded_line);
                        //System.out.print(line);
                        line_size = 0;
                        size=0;
                        line = "";

                    }

                }

                data = infile.read();
                if (data ==-1)
                {
                    byte [] b_strutf = line.getBytes("ISO_8859_1");
                    String encoded_line = new String(b_strutf, "UTF8");
                    outfile.write(encoded_line);

                }

            }
            outfile.close();
            infile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Done");

    }

}
