package guru.qa.niffler.jupiter.extension;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import guru.qa.niffler.model.NifflerUser;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class UserQueueExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(UserQueueExtension.class);

    private static final Map<Pair<NifflerUser, NifflerUser>, AtomicInteger> USER_PAIRS = new ConcurrentHashMap<>();

    static {
        USER_PAIRS.put(Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_CHUCKNORRIS), new AtomicInteger(0));
        USER_PAIRS.put(Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_DIMA), new AtomicInteger(0));
        USER_PAIRS.put(Pair.of(NifflerUser.NIFFLER_CHUCKNORRIS, NifflerUser.NIFFLER_DIMA), new AtomicInteger(0));
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Pair<NifflerUser, NifflerUser> userPairForTest = null;
        while (userPairForTest == null) {
            for (Map.Entry<Pair<NifflerUser, NifflerUser>, AtomicInteger> entry : USER_PAIRS.entrySet()) {
                if (entry.getValue().getAndIncrement() == 0) {
                    userPairForTest = entry.getKey();
                    break;
                }
            }
        }
        Allure.getLifecycle().updateTestCase(testCase -> {
            testCase.setStart(new Date().getTime());
        });
        context.getStore(NAMESPACE).put(context.getUniqueId(), userPairForTest);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Pair<NifflerUser, NifflerUser> userPairFromTest = context.getStore(NAMESPACE).get(context.getUniqueId(), Pair.class);
        USER_PAIRS.get(userPairFromTest).decrementAndGet();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getType()
                .isAssignableFrom(Pair.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE)
                .get(extensionContext.getUniqueId(), Pair.class);
    }
}
