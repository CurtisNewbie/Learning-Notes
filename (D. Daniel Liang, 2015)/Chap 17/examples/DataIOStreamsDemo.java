import java.io.*;

public class DataIOStreamsDemo {

    public static void main(String[] args) {
        String str = "This is a String";
        int numOfBytes = str.getBytes().length;

        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("examplesDemoFiles/dataTemp.dat"))) {

            // a sequence of 2-bytes chars with no length
            dataOut.writeChars(str); // equivalent to writeChar() * (n number of chars)
            dataOut.writeInt(99999);

            // a sequence of bytes
            dataOut.writeBytes(str);
            dataOut.writeChar('c');
            dataOut.writeDouble(10.1);
            dataOut.writeUTF(
                    "The first two bytes are used to indicate the number of characters in this string, and the following bytes are the UTF-8 code representing the characters.");

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        int len = str.length();
        try (DataInputStream datain = new DataInputStream(new FileInputStream("examplesDemoFiles/dataTemp.dat"))) {
            for (int x = 0; x < len; x++) {
                // 2-bytes char every time it reads
                System.out.print(datain.readChar());
            }

            System.out.println("\n" + datain.readInt());

            // read a number of bytes
            for (int x = 0; x < numOfBytes; x++) {
                System.out.print((char) datain.readByte());
            }
            System.out.println("\n" + datain.readChar());
            System.out.println(datain.readDouble());
            System.out.println(datain.readUTF());
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}