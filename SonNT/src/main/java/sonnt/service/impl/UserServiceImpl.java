package sonnt.service.impl;

import sonnt.model.User;

import java.util.UUID;

public interface UserServiceImpl {

    User findByEmail(String email);

    void update(UUID id, User update);


}
