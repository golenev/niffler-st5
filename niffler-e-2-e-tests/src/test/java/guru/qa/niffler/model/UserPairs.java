package guru.qa.niffler.model;

import org.apache.commons.lang3.tuple.Pair;

public class UserPairs {

    Pair<NifflerUser, NifflerUser> pair1 = Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_CHUCKNORRIS);
    Pair<NifflerUser, NifflerUser> pair2 = Pair.of(NifflerUser.NIFFLER_QWERTY, NifflerUser.NIFFLER_DIMA);

    Pair<NifflerUser, NifflerUser> pair3 = Pair.of(NifflerUser.NIFFLER_CHUCKNORRIS, NifflerUser.NIFFLER_DIMA);

    void test(){

    }



}
