package ChienSystem;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by tahel on 24/03/17.
 */
public class Message implements Serializable {

    private DataType type;
    private String data;

    private String destPseudo;
    private String srcPseudo;

    public Message(DataType type, String data, String destPseudo, String srcPseudo) {
        this.type = type;
        this.data = data;
        this.destPseudo = destPseudo;
        this.srcPseudo = srcPseudo;
    }


    public DataType getType() {
        return this.type;
    }

    public String getData() {
        return this.data;
    }

    public String getDestPseudo() {
        return this.destPseudo;
    }

    public String getSrcPseudo() {
        return this.srcPseudo;
    }

    public enum DataType {
        File,
        Text
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", data='" + data + '\'' +
                ", destPseudo='" + destPseudo + '\'' +
                ", srcPseudo='" + srcPseudo + '\'' +
                '}';
    }
}