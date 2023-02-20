import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author Jackson Chen
 * @version 1.0
 * @date 2022/11/15
 */
public class TestDemo {

    @Test
    public void test() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yml");
        Map<String, Object> data = yaml.load(inputStream);
        String id = (String) ((Map<String, Object>)data.get("serverGeneralConfig")).get("channelID");
        System.out.println(id);
        System.out.println(Long.parseLong(id));
    }
}
