package Application.Repo;

import org.springframework.data.repository.CrudRepository;

import Application.Models.Users;

public interface UserRepo extends CrudRepository<Users, Integer> {

}
