import configuration.ApplicationContext;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @InjectMocks
    Main mainFile;

    @Mock
    private ApplicationContext context;

//    @Test
//    public void testMain() throws Exception {
//        Main.getBean(context);
//        verify(context, times(1)).getBean(any());
//    }

}