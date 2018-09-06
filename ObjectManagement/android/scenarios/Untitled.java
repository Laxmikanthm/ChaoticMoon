//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
 *
 *
*/
public class Untitled {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\ALL_ABOUT_SELENIUM_SEETEST\\PROJECTS\\ESPN\\ObjectManagement\\android";
    protected Client client = null;

    @Before
    public void setUp(){
        client = new Client(host, port, true);
        client.setProjectBaseDirectory(projectBaseDirectory);
        client.setReporter("xml", "reports", "Untitled");
    }

    @Test
    public void testUntitled(){
        // it is recommended to start your script with SetDevice command:
        // client.setDevice( <Device Name>);
        client.launch("com.espn.fantasy.lm.football/com.espn.fantasy.lm.activity.MainActivity", true, false);
    }

    @After
    public void tearDown(){
        client.generateReport(true);
    }
}
