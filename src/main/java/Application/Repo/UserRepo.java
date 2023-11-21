package Application.Repo;

import org.springframework.data.repository.CrudRepository;

import Application.Models.UsersDto;

public interface UserRepo extends CrudRepository<UsersDto, Integer> {

}
