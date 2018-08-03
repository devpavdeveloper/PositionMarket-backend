package by.psu.config;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

//mail configuration used for sending HTML messages - singleton
public class MailConfig {

    private static Properties props = new Properties();

    private static String propsPath = "target/classes/application-mail-local.properties";

    private static MailConfig mailConfig = null;

    private MailConfig(){
        try(InputStream is = new FileInputStream(propsPath)){
            props.load(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String property){

        return props.getProperty(property);
    }

    public static MailConfig getInstance(){

        if(Objects.isNull(mailConfig)){
            mailConfig = new MailConfig();
        }

        return mailConfig;
    }

    public Properties getPropsInstance(){
        return props;
    }

}
