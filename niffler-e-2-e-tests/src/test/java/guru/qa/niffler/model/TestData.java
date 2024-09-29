package guru.qa.niffler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import guru.qa.niffler.authClient.UserQueue;

public record TestData(
        @JsonIgnore String password,
        @JsonIgnore UserQueue.UserType userTypeQueue
) {
}
