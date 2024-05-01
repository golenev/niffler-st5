package guru.qa.niffler.jupiter.extension;

import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    private static final Queue<Pair<NifflerUser, NifflerUser>> USER_PAIRS = new ConcurrentLinkedQueue<>();

    static {
        USER_PAIRS.add(Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_CHUCKNORRIS));
        USER_PAIRS.add(Pair.of(NifflerUser.NIFFLER_DIMA, NifflerUser.NIFFLER_QWERTY));
        USER_PAIRS.add(Pair.of(NifflerUser.NIFFLER_CHUCKNORRIS, NifflerUser.NIFFLER_DIMA));
        USER_PAIRS.add(Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_CHUCKNORRIS));
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Pair<NifflerUser, NifflerUser> userPairForTest = null;
        while (userPairForTest == null) {
            userPairForTest = USER_PAIRS.poll();
        }
        Allure.getLifecycle()
                .updateTestCase(testCase -> testCase.setStart(new Date().getTime())                );
        context.getStore(NAMESPACE).put(context.getUniqueId(), userPairForTest);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Pair<NifflerUser, NifflerUser> userPairFromTest = context.getStore(NAMESPACE).get(context.getUniqueId(), Pair.class);
        USER_PAIRS.add(userPairFromTest);
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
