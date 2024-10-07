package Redux2;
    import java.io.FileInputStream;

    import java.io.IOException;
    
    import java.io.ObjectInputStream;
    
    
    
    public class SerializationUtil {
    
    
    
        public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
    
            try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream in = new ObjectInputStream(fileIn)) {
    
                return in.readObject();
    
            }
    
        }
    
    }
    
