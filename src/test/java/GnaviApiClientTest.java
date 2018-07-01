import com.otsukatsuka.daisukebot.api.GnaviApiClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GnaviApiClientTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGnaviSearchParameters() {
        GnaviApiClient gnaviApiClient = new GnaviApiClient();
        gnaviApiClient.getGnaviSearchParameters("池袋でこってりなラーメン探して");
    }
}