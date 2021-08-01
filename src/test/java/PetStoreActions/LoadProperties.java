package PetStoreActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class LoadProperties
{

    public static Properties getProperties()
    {
        Properties prop = new Properties();
        String propPath = System.getProperty("user.dir") + "\\src\\test\\resources\\application.properties";
        try
        {
            prop.load(new FileInputStream(propPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return prop;
    }
}
